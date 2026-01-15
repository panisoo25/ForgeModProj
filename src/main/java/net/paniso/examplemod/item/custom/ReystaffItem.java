package net.paniso.examplemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.syncher.EntityDataAccessor;

import java.lang.reflect.Field;

public class ReystaffItem extends Item {

    public ReystaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            RandomSource random = serverLevel.getRandom();

            Wolf wolf = EntityType.WOLF.create(serverLevel);
            if (wolf != null) {
                wolf.moveTo(player.getX(), player.getY(), player.getZ(), 0.0F, 0.0F);

                wolf.tame(player);
                wolf.setOwnerUUID(player.getUUID());

                setWolfCollarColor(wolf, DyeColor.values()[random.nextInt(DyeColor.values().length)]);

                if (random.nextInt(67) == 0) {
                    wolf.setCustomName(Component.literal("Dinnerbone"));
                } else {
                    wolf.setCustomName(Component.literal("Rey"));
                }

                wolf.setCustomNameVisible(true);
                serverLevel.addFreshEntity(wolf);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    private static void setWolfCollarColor(Wolf wolf, DyeColor color) {
        try {
            Field field = Wolf.class.getDeclaredField("DATA_COLLAR_COLOR");
            field.setAccessible(true);

            @SuppressWarnings("unchecked")
            EntityDataAccessor<Integer> accessor =
                    (EntityDataAccessor<Integer>) field.get(null);

            wolf.getEntityData().set(accessor, color.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
