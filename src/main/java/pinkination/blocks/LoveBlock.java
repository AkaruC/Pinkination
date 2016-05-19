package pinkination.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class LoveBlock extends PinkinationBlock {

    public LoveBlock() {

        setUnlocalizedName("pinkination.loveblock");


        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
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


}
