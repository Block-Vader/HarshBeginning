package com.blockvader.harshbeginning.init;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.items.ModItemTier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModItems {
	
	public static Item LEATHER_PIECE;
	
	public static Item FLINT_PICKAXE;
	public static Item FLINT_AXE;
	public static Item FLINT_SWORD;
	public static Item FLINT_SHOVEL;
	public static Item FLINT_HOE;
	
	@SubscribeEvent
	public static void onItemRegistry(final RegistryEvent.Register<Item> event)
	{
		LEATHER_PIECE = new Item(new Item.Properties()).setRegistryName("leather_piece");
		event.getRegistry().register(LEATHER_PIECE);
		
		FLINT_AXE = new AxeItem(ModItemTier.FLINT, 6.0F, -3.2F, new Item.Properties()).setRegistryName("flint_axe");
		event.getRegistry().register(FLINT_AXE);
		FLINT_PICKAXE = new PickaxeItem(ModItemTier.FLINT, 1, -2.8F, new Item.Properties()).setRegistryName("flint_pickaxe");
		event.getRegistry().register(FLINT_PICKAXE);
		FLINT_SWORD = new SwordItem(ModItemTier.FLINT, 3, -2.4F, new Item.Properties()).setRegistryName("flint_sword");
		event.getRegistry().register(FLINT_SWORD);
		FLINT_SHOVEL = new ShovelItem(ModItemTier.FLINT, 1.5F, -3.0F, new Item.Properties()).setRegistryName("flint_shovel");
		event.getRegistry().register(FLINT_SHOVEL);
		FLINT_HOE = new HoeItem(ModItemTier.FLINT, -3.0F, new Item.Properties()).setRegistryName("flint_hoe");
		event.getRegistry().register(FLINT_HOE);
		
		event.getRegistry().register(new BlockItem(ModBlocks.COPPER_ORE, new Item.Properties()).setRegistryName("copper_ore"));
		event.getRegistry().register(new BlockItem(ModBlocks.TIN_ORE, new Item.Properties()).setRegistryName("tin_ore"));
		event.getRegistry().register(new BlockItem(ModBlocks.SMELTERY, new Item.Properties()).setRegistryName("smeltery"));
	}
	
	@SuppressWarnings("deprecation")
	public static void registerRenders()
	{
		registerRender(LEATHER_PIECE);
		
		registerRender(FLINT_PICKAXE);
		registerRender(FLINT_AXE);
		registerRender(FLINT_SWORD);
		registerRender(FLINT_SHOVEL);
		registerRender(FLINT_HOE);
		
		registerRender(Item.getItemFromBlock(ModBlocks.COPPER_ORE));
		registerRender(Item.getItemFromBlock(ModBlocks.TIN_ORE));
		registerRender(Item.getItemFromBlock(ModBlocks.SMELTERY));
	}
	
	private static void registerRender(Item item)
	{
		Minecraft.getInstance().getItemRenderer().getItemModelMesher().register(item, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}