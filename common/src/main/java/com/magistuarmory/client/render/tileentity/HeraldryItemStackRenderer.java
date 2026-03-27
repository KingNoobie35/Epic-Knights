package com.magistuarmory.client.render.tileentity;

import com.magistuarmory.client.render.PatternLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HeraldryItemStackRenderer implements ShieldPatternLayer {
    public HeraldryItemStackRenderer(String id, ResourceLocation location) {
        // rendering disabled stub for 1.21.4 compatibility
    }

    public void loadModel(Object context) {
        // no-op
    }

    public void setModel(Object model) {
        // no-op
    }

    @Override
    public void renderByItem(Object stack, Object transform, PoseStack pose, MultiBufferSource buffer, int p, int overlay) {
        // no-op rendering stub for 1.21.4 compatibility
    }

    @Override
    public Material getBaseMaterial(boolean withPattern) {
        return new Material(Sheets.SHIELD_SHEET, new ResourceLocation("minecraft", "entity/shield/base"));
    }

    @Override
    public Material getBasePatternMaterial() {
        return new Material(Sheets.SHIELD_SHEET, new ResourceLocation("minecraft", "entity/shield/base"));
    }

    @Override
    public Material getPatternMaterial(ResourceLocation patternLocation) {
        return new Material(Sheets.SHIELD_SHEET, new ResourceLocation("minecraft", "entity/shield/base"));
    }
}
