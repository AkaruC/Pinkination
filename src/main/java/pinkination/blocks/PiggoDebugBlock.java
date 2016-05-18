package pinkination.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pinkination.registry.ModItems;
import pinkination.util.PlayerDataHandler;

/**
 * Created by Akaru on 5/17/2016.
 */
public class PiggoDebugBlock extends PinkinationBlock {
    public PiggoDebugBlock() {

        setUnlocalizedName("pinkination.debugblock");

        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);


        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        EnumFacing facing = state.getValue(FACING);

        ItemStack copper = new ItemStack(ModItems.piggoCoinCopper, 1);
        ItemStack silver = new ItemStack(ModItems.piggoCoinSilver, 1);
        ItemStack gold = new ItemStack(ModItems.piggoCoinGold, 1);
        ItemStack pigtastic = new ItemStack(ModItems.piggoCoinPigtastic, 1);

        if (heldItem != null && heldItem.getItem() == ModItems.piggoPurse) {

            InventoryPlayer playerInventory = playerIn.inventory;


            switch (side) {
                case NORTH:
                    playerInventory.addItemStackToInventory(gold);
                    break;
                case SOUTH:
                    playerInventory.addItemStackToInventory(copper);
                    break;
                case EAST:
                    playerInventory.addItemStackToInventory(pigtastic);
                    break;
                case WEST:
                    playerInventory.addItemStackToInventory(silver);
                    break;
                case UP:
                    if (playerIn.isSneaking()) {
                        PlayerDataHandler.get(playerIn).setBalance(1000);
                        break;
                    } else {
                        PlayerDataHandler.get(playerIn).setBalance(0);
                        break;
                    }


                default:
                    break;
            }


        }


        worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);

    }


    private void dropItemStack(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
        EntityItem entity = new EntityItem(world, pos.getX() + .5f, pos.getY() + .1f, pos.getZ() + .5f, stack);
        entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
        world.spawnEntityInWorld(entity);
    }
}
