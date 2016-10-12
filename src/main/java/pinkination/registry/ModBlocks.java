package pinkination.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pinkination.blocks.*;

/**
 * Credit to WayofTime for this system in his bloodmagic mod
 */
public class ModBlocks {

    public static Block piggoAngry;
    public static Block piggoNom;
    public static Block love;
    public static Block kitteh;
    public static Block piggoDeposit;
    public static Block piggoSwap;
    public static Block piggoDebug;
    public static Block piggoWithdraw;


    public static void init() {
        love = registerBlock(new LoveBlock(), "blocks_loveblock");
        kitteh = registerBlock(new KittehBlock(), "blocks_kittehblock");
        piggoSwap = registerBlock(new PiggoSwapBlock(), "blocks_swapblock");
        piggoNom = registerBlock(new PiggoNomBlock(), "blocks_nomblock");
        piggoAngry = registerBlock(new PiggoAngryBlock(), "blocks_angryblock");
        piggoDeposit = registerBlock(new PiggoDepositBlock(), "blocks_depositblock");
        piggoDebug = registerBlock(new PiggoDebugBlock(), "blocks_debugblock");
        piggoWithdraw = registerBlock(new PiggoWithdrawBlock(), "blocks_withdrawblock");

    }

    private static Block registerBlock(Block block, String name) {
        if (block.getRegistryName() == null)
            block.setRegistryName(name);
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(name));

        return block;
    }

    private static Block registerBlock(ItemBlock itemBlock, String name) {
        Block block = itemBlock.getBlock();

        if (block.getRegistryName() == null)
            block.setRegistryName(name);

        GameRegistry.register(block);
        GameRegistry.register(itemBlock.setRegistryName(name));

        return block;
    }
}
