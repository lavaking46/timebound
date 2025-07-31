package us.newrealms.timeBound.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.newrealms.timeBound.utils.DungeonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static us.newrealms.timeBound.TimeBound.log;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack used = event.getItem();
        Block b = event.getClickedBlock();
        if (event.getAction().isRightClick()) {
            if(used != null){
                if(b != null){
                    Location bLocation = b.getLocation();
                    if(b.getType() == Material.DISPENSER){
                        log.info("Dispenser right clicked with item");
                    }
                    if(b.getType() == Material.STONE_BUTTON){
                        String sequence = DungeonUtils.getTutorialDungeon("soundRoomSequence");
                        List<String> answer = Arrays.stream(DungeonUtils.getTutorialDungeon("soundroom1answer").splitWithDelimiters(",",5)).toList();
                        if(bLocation.getBlockX() == 977 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 468){
                            if(sequence.isBlank()){
                                if(answer.getFirst().equalsIgnoreCase("1")){
                                    p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS,1,1);
                                    DungeonUtils.updateTutorialDungeon("soundRoomSequence","1");
                                }
                                else{
                                    p.playSound(p, Sound.ENTITY_VILLAGER_HURT,1,1);
                                }
                            }
                            else{
                                sequence.concat(",1");
                                List<String> sequence2 = Arrays.stream(sequence.splitWithDelimiters(",",5)).toList();
                                for(String s : sequence2){
                                    if(answer.indexOf(s) != sequence2.indexOf(s)){
                                        p.playSound(p, Sound.ENTITY_VILLAGER_HURT,1,1);
                                        DungeonUtils.updateTutorialDungeon("soundRoomSequence","");
                                    }
                                    else{
                                        p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS,1,1);
                                        DungeonUtils.updateTutorialDungeon("soundRoomSequence",sequence.concat(",1"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CHEST) {
                Chest chest = (Chest) event.getClickedBlock().getState();
                UUID playerUUID = event.getPlayer().getUniqueId();
            }
        }
    }
}
