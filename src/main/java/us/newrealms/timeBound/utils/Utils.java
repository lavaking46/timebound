package us.newrealms.timeBound.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;

public class Utils {
    public static void cMsg(boolean prefix, Player p, Component msg){
        if(prefix){
            Component msgPrefix = text("Time Bound Plugin:").color(GRAY);
            p.sendMessage(msgPrefix.append(msg));
        }else{
            p.sendMessage(msg);
        }
    }
}
