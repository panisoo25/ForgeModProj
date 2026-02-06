package net.paniso.examplemod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ReversedswordItem extends SwordItem {
    public ReversedswordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide() && target.isDeadOrDying()) {
            ServerLevel level = (ServerLevel) attacker.level();
            EntityType<?> type = target.getType();

            for (int i = 0; i < 2; i++) {
                type.spawn(level, target.blockPosition(), MobSpawnType.SPAWN_EGG);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}