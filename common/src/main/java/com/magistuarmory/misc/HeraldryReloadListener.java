package com.magistuarmory.misc;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class HeraldryReloadListener extends SimplePreparableReloadListener<Map<ResourceLocation, JsonElement>> {

    private static final Gson GSON = new Gson();

    public HeraldryReloadListener() {
    }

    @Override
    protected CompletableFuture<Map<ResourceLocation, JsonElement>> prepare(ResourceManager resourceManager, ProfilerFiller profilerFiller, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            Map<ResourceLocation, JsonElement> map = new java.util.HashMap<>();
            for (ResourceLocation resourcelocation : resourceManager.listResources("heraldry", (p_10713_) -> p_10713_.getPath().endsWith(".json")).keySet()) {
                String s = resourcelocation.getPath();
                ResourceLocation resourcelocation1 = ResourceLocation.fromNamespaceAndPath(resourcelocation.getNamespace(), s.substring("heraldry/".length(), s.length() - ".json".length()));
                try {
                    map.put(resourcelocation1, GSON.fromJson(resourceManager.getResourceOrThrow(resourcelocation).openAsReader(), JsonElement.class));
                } catch (Exception exception) {
                    // handle
                }
            }
            return map;
        }, executor);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> data,
                         ResourceManager manager,
                         ProfilerFiller profiler) {
        for (Map.Entry<ResourceLocation, JsonElement> entry : data.entrySet()) {
            if (entry.getKey().getPath().equals("banner_patterns")) {
                for (Map.Entry<String, JsonElement> entry2 : entry.getValue().getAsJsonObject().entrySet()) {
                    if (entry2.getKey().equals("values")) {
                        for (JsonElement element : entry2.getValue().getAsJsonArray()) {
                            HeraldryRegistry.register(element.getAsString());
                        }
                    }
                }
            }
        }
    }
}
