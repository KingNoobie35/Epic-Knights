package com.magistuarmory.fabric.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class MedievalArmorLayer implements ArmorRenderer
{
   @Override
   public void render(PoseStack pose, MultiBufferSource buffer, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int i, HumanoidModel<LivingEntity> contextmodel)
   {
      // No Additional overlay rendering required for now.
   }
}
