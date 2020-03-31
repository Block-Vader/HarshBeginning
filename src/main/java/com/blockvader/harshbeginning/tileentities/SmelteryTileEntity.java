package com.blockvader.harshbeginning.tileentities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.blockvader.harshbeginning.HarshBeginning;
import com.blockvader.harshbeginning.SmelteryContainer;
import com.blockvader.harshbeginning.SmelteryRecipe;
import com.blockvader.harshbeginning.blocks.SmelteryBlock;
import com.blockvader.harshbeginning.init.ModTileEntities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class SmelteryTileEntity extends TileEntity implements ICapabilityProvider, INamedContainerProvider, INameable, ITickableTileEntity{
	
	private ItemStackHandler inventory;
	
	private LazyOptional<IItemHandler> inventoryHandler = LazyOptional.of(() -> inventory);
	
	public ITextComponent customName;
	private int burnTime;
	private int smeltTime;
	private int fuelUsed;
	
	public final IIntArray smelteryData = new IIntArray()
	{
		@Override
		public int get(int index)
		{
			switch(index) {
			case 0: return SmelteryTileEntity.this.burnTime;
			case 1: return SmelteryTileEntity.this.fuelUsed;
			case 2: return SmelteryTileEntity.this.smeltTime;
			default: return 0;
			}
		}

		@Override
		public void set(int index, int value) {
	         switch(index) {
	         case 0:
	        	 SmelteryTileEntity.this.burnTime = value;
	            break;
	         case 1:
	        	 SmelteryTileEntity.this.fuelUsed = value;
	         case 2:
	        	 SmelteryTileEntity.this.smeltTime = value;
	         }

	      }

		@Override
		public int size() {
			return 3;
		}
	};
	
	public SmelteryTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public SmelteryTileEntity() {
		this(ModTileEntities.SMELTERY);
		inventory = new ItemStackHandler(6);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player)
	{
		return new SmelteryContainer(windowId, this.inventory, inventory, this.smelteryData);
	}
	
	private boolean isBurning()
	{
		return this.burnTime > 0;
	}
	
	@Override
	public void read(CompoundNBT compound)
	{
		super.read(compound);
		this.inventory.deserializeNBT(compound.getCompound("Inventory"));
		this.burnTime = compound.getInt("BurnTime");
		this.smeltTime = compound.getInt("smeltTime");
		if (compound.contains("CustomName", 8))
		{
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound)
	{
		super.write(compound);
		compound.put("Inventory", this.inventory.serializeNBT());
		compound.putInt("BurnTime", this.burnTime);
		compound.putInt("smeltTime", this.smeltTime);
		if (this.customName != null)
		{
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		return compound;
	}
	
	@Override
	public void tick()
	{
		boolean b = this.isBurning();
		if (this.isBurning())
		{
			this.burnTime--;
		}
		if (!this.world.isRemote)
		{
			boolean b1 = false;
			List<ItemStack> input = new ArrayList<>();
			ItemStack output = this.inventory.getStackInSlot(5);
			
			for (int i = 0; i < 4; i++)
			{
				if (!this.inventory.getStackInSlot(i).isEmpty()) input.add(this.inventory.getStackInSlot(i));
			}
			
			if (!input.isEmpty())
			{
				ItemStack result = SmelteryRecipe.getOutput(input);
				if (!result.isEmpty())
				{	
					if (output.isEmpty() || (output.getItem() == result.getItem() && output.getCount() < 65 - result.getCount()))
					{
						if (this.burnTime == 0)
						{
							ItemStack fuel = this.inventory.getStackInSlot(4);
							
							if (AbstractFurnaceTileEntity.isFuel(fuel))
							{
								this.burnTime = ForgeHooks.getBurnTime(fuel);
								this.fuelUsed = this.burnTime;
								fuel.shrink(1);
							}
						}
						
						if (this.isBurning())
						{
							this.smeltTime ++;
							
							if (smeltTime >= 200)
							{
								if (output.isEmpty())
								{
									this.inventory.setStackInSlot(5, result.copy());
								}
								else
								{
									output.grow(result.getCount());
									this.inventory.setStackInSlot(5, output);
								}
								
								for (int i = 0; i < 4; i++)
								{
									this.inventory.getStackInSlot(i).shrink(1);
								}
							}
							else b1 = true;
						}
					}
				}
			}
			if (!b1)
			{
				smeltTime = 0;
			}
			if (b != this.isBurning())
			{
				this.getWorld().setBlockState(this.pos, this.world.getBlockState(this.pos).with(SmelteryBlock.LIT, Boolean.valueOf(this.isBurning())));
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return this.inventoryHandler.cast();
		}
		return super.getCapability(cap, side);
	}
	
	public void setCustomName(ITextComponent name)
	{
		this.customName = name;
	}
	
	@Override
	public ITextComponent getName()
	{
		return this.customName != null ? this.customName : new TranslationTextComponent("container." + HarshBeginning.MOD_ID + ".smeltery");
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return this.getName();
	}

	@Override
	@Nullable
	public ITextComponent getCustomName()
	{
		return this.customName;
	}

}
