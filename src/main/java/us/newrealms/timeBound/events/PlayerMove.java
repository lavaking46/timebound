package us.newrealms.timeBound.events;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import us.newrealms.timeBound.TimeBound;
import us.newrealms.timeBound.utils.DungeonUtils;

import java.util.List;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.DARK_GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static org.bukkit.map.MapPalette.LIGHT_GRAY;
import static us.newrealms.timeBound.TimeBound.log;
import static us.newrealms.timeBound.utils.DungeonUtils.PuzzleSounds;

public class PlayerMove extends BaseListener {
    static BukkitScheduler scheduler = Bukkit.getScheduler();

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer pPDC = p.getPersistentDataContainer();
        Location from = e.getFrom();
        Location to = e.getTo();
        World world = p.getWorld();
        Material sFire = Material.SOUL_FIRE;
        if(world.getName().equalsIgnoreCase("world")) {
            List<Location> flames = List.of(new Location(world, 946, 61, 481), new Location(world, 952, 61, 481), new Location(world, 946, 61, 478), new Location(world, 952, 61, 478), new Location(world, 946, 61, 475), new Location(world, 952, 61, 475));
            //Activate Flames at the beginning of the Tutorial dungeon.
            if (to.z() < 484 & from.z() >= 484 & p.getY() > 56 & p.getY() < 68 & p.getX() < 952 & p.getX() > 946) {
//            log.info("Player Activated Flames");
                world.setType(flames.getFirst(), sFire);
                world.setType(flames.get(1), sFire);
                scheduler.runTaskLater(plugin, () -> {
                    world.setType(flames.get(2), sFire);
                    world.setType(flames.get(3), sFire);
                }, 20);
                scheduler.runTaskLater(plugin, () -> {
                    world.setType(flames.get(4), sFire);
                    world.setType(flames.get(5), sFire);
                }, 30);
            }
            //Tutorial Dungeon entrance
            if (to.z() < 512 & from.z() >= 512 & p.getY() > 56 & p.getY() < 68 & p.getX() < 952 & p.getX() > 946) {
                if(pPDC.get(TimeBound.getKey("tutorialDungeonPuzzleOneAnswer"), PersistentDataType.LIST.integers()) == null){
                    List<Integer> pAnswer = DungeonUtils.AnswerRandomizer(4,1,5);
                    pPDC.set(TimeBound.getKey("tutorialDungeonPuzzleOneAnswer"),PersistentDataType.LIST.integers(),pAnswer);
//                    log.info(pAnswer.toString());
                }
                Title title = Title.title(text("Entering").color(TextColor.color(GRAY)), text("Tutorial Dungeon").color(DARK_GREEN));
                p.showTitle(title);
            }
            //Tutorial Dungeon cave entrance
            if (to.z() < 534 & from.z() >= 534 & p.getY() > 62 & p.getY() < 69 & p.getX() < 951 & p.getX() > 947) {
                Transformation bDisplay = new Transformation(new Vector3f(0, 0, 0), new AxisAngle4f(0, 0, 0, 0), new Vector3f(3, 6, 0.9f), new AxisAngle4f(0, 0, 0, 0));
                BlockDisplay blockDisplay = world.spawn(new Location(world, 948, 57, 455), BlockDisplay.class, entity -> {
                    entity.setBlock(Material.NETHERITE_BLOCK.createBlockData());
                    entity.setBillboard(Display.Billboard.FIXED);
                    entity.setTransformation(bDisplay);
                });
                world.setType(new Location(world, 948, 57, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 57, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 57, 455), Material.BARRIER);
                world.setType(new Location(world, 948, 58, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 58, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 58, 455), Material.BARRIER);
                world.setType(new Location(world, 948, 59, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 59, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 59, 455), Material.BARRIER);
                world.setType(new Location(world, 948, 60, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 60, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 60, 455), Material.BARRIER);
                world.setType(new Location(world, 948, 61, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 61, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 61, 455), Material.BARRIER);
                world.setType(new Location(world, 949, 62, 455), Material.BARRIER);
                world.setType(new Location(world, 950, 62, 454), Material.BARRIER);
                world.setType(new Location(world, 948, 62, 454), Material.BARRIER);
                scheduler.runTaskLater(plugin, () -> {
                    Transformation bDisplay2 = new Transformation(new Vector3f(0, 6, 0), new AxisAngle4f(0, 0, 0, 0), new Vector3f(3, 6, 0.9f), new AxisAngle4f(0, 0, 0, 0));
                    blockDisplay.setInterpolationDelay(0);
                    blockDisplay.setInterpolationDuration(12 * 20);
                    blockDisplay.setTransformation(bDisplay2);
                    scheduler.runTaskTimer(plugin, event -> {
                        int x = 57;
                        world.setType(new Location(world, 948, x, 455), Material.AIR);
                        world.setType(new Location(world, 949, x, 455), Material.AIR);
                        world.setType(new Location(world, 950, x, 455), Material.AIR);
                        x++;
                        if(x == 62){
                            event.cancel();
                        }
                    }, 0, 20);
                }, 500);
                scheduler.runTaskLater(plugin, blockDisplay::remove, 800);
            }
            if (to.x() > 973 & from.x() <= 973 & p.getY() > 46 & p.getY() < 52 & p.getZ() < 464 & p.getZ() > 461){
                List<Integer> answer = pPDC.get(TimeBound.getKey("tutorialDungeonPuzzleOneAnswer"),PersistentDataType.LIST.integers());
                if(answer != null){
                    int numRun = 0;
                    for(Integer x : answer){
                        numRun += 1;
//                        log.info(x.toString() + "Integer from answer");
                        scheduler.runTaskLater(plugin,() -> {
                            p.playSound(p,PuzzleSounds("TutorialSoundPuzzleOne",x),1,1);
                        }, 20L * numRun);
                    }
                }
            }
        }
    }
}
