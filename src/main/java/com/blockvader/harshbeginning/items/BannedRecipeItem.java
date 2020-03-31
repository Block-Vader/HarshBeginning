package com.blockvader.harshbeginning.items;

import java.util.List;

import com.blockvader.harshbeginning.HarshBeginning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BannedRecipeItem extends Item{

	public BannedRecipeItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent(HarshBeginning.MOD_ID + ".recipe_banned"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
