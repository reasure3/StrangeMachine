package com.reasure.strangemachine;

import com.reasure.strangemachine.block.ModBlocks;
import com.reasure.strangemachine.item.ModItems;
import com.reasure.strangemachine.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StrangeMachineMod implements ModInitializer {
	public static final String MOD_ID = "strangemachine";
	public static final Logger LOGGER = LogManager.getLogger("Strange Machine");

	@Override
	public void onInitialize() {
		LOGGER.info("Init mod for " + MOD_ID);

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();
	}
}
