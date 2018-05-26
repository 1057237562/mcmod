package com.scirev.gen;

import java.util.Random;

import com.scirev.SciRevolution;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	        IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
			case 0:
				generateOverworld(world, random, chunkX, chunkZ);
			break;
		}
	}

	public void generateOverworld(World world, Random rand, int x, int z) {
		generateOre(SciRevolution.ore_copperblock, world, rand, x, z, 4, 12, 6, 0, 60, Blocks.stone);
		generateOre(SciRevolution.ore_tinblock, world, rand, x, z, 2, 5, 10, 0, 60, Blocks.stone);
		generateOre(SciRevolution.ore_zincblock, world, rand, x, z, 3, 4, 6, 0, 55, Blocks.stone);
		generateOre(SciRevolution.ore_alblock, world, rand, x, z, 2, 8, 16, 0, 70, Blocks.stone);
		generateOre(SciRevolution.ore_magblock, world, rand, x, z, 2, 4, 6, 0, 60, Blocks.stone);
	}

	public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize,
	        int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block, vienSize, generateIn);
		for (int i = 0; i < chance; i++) {
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			gen.generate(world, random, xRand, yRand, zRand);
		}
	}
}