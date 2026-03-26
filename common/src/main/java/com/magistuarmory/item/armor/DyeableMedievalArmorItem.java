package com.magistuarmory.item.armor;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.item.DyeableItemLike;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.List;

public class DyeableMedievalArmorItem extends MedievalArmorItem implements DyeableItemLike
{
    private final int defaultcolor;

	public DyeableMedievalArmorItem(com.magistuarmory.item.armor.ArmorType material, net.minecraft.world.item.equipment.ArmorType type, Properties properties, int defaultcolor)
    {
        super(material, type, properties);
        this.defaultcolor = defaultcolor & 0xFFFFFF;  // Ensure opaque by removing alpha
    }

    public int getDefaultColor() {
        return defaultcolor;
    }

    @Override
    public int getColor(ItemStack stack)
    {
        DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
        // Use bitwise AND with 0xFFFFFF to ensure opaque color
        return (color != null ? color.rgb() : getDefaultColor()) & 0xFFFFFF;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag flag)
    {
        super.appendHoverText(stack, tooltipContext, tooltip, flag);
        tooltip.add((Component.translatable(EpicKnights.ID + ".dyeable_armor.description")).withStyle(Style.EMPTY.withColor(ChatFormatting.BLUE).withItalic(true)));
    }
}
