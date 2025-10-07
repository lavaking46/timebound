package us.newrealms.timeBound.events;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import us.newrealms.timeBound.TimeBound;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static us.newrealms.timeBound.TimeBound.log;
import static us.newrealms.timeBound.utils.DungeonUtils.PuzzleSounds;
import static us.newrealms.timeBound.utils.Utils.cMsg;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack used = event.getItem();
        Block b = event.getClickedBlock();
        PersistentDataContainer pPDC = p.getPersistentDataContainer();
//        log.info("Click Event");
        if (event.getAction().isRightClick()) {
//            log.info("Right Click Event");
            if(used != null){
//                log.info("Used item is not null");
                if(b != null){
                    Location bLocation = b.getLocation();
                    if(b.getType() == Material.DISPENSER){
                        log.info("Dispenser right clicked with item");
                    }
                }
            }
            if(b != null){
                Location bLocation = b.getLocation();
                if(b.getType() == Material.STONE_BUTTON){
//                    log.info("Stone Button Right Clicked");
                    List<Integer> answer = pPDC.get(TimeBound.getKey("tutorialDungeonPuzzleOneAnswer"), PersistentDataType.LIST.integers());
                    List<Integer> attempt = new java.util.ArrayList<>();
                    List<Integer> attemptPDC = pPDC.get(TimeBound.getKey("tutorialDungeonPuzzleOneAttempt"), PersistentDataType.LIST.integers());
                    if(attemptPDC != null){
                        attempt.addAll(attemptPDC);
                    }
//                    log.info(String.valueOf(bLocation.getBlockX()) + String.valueOf(bLocation.getBlockY()) + String.valueOf(bLocation.getBlockZ()));
//                    log.info(attempt.toString());
                    if(bLocation.getBlockX() == 978 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 468){
//                        log.info("Tutorial Dungeon Sound Puzzle One Button One Pressed");
                        attempt.add(1);
                        p.playSound(p,PuzzleSounds("TutorialSoundPuzzleOne",1),1,1);
                    }
                    if(bLocation.getBlockX() == 985 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 468){
//                        log.info("Tutorial Dungeon Sound Puzzle One Button Two Pressed");
                        attempt.add(2);
                        p.playSound(p,PuzzleSounds("TutorialSoundPuzzleOne",2),1,1);
                    }
                    if(bLocation.getBlockX() == 985 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 457){
//                        log.info("Tutorial Dungeon Sound Puzzle One Button Three Pressed");
                        attempt.add(3);
                        p.playSound(p,PuzzleSounds("TutorialSoundPuzzleOne",3),1,1);
                    }
                    if(bLocation.getBlockX() == 978 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 457){
//                        log.info("Tutorial Dungeon Sound Puzzle One Button Four Pressed");
                        attempt.add(4);
                        p.playSound(p,PuzzleSounds("TutorialSoundPuzzleOne",4),1,1);
                    }
//                    log.info(attempt.toString());
                    if(answer != null && ((bLocation.getBlockX() == 978 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 457) | (bLocation.getBlockX() == 985 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 457) | (bLocation.getBlockX() == 985 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 468) | (bLocation.getBlockX() == 978 & bLocation.getBlockY() == 48 & bLocation.getBlockZ() == 468))){
//                        log.info("Tutorial Dungeon Sound Puzzle One Answer is not null");
                        int numRun = 0;
                        for(Integer x : attempt){
//                            log.info(x.toString());
                            if(!Objects.equals(answer.get(numRun), x)){
                                p.playSound(p, Sound.ENTITY_VILLAGER_HURT,1,1);
                                pPDC.remove(TimeBound.getKey("tutorialDungeonPuzzleOneAttempt"));
                                attempt = null;
                                cMsg(false,p, Component.text("You failed").color(RED));
                                break;
                            }
                            numRun += 1;
                        }
                        if(attempt != null){
                            pPDC.set(TimeBound.getKey("tutorialDungeonPuzzleOneAttempt"),PersistentDataType.LIST.integers(),attempt);
                            if(attempt.equals(answer)){
                                p.playSound(p,Sound.ENTITY_VILLAGER_YES,1,1);
                                pPDC.remove(TimeBound.getKey("tutorialDungeonPuzzleOneAnswer"));
                                pPDC.remove(TimeBound.getKey("tutorialDungeonPuzzleOneAttempt"));
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
