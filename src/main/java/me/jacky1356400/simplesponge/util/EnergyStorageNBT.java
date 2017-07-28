package me.jacky1356400.simplesponge.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageNBT extends EnergyStorage implements INBTSerializable<NBTTagCompound> {
    public EnergyStorageNBT(int capacity) {
        super(capacity);
    }

    public EnergyStorageNBT(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public EnergyStorageNBT(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setInteger("energy", energy);
        compound.setInteger("capacity", capacity);
        compound.setInteger("maxReceive", maxReceive);
        compound.setInteger("maxExtract", maxExtract);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        energy = nbt.getInteger("energy");
        capacity = nbt.getInteger("capacity");
        maxReceive = nbt.getInteger("maxReceive");
        maxExtract = nbt.getInteger("maxExtract");
    }

    public void setEnergyStorad(int energy) {
        this.energy = energy;
    }
}
