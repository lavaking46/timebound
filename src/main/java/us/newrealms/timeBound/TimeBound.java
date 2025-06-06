package us.newrealms.timeBound;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.newrealms.timeBound.commands.Statistics;

import java.util.Map;

public final class TimeBound extends JavaPlugin {
    private final Plugin plugin = this;

    public static Map<String, NamespacedKey> keys;
    public static NamespacedKey getKey(String key){return keys.get(key);}
    private void setKeys(){
        keys = Map.of(
                "statistics", new NamespacedKey(plugin, "statistics")
//                "pause", new NamespacedKey(plugin, "pause"),
//                "UUID", new NamespacedKey(plugin,"UUID"),
//                "custom_item_name", new NamespacedKey(plugin,"custom_item_name"),
//                "team", new NamespacedKey(plugin,"team"),
//                "structureScore", new NamespacedKey(plugin,"structureScore")
        );
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        LifecycleEventManager<@NotNull Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("statistics","Statistics command",new Statistics());
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
