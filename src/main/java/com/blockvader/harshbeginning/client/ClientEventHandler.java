package com.blockvader.harshbeginning.client;

import com.blockvader.harshbeginning.HarshBeginning;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {
	
	@SubscribeEvent
	public static void onTooltipDisplay(ItemTooltipEvent event)
	{
		Item item = event.getItemStack().getItem();
		if (HarshBeginning.bannedItems.contains(item))
		{
			String s = "tooltip." + HarshBeginning.MOD_ID + "." + item.getRegistryName() + "_banned";
			event.getToolTip().add(new TranslationTextComponent(s).applyTextStyle(TextFormatting.RED));
		}
		else return;
		/*
		if (item == Items.WOODEN_AXE)
		{
			s += ".wooden_axe_banned";
		}
		else if (item == Items.WOODEN_HOE)
		{
			s += ".wooden_hoe_banned";
		}
		else if (item == Items.WOODEN_PICKAXE)
		{
			s += ".wooden_pickaxe_banned";
		}
		else if (item == Items.WOODEN_SHOVEL)
		{
			s += ".wooden_shovel_banned";
		}
		else if (item == Items.WOODEN_SWORD)
		{
			s += ".wooden_sword_banned";
		}
		else if (item == Items.STONE_AXE)
		{
			s += ".stone_axe_banned";
		}
		else if (item == Items.STONE_HOE)
		{
			s += ".stone_hoe_banned";
		}
		else if (item == Items.STONE_PICKAXE)
		{
			s += ".stone_pickaxe_banned";
		}
		else if (item == Items.STONE_SHOVEL)
		{
			s += ".stone_shovel_banned";
		}
		else if (item == Items.STONE_SWORD)
		{
			s += ".stone_sword_banned";
		} else return;*/
	}

}
