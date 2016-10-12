package pinkination;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import pinkination.network.PinkinationPacketHandler;
import pinkination.proxy.IProxy;
import pinkination.registry.ModBlocks;
import pinkination.registry.ModItems;
import pinkination.util.PlayerDataHandler;


@Mod(modid = Pinkination.MODID, name = Pinkination.MODNAME, version = Pinkination.VERSION)

public class Pinkination {
    public static final String MODID = "pinkination";
    public static final String MODNAME = "Pinkination";
    public static final String VERSION = "1.0";


    public static PlayerDataHandler playerDataHandler;


    @Mod.Instance(Pinkination.MODID)
    public static Pinkination instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "pinkination.proxy.ClientProxy", serverSide = "pinkination.proxy.CommonProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        playerDataHandler = new PlayerDataHandler();
        ModBlocks.init();
        ModItems.init();


        proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PinkinationPacketHandler.init();
    }

    @SuppressWarnings("EmptyMethod")
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


    public static String prependModID(String name) {
        return MODID + ":" + name;
    }
}
