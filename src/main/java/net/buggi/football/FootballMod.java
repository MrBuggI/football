package net.buggi.football;

import net.buggi.football.entity.ModEntities;
import net.buggi.football.entity.custom.SoccerBallEntity;
import net.buggi.football.item.ModItems;
import net.buggi.football.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FootballMod implements ModInitializer {
    public static final String MOD_ID = "football";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModSounds.register();
        ModEntities.register();
        ModItems.register();

        FabricDefaultAttributeRegistry.register(ModEntities.SOCCERBALL, SoccerBallEntity.createAttributes());

        LOGGER.info("Football mod initialized");
    }
}
