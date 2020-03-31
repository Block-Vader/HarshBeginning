package com.blockvader.harshbeginning.init;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.SmelteryContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ModContainers {
	
	public static ContainerType<SmelteryContainer> SMELTERY;
	
	@SubscribeEvent
	public static void onContainerRegistry(RegistryEvent.Register<ContainerType<?>> event)
	{
		SMELTERY = IForgeContainerType.create(SmelteryContainer::new);
		SMELTERY.setRegistryName(HarshBeginning.MOD_ID, "smeltery");
		event.getRegistry().register(SMELTERY);
	}

}
