package io.github.mrbuggi.football;

import io.github.mrbuggi.football.entity.ModEntities;
import io.github.mrbuggi.football.entity.client.ModModelLayers;
import io.github.mrbuggi.football.entity.client.SoccerBallEntityModel;
import io.github.mrbuggi.football.entity.client.SoccerBallEntityRenderer;
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
