// Heraldry recipe system disabled for Fabric 1.21.4  - requires RecipeSerializer streamCodec() API migration
package com.magistuarmory.item.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.Container;

public class HeraldryRecipe implements Recipe<Container> {
    // Stub implementation - recipe system disabled

    public static final RecipeSerializer<HeraldryRecipe> SERIALIZER = new RecipeSerializer<HeraldryRecipe>() {
        @Override
        public MapCodec<HeraldryRecipe> codec() {
            return MapCodec.unit(new HeraldryRecipe());
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, HeraldryRecipe> streamCodec() {
            return StreamCodec.unit(new HeraldryRecipe());
        }
    };
}
