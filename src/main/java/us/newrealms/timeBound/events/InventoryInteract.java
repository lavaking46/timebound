package us.newrealms.timeBound.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class InventoryInteract implements Listener {
    @EventHandler
    public void inventoryInteract(InventoryInteractEvent e){
        e.getWhoClicked();
    }
}
