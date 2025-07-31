package us.newrealms.timeBound.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport extends BaseListener {
    @EventHandler
    public void playerTeleportEvent(PlayerTeleportEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location to = event.getTo();
        Location from = event.getFrom();
        PlayerTeleportEvent.TeleportCause cause = event.getCause();
        if(cause.equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            if(!world.getName().equalsIgnoreCase("world")){
                event.setCancelled(true);
            }
        }
    }
}
