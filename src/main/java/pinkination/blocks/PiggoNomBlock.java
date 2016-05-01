package pinkination.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemFood;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import pinkination.Pinkination;


public class PiggoNomBlock extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public PiggoNomBlock() {
        super(Material.rock);
        setUnlocalizedName("pinkination.nomblock");
        setRegistryName("blocks_nomblock");
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());

        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }

    // the block will render in the SOLID layer.  See http://greyminecraftcoder.blogspot.co.at/2014/12/block-rendering-18.html for more information.
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityFurnace) {
                ((TileEntityFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
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


        if (side == EnumFacing.NORTH) {
            if (heldItem != null && heldItem.getItem() instanceof ItemFood) {

                InventoryPlayer playerInventory = playerIn.inventory;

                for (int i = 0; i < playerInventory.getSizeInventory(); i++) {
                    ItemStack stackInSlot = playerInventory.getStackInSlot(i);
                    if (stackInSlot != null && stackInSlot.getItem() instanceof ItemFood) {

                        int amount = ((ItemFood) stackInSlot.getItem()).getHealAmount(stackInSlot);


                        // remove 1 :D
                        playerInventory.decrStackSize(i, 1);

                        // add 1 :D
                        ItemStack item = new ItemStack(Pinkination.piggocoincopper, amount);
                        boolean added = playerInventory.addItemStackToInventory(item);

                        if (added) {
                            if (!playerInventory.addItemStackToInventory(item)) {
                                dropItemStack(worldIn, pos, playerIn, item);
                            }
                            worldIn.playSound(playerIn, pos, SoundEvents.entity_player_burp, SoundCategory.MASTER, 1F, 1F);
                        }
                    }
                }

                worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX(), pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
                worldIn.spawnParticle(EnumParticleTypes.HEART, pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);

            }
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);
        } else if (side == EnumFacing.SOUTH) {

            worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX(), pos.getY() + 1.0, pos.getZ(), 0D, 0D, 0D);
            worldIn.playSound(playerIn, pos, SoundEvents.entity_endermen_scream, SoundCategory.MASTER, 1F, 1F);
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_death, SoundCategory.MASTER, 1F, 1F);
        } else {
            worldIn.playSound(playerIn, pos, SoundEvents.entity_pig_ambient, SoundCategory.MASTER, 1F, 1F);
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);

    }

    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing) state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
// used by the renderer to control lighting and visibility of other blocks.
    // set to true because this block is opaque and occupies the entire 1x1x1 space
    // not strictly required because the default (super method) is true

}
