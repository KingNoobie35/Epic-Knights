// Armor decoration recipe disabled for 1.21.4 port
package com.magistuarmory.item.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.Container;

public class ArmorDecorationRecipe implements Recipe<Container> {
    // Stub implementation - recipe system disabled

    public static final RecipeSerializer<ArmorDecorationRecipe> SERIALIZER = new RecipeSerializer<ArmorDecorationRecipe>() {
        @Override
        public MapCodec<ArmorDecorationRecipe> codec() {
            return MapCodec.unit(new ArmorDecorationRecipe());
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ArmorDecorationRecipe> streamCodec() {
            return StreamCodec.unit(new ArmorDecorationRecipe());
        }
    };
}
