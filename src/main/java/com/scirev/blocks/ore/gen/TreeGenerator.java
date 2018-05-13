package com.scirev.blocks.ore.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.scirev.SciRevolution;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeGenerator implements IWorldGenerator {

	private final WorldGenerator rubber = new WorldGenRubberTree(true, SciRevolution.generallog,
	        SciRevolution.generalleave);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	        IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		switch (world.provider.dimensionId) {
			case 1:

			break;

			case 0:
				runGenerator(rubber, world, random, chunkX, chunkZ, 3, 0, 90, BiomeGenForest.class);
			break;

			case -1:

		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ,
	        double chancesToSpawn, int minHeight, int maxHeight, Class<?>... classes) {
		if (chancesToSpawn < 1) {
			if (random.nextDouble() < chancesToSpawn)
				chancesToSpawn = 1;
			else
				chancesToSpawn = 0;
		}

		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunkX * 16 + 10 + random.nextInt(15);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + 10 + random.nextInt(15);
			Class<?> biome = world.provider.getBiomeGenForCoords(x, z).getClass();
			if (classesList.contains(biome) || classes.length == 0)
				generator.generate(world, random, x, y, z);
		}
	}

}
