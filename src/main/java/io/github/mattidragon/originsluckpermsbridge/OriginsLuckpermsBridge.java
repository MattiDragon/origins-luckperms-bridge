package io.github.mattidragon.originsluckpermsbridge;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.luckperms.api.LuckPermsProvider;

public class OriginsLuckpermsBridge implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> LuckPermsProvider.get().getContextManager().registerCalculator(new OriginContextCalculator()));
    }
}
