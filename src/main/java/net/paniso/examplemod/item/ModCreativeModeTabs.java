package net.paniso.examplemod.item;

import com.mojang.brigadier.LiteralMessage;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.paniso.examplemod.ExampleMod;
import net.paniso.examplemod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> REYTHERITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("reytherite_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.REYTHERITE.get()))
                    .title(Component.translatable("creativetab.examplemod.reytherite_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(Moditems.REYTHERITE.get());
                        output.accept(Moditems.RAW_REYTHERITE.get());
                        output.accept(Moditems.MEVERITE.get());
                        output.accept(Moditems.REYSTAFF.get());
                        output.accept(Moditems.REYLEG.get());
                        output.accept(Moditems.COOKED_REYLEG.get());

                    })

                    .build());


public static final RegistryObject<CreativeModeTab> REYTHERITE_BLOCKS_TAB =
        CREATIVE_MODE_TABS.register("reytherite_blocks_tab",
                () -> CreativeModeTab.builder()
                        .icon(() -> new ItemStack(ModBlocks.REYTHERITE_BLOCK.get()))
                        .title(Component.translatable("creativetab.examplemod.reytherite_blocks"))
                        .displayItems((params, output) -> {
                            output.accept(ModBlocks.REYTHERITE_BLOCK.get());
                            output.accept(ModBlocks.RAW_REYTHERITE_BLOCK.get());
                            output.accept(ModBlocks.REYTHERITE_ORE.get());
                            output.accept(ModBlocks.REYTHERITE_DEEPSLATE_ORE.get());
                        })
                        .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
