package net.paniso.examplemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ReyckaxeItem extends PickaxeItem {

    public ReyckaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!level.isClientSide() && state.getDestroySpeed(level, pos) != 0.0F) {

            Direction lookDirection = miner.getDirection();

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    for (int k = 0; k < 5; k++) {

                        BlockPos targetPos;

                        if (lookDirection.getAxis().isVertical()) {
                            targetPos = pos.offset(i, lookDirection.getStepY() * k, j);
                        } else {

                            targetPos = pos.relative(lookDirection, k)
                                    .relative(lookDirection.getClockWise(), i)
                                    .relative(Direction.UP, j);
                        }

                        BlockState targetState = level.getBlockState(targetPos);

                        if (isCommonBlock(targetState)) {
                            level.destroyBlock(targetPos, true, miner);
                        }
                    }
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, miner);
    }

    private boolean isCommonBlock(BlockState state) {
        return !state.is(BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "ores")));
    }
}