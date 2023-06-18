package dev.mayaqq.endless.energy.storage;

/**
 * Defines that an object holds a EndergyStorage
 * Objects are supposed to be block entities
 *
 * @param <PStorage>
 */
public interface EndergyStorageBlockEntity<PStorage extends EndergyStorage> {

    PStorage getEnergyStorage();

    void setEndergyDirty();

    boolean getEndergyDirty();
}