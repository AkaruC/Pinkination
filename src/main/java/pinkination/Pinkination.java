package pinkination;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pinkination.blocks.*;
import pinkination.items.*;
import pinkination.property.PlayerDataHandler;
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
    public static Block swap;

    public static PlayerDataHandler playerDataHandler;
    public static Item piggocoincopper;
    public static Item piggocoinsilver;
    public static Item piggocoingold;
    public static Item piggocoinpigtastic;
    public static Item piggopurse;



    @Mod.Instance(Pinkination.MODID)
    public static Pinkination instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide = "pinkination.proxy.ClientProxy", serverSide = "pinkination.proxy.CommonProxy")
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        playerDataHandler = new PlayerDataHandler();
      oinker =  new PiggoNomBlock();
        GameRegistry.register(oinker);

      love = new LoveBlock();
        GameRegistry.register(love);

      kitteh = new KittehBlock();
        GameRegistry.register(kitteh);

      deposit = new PiggoDepositBlock();
        GameRegistry.register(deposit);

      swap =  new PiggoSwapBlock();
        GameRegistry.register(swap);




      piggocoincopper = new PiggoCoinCopper();
        GameRegistry.register(piggocoincopper);
      piggocoinsilver = new PiggoCoinSilver();
        GameRegistry.register(piggocoinsilver);
      piggocoingold = new PiggoCoinGold();
        GameRegistry.register(piggocoingold);
      piggocoinpigtastic = new PiggoCoinPigtastic();
        GameRegistry.register(piggocoinpigtastic);
      piggopurse = new PiggoPurse();
        GameRegistry.register(piggopurse);
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
