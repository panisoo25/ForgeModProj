package net.paniso.examplemod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paniso.examplemod.ExampleMod;
import net.paniso.examplemod.item.custom.*;

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

    public static final RegistryObject<Item> REYSTAFF = ITEMS.register("reystaff",
            () -> new ReystaffItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> REYCKAXE = ITEMS.register("reyckaxe",
            () -> new ReyckaxeItem(ModToolTiers.REY, new Item.Properties()
                    .stacksTo(1)
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.REY, 1.0F, -2.8F))));

    public static final RegistryObject<Item> REYSWORD =
            ITEMS.register("reysword",
                    () -> new ReyswordItem(
                            ModToolTiers.REY,
                            14,        // damage
                            -2.4f,    // speed (normal sword)
                            new Item.Properties()
                    ));


    public static final RegistryObject<Item> REYSHOVEL = ITEMS.register("reyshovel",
            () -> new ReyshovelItem(ModToolTiers.REY, new Item.Properties()
                    .stacksTo(1)
                    .attributes(ShovelItem.createAttributes(ModToolTiers.REY, 1.5F, -3.0F))));


    public static final RegistryObject<Item> REYAXE = ITEMS.register("reyaxe",
            () -> new ReyaxeItem(ModToolTiers.REY, new Item.Properties()
                    .stacksTo(1)
                    .attributes(AxeItem.createAttributes(ModToolTiers.REY, 6.0F, -3.1F))));


    public static final RegistryObject<Item> REVERSEDSWORD = ITEMS.register("reversedsword",
            () -> new ReversedswordItem(ModToolTiers.REY, 3, -2.4f, new Item.Properties()));


    public static final RegistryObject<Item> ENHANCEDREYCKAXE = ITEMS.register("enhancedreyckaxe",
            () -> new EnhancedreyckaxeItem(ModToolTiers.REY, new Item.Properties()
                    .stacksTo(1)
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.REY, 2.0F, -2.8F))));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}