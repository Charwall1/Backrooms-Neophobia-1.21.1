package ch.charwall.backroomsneo.world;

import ch.charwall.backroomsneo.BackroomsNeo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ModDimensions {

    public static final ResourceKey<Level> LEVEL_ZERO =
            ResourceKey.create(
                    Registries.DIMENSION,
                    ResourceLocation.fromNamespaceAndPath(
                            BackroomsNeo.MOD_ID,
                            "level_zero"
                    )
            );
}