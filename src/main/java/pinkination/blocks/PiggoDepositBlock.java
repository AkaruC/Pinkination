package pinkination.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pinkination.items.ItemCoin;
import pinkination.util.PlayerDataHandler;


public class PiggoDepositBlock extends PinkinationBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;


    public PiggoDepositBlock() {

        setUnlocalizedName("pinkination.depositblock");

        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);


        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }


    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_hurt, SoundCategory.MASTER, 1F, 1F);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        EnumFacing facing = state.getValue(FACING);


        if (side == EnumFacing.UP) {
            if (heldItem != null && heldItem.getItem() instanceof ItemCoin) {
                InventoryPlayer playerInventory = playerIn.inventory;

                for (int i = 0; i < playerInventory.getSizeInventory(); i++) {
                    ItemStack stackInSlot = playerInventory.getStackInSlot(i);
                    if (stackInSlot != null && stackInSlot.getItem() == heldItem.getItem()) {


                        playerInventory.decrStackSize(i, 1);
                        PlayerDataHandler.get(playerIn).addBalance(((ItemCoin) heldItem.getItem()).getValue());

                    }


                }
            }
        }

        worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);


    }
}
