package pinkination.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;

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
public class KittehBlock extends Block {
  public KittehBlock() {
    super(Material.rock);
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
}


