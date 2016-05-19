package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import pinkination.util.ChatUtil;
import pinkination.util.PlayerDataHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akaru on 5/16/2016.
 */
public class PiggoPurse extends Item {
    public PiggoPurse() {
        this.setCreativeTab(CreativeTabs.MATERIALS);
        this.setUnlocalizedName("pinkination.piggopurse");


        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        int currentBalance = PlayerDataHandler.get(playerIn).getBalance();


        List<ITextComponent> toSend = new ArrayList<ITextComponent>();
        toSend.add(new TextComponentTranslation("My current balance is: " + currentBalance + " Piggo.", currentBalance));
        ChatUtil.sendNoSpam(playerIn, toSend.toArray(new ITextComponent[toSend.size()]));


        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }


}
