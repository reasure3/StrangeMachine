package com.reasure.strangemachine.util;

import com.reasure.strangemachine.StrangeMachineMod;
import com.reasure.strangemachine.item.ModItems;
import com.reasure.strangemachine.mixin.ItemAccessor;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFuels();
    }

    private static void registerFuels() {
        StrangeMachineMod.LOGGER.info("Registering Fuels for "+ StrangeMachineMod.MOD_ID);

        FuelRegistry registry = FuelRegistry.INSTANCE;
        // Coal: 80seconds, Coal Sliver: 20seconds
        registry.add(ModItems.COAL_SLIVER, 400);
        registry.add(ModItems.INFINITY_COAL, 1600);

        ((ItemAccessor)ModItems.INFINITY_COAL).setRecipeRemainder(ModItems.INFINITY_COAL);
    }
}
