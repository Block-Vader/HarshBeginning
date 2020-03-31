package com.blockvader.harshbeginning.init;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.items.BannedRecipeItem;
import com.blockvader.harshbeginning.items.ModItemTier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModItems {
	
	public static final Tag<Item> INGOTS_COPPER = tag("ingots/copper");
	public static final Tag<Item> INGOTS_TIN = tag("ingots/tin");
	public static final Tag<Item> INGOTS_BRONZE = tag("ingots/bronze");
	
	public static final Tag<Item> ORES_COPPER = tag("ores/copper");
	public static final Tag<Item> ORES_TIN = tag("ores/tin");
	
	public static Item LEATHER_PIECE;
	public static Item COPPER_INGOT;
	public static Item TIN_INGOT;
	public static Item BRONZE_INGOT;
	
	public static Item BANNED_RECIPE;
	
	public static Item FLINT_PICKAXE;
	public static Item FLINT_AXE;
	public static Item FLINT_SWORD;
	public static Item FLINT_SHOVEL;
	public static Item FLINT_HOE;
	
	public static Item BRONZE_PICKAXE;
	public static Item BRONZE_AXE;
	public static Item BRONZE_SWORD;
	public static Item BRONZE_SHOVEL;
	public static Item BRONZE_HOE;
	
	@SubscribeEvent
	public static void onItemRegistry(final RegistryEvent.Register<Item> event)
	{
		LEATHER_PIECE = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("leather_piece");
		event.getRegistry().register(LEATHER_PIECE);
		COPPER_INGOT = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("copper_ingot");
		event.getRegistry().register(COPPER_INGOT);
		TIN_INGOT = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("tin_ingot");
		event.getRegistry().register(TIN_INGOT);
		BRONZE_INGOT = new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("bronze_ingot");
		event.getRegistry().register(BRONZE_INGOT);
		
		BANNED_RECIPE = new BannedRecipeItem(new Item.Properties()).setRegistryName("banned_recipe");
		event.getRegistry().register(BANNED_RECIPE);
		
		FLINT_AXE = new AxeItem(ModItemTier.FLINT, 6.0F, -3.2F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("flint_axe");
		event.getRegistry().register(FLINT_AXE);
		FLINT_PICKAXE = new PickaxeItem(ModItemTier.FLINT, 1, -2.8F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("flint_pickaxe");
		event.getRegistry().register(FLINT_PICKAXE);
		FLINT_SWORD = new SwordItem(ModItemTier.FLINT, 3, -2.4F, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("flint_sword");
		event.getRegistry().register(FLINT_SWORD);
		FLINT_SHOVEL = new ShovelItem(ModItemTier.FLINT, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("flint_shovel");
		event.getRegistry().register(FLINT_SHOVEL);
		FLINT_HOE = new HoeItem(ModItemTier.FLINT, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("flint_hoe");
		event.getRegistry().register(FLINT_HOE);
		
		BRONZE_AXE = new AxeItem(ModItemTier.BRONZE, 6.0F, -3.2F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("bronze_axe");
		event.getRegistry().register(BRONZE_AXE);
		BRONZE_PICKAXE = new PickaxeItem(ModItemTier.BRONZE, 1, -2.8F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("bronze_pickaxe");
		event.getRegistry().register(BRONZE_PICKAXE);
		BRONZE_SWORD = new SwordItem(ModItemTier.BRONZE, 3, -2.4F, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName("bronze_sword");
		event.getRegistry().register(BRONZE_SWORD);
		BRONZE_SHOVEL = new ShovelItem(ModItemTier.BRONZE, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("bronze_shovel");
		event.getRegistry().register(BRONZE_SHOVEL);
		BRONZE_HOE = new HoeItem(ModItemTier.BRONZE, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)).setRegistryName("bronze_hoe");
		event.getRegistry().register(BRONZE_HOE);
		
		event.getRegistry().register(new BlockItem(ModBlocks.COPPER_ORE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("copper_ore"));
		event.getRegistry().register(new BlockItem(ModBlocks.TIN_ORE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("tin_ore"));
		event.getRegistry().register(new BlockItem(ModBlocks.SMELTERY, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("smeltery"));
	}
	
	@SuppressWarnings("deprecation")
	public static void registerRenders()
	{
		registerRender(LEATHER_PIECE);
		registerRender(COPPER_INGOT);
		registerRender(TIN_INGOT);
		registerRender(BRONZE_INGOT);
		
		registerRender(BANNED_RECIPE);
		
		registerRender(FLINT_PICKAXE);
		registerRender(FLINT_AXE);
		registerRender(FLINT_SWORD);
		registerRender(FLINT_SHOVEL);
		registerRender(FLINT_HOE);
		
		registerRender(BRONZE_PICKAXE);
		registerRender(BRONZE_AXE);
		registerRender(BRONZE_SWORD);
		registerRender(BRONZE_SHOVEL);
		registerRender(BRONZE_HOE);
		
		registerRender(Item.getItemFromBlock(ModBlocks.COPPER_ORE));
		registerRender(Item.getItemFromBlock(ModBlocks.TIN_ORE));
		registerRender(Item.getItemFromBlock(ModBlocks.SMELTERY));
	}
	
	private static void registerRender(Item item)
	{
		Minecraft.getInstance().getItemRenderer().getItemModelMesher().register(item, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static Tag<Item> tag(String name)
    {
        return new ItemTags.Wrapper(new ResourceLocation("forge", name));
    }

}