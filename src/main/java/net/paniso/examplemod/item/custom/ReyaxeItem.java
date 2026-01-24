package net.paniso.examplemod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

public class ReyaxeItem extends AxeItem {
    public ReyaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!level.isClientSide && state.is(BlockTags.LOGS)) {
            // רשימה שתשמור את כל הבלוקים שכבר מצאנו כדי למנוע לולאה אינסופית
            breakTree(level, pos, new HashSet<>());
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    private void breakTree(Level level, BlockPos pos, Set<BlockPos> visited) {
        // הגבלה כדי לא להפיל את השרת אם יש עץ ענק מדי (למשל 500 בלוקים)
        if (visited.size() > 500 || visited.contains(pos)) return;

        visited.add(pos);
        BlockState state = level.getBlockState(pos);

        // אם הבלוק הוא עץ (Log), נשבור אותו ונמשיך לשכנים שלו
        if (state.is(BlockTags.LOGS)) {
            level.destroyBlock(pos, true);

            // בדיקת כל הבלוקים מסביב (כולל באלכסון ולמעלה/למטה)
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;
                        breakTree(level, pos.offset(x, y, z), visited);
                    }
                }
            }
        }
    }
}