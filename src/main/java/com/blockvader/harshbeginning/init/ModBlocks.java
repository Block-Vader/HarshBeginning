package com.blockvader.harshbeginning.init;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.blocks.SmelteryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModBlocks {
	
	public static Block COPPER_ORE;
	public static Block TIN_ORE;
	public static Block SMELTERY;

	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
	{
		COPPER_ORE = new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F)).setRegistryName("copper_ore");
		TIN_ORE = new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F)).setRegistryName("tin_ore");
		SMELTERY = new SmelteryBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)).setRegistryName("smeltery");
		event.getRegistry().register(COPPER_ORE);
		event.getRegistry().register(TIN_ORE);
		event.getRegistry().register(SMELTERY);
	}
}
