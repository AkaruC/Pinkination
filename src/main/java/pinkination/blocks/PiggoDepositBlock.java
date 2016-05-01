package pinkination.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class PiggoDepositBlock extends Block
{
    public PiggoDepositBlock()
    {
        super(Material.rock);
        setUnlocalizedName("pinkination.depositblock");
        setRegistryName("blocks_depositblock");
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());

        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }
}
