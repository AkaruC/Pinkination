package pinkination;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pinkination.blocks.KittehBlock;
import pinkination.blocks.LoveBlock;
import pinkination.blocks.PiggoDepositBlock;
import pinkination.blocks.PiggoNomBlock;
import pinkination.items.PiggoCoin;
import pinkination.proxy.IProxy;


@Mod(modid = Pinkination.MODID,  name =Pinkination.MODNAME, version = Pinkination.VERSION)

public class Pinkination {
	public static final String MODID = "pinkination";
    public static final String MODNAME ="Pinkination";
    public static final String VERSION = "1.0";

    public static Block oinker;
    public static Block love;
    public static Block kitteh;
    public static Block deposit;
    public static Item piggocoin;



    @Mod.Instance(Pinkination.MODID)
    public static Pinkination instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "pinkination.proxy.ClientProxy", serverSide = "pinkination.proxy.CommonProxy")
    public static IProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      oinker =  new PiggoNomBlock();
        GameRegistry.register(oinker);

      love = new LoveBlock();
        GameRegistry.register(love);

      kitteh = new KittehBlock();
        GameRegistry.register(kitteh);

      deposit = new PiggoDepositBlock();
        GameRegistry.register(deposit);

      piggocoin = new PiggoCoin();
        GameRegistry.register(piggocoin);

      proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

}

   
    public static String prependModID(String name) {return MODID + ":" + name;}
}
