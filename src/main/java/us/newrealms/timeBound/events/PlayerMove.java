package us.newrealms.timeBound.events;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
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
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.List;

import static net.kyori.adventure.text.Component.text;
import static us.newrealms.timeBound.TimeBound.log;

public class PlayerMove extends BaseListener {
    static BukkitScheduler scheduler = Bukkit.getScheduler();

    @EventHandler
    public void playerMoveEvent(PlayerMoveEvent e){
        Player p = e.getPlayer();
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
                Title title = Title.title(text("Entering Tutorial Dungeon"), text(""));
                p.showTitle(title);
            }
            //Tutorial Dungeon cave entrance
            if (to.z() < 534 & from.z() >= 534 & p.getY() > 62 & p.getY() < 69 & p.getX() < 951 & p.getX() > 947) {
                Transformation bDisplay = new Transformation(new Vector3f(0, 0, 0), new AxisAngle4f(0, 0, 0, 0), new Vector3f(3, 6, 0.9f), new AxisAngle4f(0, 0, 0, 0));
                Shulker doorShulker = world.spawn(new Location(world, 949, 57, 454), Shulker.class, entity -> {
                    entity.setAI(false);
                    entity.setSilent(true);
                    entity.setInvisible(true);
                    entity.setInvulnerable(true);
                    AttributeInstance scale = entity.getAttribute(Attribute.SCALE);
                    if (scale != null) {
                        scale.setBaseValue(3);
                    }
                });
                BlockDisplay blockDisplay = world.spawn(new Location(world, 948, 57, 455), BlockDisplay.class, entity -> {
                    entity.setBlock(Material.NETHERITE_BLOCK.createBlockData());
                    entity.setBillboard(Display.Billboard.FIXED);
                    entity.setTransformation(bDisplay);
                    entity.addPassenger(doorShulker);
                });
                doorShulker.teleportAsync(new Location(world, 949, 57, 454));
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
                        world.setType(new Location(world, 948, x, 455), Material.BARRIER);
                        world.setType(new Location(world, 949, x, 455), Material.BARRIER);
                        world.setType(new Location(world, 950, x, 455), Material.BARRIER);
                        x++;
                        if(x == 62){
                            event.cancel();
                        }
                    }, 0, 20);
                }, 500);
                scheduler.runTaskLater(plugin, () -> {
                    blockDisplay.remove();
                    doorShulker.remove();
                }, 800);
            }
            if (to.x() > 973 & from.x() <= 973 & p.getY() > 46 & p.getY() < 52 & p.getZ() < 464 & p.getZ() > 461){
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS,1,1);
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BANJO,1,1);
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASEDRUM,1,1);
                p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BIT,1,1);
            }
        }
    }
}
