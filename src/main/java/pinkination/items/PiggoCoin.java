package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoin extends Item {

    public PiggoCoin() {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pinkination.piggocoin");
        this.setRegistryName("items_piggocoin");

        this.setMaxStackSize(64);
    }
}