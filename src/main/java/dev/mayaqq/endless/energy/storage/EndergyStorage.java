package dev.mayaqq.endless.energy.storage;

import org.jetbrains.annotations.NotNull;

public interface EndergyStorage {

    static long transferEndergy(@NotNull EndergyStorage from, @NotNull EndergyStorage to) {
        long sourceAmount = from.getEnergy();
        if (sourceAmount > 0) {
            long destinationRoom = to.getRoom();
            if (destinationRoom > 0) {
                long destinationAmount = to.getEnergy();
                if (sourceAmount > destinationAmount + 1) {
                    long transferAmount = Math.max(1, (sourceAmount - destinationAmount) / 32); // the constant here is simulating pressure flow
                    transferAmount = Math.min(transferAmount, Math.min(sourceAmount, destinationRoom));
                    to.addEnergy(transferAmount);
                    from.drainEnergy(transferAmount);
                    return transferAmount;
                }
            }
        }
        return 0;
    }

    boolean accepts();

    long addEnergy(long amount);

    long drainEnergy(long amount);

    boolean requestEnergy(long amount);

    long getEnergy();

    void setEnergy(long amount);

    long getMax();

    long getCurrent();

    boolean isEmpty();

    boolean isFull();

    void fillCompletely();

    void clear();

    long getRoom();
}
