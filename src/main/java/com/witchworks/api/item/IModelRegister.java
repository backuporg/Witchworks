package com.witchworks.api.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IModelRegister {
	@SideOnly(Side.CLIENT)
	void registerModels();
}
