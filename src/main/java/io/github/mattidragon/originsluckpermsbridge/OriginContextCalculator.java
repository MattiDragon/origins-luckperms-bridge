package io.github.mattidragon.originsluckpermsbridge;

import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.registry.ModComponents;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ContextConsumer;
import net.luckperms.api.context.ContextSet;
import net.luckperms.api.context.MutableContextSet;
import net.minecraft.entity.player.PlayerEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

public class OriginContextCalculator implements ContextCalculator<PlayerEntity> {
    @Override
    public void calculate(@NonNull PlayerEntity player, @NonNull ContextConsumer consumer) {
        ModComponents.ORIGIN.maybeGet(player).ifPresent(component ->
                component.getOrigins().forEach((layer, origin) ->
                        consumer.accept("origins-luckperms-bridge:layer:" + layer.getIdentifier().toString(), origin.getIdentifier().toString())));
    }
    
    @Override
    public @NonNull ContextSet estimatePotentialContexts() {
        var contexts = MutableContextSet.create();
        OriginLayers.getLayers().forEach(layer ->
                layer.getOrigins().forEach(origin ->
                        contexts.add("origins-luckperms-bridge:layer:" + layer.getIdentifier().toString(), origin.toString())));
        return contexts;
    }
}
