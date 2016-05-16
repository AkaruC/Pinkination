package pinkination.items;

import ibxm.Player;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import pinkination.property.PlayerDataHandler;

import java.util.ArrayList;
import java.util.List;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Akaru on 5/16/2016.
 */
public class PiggoPurse extends Item {
    public PiggoPurse(){
    this.setCreativeTab(CreativeTabs.tabMaterials);
    this.setUnlocalizedName("pinkination.piggopurse");
    this.setRegistryName("items_piggopurse");

    this.setMaxStackSize(1);
}

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        int currentBalance = PlayerDataHandler.get(playerIn).getBalance();



        playerIn.addChatComponentMessage(new TextComponentTranslation("My current balance is "+ PlayerDataHandler.get(playerIn).getBalance() +"Piggold"));
        PlayerDataHandler.get(playerIn).setBalance(currentBalance + 12);
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }




}
