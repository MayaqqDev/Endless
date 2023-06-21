package dev.mayaqq.endless.energy.storage;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TotalCappedEndergyStorage implements EndergyStorage {
    protected final long maxEnergy;
    protected Long storedEnergy;
    protected long currentTotal;

    public TotalCappedEndergyStorage(long maxEnergy) {
        this.maxEnergy = maxEnergy;
        this.storedEnergy = 0L;
        this.currentTotal = 0;
    }
    public static @Nullable TotalCappedEndergyStorage fromNbt(@NotNull NbtCompound compound) {
        if (compound.contains("MaxEndergy", NbtElement.LONG_TYPE)) {
            long maxEnergyTotal = compound.getLong("MaxEndergy");
            TotalCappedEndergyStorage storage = new TotalCappedEndergyStorage(maxEnergyTotal);
            if (compound.contains("Endergy", NbtElement.LONG_TYPE)) {
                storage.storedEnergy = compound.getLong("Endergy");
            }
            return storage;
        }
        return null;
    }

    public NbtCompound toNbt() {
        NbtCompound compound = new NbtCompound();
        compound.putLong("MaxEndergy", this.maxEnergy);
        compound.putLong("Endergy", this.storedEnergy);
        return compound;
    }

    @Override
    public boolean accepts() {
        return true;
    }

    @Override
    public long addEnergy(long amount) {
        long currentAmount = this.storedEnergy;
        if (amount > this.maxEnergy - this.currentTotal) {
            long resultingAmount = currentAmount + amount;
            long overflow = resultingAmount - this.maxEnergy + this.currentTotal;
            this.currentTotal = this.currentTotal + (resultingAmount - this.maxEnergy);
            this.storedEnergy = maxEnergy;
            return overflow;
        } else {
            this.currentTotal += amount;
            this.storedEnergy = currentAmount + amount;
            return 0;
        }
    }

    @Override
    public boolean requestEnergy(long amount) {
        long storedAmount = this.storedEnergy;
        if (storedAmount < amount) {
            return false;
        } else {
            this.currentTotal -= amount;
            this.storedEnergy = storedAmount - amount;
            return true;
        }
    }

    @Override
    public long drainEnergy(long amount) {
        long storedAmount = this.storedEnergy;
        long drainedAmount = Math.min(storedAmount, amount);
        this.storedEnergy = storedAmount - drainedAmount;
        this.currentTotal -= drainedAmount;
        return drainedAmount;
    }

    @Override
    public long getEnergy() {
        return this.storedEnergy;
    }

    @Override
    public void setEnergy(long amount) {
        this.storedEnergy = amount;
        this.currentTotal = amount;

    }

    @Override
    public long getMax() {
        return this.maxEnergy;
    }

    @Override
    public long getCurrent() {
        return this.currentTotal;
    }

    @Override
    public boolean isEmpty() {
        return this.currentTotal == 0;
    }

    @Override
    public boolean isFull() {
        return this.currentTotal >= this.maxEnergy;
    }

    @Override
    public long getRoom() {
        return this.maxEnergy - this.currentTotal;
    }

    @Override
    public void fillCompletely() {
        long maxEnergy = this.maxEnergy;
        this.storedEnergy = maxEnergy;
        this.currentTotal = maxEnergy;
    }

    @Override
    public void clear() {
        this.storedEnergy = 0L;
    }
}
