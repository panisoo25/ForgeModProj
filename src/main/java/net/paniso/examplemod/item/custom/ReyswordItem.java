package net.paniso.examplemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ReyswordItem extends SwordItem {

    private static final ResourceLocation RANGE_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath("examplemod", "reysword_range");
    private static final ResourceLocation SPEED_MODIFIER_ID = ResourceLocation.fromNamespaceAndPath("examplemod", "reysword_speed");

    public ReyswordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties.attributes(SwordItem.createAttributes(tier, attackDamage, attackSpeed)));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();

        if (!level.isClientSide()) {
            target.igniteForSeconds(4.0f);

            if (level instanceof ServerLevel serverLevel) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (lightning != null) {
                    BlockPos pos = target.blockPosition();
                    lightning.moveTo(pos.getX(), pos.getY(), pos.getZ());
                    serverLevel.addFreshEntity(lightning);
                }
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        if (selected) {
            addModifier(player, Attributes.ENTITY_INTERACTION_RANGE, RANGE_MODIFIER_ID, 3.0);
            addModifier(player, Attributes.MOVEMENT_SPEED, SPEED_MODIFIER_ID, 0.05);
        } else {
            removeModifier(player, Attributes.ENTITY_INTERACTION_RANGE, RANGE_MODIFIER_ID);
            removeModifier(player, Attributes.MOVEMENT_SPEED, SPEED_MODIFIER_ID);
        }
    }

    private void addModifier(Player player, Holder<Attribute> attribute, ResourceLocation id, double amount) {
        AttributeModifier modifier = new AttributeModifier(
                id,
                amount,
                AttributeModifier.Operation.ADD_VALUE
        );

        var attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null && !attributeInstance.hasModifier(id)) {
            attributeInstance.addTransientModifier(modifier);
        }
    }

    private void removeModifier(Player player, Holder<Attribute> attribute, ResourceLocation id) {
        var attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.removeModifier(id);
        }
    }
}