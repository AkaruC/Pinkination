package pinkination.blocks;


import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pinkination.registry.ModItems;


public class PiggoNomBlock extends PinkinationBlock {


    public PiggoNomBlock() {

        setUnlocalizedName("pinkination.nomblock");

        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));


        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }

    // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }


    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        super.onBlockClicked(worldIn, pos, playerIn);
        worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_hurt, SoundCategory.MASTER, 1F, 1F);
    }

    private void dropItemStack(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
        EntityItem entity = new EntityItem(world, pos.getX() + .5f, pos.getY() + .1f, pos.getZ() + .5f, stack);
        entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
        world.spawnEntityInWorld(entity);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        EnumFacing facing = state.getValue(FACING);
        BlockPos facepos = pos.offset(facing);


        if (side == facing) {
            if (heldItem != null && heldItem.getItem() instanceof ItemFood) {

                InventoryPlayer playerInventory = playerIn.inventory;

                for (int i = 0; i < playerInventory.getSizeInventory(); i++) {
                    ItemStack stackInSlot = playerInventory.getStackInSlot(i);
                    if (stackInSlot != null && stackInSlot.getItem() instanceof ItemFood) {

                        int amount = ((ItemFood) stackInSlot.getItem()).getHealAmount(stackInSlot);


                        // remove 1 :D
                        playerInventory.decrStackSize(i, 1);

                        // add 1 :D
                        ItemStack item = new ItemStack(ModItems.piggoCoinCopper, amount);
                        boolean added = playerInventory.addItemStackToInventory(item);

                        if (added) {
                            if (!playerInventory.addItemStackToInventory(item)) {
                                dropItemStack(worldIn, pos, playerIn, item);
                            }
                            worldIn.playSound(playerIn, pos, SoundEvents.entity_player_burp, SoundCategory.MASTER, 1F, 1F);
                        }
                    }
                }

                switch (facing) {
                    case NORTH:
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                        break;
                    case SOUTH:
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ() + 1.0, 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 1.0, 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0, 0D, 0D, 0D);
                        break;
                    case EAST:
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 0.5, 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0, 0D, 0D, 0D);
                        break;
                    case WEST:
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ() + 0.5, 0D, 0D, 0D);
                        worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ() + 1.0, 0D, 0D, 0D);
                        break;
                    default:
                        break;
                }


            }
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);
        } else if (side == facing.getOpposite()) {


            worldIn.spawnParticle(EnumParticleTypes.FLAME, facepos.getX(), pos.getY(), pos.getZ(), 0.0D, 1.0D, 0.0D);

            worldIn.playSound(playerIn, pos, SoundEvents.entity_endermen_scream, SoundCategory.MASTER, 1F, 1F);
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_death, SoundCategory.MASTER, 1F, 1F);
        } else {
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);

    }


// used by the renderer to control lighting and visibility of other blocks.
    // set to true because this block is opaque and occupies the entire 1x1x1 space
    // not strictly required because the default (super method) is true

}
