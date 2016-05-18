package pinkination.registry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pinkination.items.*;

/**
 * Credit to WayofTime for this system in his bloodmagic mod
 */
public class ModItems {
    public static Item piggoCoinCopper;
    public static Item piggoCoinSilver;
    public static Item piggoCoinGold;
    public static Item piggoCoinPigtastic;
    public static Item piggoPurse;

    public static void init() {

        piggoCoinCopper = registerItem(new PiggoCoinCopper(), "items_piggocoincopper");

        piggoCoinSilver = registerItem(new PiggoCoinSilver(), "items_piggocoinsilver");

        piggoCoinGold = registerItem(new PiggoCoinGold(), "items_piggocoingold");

        piggoCoinPigtastic = registerItem(new PiggoCoinPigtastic(), "items_piggocoinpigtastic");

        piggoPurse = registerItem(new PiggoPurse(), "items_piggopurse");


    }

    private static Item registerItem(Item item, String name) {

        if (item.getRegistryName() == null)
            item.setRegistryName(name);
        GameRegistry.register(item);


        return item;
    }
}
