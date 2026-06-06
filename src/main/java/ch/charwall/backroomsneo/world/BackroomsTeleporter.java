package ch.charwall.backroomsneo.world;

import ch.charwall.backroomsneo.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class BackroomsTeleporter {

    public static final String COOLDOWN = "backroomsneo_tp_cooldown";

    private static final String RETURN_X = "backroomsneo_return_x";
    private static final String RETURN_Y = "backroomsneo_return_y";
    private static final String RETURN_Z = "backroomsneo_return_z";
    private static final String RETURN_YAW = "backroomsneo_return_yaw";
    private static final String RETURN_PITCH = "backroomsneo_return_pitch";

    public static void sendToLevelZero(ServerPlayer player) {

        saveReturnLocation(player);

        ServerLevel levelZero = player.server.getLevel(ModDimensions.LEVEL_ZERO);
        if (levelZero == null)
            return;

        BlockPos spawnPos = new BlockPos(
                player.blockPosition().getX(),
                2,
                player.blockPosition().getZ()
        );

        createExitWall(levelZero, spawnPos.offset(3, -1, 0));

        setCooldown(player, 60);

        player.teleportTo(
                levelZero,
                spawnPos.getX() + 0.5,
                spawnPos.getY(),
                spawnPos.getZ() + 0.5,
                player.getYRot(),
                player.getXRot()
        );
    }

    public static void sendToOverworld(ServerPlayer player) {

        ServerLevel overworld = player.server.getLevel(Level.OVERWORLD);
        if (overworld == null)
            return;

        CompoundTag data = player.getPersistentData();

        double x = data.contains(RETURN_X) ? data.getDouble(RETURN_X) : 0.5;
        double y = data.contains(RETURN_Y) ? data.getDouble(RETURN_Y) : 100;
        double z = data.contains(RETURN_Z) ? data.getDouble(RETURN_Z) : 0.5;
        float yaw = data.contains(RETURN_YAW) ? data.getFloat(RETURN_YAW) : player.getYRot();
        float pitch = data.contains(RETURN_PITCH) ? data.getFloat(RETURN_PITCH) : player.getXRot();

        setCooldown(player, 60);

        player.teleportTo(
                overworld,
                x,
                y,
                z,
                yaw,
                pitch
        );
    }

    public static boolean hasCooldown(ServerPlayer player) {
        return player.getPersistentData().getInt(COOLDOWN) > 0;
    }

    public static void tickCooldown(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();
        int cooldown = data.getInt(COOLDOWN);

        if (cooldown > 0) {
            data.putInt(COOLDOWN, cooldown - 1);
        }
    }

    private static void setCooldown(ServerPlayer player, int ticks) {
        player.getPersistentData().putInt(COOLDOWN, ticks);
    }

    private static void saveReturnLocation(ServerPlayer player) {
        CompoundTag data = player.getPersistentData();

        data.putDouble(RETURN_X, player.getX());
        data.putDouble(RETURN_Y, player.getY());
        data.putDouble(RETURN_Z, player.getZ());
        data.putFloat(RETURN_YAW, player.getYRot());
        data.putFloat(RETURN_PITCH, player.getXRot());
    }

    private static void createExitWall(ServerLevel level, BlockPos bottomCenter) {
        for (int y = 0; y <= 2; y++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = bottomCenter.offset(0, y, z);
                level.setBlock(
                        pos,
                        ModBlocks.LEVEL_ZERO_WALL.get().defaultBlockState(),
                        3
                );
            }
        }
    }
}