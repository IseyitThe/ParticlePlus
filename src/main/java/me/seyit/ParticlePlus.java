package me.seyit;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.seyit.client.KeyBindings;
import me.seyit.config.ParticlePlusConfig;
import me.seyit.particle.InvisibilityParticleSpawner;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParticlePlus implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("particle-plus");
    public static ParticlePlusConfig CONFIG;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Loading Particle Plus mod...");

        AutoConfig.register(ParticlePlusConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ParticlePlusConfig.class).getConfig();

        int[] rgb = CONFIG.getRGBFromHex();
        LOGGER.info("Config loaded - Particle Count: {}, Particle Size: {}, Color Hex: {} (RGB: {},{},{})",
                CONFIG.particleMultiplier, CONFIG.particleSize, CONFIG.colorHex,
                rgb[0], rgb[1], rgb[2]);

        KeyBindings.registerKeyBindings();
        InvisibilityParticleSpawner.register();

        LOGGER.info("Particle Plus mod loaded successfully!");
    }
}
