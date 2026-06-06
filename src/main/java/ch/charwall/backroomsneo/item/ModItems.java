package ch.charwall.backroomsneo.item;

import ch.charwall.backroomsneo.BackroomsNeo;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(BackroomsNeo.MOD_ID);

    public static final DeferredItem<Item> WALLPAPER =
            ITEMS.register("wallpaper",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ALMONDWATER =
            ITEMS.register("almond_water",
                    () -> new AlmondWater(new Item.Properties()
                            .stacksTo(16)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .effect(
                                            () -> new MobEffectInstance(
                                                    MobEffects.REGENERATION,
                                                    200,
                                                    0
                                            ),
                                            1.0F
                                    )
                                    .build()
                            )
                    ));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
