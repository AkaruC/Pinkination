package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoinSilver extends ItemCoin {

    public PiggoCoinSilver() {
        this.setCreativeTab(CreativeTabs.MATERIALS);
        this.setUnlocalizedName("pinkination.piggocoinsilver");
        this.setValue(50);
    }
}