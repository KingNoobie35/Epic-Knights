package com.magistuarmory.item;

import dev.architectury.platform.Platform;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Wrapper class around ToolMaterial for custom weapon materials.
 * Since ToolMaterial is a Record (final) in 1.21.4, we cannot extend it.
 * Instead, we wrap it and provide convenience accessors.
 */
public class ModItemTier
{
	private final String name;
	private final ToolMaterial toolMaterial;
	private final float density;

	// Static references - initialized with vanilla tier values and custom materials
	public static ModItemTier WOOD = new ModItemTier("wood", BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, "minecraft:planks", 0);
	public static ModItemTier STONE = new ModItemTier("stone", BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 4.0F, 1.0F, 5, "minecraft:cobblestone", 1);
	public static ModItemTier IRON = new ModItemTier("iron", BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0F, 2.0F, 14, "minecraft:iron_ingot", 2);
	public static ModItemTier DIAMOND = new ModItemTier("diamond", BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 8.0F, 3.0F, 10, "minecraft:diamond", 3);
	public static ModItemTier GOLD = new ModItemTier("gold", BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 12.0F, 0.0F, 22, "minecraft:gold_ingot", 4);
	public static ModItemTier NETHERITE = new ModItemTier("netherite", BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 4.0F, 15, "minecraft:netherite_ingot", 5);

	public static ModItemTier COPPER = new ModItemTier("copper", BlockTags.INCORRECT_FOR_STONE_TOOL, 150, 0.7F, 0.0F, 10, "c:ingots/copper", 1);
	public static ModItemTier SILVER = new ModItemTier("silver", BlockTags.INCORRECT_FOR_STONE_TOOL, 230, 5.5F, 1.0F, 18, "c:ingots/silver", 2);
	public static ModItemTier STEEL = new ModItemTier("steel", BlockTags.INCORRECT_FOR_IRON_TOOL, 400, 6.0F, 2.5F, 14, "c:ingots/steel", 2);
	public static ModItemTier TIN = new ModItemTier("tin", BlockTags.INCORRECT_FOR_STONE_TOOL, 130, 6.0F, 0.0F, 20, "c:ingots/tin", 2);
	public static ModItemTier BRONZE = new ModItemTier("bronze", BlockTags.INCORRECT_FOR_IRON_TOOL, 200, 6.0F, 2.0F, 15, "c:ingots/bronze", 2);

	public ModItemTier(String name, TagKey<Block> incorrectBlocks, int uses, float speed, float attack, int enchantment, String repairitemtag, float density)
	{
		this.name = name;
		this.density = density;
		TagKey<net.minecraft.world.item.Item> itemTag = TagKey.create(Registries.ITEM, ResourceLocation.parse(repairitemtag));
		this.toolMaterial = new ToolMaterial(incorrectBlocks, uses, speed, attack, enchantment, itemTag);
	}

	public ToolMaterial getToolMaterial()
	{
		return toolMaterial;
	}

	public float getAttackDamageBonus()
	{
		return toolMaterial.attackDamageBonus();
	}

	public @NotNull TagKey<Block> getIncorrectBlocksForDrops()
	{
		return toolMaterial.incorrectBlocksForDrops();
	}

	public int getEnchantmentValue()
	{
		return toolMaterial.enchantmentValue();
	}

	public Ingredient getRepairIngredient()
	{
		return Ingredient.of(toolMaterial.repairItems());
	}

	public float getSpeed()
	{
		return toolMaterial.speed();
	}

	public int getUses()
	{
		return toolMaterial.durability();
	}

	public String getMaterialName()
	{
		return name;
	}

	public float getDensity()
	{
		return density;
	}
}
