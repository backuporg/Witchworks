package com.wiccanarts.client.handler;

import com.wiccanarts.api.item.IModelRegister;
import com.wiccanarts.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@SideOnly(Side.CLIENT)
public final class ModelHandler {

	private ModelHandler() {}

	/**
	 * Register all Item and Block models from the registry.
	 */
	public static void registerModels() {
		for (Block block : Block.REGISTRY) {
			if (block instanceof IModelRegister)
				((IModelRegister) block).registerModels();
		}

		for (Item item : Item.REGISTRY) {
			if (item instanceof IModelRegister)
				((IModelRegister) item).registerModels();
		}
	}

	//Items
	public static void registerItem(Item item) {
		registerItem(item, 0);
	}

	public static void registerItem(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void registerItemAllMeta(Item item, int meta) {
		registerMetas(item, meta, item.getRegistryName().getResourcePath());
	}

	//Blocks
	public static void registerBlock(Block block) {
		registerBlock(block, 0);
	}

	public static void registerBlock(Block block, int meta) {
		Item iBlock = Item.getItemFromBlock(block);
		if (iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		ModelLoader.setCustomModelResourceLocation(iBlock, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	public static void registerBlockAllMeta(Block block, int meta) {
		Item iBlock = Item.getItemFromBlock(block);
		if (iBlock == null) throw new IllegalArgumentException("Tried to register a block that doesn't have an item");
		registerMetas(iBlock, meta, block.getRegistryName().getResourcePath());
	}

	/**
	 * Set a new model resource location to an Item for as many meta the item has.
	 *
	 * @param item The Item
	 * @param maxMeta The max meta
	 * @param itemName The name of the Item
	 */
	public static void registerMetas(Item item, int maxMeta, String itemName) {
		for (int i = 0; i < maxMeta; i++) {
			ModelLoader.setCustomModelResourceLocation(item, i,
					new ModelResourceLocation(LibMod.MOD_ID + ":" + itemName + "_" + i, "inventory")
			);
		}
	}
}
