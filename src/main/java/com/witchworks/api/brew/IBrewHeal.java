package com.witchworks.api.brew;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHealEvent;

/**
 * This class was created by Arekkuusu on 06/06/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IBrewHeal {

	void onHeal(LivingHealEvent event, EntityLivingBase healed, int amplifier);
}
