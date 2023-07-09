package dev.mayaqq.endless.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.mayaqq.endless.Endless;
import dev.mayaqq.endless.utils.gson.VoidItemGsonSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VoidItemsStorage {
    private static final File VOID_ITEMS_FILE = new File(Endless.SERVER_DATA_PATH.toFile(), "void_items.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(VoidItem.class, new VoidItemGsonSerializer()).create();
    public static Storage DATA = new Storage();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        try {
            if (!VOID_ITEMS_FILE.exists()) {
                VOID_ITEMS_FILE.getParentFile().mkdirs();
                VOID_ITEMS_FILE.createNewFile();
                save();
            } else {
                try {
                    DATA = gson.fromJson(new FileReader(VOID_ITEMS_FILE), Storage.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    save();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void save() {
        try {
            FileWriter writer = new FileWriter(VOID_ITEMS_FILE);
            gson.toJson(DATA, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Storage {
        public ArrayList<VoidItem> items = new ArrayList<>();
    }

    public static class VoidItem {
        public String item;
        public String nbt = "";
        public int count;
    }

    public static ItemStack toStack(VoidItem item) {
        try {
            ItemStack stack = new ItemStack(Registries.ITEM.get(new Identifier(item.item)));
            stack.setCount(item.count);
            stack.setNbt(StringNbtReader.parse(item.nbt));
            return stack;
        } catch (Exception e) {
            e.printStackTrace();
            return ItemStack.EMPTY;
        }
    }
}
