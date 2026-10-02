package io.github.mrbuggi.football.sound;

import io.github.mrbuggi.football.FootballMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent SOCCER_BALL = register("kick_ball");

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation(FootballMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void register() {
        // Classloads this class so the static registration runs.
    }
}
