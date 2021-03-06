package com.reasure.strangemachine.item.custom;

import com.reasure.strangemachine.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {
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
            addValuablesTooltip(tooltip);
        } else {
            tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip"));
            tooltip.add(new LiteralText(""));
            tooltip.add(new TranslatableText("item.strangemachine.dowsing_rod.tooltip.do_shift"));
        }
    }

    private void sendBlockMessage(BlockPos blockPos, PlayerEntity player, Block belowBlock) {
        player.sendMessage(new TranslatableText("item.strangemachine.dowsing_rod.found_valuables",
                belowBlock.getName(), blockPos.getX(), blockPos.getY(), blockPos.getZ()), false);
    }

    private boolean isValuableBlock(Block block) {
        return ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS.contains(block);
    }

    private void addValuablesTooltip(List<Text> tooltip) {
        StringBuilder line = new StringBuilder();
        for (Block block : ModTags.Blocks.DOWSING_ROD_DETECTABLE_BLOCKS.values()) {
            for (String token : block.getName().getString().split(" ")) {
                if (line.length() + token.length() > 30) {
                    tooltip.add(new LiteralText(line.toString().trim())
                            .formatted(Formatting.DARK_GRAY));
                    line = new StringBuilder();
                }

                line.append(token).append(" ");
            }

            line.deleteCharAt(line.lastIndexOf(" ")).append(", ");
        }

        line.deleteCharAt(line.lastIndexOf(" "));
        line.deleteCharAt(line.lastIndexOf(","));
        if (line.length() > 0) {
            tooltip.add(new LiteralText(line.toString())
                    .formatted(Formatting.DARK_GRAY));
        }
    }
}
