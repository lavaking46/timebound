package us.newrealms.timeBound.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import us.newrealms.timeBound.TimeBound;

import java.util.List;

public class PlayerJoin implements Listener {
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        Player p = event.getPlayer();
        PersistentDataContainer pPDC = p.getPersistentDataContainer();
        List<Integer> stats = pPDC.get(TimeBound.getKey("statistics"), PersistentDataType.LIST.integers());
        if(stats == null){
            pPDC.set(TimeBound.getKey("statistics"),PersistentDataType.LIST.integers(),List.of(20,3,0,0,0,0,0));
        }
    }
}
