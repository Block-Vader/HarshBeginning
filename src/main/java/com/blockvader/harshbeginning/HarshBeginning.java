package com.blockvader.harshbeginning;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.blockvader.harshbeginning.client.SmelteryScreen;
import com.blockvader.harshbeginning.init.ModContainers;
import com.blockvader.harshbeginning.init.ModItems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod(HarshBeginning.MOD_ID)
public class HarshBeginning
{
    public static final String MOD_ID = "harsh_beginning";
    
    public static List<Item> bannedItems = new ArrayList<Item>();
	
    private static final Logger LOGGER = LogManager.getLogger();

    public HarshBeginning() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        OreGeneration.setUpOreGen();
        bannedItems.add(Items.WOODEN_AXE);
        bannedItems.add(Items.WOODEN_HOE);
        bannedItems.add(Items.WOODEN_PICKAXE);
        bannedItems.add(Items.WOODEN_SHOVEL);
        bannedItems.add(Items.WOODEN_SWORD);
        bannedItems.add(Items.STONE_AXE);
        bannedItems.add(Items.STONE_HOE);
        bannedItems.add(Items.STONE_PICKAXE);
        bannedItems.add(Items.STONE_SHOVEL);
        bannedItems.add(Items.STONE_SWORD);
    }

    private void doClientStuff(final FMLClientSetupEvent event)
	{
		ModItems.registerRenders();
		
		ScreenManager.registerFactory(ModContainers.SMELTERY, SmelteryScreen::new);
	}

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
