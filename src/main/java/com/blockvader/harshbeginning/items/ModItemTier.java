package com.blockvader.harshbeginning.items;

import java.util.function.Supplier;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

public enum ModItemTier implements IItemTier {
	   FLINT(0, 59, 2.0F, 0.0F, 15, () -> {
	      return Ingredient.fromItems(Items.FLINT);
	   }),
	   BRONZE(1, 131, 4.0F, 1.0F, 5, () -> {
	      return Ingredient.fromItems(Blocks.COBBLESTONE);
	   });

	   private final int harvestLevel;
	   private final int maxUses;
	   private final float efficiency;
	   private final float attackDamage;
	   private final int enchantability;
	   private final LazyLoadBase<Ingredient> repairMaterial;

	   private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
	      this.harvestLevel = harvestLevelIn;
	      this.maxUses = maxUsesIn;
	      this.efficiency = efficiencyIn;
	      this.attackDamage = attackDamageIn;
	      this.enchantability = enchantabilityIn;
	      this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
	   }

	   @Override
	public int getMaxUses() {
	      return this.maxUses;
	   }

	   @Override
	public float getEfficiency() {
	      return this.efficiency;
	   }

	   @Override
	public float getAttackDamage() {
	      return this.attackDamage;
	   }

	   @Override
	public int getHarvestLevel() {
	      return this.harvestLevel;
	   }

	   @Override
	public int getEnchantability() {
	      return this.enchantability;
	   }

	   @Override
	public Ingredient getRepairMaterial() {
	      return this.repairMaterial.getValue();
	   }
	}