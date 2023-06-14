package dev.mayaqq.endless.mixin;

import dev.mayaqq.endless.data.ServerState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "tickInVoid", at = @At("HEAD"), cancellable = true)
    private void tickInVoid(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof ItemEntity && !entity.getWorld().isClient()) {
            ServerState.voidItemsList.add(((ItemEntity) entity).getStack());
        }
    }
}
