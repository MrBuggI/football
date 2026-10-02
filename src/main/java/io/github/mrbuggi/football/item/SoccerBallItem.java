package io.github.mrbuggi.football.item;

import io.github.mrbuggi.football.entity.custom.SoccerBallEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoccerBallItem extends Item {
    public SoccerBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            double distance = 1.5;
            double dx = -Math.sin(Math.toRadians(player.getYRot())) * distance;
            double dz = Math.cos(Math.toRadians(player.getYRot())) * distance;

            double x = player.getX() + dx;
            double y = player.getY();
            double z = player.getZ() + dz;

            SoccerBallEntity ball = new SoccerBallEntity(level, x, y, z);
            level.addFreshEntity(ball);

            if (!player.getAbilities().instabuild) {
                player.getItemInHand(hand).shrink(1);
            }
        }
        return super.use(level, player, hand);
    }
}
