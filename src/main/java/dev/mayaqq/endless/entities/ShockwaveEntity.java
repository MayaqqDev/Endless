package dev.mayaqq.endless.entities;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import net.minecraft.util.math.Box;

public class ShockwaveEntity extends Entity {
    public ShockwaveEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Override
    public void tick() {
        if(!world.isClient()) {
            if(age <= 9) {
                Box box = new Box(getX() - 4, getY() - 1, getZ() - 4, getX() + 4, (world.getHeight() + 2048) - getY(), getZ() + 4);
                float radius = (float) (box.maxX - box.minX) / 2;
            }
            if(age > 23)
                kill();
        }
        else {
            if(age == 1)
                world.playSound(getX(), getY(), getZ(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS, MathHelper.clamp(1 - (MinecraftClient.getInstance().player.distanceTo(this) / 256F), 0, 1), (1.0F + (random.nextFloat() - random.nextFloat()) * 0.2F) * 0.7F, false);

            if(age >= 2 && age <= 5) {
                world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX() + 2, getY(), getZ(), 1.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX() - 2, getY(), getZ(), 1.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ() + 2, 1.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ() - 2, 1.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void kill() {
        if(!world.isClient())
            ((ServerWorld) world).setChunkForced(getChunkPos().x, getChunkPos().z, false);

        super.kill();
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }
}
