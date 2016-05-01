package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoinGold extends Item {

    public PiggoCoinGold() {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pinkination.piggocoingold");
        this.setRegistryName("items_piggocoingold");

        this.setMaxStackSize(64);
    }
}