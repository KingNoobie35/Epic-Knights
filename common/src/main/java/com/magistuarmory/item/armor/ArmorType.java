package com.magistuarmory.item.armor;

import com.magistuarmory.client.render.model.ModModels;
import dev.architectury.registry.registries.DeferredRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public final class ArmorType
{
	private final Holder<ArmorMaterial> material;
	private final ResourceLocation location;
	private final ResourceLocation modellocation;
	private final EnumMap<net.minecraft.world.item.equipment.ArmorType, Integer> durability;
	private final boolean enabled;

	public ArmorType(ResourceLocation location, ResourceLocation modellocation, Holder<ArmorMaterial> material, Integer[] durability, boolean enabled)
	{
		this.material = material;
		this.location = location;
		this.modellocation = modellocation;
		this.durability = Util.make(new EnumMap<>(net.minecraft.world.item.equipment.ArmorType.class), (enumMap) -> {
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.BOOTS, durability[0]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.LEGGINGS, durability[1]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.CHESTPLATE, durability[2]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.HELMET, durability[3]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.BODY, durability[2]);
		});
		this.enabled = enabled;
	}

	public ArmorType(DeferredRegister<ArmorMaterial> armorMaterial, ResourceLocation location, ResourceLocation modellocation, float toughness, float knockbackResistance, Integer[] durability, Integer[] defenseForSlot, int enchantmentValue, Holder<SoundEvent> equipSound, boolean enabled, boolean dyeable, Supplier<Ingredient> repairIngredient)
	{
		List<ArmorMaterial.Layer> layers = dyeable ? 
				List.of(new ArmorMaterial.Layer(location, "", true), new ArmorMaterial.Layer(location, "_overlay", false)) : 
				List.of(new ArmorMaterial.Layer(location, "", false));

		this.material = armorMaterial.register(location, () -> new ArmorMaterial(
				Util.make(new EnumMap<>(net.minecraft.world.item.equipment.ArmorType.class), (enumMap) -> {
					enumMap.put(net.minecraft.world.item.equipment.ArmorType.BOOTS, defenseForSlot[0]);
					enumMap.put(net.minecraft.world.item.equipment.ArmorType.LEGGINGS, defenseForSlot[1]);
					enumMap.put(net.minecraft.world.item.equipment.ArmorType.CHESTPLATE, defenseForSlot[2]);
					enumMap.put(net.minecraft.world.item.equipment.ArmorType.BODY, defenseForSlot[2]);
					enumMap.put(net.minecraft.world.item.equipment.ArmorType.HELMET, defenseForSlot[3]);
				}),
				enchantmentValue,
				equipSound,
				repairIngredient,
				layers,
				toughness,
				knockbackResistance
		));
		this.location = location;
		this.modellocation = modellocation;
		this.durability = Util.make(new EnumMap<>(net.minecraft.world.item.equipment.ArmorType.class), (enumMap) -> {
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.BOOTS, durability[0]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.LEGGINGS, durability[1]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.CHESTPLATE, durability[2]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.BODY, durability[2]);
			enumMap.put(net.minecraft.world.item.equipment.ArmorType.HELMET, durability[3]);
		});
		this.enabled = enabled;
	}

	public ArmorType(DeferredRegister<ArmorMaterial> armorMaterials, ResourceLocation location, ResourceLocation modellocation, float toughness, float knockbackResistance, Integer[] durability, Integer[] defenseForSlot, int enchantmentValue, Holder<SoundEvent> equipSound, boolean enabled, boolean dyeable)
	{
		this(armorMaterials, location, modellocation, toughness, knockbackResistance, durability, defenseForSlot, enchantmentValue, equipSound, enabled, dyeable, () -> Ingredient.EMPTY);
	}

	public ArmorType(DeferredRegister<ArmorMaterial> armorMaterials, ResourceLocation location, ResourceLocation modellocation, float toughness, float knockbackResistance, Integer[] durability, Integer[] defenseForSlot, int enchantmentValue, Holder<SoundEvent> equipSound, boolean enabled, boolean dyeable, String repairitemtag)
	{
		this(armorMaterials, location, modellocation, toughness, knockbackResistance, durability, defenseForSlot, enchantmentValue, equipSound, enabled, dyeable, () -> Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse(repairitemtag))));
	}

	public String getName() {
		return this.location.toString();
	}

	public float getToughness() {
		return this.material.value().toughness();
	}

	public float getKnockbackResistance() {
		return this.material.value().knockbackResistance();
	}

	public int getDurabilityForType(net.minecraft.world.item.equipment.@NotNull ArmorType type) {
		return this.durability.get(type);
	}

	public int getDefenseForType(net.minecraft.world.item.equipment.@NotNull ArmorType type) {
		// In 1.21.4, defense values are defined on the ArmorMaterial constructor
		// We need to access them differently - store them internally
		return this.material.value().defense().apply(type);
	}

	public int getEnchantmentValue() {
		return this.material.value().enchantmentValue();
	}

	public Holder<SoundEvent> getEquipSound() {
		return this.material.value().equipSound();
	}

	public Supplier<Ingredient> getRepairIngredient() {
		return this.material.value().repairIngredient();
	}
	
	public List<net.minecraft.world.item.equipment.ArmorMaterial.Layer> getLayers() {
		return this.material.value().layers();
	}
	
	public boolean isDisabled()
	{
		return !this.enabled;
	}
	
	@Environment(EnvType.CLIENT)
	public Optional<ModelLayerLocation> getModelLocation()
	{
		if (Objects.equals(this.modellocation.getPath(), "default"))
			return Optional.empty();
		return Optional.of(ModModels.createArmorLocation(this.modellocation));
	}

	public Holder<ArmorMaterial> getMaterial()
	{
		return this.material;
	}

	public ResourceLocation getLocation()
	{
		return this.location;
	}
}
