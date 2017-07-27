package com.witchworks.common.item.tool;

import com.witchworks.api.helper.IModelRegister;
import com.witchworks.client.handler.ModelHandler;
import com.witchworks.common.core.WitchWorksCreativeTabs;
import com.witchworks.common.core.helper.ItemNBTHelper;
import com.witchworks.common.item.ModItems;
import com.witchworks.common.item.ModMaterials;
import com.witchworks.common.lib.LibItemName;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * This class was created by BerciTheBeast on 27.3.2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
// Uses code from Botania

public class ItemAthame extends ItemSword implements IModelRegister {

	public ItemAthame() {
		super(ModMaterials.TOOL_RITUAL);
		setRegistryName(LibItemName.ATHAME);
		setUnlocalizedName(LibItemName.ATHAME);
		setCreativeTab(WitchWorksCreativeTabs.ITEMS_CREATIVE_TAB);
		MinecraftForge.EVENT_BUS.register(this);
	}


	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!target.world.isRemote)
			if (target instanceof EntityEnderman && attacker instanceof EntityPlayer) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 20);
				stack.damageItem(50, attacker);
			} else {
				stack.damageItem(1, attacker);
			}
		return true;
	}

	@SubscribeEvent
	public void onEntityDrops(LivingDropsEvent event) {
		if (event.isRecentlyHit() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {
			ItemStack weapon = ((EntityPlayer) event.getSource().getTrueSource()).getHeldItemMainhand();
			if (!weapon.isEmpty() && weapon.getItem() == this) {
				Random rand = event.getEntityLiving().world.rand;
				int looting = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, weapon);

				if (event.getEntityLiving() instanceof AbstractSkeleton && rand.nextInt(26) <= 3 + looting)
					addDrop(event, new ItemStack(Items.SKULL, 1, event.getEntityLiving() instanceof EntityWitherSkeleton ? 1 : 0));
				else if (event.getEntityLiving() instanceof EntityZombie && !(event.getEntityLiving() instanceof EntityPigZombie) && rand.nextInt(26) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(Items.SKULL, 1, 2));
				else if (event.getEntityLiving() instanceof EntityCreeper && rand.nextInt(26) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(Items.SKULL, 1, 4));
				else if (event.getEntityLiving() instanceof EntityBat && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.wool_of_bat, 3));
				else if (event.getEntityLiving() instanceof EntityWolf && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.tongue_of_dog, 1));
				else if (event.getEntityLiving() instanceof EntityVex && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.ectoplasm, 4));
				else if (event.getEntityLiving() instanceof EntityGhast && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.ectoplasm, 2));
				else if (event.getEntityLiving() instanceof EntityBlaze && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.ectoplasm, 1));
				else if (event.getEntityLiving() instanceof EntityEnderman && rand.nextInt(5) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.ectoplasm, 1));
				else if (event.getEntityLiving() instanceof EntityZombie && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntitySkeleton && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntitySkeletonHorse && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 2));
				else if (event.getEntityLiving() instanceof EntityStray && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntityPigZombie && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 3));
				else if (event.getEntityLiving() instanceof EntityHusk && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntityWitherSkeleton && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 2));
				else if (event.getEntityLiving() instanceof EntityZombieHorse && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntityZombieVillager && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 1));
				else if (event.getEntityLiving() instanceof EntityWither && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.spectral_dust, 6));
				else if (event.getEntityLiving() instanceof EntitySilverfish && rand.nextInt(16) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.silver_scales, 2));
				else if (event.getEntityLiving() instanceof EntityGuardian && rand.nextInt(10) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.eye_of_old, 1));
				else if (event.getEntityLiving() instanceof EntityVillager && rand.nextInt(4) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.heart, 1));
				else if (event.getEntityLiving() instanceof EntityPlayer && rand.nextInt(4) <= 2 + 2 * looting)
					addDrop(event, new ItemStack(ModItems.heart, 1));
				else if (event.getEntityLiving() instanceof EntityPlayer && rand.nextInt(11) <= 1 + looting) {
					ItemStack stack = new ItemStack(Items.SKULL, 1, 3);
					ItemNBTHelper.setString(stack, "SkullOwner", event.getEntityLiving().getName());
					addDrop(event, stack);
				}
			}
		}
	}

	private void addDrop(LivingDropsEvent event, ItemStack drop) {
		EntityItem entityitem = new EntityItem(event.getEntityLiving().world, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, drop);
		entityitem.setPickupDelay(10);
		event.getDrops().add(entityitem);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
