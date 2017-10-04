package com.witchworks.common.core.handler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.witchworks.common.lib.LibMod.MOD_ID;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
//Note: Current code is ripped from Choonster's TestMod3, and modified for usage here.

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(MOD_ID)
public final class ModSounds {

	@GameRegistry.ObjectHolder("buzz")
	public static final SoundEvent BUZZ = createSoundEvent("buzz");
	@GameRegistry.ObjectHolder("boil")
	public static final SoundEvent BOIL = createSoundEvent("boil");

	private ModSounds() {
	}

	/**
	 * Create a {@link SoundEvent}.
	 *
	 * @param soundName The SoundEvent's name without the testmod3 prefix
	 * @return The SoundEvent
	 */
	private static SoundEvent createSoundEvent(final String soundName) {
		final ResourceLocation soundID = new ResourceLocation(MOD_ID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	@Mod.EventBusSubscriber(modid = MOD_ID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().registerAll(
					BUZZ,
					BOIL
			);
		}
	}
}