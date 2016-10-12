package pinkination.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pinkination.items.ItemCoin;
import pinkination.registry.ModItems;
import pinkination.util.ChatUtil;
import pinkination.util.PlayerDataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akaru on 5/1/2016.
 */
public class PiggoWithdrawBlock extends PinkinationBlock {

    private int pressed;

    public PiggoWithdrawBlock() {

        setUnlocalizedName("pinkination.withdrawblock");
        setRegistryName("blocks_withdrawblock");
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);


        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);   // the block will appear on the Blocks tab in creative
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }


    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        worldIn.playSound(playerIn, pos, SoundEvents.ENTITY_PIG_HURT, SoundCategory.MASTER, 1F, 1F);
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        EnumFacing facing = state.getValue(FACING);


        if (side == facing && heldItem !=  null &&  heldItem.getItem() == ModItems.piggoPurse) {

            if (worldIn.isRemote) {
                pressed++;
            }
            piginfo(playerIn);
        } else if (side == EnumFacing.UP) {

            boolean enough = false;
            if (pressed == 1) {
                if (PlayerDataHandler.get(playerIn).getBalance() >= ((ItemCoin) ModItems.piggoCoinCopper).getValue()) {
                    enough = true;
                } else {
                    enough = false;
                }
            } else if (pressed == 2) {
                if (PlayerDataHandler.get(playerIn).getBalance() >= ((ItemCoin) ModItems.piggoCoinSilver).getValue()) {
                    enough = true;
                } else {
                    enough = false;
                }
            } else if (pressed == 3) {
                if (PlayerDataHandler.get(playerIn).getBalance() >= ((ItemCoin) ModItems.piggoCoinGold).getValue()) {
                    enough = true;
                } else {
                    enough = false;
                }
            } else if (pressed == 4) {
                if (PlayerDataHandler.get(playerIn).getBalance() >= ((ItemCoin) ModItems.piggoCoinPigtastic).getValue()) {
                    enough = true;
                } else {
                    enough = false;
                }
            }
            InventoryPlayer playerInventory = playerIn.inventory;
            ItemStack item;
            switch (pressed) {


                case 1:
                    item = new ItemStack(ModItems.piggoCoinCopper, 1);
                    checkEnough(enough, playerInventory, item, worldIn, playerIn, pos);
                    break;
                case 2:
                    item = new ItemStack(ModItems.piggoCoinSilver, 1);
                    checkEnough(enough, playerInventory, item, worldIn, playerIn, pos);
                    break;
                case 3:
                    item = new ItemStack(ModItems.piggoCoinGold, 1);
                    checkEnough(enough, playerInventory, item, worldIn, playerIn, pos);
                    break;
                case 4:
                    item = new ItemStack(ModItems.piggoCoinPigtastic, 1);
                    checkEnough(enough, playerInventory, item, worldIn, playerIn, pos);
                    break;
                default:
                    ChatUtil.sendNoSpam(playerIn, "How dare you break pig!");
                    break;
            }

        }


        return true;


    }


    private void checkEnough(boolean enough, InventoryPlayer playerInventory, ItemStack item, World worldIn, EntityPlayer playerIn, BlockPos pos) {
        int currentBalance = PlayerDataHandler.get(playerIn).getBalance();

        if (enough) {
            boolean added = playerInventory.addItemStackToInventory(item);

            if (added) {
                PlayerDataHandler.get(playerIn).subtractBalance(((ItemCoin) item.getItem()).getValue());
                List<ITextComponent> toSend = new ArrayList<ITextComponent>();
                toSend.add(new TextComponentTranslation("You withdraw some Piggo, I have : " + currentBalance + " Piggo left in my account.", currentBalance));
                ChatUtil.sendNoSpam(playerIn, toSend.toArray(new ITextComponent[toSend.size()]));
                if (!playerInventory.addItemStackToInventory(item)) {
                    dropItemStack(worldIn, pos, playerIn, item);
                }
            }
        } else {
            ChatUtil.sendNoSpam(playerIn, "Not enough balance.");
        }
    }

    private void dropItemStack(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
        EntityItem entity = new EntityItem(world, pos.getX() + .5f, pos.getY() + .1f, pos.getZ() + .5f, stack);
        entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
        world.spawnEntityInWorld(entity);
    }

    public void piginfo(EntityPlayer playerIn) {

        switch (pressed) {
            case 1:
                ChatUtil.sendNoSpam(playerIn, "Pig set to give copper coins.");
                break;
            case 2:
                ChatUtil.sendNoSpam(playerIn, "Pig set to give silver coins.");
                break;
            case 3:
                ChatUtil.sendNoSpam(playerIn, "Pig set to give gold coins.");
                break;
            case 4:
                ChatUtil.sendNoSpam(playerIn, "Pig set to give pigtastic coins.");
                break;
            default:
                ChatUtil.sendNoSpam(playerIn, "How dare you break pig!");
                pressed = 1;
                break;
        }
    }
}