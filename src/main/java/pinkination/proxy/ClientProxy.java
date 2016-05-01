package pinkination.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import pinkination.Pinkination;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements IProxy {

    @Override
    public void registerRenderers() {
        //the blocks
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Pinkination.kitteh), 0, new ModelResourceLocation("pinkination:blocks_kittehblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Pinkination.love), 0, new ModelResourceLocation("pinkination:blocks_loveblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Pinkination.oinker), 0, new ModelResourceLocation("pinkination:blocks_nomblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Pinkination.deposit), 0, new ModelResourceLocation("pinkination:blocks_depositblock", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(Pinkination.swap), 0, new ModelResourceLocation("pinkination:blocks_swapblock", "inventory"));


        // Items
        ModelLoader.setCustomModelResourceLocation(Pinkination.piggocoincopper, 0, new ModelResourceLocation("Pinkination:items_piggocoincopper", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Pinkination.piggocoinsilver, 0, new ModelResourceLocation("Pinkination:items_piggocoinsilver", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Pinkination.piggocoingold, 0, new ModelResourceLocation("Pinkination:items_piggocoingold", "inventory"));
        ModelLoader.setCustomModelResourceLocation(Pinkination.piggocoinpigtastic, 0, new ModelResourceLocation("Pinkination:items_piggocoinpigtastic", "inventory"));


    }

}
