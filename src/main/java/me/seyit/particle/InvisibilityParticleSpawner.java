package me.seyit.particle;

import me.seyit.ParticlePlus;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class InvisibilityParticleSpawner {
    private static int tickCounter = 0;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.level == null || client.player == null) return;

            tickCounter++;
            if (tickCounter % 2 != 0) return;

            for (AbstractClientPlayer player : client.level.players()) {
                if (player == client.player) continue;
                
                if (player.isInvisible()) {
                    spawnCustomInvisibilityParticlesForEntity(client, player);
                }
            }

            for (Entity entity : client.level.entitiesForRendering()) {
                if (entity instanceof LivingEntity livingEntity) {
                    if (entity instanceof AbstractClientPlayer) continue;
                    
                    if (livingEntity.isInvisible()) {
                        spawnCustomInvisibilityParticlesForEntity(client, livingEntity);
                    }
                }
            }
        });
    }

    private static void spawnCustomInvisibilityParticlesForEntity(Minecraft client, LivingEntity entity) {
        ParticleStatus originalSetting = client.options.particles().get();

        try {
            client.options.particles().set(ParticleStatus.ALL);

            int[] rgb = ParticlePlus.CONFIG.getRGBFromHex();
            double red = rgb[0] / 255.0;
            double green = rgb[1] / 255.0;
            double blue = rgb[2] / 255.0;

            double entityHeight = entity.getBbHeight();

            for (int i = 0; i < ParticlePlus.CONFIG.particleMultiplier; i++) {
                double offsetX = (Math.random() - 0.5) * 0.8;
                double offsetY = Math.random() * entityHeight;
                double offsetZ = (Math.random() - 0.5) * 0.8;

                double particleSize = ParticlePlus.CONFIG.particleSize * 0.1;

                for (int j = 0; j < ParticlePlus.CONFIG.particleSize; j++) {
                    double sizeOffsetX = (Math.random() - 0.5) * particleSize;
                    double sizeOffsetY = (Math.random() - 0.5) * particleSize;
                    double sizeOffsetZ = (Math.random() - 0.5) * particleSize;

                    client.level.addParticle(
                        ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, (float) red, (float) green, (float) blue),
                        entity.getX() + offsetX + sizeOffsetX,
                        entity.getY() + offsetY + sizeOffsetY,
                        entity.getZ() + offsetZ + sizeOffsetZ,
                        0, 0, 0
                    );
                }
            }
        } finally {
            client.options.particles().set(originalSetting);
        }
    }
}
