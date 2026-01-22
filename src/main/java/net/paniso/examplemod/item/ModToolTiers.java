package net.paniso.examplemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.TagKey;

public class ModToolTiers {
    public static final Tier REY = new Tier() {
        @Override
        public int getUses() { return 2000; } // עמידות
        @Override
        public float getSpeed() { return 8.0F; } // מהירות חציבה
        @Override
        public float getAttackDamageBonus() { return 3.0F; }
        @Override
        public int getEnchantmentValue() { return 15; }
        @Override
        public Ingredient getRepairIngredient() { return Ingredient.of(Items.DIAMOND); }
        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }
    };
}