// Decoration remove recipe disabled for 1.21.4 port
package com.magistuarmory.item.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.Container;

public class DecorationRemoveRecipe implements Recipe<?> {
    // Stub implementation - recipe system disabled

    public static final RecipeSerializer<DecorationRemoveRecipe> SERIALIZER = new RecipeSerializer<DecorationRemoveRecipe>() {
        @Override
        public MapCodec<DecorationRemoveRecipe> codec() {
            return MapCodec.unit(new DecorationRemoveRecipe());
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DecorationRemoveRecipe> streamCodec() {
            return StreamCodec.unit(new DecorationRemoveRecipe());
        }
    };
}
