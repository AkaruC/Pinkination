package pinkination.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import pinkination.registry.ModBlocks;
import pinkination.registry.ModItems;

public class ClientProxy implements IProxy {

    @Override
    public void registerRenderers() {
        //the blocks
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.kitteh), 0, new ModelResourceLocation("pinkination:blocks_kittehblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.love), 0, new ModelResourceLocation("pinkination:blocks_loveblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoNom), 0, new ModelResourceLocation("pinkination:blocks_nomblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoDeposit), 0, new ModelResourceLocation("pinkination:blocks_depositblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoSwap), 0, new ModelResourceLocation("pinkination:blocks_swapblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoDebug), 0, new ModelResourceLocation("pinkination:blocks_debugblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoWithdraw), 0, new ModelResourceLocation("pinkination:blocks_withdrawblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.piggoAngry), 0, new ModelResourceLocation("pinkination:blocks_angryblock", "inventory"));



        // Items
        ModelLoader.setCustomModelResourceLocation(ModItems.piggoCoinCopper, 0, new ModelResourceLocation("Pinkination:items_piggocoincopper", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.piggoCoinSilver, 0, new ModelResourceLocation("Pinkination:items_piggocoinsilver", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.piggoCoinGold, 0, new ModelResourceLocation("Pinkination:items_piggocoingold", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.piggoCoinPigtastic, 0, new ModelResourceLocation("Pinkination:items_piggocoinpigtastic", "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.piggoPurse, 0, new ModelResourceLocation("Pinkination:items_piggopurse", "inventory"));


    }

}
