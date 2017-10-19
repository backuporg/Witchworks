package com.witchworks.common.tile;

import com.witchworks.api.helper.ItemNullHelper;
import com.witchworks.client.gui.container.ContainerOven;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;

/**
 * Created by Joseph on 7/17/2017.
 */
public class TileOven extends TileEntityLockable implements ITickable, ISidedInventory {
	private static final int[] SLOT_TOP = new int[]{1, 2};
	private static final int[] SLOT_BOTTOM = new int[]{1, 2, 3};
	private List<ItemStack> itemStacks = ItemNullHelper.asList(5);
	private String customName;

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return side == EnumFacing.UP ? SLOT_TOP : SLOT_BOTTOM;
	}


	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return direction == EnumFacing.DOWN && index > 0;
	}

	@Override
	public int getSizeInventory() {
		return this.itemStacks.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.itemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return itemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(itemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(itemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	public void dropItems() {
		for (int i = 0; i < 16; ++i) {
			final ItemStack stack = itemStacks.get(i);
			if (!world.isRemote && !stack.isEmpty()) {
				final EntityItem item = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
				world.spawnEntity(item);
			}
			itemStacks.set(i, ItemStack.EMPTY);
		}
	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

	@Override
	public void update() {

	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerOven(playerInventory, this);
	}

	@Override
	public String getGuiID() {
		return "witchworks:oven";
	}

	public void setCustomInventoryName(String name) {
		this.customName = name;
	}

	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : new TextComponentTranslation("container.oven").getFormattedText();
	}

	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		final NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.itemStacks = ItemNullHelper.asList(5);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			final NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			final int j = nbttagcompound.getByte("Slot");

			if (j >= 0 && j < 5) {
				this.itemStacks.set(j, new ItemStack(nbttagcompound));
			}
		}

		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		final NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < 5; ++i) {
			if (!itemStacks.get(i).isEmpty()) {
				final NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				itemStacks.get(i).writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}

		return compound;
	}
}
