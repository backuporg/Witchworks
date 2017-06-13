package com.witchworks.common.brew;

import com.witchworks.api.brew.BrewAtributeModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 24/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class HolyWaterBrew extends BrewAtributeModifier {

	@Override
	public void apply(World world, BlockPos pos, EntityLivingBase entity, int amplifier, int tick) {
		if (entity.isEntityUndead()) {
			int damage = (int) (entity.getHealth() * (double) (6 << amplifier) + 0.5D);
			entity.attackEntityFrom(DamageSource.MAGIC, (float) damage);
		}
	}

	@Override
	public void onFinish(World world, BlockPos pos, EntityLivingBase entity, int amplifier) {
		//NO-OP
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public int getColor() {
		return 0x8DA399;
	}

	@Override
	public String getName() {
		return "brew.holy_water_brew";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUD(int x, int y, Minecraft mc) {
		render(x, y, mc, 6);
	}

	@Override
	protected void initAtributes() {
		register(SharedMonsterAttributes.MOVEMENT_SPEED, "70c48882-4e42-11e7-b114-b2f933d5fe66", -0.15000000596046448D, 2);
	}
}
