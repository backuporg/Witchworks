package com.witchworks.client;

import com.witchworks.common.lib.LibMod;
import net.minecraft.util.ResourceLocation;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public final class ResourceLocations {

	//Misc
	public static final ResourceLocation GRAY_WATER = getLocation("blocks/fluid/gray_scale_fluid");

	//GUI
	public static final ResourceLocation POTION_TEXTURES = getLocation("textures/gui/potions.png");
	public static final ResourceLocation BREW_TEXTURES = getLocation("textures/gui/brews.png");
	public static final ResourceLocation APIARY_GUI = getLocation("textures/gui/apiary.png");
	public static final ResourceLocation ENERGY = getLocation("textures/gui/energy.png");
	public static final ResourceLocation[] ENERGY_BACKGROUND = new ResourceLocation[]{
			getLocation("textures/gui/energy_dark.png"),
			getLocation("textures/gui/energy_white.png"),
	};

	//Particles
	public static final ResourceLocation BUBBLE = getLocation("textures/particle/bubble.png");
	public static final ResourceLocation STEAM = getLocation("particle/steam");
	public static final ResourceLocation BEE = getLocation("particle/bee");

	//Loot Tables

	private ResourceLocations() {
	}

	private static ResourceLocation getLocation(String name) {
		return new ResourceLocation(LibMod.MOD_ID, name);
	}
}
