package me.seyit.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "particle-plus")
public class ParticlePlusConfig implements ConfigData {
    
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.BoundedDiscrete(min = 1, max = 20)
    public int particleMultiplier = 3;
    
    @ConfigEntry.Gui.Tooltip(count = 1) 
    @ConfigEntry.BoundedDiscrete(min = 1, max = 10)
    public int particleSize = 2;
    
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int redValue = 255;
    
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255) 
    public int greenValue = 255;
    
    @ConfigEntry.Gui.Tooltip(count = 1)
    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int blueValue = 255;
}
