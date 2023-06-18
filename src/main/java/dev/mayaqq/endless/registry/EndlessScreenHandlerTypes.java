package dev.mayaqq.endless.registry;

import dev.mayaqq.endless.Endless;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class EndlessScreenHandlerTypes {

    //public static ScreenHandlerType<MakeshiftVoidExtractorScreenHandler> MAKESHIFT_VOID_EXTRACTOR;
    public static void register() {
        //MAKESHIFT_VOID_EXTRACTOR = registerExtended(Endless.id("makeshift_void_extractor"), MakeshiftVoidExtractorScreenHandler::new);
    }

    public static <T extends ScreenHandler> ScreenHandlerType<T> registerExtended(Identifier id, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
        ScreenHandlerType<T> type = new ExtendedScreenHandlerType<>(factory);
        return Registry.register(Registries.SCREEN_HANDLER, id, type);
    }
}
