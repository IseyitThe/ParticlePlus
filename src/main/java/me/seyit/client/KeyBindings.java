package me.seyit.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import me.seyit.ParticlePlus;
import me.seyit.config.ParticlePlusConfig;

public class KeyBindings {
    public static KeyBinding openConfigKey;
    
    public static void registerKeyBindings() {
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.particle-plus.config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.particle-plus"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.wasPressed()) {
                if (client.player != null) {
                    try {
                        client.setScreen(AutoConfig.getConfigScreen(ParticlePlusConfig.class, client.currentScreen).get());
                    } catch (Exception e) {
                        client.player.sendMessage(
                            Text.literal("The config GUI couldn’t be opened. Make sure Mod Menu is installed"),
                            false
                        );
                    }
                }
            }
        });
    }
}
