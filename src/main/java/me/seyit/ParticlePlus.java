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
        
        LOGGER.info("Config loaded - Particle Count: {}, Particle Size: {}, Colors: R{} G{} B{}", 
            CONFIG.particleMultiplier, CONFIG.particleSize, 
            CONFIG.redValue, CONFIG.greenValue, CONFIG.blueValue);
        
        KeyBindings.registerKeyBindings();
        InvisibilityParticleSpawner.register();
        
        LOGGER.info("Particle Plus mod loaded successfully!");
    }
}
