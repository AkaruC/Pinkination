package pinkination.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class LoveBlock extends Block
{
  public LoveBlock()
  {
    super(Material.rock);
      setUnlocalizedName("pinkination.loveblock");
      setRegistryName("blocks_loveblock");
    GameRegistry.register(this);
    GameRegistry.register(new ItemBlock(this), getRegistryName());
    this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
  }


  @SideOnly(Side.CLIENT)
  public BlockRenderLayer getBlockLayer() {
    return BlockRenderLayer.SOLID;
  }


}
