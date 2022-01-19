package com.reasure.strangemachine.item;

import com.reasure.strangemachine.StrangeMachine;
import com.reasure.strangemachine.item.custom.DowsingRodItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(StrangeMachine.MOD_ID, name), item);
    }

    public static void registerModItems() {
        StrangeMachine.LOGGER.info("Registering Mod Items for " + StrangeMachine.MOD_ID);
    }

}
