package com.reasure.strangemachine.item;

import com.reasure.strangemachine.StrangeMachine;
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

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(StrangeMachine.MOD_ID, name), item);
    }

    public static void registerModItems() {
        StrangeMachine.LOGGER.info("Registering Mod Items for " + StrangeMachine.MOD_ID);
    }

}
