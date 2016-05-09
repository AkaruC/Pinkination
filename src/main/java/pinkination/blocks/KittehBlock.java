package pinkination.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockRenderLayer;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * BlockSimple is a ordinary solid cube with the six faces numbered from 0 - 5.
 * For background information on blocks see here http://greyminecraftcoder.blogspot.com.au/2014/12/blocks-18.html
 */
public class KittehBlock extends PinkinationBlock {

  public KittehBlock() {

    setUnlocalizedName("pinkination.kittehblock");
    setRegistryName("blocks_kittehblock");
    GameRegistry.register(this);
    GameRegistry.register(new ItemBlock(this), getRegistryName());
    this.setCreativeTab(CreativeTabs.tabBlock);
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

}


