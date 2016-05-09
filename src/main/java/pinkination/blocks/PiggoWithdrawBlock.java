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
 * Created by Akaru on 5/1/2016.
 */
public class PiggoWithdrawBlock extends PinkinationBlock
{

    public PiggoWithdrawBlock()
    {

        setUnlocalizedName("pinkination.withdrawblock");
        setRegistryName("blocks_withdrawblock");
        setHardness(2.0F);
        setHarvestLevel("pickaxe", 1);
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());

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

}
