package io.github.mrbuggi.football.entity;

import io.github.mrbuggi.football.FootballMod;
import io.github.mrbuggi.football.entity.custom.SoccerBallEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<SoccerBallEntity> SOCCERBALL = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(FootballMod.MOD_ID, "soccerball"),
            EntityType.Builder.<SoccerBallEntity>of(SoccerBallEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.0F)
                    .build(FootballMod.MOD_ID + ":soccerball")
    );

    public static void register() {
        // Classloads this class so the static registration runs.
    }
}
