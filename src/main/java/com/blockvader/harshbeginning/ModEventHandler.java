package com.blockvader.harshbeginning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HarshBeginning.MOD_ID)
public class ModEventHandler {
	
	@SubscribeEvent
	public static void onPlayerMine(PlayerEvent.BreakSpeed event)
	{
		if (!event.getEntityLiving().getHeldItemMainhand().isEmpty() && !event.getEntityLiving().getEntityWorld().isRemote)
		{
			ItemStack stack = event.getEntityLiving().getHeldItemMainhand();
			if (HarshBeginning.bannedItems.contains(stack.getItem()))
			{
				stack.damageItem(9999, event.getEntityLiving(), (p_220000_1_) -> {p_220000_1_.sendBreakAnimation(Hand.MAIN_HAND);});
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerAttack(AttackEntityEvent event)
	{
		if (!event.getEntityLiving().getHeldItemMainhand().isEmpty() && !event.getEntityLiving().getEntityWorld().isRemote)
		{
			ItemStack stack = event.getEntityLiving().getHeldItemMainhand();
			if (event.getEntityLiving() instanceof PlayerEntity)
			{
				if (((PlayerEntity)event.getEntityLiving()).abilities.isCreativeMode) return;
				

				if (HarshBeginning.bannedItems.contains(stack.getItem()))
				{
					stack.damageItem(9999, event.getEntityLiving(), (p_220000_1_) -> {p_220000_1_.sendBreakAnimation(Hand.MAIN_HAND);});
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerItemUse(PlayerInteractEvent.RightClickBlock event)
	{
		if (!event.getEntityLiving().getHeldItem(event.getHand()).isEmpty() && !event.getEntityLiving().getEntityWorld().isRemote)
		{
			ItemStack stack = event.getEntityLiving().getHeldItem(event.getHand());
			if (event.getEntityLiving() instanceof PlayerEntity)
			{
				if (((PlayerEntity)event.getEntityLiving()).abilities.isCreativeMode) return;
				
				if (HarshBeginning.bannedItems.contains(stack.getItem()))
				{
					stack.damageItem(9999, event.getEntityLiving(), (p_220000_1_) -> {p_220000_1_.sendBreakAnimation(Hand.MAIN_HAND);});
					event.setCanceled(true);
				}
			}
		}
	}

}
