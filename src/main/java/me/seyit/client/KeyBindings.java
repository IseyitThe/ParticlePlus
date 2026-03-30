package me.seyit.client;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;
import me.seyit.config.ParticlePlusConfig;

public class KeyBindings {
    public static KeyMapping openConfigKey;
    
    public static void registerKeyBindings() {
        openConfigKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.particle-plus.config",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            KeyMapping.Category.MISC
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.consumeClick()) {
                if (client.player != null) {
                    try {
                        client.setScreen(AutoConfigClient.getConfigScreen(ParticlePlusConfig.class, client.screen).get());
                    } catch (Exception e) {
                        client.player.sendSystemMessage(
                            Component.literal("The config GUI couldn't be opened. Make sure Cloth Config is installed")
                        );
                    }
                }
            }
        });
    }
}
