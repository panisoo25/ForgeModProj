package net.paniso.examplemod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paniso.examplemod.ExampleMod;
import net.paniso.examplemod.item.custom.ReystaffItem;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> REYTHERITE = ITEMS.register("reytherite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_REYTHERITE = ITEMS.register("raw_reytherite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MEVERITE = ITEMS.register("meverite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REYLEG = ITEMS.register("reyleg",
            () -> new Item(new Item.Properties().food(ModFoodProperties.REYLEG)));

    public static final RegistryObject<Item> COOKED_REYLEG = ITEMS.register("cooked_reyleg",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_REYLEG)));

    public static final RegistryObject<Item> REYSTAFF =
            ITEMS.register("reystaff",
                    () -> new ReystaffItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
