package com.blockvader.harshbeginning.init;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.SmelteryRecipe;
import com.blockvader.harshbeginning.tileentities.SmelteryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModTileEntities {
	
	public static TileEntityType<SmelteryTileEntity> SMELTERY;
	
	@SubscribeEvent
	public static void onTileEntityRegistry(RegistryEvent.Register<TileEntityType<?>> event)
	{
		SMELTERY = TileEntityType.Builder.create(SmelteryTileEntity::new, ModBlocks.SMELTERY).build(null);
		SMELTERY.setRegistryName(HarshBeginning.MOD_ID, "smeltery");
		event.getRegistry().register(SMELTERY);
		
		SmelteryRecipe.addRecipes();
	}

}
