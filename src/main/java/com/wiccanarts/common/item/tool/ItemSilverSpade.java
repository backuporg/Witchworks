package com.wiccanarts.common.item.tool;

import com.wiccanarts.api.item.IModelRegister;
import com.wiccanarts.client.handler.ModelHandler;
import com.wiccanarts.common.core.WiccanArtsCreativeTabs;
import net.minecraft.item.ItemSpade;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by BerciTheBeast on 27.3.2017.
 */
public class ItemSilverSpade extends ItemSpade implements IModelRegister {
	public ItemSilverSpade(String id, ToolMaterial material) {
		super(MaterialSilver.SILVER);
		setRegistryName(id);
		setUnlocalizedName(id);
		setCreativeTab(WiccanArtsCreativeTabs.ITEMS_CREATIVE_TAB);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelHandler.registerItem(this);
	}
}
