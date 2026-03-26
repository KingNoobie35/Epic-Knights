package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HorseRenderState;

@Environment(EnvType.CLIENT)
public class HorseArmorDecorationModel extends HorseModel
{
    ModelPart[] parts;
    
    public HorseArmorDecorationModel(ModelPart root)
    {
        super(root);
        this.parts = new ModelPart[] { this.body };
    }
    
    public ModelPart[] parts()
    {
        return this.parts;
    }
}
