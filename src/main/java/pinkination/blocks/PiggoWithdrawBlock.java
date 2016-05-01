package pinkination.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Akaru on 5/1/2016.
 */
public class PiggoWithdrawBlock extends Block
{
    public PiggoWithdrawBlock()
    {
        super(Material.rock);
        setUnlocalizedName("pinkination.withdrawblock");
        setRegistryName("blocks_withdrawblock");
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());

        this.setCreativeTab(CreativeTabs.tabBlock);   // the block will appear on the Blocks tab in creative
    }
}
