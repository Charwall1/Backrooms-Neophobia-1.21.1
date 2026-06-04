package ch.charwall.backroomsneo.item;

import ch.charwall.backroomsneo.BackroomsNeo;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackroomsNeo.MOD_ID);

    public static final DeferredItem<Item> WALLPAPER = ITEMS.register("wallpaper",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
