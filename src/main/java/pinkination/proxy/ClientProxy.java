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

        // Items
        ModelLoader.setCustomModelResourceLocation(Pinkination.piggocoin, 0, new ModelResourceLocation("Pinkination:items_piggocoin", "inventory"));


    }

}
