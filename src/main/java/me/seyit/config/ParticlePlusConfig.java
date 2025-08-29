package me.seyit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "particle-plus")
public class ParticlePlusConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip(count = 1)
    public int particleMultiplier = 3;

    @ConfigEntry.Gui.Tooltip(count = 1)
    public int particleSize = 2;

    @ConfigEntry.Gui.Tooltip(count = 1)
    public String colorHex = "#FFFFFF";

    @ConfigEntry.Gui.Tooltip(count = 3)
    @ConfigEntry.Gui.PrefixText
    public byte colorHelp;

    public int[] getRGBFromHex() {
        try {
            String hex = colorHex.replace("#", "");
            if (hex.length() != 6) {
                return new int[]{255, 255, 255};
            }
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);
            return new int[]{r, g, b};
        } catch (Exception e) {
            return new int[]{255, 255, 255};
        }
    }
}
