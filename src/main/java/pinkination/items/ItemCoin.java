package pinkination.items;

import net.minecraft.item.Item;

/**
 * Created by Akaru on 5/17/2016.
 */
public class ItemCoin extends Item {
    public int value;
public ItemCoin(){
    this.setMaxStackSize(64);
    this.setValue(0);
}
    public int getValue(){
        return this.value;
    }
    public void setValue(int val){
        this.value = val;

    }
}
