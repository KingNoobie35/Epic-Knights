package com.magistuarmory.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class DyeableArmorDecorationItem extends ArmorDecorationItem implements DyeableItemLike
{
	int defaultcolor;

	public DyeableArmorDecorationItem(ResourceLocation location, Properties properties, net.minecraft.world.item.equipment.ArmorType armorType)
	{
		this(location, properties, armorType, 0xFFFFFFFF);
		this.armorType = armorType;
	}

	public DyeableArmorDecorationItem(ResourceLocation location, Properties properties, net.minecraft.world.item.equipment.ArmorType armorType, int defaultcolor)
	{
		super(location, properties, armorType);
		this.defaultcolor = defaultcolor & 0xFFFFFF;  // Ensure opaque by removing alpha
	}

	public void setColor(ItemStack stack, int color)
	{
		stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color, true));
	}
	
	@Override
	public int getColor(ItemStack stack)
	{
		DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
		// Use bitwise AND with 0xFFFFFF to ensure opaque color
		return (color != null ? color.rgb() : defaultcolor) & 0xFFFFFF;
	}

	@Override
	public CompoundTag getCompoundTag(ItemStack stack) {
		CompoundTag compoundnbt = new CompoundTag();

		compoundnbt.putString("name", this.location.toString());
		compoundnbt.putBoolean("dyeable", true);
		compoundnbt.putInt("color", this.getColor(stack));
		
		return compoundnbt;
	}
}
