package us.newrealms.timeBound.events;

import org.bukkit.event.Listener;
import us.newrealms.timeBound.TimeBound;

public class BaseListener implements Listener {
    public TimeBound plugin;
    public BaseListener() {
        this.plugin = TimeBound.getPlugin();
    }
}
