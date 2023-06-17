package dev.mayaqq.endless.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dev.mayaqq.endless.data.VoidItemsStorage;

import java.lang.reflect.Type;

public class VoidItemGsonSerializer implements JsonSerializer<VoidItemsStorage.VoidItem> {
    @Override
    public JsonElement serialize(VoidItemsStorage.VoidItem src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("item", src.item);
        json.addProperty("count", src.count);
        json.addProperty("nbt", src.nbt);
        return json;
    }
}
