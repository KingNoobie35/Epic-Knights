package com.magistuarmory.fabric.client.render.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class HeraldryItemStackRendererFabric implements BuiltinItemRendererRegistry.DynamicItemRenderer
{
	private final String id;
	private final ResourceLocation location;

	public HeraldryItemStackRendererFabric(String id, ResourceLocation location)
	{
		this.id = id;
		this.location = location;
	}

	@Override
	public void render(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int p, int overlay)
	{
		// TODO: Implement heraldry item rendering when rendering system is ported
		// For now, this is a stub to allow compilation
	}
}