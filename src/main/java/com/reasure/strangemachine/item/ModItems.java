package com.reasure.strangemachine.item;

import com.reasure.strangemachine.StrangeMachineMod;
import com.reasure.strangemachine.item.custom.DowsingRodItem;
import com.reasure.strangemachine.item.custom.GlintItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item ORICHALCUM_INGOT = registerItem("orichalcum_ingot",
            new Item(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE)));

    public static final Item ORICHALCUM_NUGGET = registerItem("orichalcum_nugget",
            new Item(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE)));

    public static final Item RAW_ORICHALCUM = registerItem("raw_orichalcum",
            new Item(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE).maxDamage(32)));

    public static final Item TURNIP = registerItem("turnip",
            new Item(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE).food(ModFoodComponents.TURNIP)));

    public static final Item COAL_SLIVER = registerItem("coal_sliver",
            new Item(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE)));

    public static final Item INFINITY_COAL = registerItem("infinity_coal",
            new GlintItem(new FabricItemSettings().group(ModItemGroups.STRANGE_MACHINE).maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(StrangeMachineMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        StrangeMachineMod.LOGGER.info("Registering Mod Items for " + StrangeMachineMod.MOD_ID);
    }

}
