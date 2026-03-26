package com.magistuarmory.util;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;

public class ModDamageSources
{
	private static Registry<DamageType> DAMAGE_TYPES;
	
	private static DamageSource ADDITIONAL;
	
	public static void setup(RegistryAccess registryAccess)
	{
		DAMAGE_TYPES = registryAccess.lookup(Registries.DAMAGE_TYPE).orElseThrow();
		ADDITIONAL = new DamageSource(DAMAGE_TYPES.getHolder(ModDamageTypes.ADDITIONAL).orElseThrow());
	}
	
	public static DamageSource additional()
	{
		return ADDITIONAL;
	}

	public static DamageSource additional(Entity attacker)
	{
		return attacker == null ? additional() : new DamageSource(DAMAGE_TYPES.getHolder(ModDamageTypes.ENTITY_ADDITIONAL).orElseThrow(), attacker);
	}

	public static DamageSource silverAttack(Entity attacker)
	{
		return new DamageSource(DAMAGE_TYPES.getHolder(ModDamageTypes.SILVER).orElseThrow(), attacker);
	}

	public static DamageSource armorPiercing(Entity attacker)
	{
		return new DamageSource(DAMAGE_TYPES.getHolder(ModDamageTypes.ARMOR_PIERCING).orElseThrow(), attacker);
	}

	public static boolean isAdditional(DamageSource source) throws NullPointerException
	{
		if (source == null)
			throw new NullPointerException("Got a null damage source");
		return	source.is(DamageTypes.GENERIC) ||
				source.is(DamageTypes.MAGIC) ||
				source.is(ModDamageTypes.ADDITIONAL) || 
				source.is(ModDamageTypes.ENTITY_ADDITIONAL) || 
				source.is(ModDamageTypes.SILVER) || 
				source.is(ModDamageTypes.ARMOR_PIERCING);
	}
}
