package dev.mayaqq.endless.utils;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;

import java.lang.reflect.Method;
import java.util.function.Function;

public class RenderLayerUtils {
    public static RenderLayer createCylinderLayer(Function<RenderLayer, VertexFormat> formatFunc) {
        try {
            Method method = RenderLayer.class.getDeclaredMethod("of", String.class, VertexFormat.class, VertexFormat.DrawMode.class, int.class, boolean.class, boolean.class, Runnable.class);
            method.setAccessible(true);
            return (RenderLayer) method.invoke(null, "cylinder", formatFunc.apply(RenderLayer.getSolid()), VertexFormat.DrawMode.QUADS, 256, true, false, Runnable::run);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
