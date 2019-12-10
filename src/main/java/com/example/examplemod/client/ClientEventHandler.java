package com.example.examplemod.client;

import com.example.examplemod.HarshBeginning;
import com.example.examplemod.init.ModItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
	
	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event)
	{
		ModItems.registerRenders();
	}

}
