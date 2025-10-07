package us.newrealms.timeBound;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import us.newrealms.timeBound.commands.Statistics;
import us.newrealms.timeBound.events.InventoryInteract;
import us.newrealms.timeBound.events.PlayerInteract;
import us.newrealms.timeBound.events.PlayerMove;
import us.newrealms.timeBound.utils.DungeonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class TimeBound extends JavaPlugin {
    private static TimeBound plugin;
    public static final Logger log = Logger.getLogger("TimeBound Plugin");

    public static Map<String, NamespacedKey> keys;
    public static NamespacedKey getKey(String key){return keys.get(key);}
    public Map<UUID, Inventory> playerInventories = new HashMap<>();
    private void setKeys(){
        keys = Map.of(
//                "pause", new NamespacedKey(plugin, "pause"),
//                "UUID", new NamespacedKey(plugin,"UUID"),
//                "custom_item_name", new NamespacedKey(plugin,"custom_item_name"),
//                "team", new NamespacedKey(plugin,"team"),
//                "structureScore", new NamespacedKey(plugin,"structureScore"),
                "statistics", new NamespacedKey(plugin, "statistics"),
                "tutorialDungeonPuzzleOneAnswer", new NamespacedKey(plugin,"tutorialdungeonpuzzleoneanswer"),
                "tutorialDungeonPuzzleOneAttempt", new NamespacedKey(plugin,"tutorialdungeonpuzzleoneattempt")
        );
    }
    @Override
    public void onLoad() {
        plugin = this;
        setKeys();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        LifecycleEventManager<@NotNull Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("statistics","Statistics command",new Statistics());
        });
        getServer().getPluginManager().registerEvents(new InventoryInteract(),this);
        getServer().getPluginManager().registerEvents(new PlayerMove(),this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TimeBound getPlugin() {
        return plugin;
    }
}
