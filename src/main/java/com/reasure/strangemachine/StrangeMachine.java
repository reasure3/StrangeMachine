package com.reasure.strangemachine;

import com.reasure.strangemachine.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StrangeMachine implements ModInitializer {
	public static final String MOD_ID = "strangemachine";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Init mod for " + MOD_ID);

		ModItems.registerModItems();
	}
}
