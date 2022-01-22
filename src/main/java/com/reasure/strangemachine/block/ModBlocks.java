package com.reasure.strangemachine.block;

import com.reasure.strangemachine.StrangeMachine;
import com.reasure.strangemachine.block.custom.ModPressurePlateBlock;
import com.reasure.strangemachine.block.custom.ModStairsBlock;
import com.reasure.strangemachine.block.custom.ModStoneButtonBlock;
import com.reasure.strangemachine.block.custom.SpeedyBlock;
import com.reasure.strangemachine.item.ModItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModBlocks {
    public static final Block ORICHALCUM_BLOCK = registerBlock("orichalcum_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ModItemGroups.STRANGE_MACHINE);

    public static final Block RAW_ORICHALCUM_BLOCK = registerBlock("raw_orichalcum_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f, 7f).requiresTool()), ModItemGroups.STRANGE_MACHINE);

    public static final Block ORICHALCUM_ORE = registerBlock("orichalcum_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.5f).requiresTool()), ModItemGroups.STRANGE_MACHINE);

    public static final Block DEEPSLATE_ORICHALCUM_ORE = registerBlock("deepslate_orichalcum_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(6f, 4.5f).requiresTool()), ModItemGroups.STRANGE_MACHINE);

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()), ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_PLANKS = registerBlock("cherry_blossom_planks",
            new Block(FabricBlockSettings.of(Material.WOOD, MapColor.PINK).strength(2f, 3f).sounds(BlockSoundGroup.WOOD)),
            ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_STAIRS = registerBlock("cherry_blossom_stairs",
            new ModStairsBlock(CHERRY_BLOSSOM_PLANKS.getDefaultState(),
                    FabricBlockSettings.copy(CHERRY_BLOSSOM_PLANKS)), ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_SLAB = registerBlock("cherry_blossom_slab",
            new SlabBlock(FabricBlockSettings.copy(CHERRY_BLOSSOM_PLANKS)), ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_BUTTON = registerBlock("cherry_blossom_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)),
            ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_PRESSURE_PLATE = registerBlock("cherry_blossom_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.WOOD, CHERRY_BLOSSOM_PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)),
            ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_FENCE = registerBlock("cherry_blossom_fence",
            new FenceBlock(FabricBlockSettings.copy(CHERRY_BLOSSOM_PLANKS)), ModItemGroups.STRANGE_MACHINE);

    public static final Block CHERRY_BLOSSOM_FENCE_GATE = registerBlock("cherry_blossom_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copy(CHERRY_BLOSSOM_FENCE)), ModItemGroups.STRANGE_MACHINE);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(StrangeMachine.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(StrangeMachine.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        StrangeMachine.LOGGER.info("Registering Mod Blocks for " + StrangeMachine.MOD_ID);
    }
}
