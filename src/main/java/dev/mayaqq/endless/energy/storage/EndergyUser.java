package dev.mayaqq.endless.energy.storage;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface EndergyUser {
    public void recheck(World world, BlockPos pos, BlockState state, ArrayList<BlockPos> ignored);
}