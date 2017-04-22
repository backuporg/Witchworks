package com.wiccanarts.common;

import com.wiccanarts.common.core.achievement.ModAchievements;
import com.wiccanarts.common.block.ModBlocks;
import com.wiccanarts.common.core.command.CommandIncantation;
import com.wiccanarts.common.core.command.ModCommands;
import com.wiccanarts.common.core.energy.CapabilityEnergy;
import com.wiccanarts.common.core.event.ModEvents;
import com.wiccanarts.common.core.gen.WorldGenOre;
import com.wiccanarts.common.core.handler.ModSounds;
import com.wiccanarts.common.core.proxy.ISidedProxy;
import com.wiccanarts.common.entity.ModEntities;
import com.wiccanarts.common.item.ModItems;
import com.wiccanarts.common.lib.LibMod;
import com.wiccanarts.common.core.net.PacketHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.Mod.EventHandler;
import static net.minecraftforge.fml.common.Mod.Instance;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings ("WeakerAccess")
@Mod (modid = LibMod.MOD_ID, name = LibMod.MOD_NAME, version = LibMod.MOD_VER, dependencies = LibMod.DEPENDENCIES)
public class WiccanArts {

	@Instance (LibMod.MOD_ID)
	public static WiccanArts instance;

	@SidedProxy (serverSide = LibMod.PROXY_COMMON, clientSide = LibMod.PROXY_CLIENT)
	public static ISidedProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CapabilityEnergy.init();
		PacketHandler.init();
		ModEvents.preInit();
		ModSounds.preInit();
		ModEntities.preInit();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);

		ModItems.initOreDictionary();
		ModItems.init();

		ModBlocks.initOreDictionary();
		ModBlocks.init();

		ModAchievements.init();

		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.SILVER_ORE, 4, 7), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.MOLDAVITE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.BLOODSTONE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.TOURMALINE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.MALACHITE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.TIGERS_EYE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.SERPENTINE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.NUUMMITE_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.GARNET_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.PETOSKEY_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.JASPER_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.SALT_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.QUARTZ_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.AMETHYST_ORE), 0);
		GameRegistry.registerWorldGenerator(new WorldGenOre(ModBlocks.ALEXANDRITE_ORE), 0);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@EventHandler
	public void start(FMLServerStartingEvent event) {
		ModCommands.init();
		event.registerServerCommand(new CommandIncantation());
	}
}
