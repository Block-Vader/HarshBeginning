package com.blockvader.harshbeginning;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.blockvader.harshbeginning.init.ModItems;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class SmelteryRecipe {
	
	private static final Map<List<Ingredient>, ItemStack> recipeMap =  Maps.<List<Ingredient>, ItemStack>newHashMap();
	
	public static void addSmelteryRecipe(List<Ingredient> list, ItemStack output)
	{
		recipeMap.put(list, output);
	}
	
	public static ItemStack getOutput(List<ItemStack> input)
	{
		for (List<Ingredient> list : recipeMap.keySet())
		{
			if (matches(list, input)) return recipeMap.get(list);
		}
		return ItemStack.EMPTY;
	}
	
	private static boolean matches (List<Ingredient> list, List<ItemStack> input)
	{
		if (list.size() != input.size()) return false;
		for (Ingredient ingredient : list)
		{
			boolean b = false;
			int i = 0;
			int j = 0;
			for (ItemStack stack : input)
			{
				if (ingredient.test(stack))
				{
					b = true;
					i = j;
				}
				j++;
			}
			if (!b) return false;
			input.set(i, ItemStack.EMPTY);
		}
		return true;
	}
	
	public static void addRecipes()
	{
		addSmelteryRecipe(Arrays.asList(Ingredient.fromTag(Tags.Items.ORES_IRON), Ingredient.fromItems(Items.CHARCOAL)), new ItemStack(Items.IRON_INGOT, 1));
		addSmelteryRecipe(Arrays.asList(Ingredient.fromItems(ModItems.TIN_INGOT), Ingredient.fromItems(ModItems.COPPER_INGOT), Ingredient.fromItems(ModItems.COPPER_INGOT), Ingredient.fromItems(ModItems.COPPER_INGOT)), new ItemStack(ModItems.BRONZE_INGOT, 4));
	}

}
