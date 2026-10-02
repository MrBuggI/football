package io.github.mrbuggi.football.entity.client;

import io.github.mrbuggi.football.FootballMod;
import io.github.mrbuggi.football.entity.custom.SoccerBallEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SoccerBallEntityRenderer extends MobRenderer<SoccerBallEntity, SoccerBallEntityModel> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(FootballMod.MOD_ID, "textures/entity/soccerball_entity.png");

    public SoccerBallEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SoccerBallEntityModel(context.bakeLayer(ModModelLayers.SOCCERBALL_ENTITY)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(SoccerBallEntity entity) {
        return TEXTURE;
    }
}
