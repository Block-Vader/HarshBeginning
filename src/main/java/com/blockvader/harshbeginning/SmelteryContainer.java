package com.blockvader.harshbeginning;

import com.blockvader.harshbeginning.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SmelteryContainer extends Container{
	
	public IIntArray smelteryData;

	protected SmelteryContainer(ContainerType<?> type, int id) {
		super(type, id);
	}
	
	public SmelteryContainer(int windowId, PlayerInventory inventory, PacketBuffer buff)
	{
		this(windowId, new ItemStackHandler(6), inventory, new IntArray(3));
	}
	
	public SmelteryContainer(int windowId, IItemHandler handler, PlayerInventory inventory, IIntArray smelteryData)
	{
		this(ModContainers.SMELTERY, windowId);
		this.addSlots(handler, inventory);
		assertIntArraySize(smelteryData, 3);
		this.smelteryData = smelteryData;
		this.trackIntArray(smelteryData);
	}
	
	private void addSlots(IItemHandler handler, PlayerInventory inventory)
	{
		for (int y = 0; y < 2; y++)
		{
			for (int x = 0; x < 2; x++)
			{
				this.addSlot(new SlotItemHandler(handler, 2 * y + x, 38 + x * 18, 17 + y * 18));
			}
		}
		
		this.addSlot(new SlotItemHandler(handler, 4, 47, 71)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return AbstractFurnaceTileEntity.isFuel(stack);
			}
		});
		
		this.addSlot(new SlotItemHandler(handler, 5, 116, 53)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return false;
			}
		});
		
		for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 102 + k * 18));
            }
        }

        for (int l = 0; l < 9; ++l)
        {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 160));
        }
	}

	@Override
	public ItemStack transferStackInSlot(final PlayerEntity player, final int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		final Slot slot = this.inventorySlots.get(index);
		if ((slot != null) && slot.getHasStack())
		{
			final ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
			if (index < containerSlots)
			{
				if (!mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!mergeItemStack(itemstack1, 0, containerSlots, false))
			{
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();
			}
			if (itemstack1.getCount() == itemstack.getCount())
			{
				return ItemStack.EMPTY;
			}
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getBurnTime()
	{
		int i = this.smelteryData.get(1);
	      if (i == 0)
	      {
	         i = 200;
	      }

	      return this.smelteryData.get(0) * 13 / i;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int geSmeltTime()
	{
		return this.smelteryData.get(2) * 3/25;
	}
	
	@OnlyIn(Dist.CLIENT)
	   public boolean isBurning() {
	      return this.smelteryData.get(0) > 0;
	   }
	
}
