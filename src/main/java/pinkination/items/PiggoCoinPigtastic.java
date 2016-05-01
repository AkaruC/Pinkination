package pinkination.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PiggoCoinPigtastic extends Item {

    public PiggoCoinPigtastic() {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName("pinkination.piggocoinpigtastic");
        this.setRegistryName("items_piggocoinpigtastic");

        this.setMaxStackSize(64);
    }
}