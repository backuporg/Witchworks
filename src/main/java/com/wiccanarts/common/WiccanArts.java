package com.wiccanarts.common;

import com.wiccanarts.common.block.ModBlocks;
import com.wiccanarts.common.core.WiccanArtsCreativeTab;
import com.wiccanarts.common.core.event.ModEvents;
import com.wiccanarts.common.core.handler.ModSounds;
import com.wiccanarts.common.core.proxy.ISidedProxy;
import com.wiccanarts.common.entity.ModEntities;
import com.wiccanarts.common.item.ModItems;
import com.wiccanarts.common.lib.LibMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static net.minecraftforge.fml.common.Mod.EventHandler;
import static net.minecraftforge.fml.common.Mod.Instance;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings("WeakerAccess")
@Mod(modid = LibMod.MOD_ID, name = LibMod.MOD_NAME, version = LibMod.MOD_VER, dependencies = LibMod.DEPENDENCIES)
public class WiccanArts {

    public static final WiccanArtsCreativeTab CREATIVE_TAB = new WiccanArtsCreativeTab();

    @Instance(LibMod.MOD_ID)
    public static WiccanArts instance;

    @SidedProxy(serverSide = LibMod.PROXY_COMMON, clientSide = LibMod.PROXY_CLIENT)
    public static ISidedProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModEvents.preInit();
        ModEntities.preInit();
        ModSounds.preInit();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ModItems.init();
        ModItems.initOreDictionary();

        ModBlocks.init();
        ModBlocks.initOreDictionary();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}