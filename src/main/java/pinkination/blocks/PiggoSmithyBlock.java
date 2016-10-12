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


public class PiggoSmithyBlock extends PinkinationBlock {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;


    public PiggoSmithyBlock() {

        setUnlocalizedName("pinkination.depositblock");

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

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        EnumFacing facing = state.getValue(FACING);


        if (side == EnumFacing.EAST || side == EnumFacing.WEST) {
            if (heldItem != null && heldItem.getItem().isRepairable()) {
                InventoryPlayer playerInventory = playerIn.inventory;

                for (int i = 0; i < playerInventory.getSizeInventory(); i++) {
                    ItemStack stackInSlot = playerInventory.getStackInSlot(i);
                    if (stackInSlot != null && stackInSlot.getItem() == heldItem.getItem() && heldItem.getItem().isRepairable()) {



                        PlayerDataHandler.get(playerIn).addBalance(((ItemCoin) heldItem.getItem()).getValue());

                    }


                }
            }
        }

        worldIn.playSound(playerIn, pos, SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.MASTER, 1F, 1F);

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);


    }
}
