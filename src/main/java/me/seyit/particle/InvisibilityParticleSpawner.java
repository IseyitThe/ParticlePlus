package me.seyit.particle;

import me.seyit.ParticlePlus;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.entity.effect.StatusEffects;

public class InvisibilityParticleSpawner {
    private static int tickCounter = 0;
    
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null || client.player == null) return;
            
            tickCounter++;
            if (tickCounter % 2 != 0) return;
            
            if (client.player.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                spawnCustomInvisibilityParticles(client);
            }
        });
    }
    
    private static void spawnCustomInvisibilityParticles(MinecraftClient client) {
        ParticlesMode originalSetting = client.options.getParticles().getValue();
        
        try {
            client.options.getParticles().setValue(ParticlesMode.ALL);
            
            int[] rgb = ParticlePlus.CONFIG.getRGBFromHex();
            double red = rgb[0] / 255.0;
            double green = rgb[1] / 255.0;
            double blue = rgb[2] / 255.0;
            
            for (int i = 0; i < ParticlePlus.CONFIG.particleMultiplier; i++) {
                double offsetX = (Math.random() - 0.5) * 0.8;
                double offsetY = Math.random() * 2.0;
                double offsetZ = (Math.random() - 0.5) * 0.8;
                
                double velX = (Math.random() - 0.5) * 0.03;
                double velY = Math.random() * 0.06;
                double velZ = (Math.random() - 0.5) * 0.03;
                
                double particleSize = ParticlePlus.CONFIG.particleSize * 0.1;
                
                for (int j = 0; j < ParticlePlus.CONFIG.particleSize; j++) {
                    double sizeOffsetX = (Math.random() - 0.5) * particleSize;
                    double sizeOffsetY = (Math.random() - 0.5) * particleSize;
                    double sizeOffsetZ = (Math.random() - 0.5) * particleSize;
                    
                    client.world.addParticle(
                        net.minecraft.particle.ParticleTypes.ENTITY_EFFECT,
                        client.player.getX() + offsetX + sizeOffsetX,
                        client.player.getY() + offsetY + sizeOffsetY,
                        client.player.getZ() + offsetZ + sizeOffsetZ,
                        red, green, blue
                    );
                }
            }
        } finally {
            client.options.getParticles().setValue(originalSetting);
        }
    }
}
