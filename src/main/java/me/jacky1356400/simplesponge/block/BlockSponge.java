package me.jacky1356400.simplesponge.block;

import me.jacky1356400.simplesponge.Config;
import me.jacky1356400.simplesponge.util.Data;
import me.jacky1356400.simplesponge.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSponge extends Block implements IHasModel {

    private static final int TICK_RATE = 20;
    private static final Random RANDOM = new Random();

    public BlockSponge() {
        super(Material.SPONGE);
        setSoundType(SoundType.CLOTH);
        setTickRandomly(true);
        setHardness(0.3f);
        setRegistryName(Data.MODID + ":sponge");
        this.setUnlocalizedName(Data.MODID + ".sponge");
        setCreativeTab(Data.TAB);
        Data.BLOCKS.add(this);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        clearupLiquid((World)world, pos);
    }

    @Override
    public int tickRate(World world) {
        return TICK_RATE;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        clearupLiquid(world, pos);
        world.scheduleUpdate(pos, this, TICK_RATE + RANDOM.nextInt(5));
    }

    public boolean isLiquid(){
        return false;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        clearupLiquid(world, pos);
        world.scheduleUpdate(pos, this, TICK_RATE + RANDOM.nextInt(5));
    }

    private void clearupLiquid(World world, BlockPos pos) {
        if (world.isRemote) return;
        boolean hitLava = false;
        for (int dx = -Config.spongeRange; dx <= Config.spongeRange; dx++) {
            for (int dy = -Config.spongeRange; dy <= Config.spongeRange; dy++) {
                for (int dz = -Config.spongeRange; dz <= Config.spongeRange; dz++) {
                    final BlockPos workPos = pos.add(dx, dy, dz);
                    final IBlockState state = world.getBlockState(workPos);
                    Material material = state.getMaterial();
                    if (material.isLiquid()) {
                        hitLava |= material == Material.LAVA;
                        world.setBlockToAir(workPos);
                    }
                }
            }
        }
        if (hitLava) world.addBlockEvent(pos, this, 0, 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean eventReceived(IBlockState state, World world, BlockPos pos, int id, int param) {
        if (world.isRemote) {
            for (int i = 0; i < 20; i++) {
                double px = pos.getX() + RANDOM.nextDouble() * 0.1;
                double py = pos.getY() + 1.0 + RANDOM.nextDouble();
                double pz = pos.getZ() + RANDOM.nextDouble();
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, px, py, pz, 0.0D, 0.0D, 0.0D);
            }
        } else {
            world.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }
        return true;
    }

}
