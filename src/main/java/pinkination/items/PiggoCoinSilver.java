package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoinSilver extends Item {

    public PiggoCoinSilver() {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pinkination.piggocoinsilver");
        this.setRegistryName("items_piggocoinsilver");

        this.setMaxStackSize(64);
    }
}