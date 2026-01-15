package net.paniso.examplemod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;

public class ModFoodProperties {
    public static final FoodProperties REYLEG = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.DARKNESS, 400), 0.20f).usingConvertsTo(Items.BONE).build();

    public static final FoodProperties COOKED_REYLEG = new FoodProperties.Builder().nutrition(6).saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 20, 255), 0.20f).usingConvertsTo(Items.BONE).build();

}


