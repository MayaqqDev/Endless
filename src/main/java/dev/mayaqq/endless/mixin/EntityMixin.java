package dev.mayaqq.endless.mixin;

import dev.mayaqq.endless.data.VoidItemsStorage;
import dev.mayaqq.endless.interfaces.EntityExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityExtension {
    @Shadow protected abstract void tickInVoid();

    @Inject(method = "tickInVoid", at = @At("HEAD"))
    private void tickInVoid(CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        World world = entity.getWorld();
        if (entity instanceof ItemEntity && !world.isClient() && world.getRegistryKey() == World.END) {
            VoidItemsStorage.VoidItem item = new VoidItemsStorage.VoidItem();
            ItemStack stack = ((ItemEntity) entity).getStack();
            String id1 = stack.getItem().getTranslationKey().split("\\.")[1];
            String id2 = stack.getItem().getTranslationKey().split("\\.")[2];
            item.item = id1 + ":" + id2;
            item.count = stack.getCount();
            item.nbt = stack.getNbt() == null ? "" : stack.getNbt().toString();
            VoidItemsStorage.DATA.items.add(item);
            VoidItemsStorage.save();
        }
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public void tickEntityVoid() {
        this.tickInVoid();
    }
}