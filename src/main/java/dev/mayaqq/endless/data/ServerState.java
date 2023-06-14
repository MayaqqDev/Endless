package dev.mayaqq.endless.data;

import dev.mayaqq.endless.Endless;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ServerState extends PersistentState {

    public static ArrayList<ItemStack> voidItemsList = new ArrayList<>();
    public HashMap<UUID, PlayerState> players = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        // Putting the 'players' hashmap, into the 'nbt' which will be saved.
        NbtCompound playersNbtCompound = new NbtCompound();
        players.forEach((UUID, playerSate) -> {
            NbtCompound playerStateNbt = new NbtCompound();

            // ANYTIME YOU PUT NEW DATA IN THE PlayerState CLASS YOU NEED TO REFLECT THAT HERE!!!
            // playerStateNbt.putBoolean("example", playerSate.example);


            playersNbtCompound.put(String.valueOf(UUID), playerStateNbt);
        });
        NbtCompound voidItemsNbtCompound = new NbtCompound();
        voidItemsList.forEach(itemStack -> {
            String id1 = itemStack.getItem().getTranslationKey().split(".")[2];
            String id2 = itemStack.getItem().getTranslationKey().split(".")[3];
            String id = id1 + ":" + id2;
            voidItemsNbtCompound.put(id, itemStack.getNbt());
        });
        nbt.put("players", playersNbtCompound);
        nbt.put("voidItems", voidItemsNbtCompound);
        return nbt;
    }
    public static ServerState createFromNbt(NbtCompound tag) {
        ServerState serverState = new ServerState();
        NbtCompound playersTag = tag.getCompound("players");
        playersTag.getKeys().forEach(key -> {
            PlayerState playerState = new PlayerState();

            // ANYTIME YOU PUT NEW DATA IN THE PlayerState CLASS YOU NEED TO REFLECT THAT HERE!!!
            //playerState.example = playersTag.getCompound(key).getBoolean("example");

            UUID uuid = UUID.fromString(key);
            serverState.players.put(uuid, playerState);
        });
        NbtCompound voidItemsTag = tag.getCompound("voidItems");
        ArrayList<ItemStack> createVoidItemsList = new ArrayList<>();
        voidItemsTag.getKeys().forEach(key -> {
            Identifier voidItemIdentifier = Identifier.tryParse(key);
            Item voidItem = Registries.ITEM.get(voidItemIdentifier);
            ItemStack voidItemStack = new ItemStack(voidItem);
            voidItemStack.setNbt(voidItemsTag.getCompound(key));
            createVoidItemsList.add(voidItemStack);
        });
        voidItemsList = createVoidItemsList;
        return serverState;
    }

    public static ServerState getServerState(MinecraftServer server) {
        // First we get the persistentStateManager for the OVERWORLD
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        // Calling this reads the file from the disk if it exists, or creates a new one and saves it to the disk
        // You need to use a unique string as the key. You should already have a MODID variable defined by you somewhere in your code. Use that.
        ServerState serverState = persistentStateManager.getOrCreate(ServerState::createFromNbt, ServerState::new, Endless.MOD_ID);

        serverState.markDirty(); // makes stuff work

        return serverState;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    public static PlayerState getPlayerState(LivingEntity player) {
        ServerState serverState = getServerState(player.getServer());
        serverState.markDirty();
        return serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerState());
    }
    public static class PlayerState {

    }
}
