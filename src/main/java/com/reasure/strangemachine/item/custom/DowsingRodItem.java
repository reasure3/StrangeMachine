package com.reasure.strangemachine.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class DowsingRodItem extends Item {
    private static final List<Tag<Block>> valuableBlockTags = List.of(
            BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.IRON_ORES, BlockTags.LAPIS_ORES,
            BlockTags.REDSTONE_ORES, BlockTags.GOLD_ORES, BlockTags.DIAMOND_ORES, BlockTags.EMERALD_ORES
    );

    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    // Right-Click on Block
    @SuppressWarnings("ConstantConditions")
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) {
            BlockPos clickedPos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= clickedPos.getY(); i++) {
                Block belowBlock = context.getWorld().getBlockState(clickedPos.down(i)).getBlock();

                if (isValuableBlock(belowBlock)) {
                    sendBlockMessage(clickedPos.down(i), player, belowBlock);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(new TranslatableText("item.strangemachine.dowsing_rod.no_valuables"), false);
            }

        }

        context.getStack().damage(1, context.getPlayer(),
                player -> player.sendToolBreakStatus(player.getActiveHand()));

        return ActionResult.CONSUME;
    }

    private void sendBlockMessage(BlockPos blockPos, PlayerEntity player, Block belowBlock) {
        player.sendMessage(new TranslatableText("item.strangemachine.dowsing_rod.found_valuables",
                belowBlock.getName(), blockPos.getX(), blockPos.getY(), blockPos.getZ()), false);
    }

    private boolean isValuableBlock(Block block) {
        return valuableBlockTags.stream().anyMatch(tag -> tag.contains(block)) || block == Blocks.CHEST;
    }
}
