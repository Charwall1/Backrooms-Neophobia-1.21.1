package ch.charwall.backroomsneo.block.custom;

import ch.charwall.backroomsneo.world.BackroomsTeleporter;
import ch.charwall.backroomsneo.world.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LevelZeroWallBlock extends Block {

    public LevelZeroWallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        return Shapes.empty();
    }

    @Override
    public void entityInside(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity) {

        if (level.isClientSide())
            return;

        if (!(entity instanceof ServerPlayer player))
            return;

        if (BackroomsTeleporter.hasCooldown(player))
            return;

        if (level.dimension() == ModDimensions.LEVEL_ZERO) {
            BackroomsTeleporter.sendToOverworld(player);
        } else {
            BackroomsTeleporter.sendToLevelZero(player);
        }
    }
}