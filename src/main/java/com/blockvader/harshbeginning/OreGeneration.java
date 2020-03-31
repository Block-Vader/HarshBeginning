package com.blockvader.harshbeginning;

import java.util.ArrayList;
import java.util.List;

import com.blockvader.harshbeginning.init.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	
	
	public static void setUpOreGen()
	{
		for (Biome biome: ForgeRegistries.BIOMES)
		{
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.COPPER_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(10, 32, 0, 64)));
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.TIN_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(4, 32, 0, 64)));
			
			List<ConfiguredFeature> features = new ArrayList<ConfiguredFeature>();

            for (ConfiguredFeature<?> f : biome.getFeatures(Decoration.UNDERGROUND_ORES))
            {
                if (((DecoratedFeatureConfig)f.config).feature.feature instanceof OreFeature) {
                    if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.IRON_ORE) {
                        features.add(f);
                    }
                }
            }
            //removes vanilla iron ore generation
            biome.getFeatures(Decoration.UNDERGROUND_ORES).removeAll(features);
            biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9), Placement.COUNT_RANGE, new CountRangeConfig(15, 0, 0, 48)));;
		}
	}

}