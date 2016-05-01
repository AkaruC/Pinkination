package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoinCopper extends Item {

    public PiggoCoinCopper() {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pinkination.piggocoincopper");
        this.setRegistryName("items_piggocoincopper");

        this.setMaxStackSize(64);
    }
}