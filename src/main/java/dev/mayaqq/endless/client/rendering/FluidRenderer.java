package dev.mayaqq.endless.client.rendering;

import dev.mayaqq.endless.registry.EndlessFluids;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class FluidRenderer {
    public static void register() {
        FluidRenderHandlerRegistry.INSTANCE.register(EndlessFluids.STILL_VOID_FLUID, EndlessFluids.FLOWING_VOID_FLUID, new SimpleFluidRenderHandler(
                new Identifier("minecraft", "block/water_still"),
                new Identifier("minecraft", "block/water_flow"),
                0x000000
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), EndlessFluids.STILL_VOID_FLUID, EndlessFluids.FLOWING_VOID_FLUID);
    }
}
