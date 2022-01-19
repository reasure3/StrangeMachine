package com.reasure.strangemachine.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpeedyBlock extends Block {
    public SpeedyBlock(Settings settings) {
        super(settings);
    }

    // Right-Click this Block
    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Called 4 times on right click:
        // 2 Times on the Server (for each Hand)
        // 2 Times on the Client (for each Hand)
        if (world.isClient) {
            if (hand == Hand.MAIN_HAND) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600));
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.CONSUME;
    }

    // Walk on this Block (With not Sneaking)
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 2, true, false));
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
