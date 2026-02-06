package net.paniso.examplemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ReyshovelItem extends ShovelItem {
    public ReyshovelItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && entity instanceof ServerPlayer player) {
            int radius = 4;

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x == 0 && z == 0) continue;

                    BlockPos targetPos = pos.offset(x, 0, z);
                    BlockState targetState = level.getBlockState(targetPos);

                    if (this.isCorrectToolForDrops(stack, targetState)) {
                        level.destroyBlock(targetPos, true, player);
                    }
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }
}