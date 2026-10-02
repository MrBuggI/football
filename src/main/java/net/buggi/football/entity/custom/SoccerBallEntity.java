package net.buggi.football.entity.custom;

import net.buggi.football.entity.ModEntities;
import net.buggi.football.item.ModItems;
import net.buggi.football.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SoccerBallEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();

    public SoccerBallEntity(EntityType<? extends SoccerBallEntity> type, Level level) {
        super(type, level);
    }

    public SoccerBallEntity(Level level, double x, double y, double z) {
        this(ModEntities.SOCCERBALL, level);
        this.setPos(x, y, z);
    }

    public SoccerBallEntity(Level level, BlockPos pos) {
        this(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.0)
                .add(Attributes.ATTACK_KNOCKBACK, 500.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            return;
        }
        // Игрок, задевший мяч телом, толкает его.
        List<Player> players = this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(0.5));
        for (Player player : players) {
            Vec3 delta = player.getDeltaMovement();
            double yPush = (delta.x != 0 || delta.z != 0) ? 0.2 : 0.0;
            this.setYRot(player.getYRot());
            this.push(delta.x * -15.0, yPush, delta.z * -8.0);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.IS_FALL)) {
            return false;
        }
        // Удар игрока не наносит урон, а отправляет мяч по направлению взгляда.
        if (source.getEntity() instanceof Player player) {
            Vec3 look = player.getLookAngle();
            this.setYRot(player.getYRot());
            this.push(look.x * 4.0 + 0.3, 0.8, look.z * 4.0 + 0.1);
            this.playSound(ModSounds.SOCCER_BALL, 1.0F, 1.0F);
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.isEmpty()) {
            // Подобрать мяч может только оператор (permission level 2).
            if (!player.hasPermissions(2)) {
                return InteractionResult.PASS;
            }
            if (this.level() instanceof ServerLevel serverLevel) {
                this.discard();
                player.getInventory().add(new ItemStack(ModItems.SOCCERBALL));
                serverLevel.sendParticles(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }
}
