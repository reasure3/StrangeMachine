package com.reasure.strangemachine.item;

import com.reasure.strangemachine.StrangeMachine;
import com.reasure.strangemachine.block.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup STRANGE_MACHINE = FabricItemGroupBuilder.build(new Identifier(StrangeMachine.MOD_ID, "strange_machine"),
            () -> new ItemStack(ModBlocks.ORICHALCUM_ORE));
}
