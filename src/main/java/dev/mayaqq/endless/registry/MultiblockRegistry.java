package dev.mayaqq.endless.registry;

import net.minecraft.util.Identifier;
import vazkii.patchouli.api.IMultiblock;

import vazkii.patchouli.api.PatchouliAPI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static dev.mayaqq.endless.Endless.id;


public class MultiblockRegistry {

    public static final Map<Identifier, IMultiblock> MULTIBLOCKS = new ConcurrentHashMap<>();


    private static void registerMultiBlock(Identifier identifier, String[][] structure, Object[] targetBlocks) {
        IMultiblock multiblock = PatchouliAPI.get().makeMultiblock(structure, targetBlocks);
        MULTIBLOCKS.put(identifier, PatchouliAPI.get().registerMultiblock(identifier, multiblock));
    }

    private static boolean registered = false;

    public static void register() {
        if (!registered) {
            registerWyrngeartDynamo();
            registered = true;
        }
    }
    public static void registerWyrngeartDynamo() {
        Object[] targetBlocks = {
            '0', "endless:wyrngeart_dynamo",
            'E', "minecraft:dragon_egg"
        };
        String[][] structure = {
                {"E"},
                {"0"}
        };
        registerMultiBlock(id("wyrngeart_dynamo_structure"), structure, targetBlocks);

    }
}
