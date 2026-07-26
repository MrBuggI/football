package net.buggi.football;

import net.buggi.football.entity.ModEntities;
import net.buggi.football.entity.client.ModModelLayers;
import net.buggi.football.entity.client.SoccerBallEntityModel;
import net.buggi.football.entity.client.SoccerBallEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FootballModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SOCCERBALL_ENTITY, SoccerBallEntityModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.SOCCERBALL, SoccerBallEntityRenderer::new);
    }
}
