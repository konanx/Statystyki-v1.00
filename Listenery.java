package pl.iboxx.statystyki;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;


public class Listenery implements Listener {


    public static HashMap<Player, Integer> wykopanebloki = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> wykopanediaxy = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> postawionebloki = new HashMap<Player, Integer>();
    public static HashMap<Player, Long> czasonline = new HashMap<Player, Long>();
    public static HashMap<Player, Long> czaswbicia = new HashMap<Player, Long>();
    public static HashMap<Player, Integer> smierci = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> kille = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> kilometry = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> kordyx = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> kordyz = new HashMap<Player, Integer>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Main.getInstance().getConfig().get(((e.getPlayer().getName()) + ".wykopanebloki")) != null) {
            //int wykopaneblokii = Main.getInstance().getConfig().getInt(e.getPlayer().getName());
            wykopanebloki.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".wykopanebloki")));
            wykopanediaxy.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".wykopanediaxy")));
            postawionebloki.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".postawionebloki")));
            czasonline.put(e.getPlayer(), Main.getInstance().getConfig().getLong((e.getPlayer().getName() + ".czasonline")));
            smierci.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".smierci")));
            kilometry.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".kilometry")));
            kille.put(e.getPlayer(), Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".kille")));


            //LocalTime sr = LocalTime.of(Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".czasonline.godziny")),Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".czasonline.minuty")),Main.getInstance().getConfig().getInt((e.getPlayer().getName() + ".czasonline.sekundy")));

        } else {
            wykopanebloki.put(e.getPlayer(), 0);
            wykopanediaxy.put(e.getPlayer(), 0);
            postawionebloki.put(e.getPlayer(), 0);
            smierci.put(e.getPlayer(), 0);
            kille.put(e.getPlayer(), 0);
            kilometry.put(e.getPlayer(), 0);
            czasonline.put(e.getPlayer(), System.currentTimeMillis() - System.currentTimeMillis());
        }
        czaswbicia.put(e.getPlayer(), System.currentTimeMillis());
        kordyx.put(e.getPlayer(), 0);
        kordyz.put(e.getPlayer(), 0);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".wykopanebloki"), wykopanebloki.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".wykopanediaxy"), wykopanediaxy.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".postawionebloki"), postawionebloki.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".smierci"), smierci.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".kille"), kille.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".kilometry"), kilometry.get(e.getPlayer()));
        Main.getInstance().getConfig().set((e.getPlayer().getName() + ".przebytekilometry"), kilometry.get(e.getPlayer()));

        Main.getInstance().getConfig().set(e.getPlayer().getName() + ".czasonline", System.currentTimeMillis() - czaswbicia.get(e.getPlayer()) + czasonline.get(e.getPlayer()));
        Main.getInstance().saveConfig();


        //czasonline.put(e.getPlayer(), czasonline.get(e.getPlayer()).plusMinutes(LocalTime.of(0)));
        //czasonline.put(e.getPlayer(), czasonline.get(e.getPlayer() + (LocalTime.now() - godzinawbicia.get(e.getPlayer()))));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        wykopanebloki.put(e.getPlayer(), wykopanebloki.get(e.getPlayer()) + 1);
        if (e.getBlock().getType() == Material.DIAMOND_ORE) {
            wykopanediaxy.put(e.getPlayer(), wykopanediaxy.get(e.getPlayer()) + 1);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        postawionebloki.put(e.getPlayer(), postawionebloki.get(e.getPlayer()) + 1);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        smierci.put(e.getEntity(), smierci.get(e.getEntity()) + 1);
        if(e.getEntity().getKiller() instanceof Player){
            kille.put(e.getEntity().getKiller(), kille.get(e.getEntity().getKiller()) + 1);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){

        if(e.getPlayer().getLocation().getBlockX() != kordyx.get(e.getPlayer()) || e.getPlayer().getLocation().getBlockZ() != kordyz.get(e.getPlayer())){
            kilometry.put(e.getPlayer(), kilometry.get(e.getPlayer()) + 1);
            kordyx.put(e.getPlayer(), e.getPlayer().getLocation().getBlockX());
            kordyz.put(e.getPlayer(), e.getPlayer().getLocation().getBlockZ());
        }
    }
}
