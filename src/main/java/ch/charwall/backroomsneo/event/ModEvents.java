package ch.charwall.backroomsneo.event;

import ch.charwall.backroomsneo.world.BackroomsTeleporter;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        if (!(event.getEntity() instanceof ServerPlayer player))
            return;

        BackroomsTeleporter.tickCooldown(player);
    }
}