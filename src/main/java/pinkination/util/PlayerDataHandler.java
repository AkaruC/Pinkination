package pinkination.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Credits to Vazkii for this system in her psi mod
 */
public class PlayerDataHandler {

    private static HashMap<Integer, PlayerData> playerData = new HashMap();


    private static final String DATA_TAG = "PiggoData";


    public static PlayerData get(EntityPlayer player) {
        int key = getKey(player);
        if (!playerData.containsKey(key))
            playerData.put(key, new PlayerData(player));

        PlayerData data = playerData.get(key);
        if (data.playerWR.get() != player) {
            NBTTagCompound cmp = new NBTTagCompound();
            data.writeToNBT(cmp);
            playerData.remove(key);
            data = get(player);
            data.readFromNBT(cmp);
        }

        return data;
    }

    public static void cleanup() {
        List<Integer> remove = new ArrayList();

        for (int i : playerData.keySet()) {
            PlayerData d = playerData.get(i);
            if (d.playerWR.get() == null)
                remove.add(i);
        }

        for (int i : remove)
            playerData.remove(i);
    }

    private static int getKey(EntityPlayer player) {
        return player.hashCode() << 1 + (player.worldObj.isRemote ? 1 : 0);
    }

    public static NBTTagCompound getDataCompoundForPlayer(EntityPlayer player) {
        NBTTagCompound forgeData = player.getEntityData();
        if (!forgeData.hasKey(EntityPlayer.PERSISTED_NBT_TAG))
            forgeData.setTag(EntityPlayer.PERSISTED_NBT_TAG, new NBTTagCompound());

        NBTTagCompound persistentData = forgeData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        if (!persistentData.hasKey(DATA_TAG))
            persistentData.setTag(DATA_TAG, new NBTTagCompound());

        return persistentData.getCompoundTag(DATA_TAG);
    }


    public static class PlayerData {


        private static final String PIGGO_BALANCE = "piggoBalance";

        public WeakReference<EntityPlayer> playerWR;
        private final boolean client;


        public PlayerData(EntityPlayer player) {
            playerWR = new WeakReference(player);
            client = player.worldObj.isRemote;

            load();
        }


        public int balance;

        public int getBalance() {
            return this.balance;
        }


        public PlayerData setBalance(int bal) {
            this.balance = bal;
            save();
            return this;
        }


        public PlayerData addBalance(int add) {
            this.balance = balance + add;
            save();
            return this;


        }

        public PlayerData subtractBalance(int sub) {
            this.balance = balance - sub;
            save();
            return this;


        }

        public void save() {
            if (!client) {
                EntityPlayer player = playerWR.get();

                if (player != null) {
                    NBTTagCompound cmp = getDataCompoundForPlayer(player);
                    writeToNBT(cmp);
                }
            }
        }

        public void writeToNBT(NBTTagCompound cmp) {
            cmp.setInteger(PIGGO_BALANCE, balance);

        }

        public void load() {
            if (!client) {
                EntityPlayer player = playerWR.get();

                if (player != null) {
                    NBTTagCompound cmp = getDataCompoundForPlayer(player);
                    readFromNBT(cmp);
                }
            }
        }

        public void readFromNBT(NBTTagCompound cmp) {
            balance = cmp.getInteger(PIGGO_BALANCE);


        }
    }
}