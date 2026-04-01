package com.magistuarmory.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.AnimalArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.Item;

public class MedievalHorseArmorItem extends AnimalArmorItem
{
	private final ResourceLocation texture;

	public MedievalHorseArmorItem(Holder<ArmorMaterial> material, ResourceLocation texture, boolean dyeable, Item.Properties properties) {
		super(material.value(), AnimalArmorItem.BodyType.EQUESTRIAN, SoundEvents.ARMOR_EQUIP_LEATHER, dyeable, properties.stacksTo(1));
		this.texture = texture;
	}

	public ResourceLocation getTexture() {
		return texture;
	}
}
