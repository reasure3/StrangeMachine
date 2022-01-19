package com.reasure.strangemachine.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {
    private static final List<Tag<Block>> valuableBlockTags = List.of(
            BlockTags.COAL_ORES, BlockTags.COPPER_ORES, BlockTags.IRON_ORES, BlockTags.LAPIS_ORES,
            BlockTags.REDSTONE_ORES, BlockTags.GOLD_ORES, BlockTags.DIAMOND_ORES, BlockTags.EMERALD_ORES
    );
    private static final List<Block> valuableBlocks = List.of(
            Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.NETHER_QUARTZ_ORE, Blocks.GOLD_BLOCK, Blocks.ANCIENT_DEBRIS, Blocks.END_PORTAL_FRAME
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip.shift"));
            for (Tag<Block> tag : valuableBlockTags) {
                tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip.valuables_tag",
                        tag.values().get(0).getName()));
            }
            for (Block block : valuableBlocks) {
                tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip.valuables_block", block.getName()));
            }
        } else {
            tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip"));
        }
    }

    private void sendBlockMessage(BlockPos blockPos, PlayerEntity player, Block belowBlock) {
        player.sendMessage(new TranslatableText("item.strangemachine.dowsing_rod.found_valuables",
                belowBlock.getName(), blockPos.getX(), blockPos.getY(), blockPos.getZ()), false);
    }

    private boolean isValuableBlock(Block block) {
        return valuableBlockTags.stream().anyMatch(tag -> tag.contains(block))
                || valuableBlocks.contains(block);
    }
}
