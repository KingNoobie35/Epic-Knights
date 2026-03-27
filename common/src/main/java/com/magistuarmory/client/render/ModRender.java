package com.magistuarmory.client.render;

import com.magistuarmory.api.item.ModItemsProvider;
import com.magistuarmory.item.*;
import com.magistuarmory.item.armor.MedievalArmorItem;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Environment(EnvType.CLIENT)
public class ModRender
{
	public static HumanoidModel INNER_ARMOR = null;
	public static HumanoidModel OUTER_ARMOR = null;
	public static Map<ResourceLocation, HumanoidModel> ARMOR_MODELS_CACHE = new HashMap<>();
	public static Map<ResourceLocation, Object> SHIELD_MODELS_CACHE = new HashMap<>();

	public static void setup(ModItemsProvider content)
	{
		for (RegistrySupplier<? extends Item> supplier : content.dyeableItems)
		{
			// ColorHandlerRegistry.registerItemColors((stack, i) -> i > 0 ? 0xFFFFFFFF : ((DyeableItemLike) stack.getItem()).getColor(stack), supplier.get());
		}
		
		for (RegistrySupplier<? extends Item> supplier : content.items)
			if (supplier.get() instanceof IHasModelProperty havingproperty)
				havingproperty.registerModelProperty();

		content.shieldItems.stream()
				.filter(s -> s.get() instanceof PaviseItem)
				.map(s -> (PaviseItem) s.get())
				.forEach(p -> BlockEntityRendererRegistry.register(p.getBlock().getEntityType(), 
						context -> new PaviseBlockRenderer(context, p.getId(), p.getLocation())));
		
		setupPlatform(content);
	}

	@ExpectPlatform
	public static void setupPlatform(ModItemsProvider content)
	{
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void registerModelsLoadListener(ModItemsProvider content)
	{
		throw new AssertionError();
	}

	public static void registerRenderers()
	{
	}

	public static void loadModels(ModItemsProvider content, EntityRendererProvider.Context context)
	{
		OUTER_ARMOR = new HumanoidModel(context.bakeLayer(ModModels.DEFAULT_ARMOR_LOCATION));
		INNER_ARMOR = new HumanoidModel(context.bakeLayer(ModModels.DEFAULT_LEGGINGS_LOCATION));

		for (RegistrySupplier<? extends MedievalShieldItem> supplier : content.shieldItems)
			loadShieldModel(context, supplier.get());

		for (RegistrySupplier<? extends MedievalArmorItem> supplier : content.armorItems)
			loadArmorModel(context, supplier.get());
	}

	public static void loadShieldModel(EntityRendererProvider.Context context, MedievalShieldItem shield)
	{
		// Shield model rendering disabled for 1.21.4
	}

	public static void loadArmorModel(EntityRendererProvider.Context context, MedievalArmorItem armor)
	{
		// Armor rendering not fully compatible with 1.21.4 rendering system
	}

	@ExpectPlatform
	public static Object createHeraldryItemStackRenderer(String id, ResourceLocation location)
	{
		throw new AssertionError();
	}
}
