package pl.iboxx.statystyki;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalTime;


public class Main extends JavaPlugin{

    private static Main instance;

    @Override
    public void onEnable(){
        saveConfig();
        instance = this;
        System.out.println("§8» §aStatystyki konanx v1.00 zaladowane");
        Bukkit.getServer().getPluginManager().registerEvents(new Listenery(), this);
        getCommand("Statystyki").setExecutor(new Komendy());
    }


    @Override
    public void onDisable(){
        for(Player r : Bukkit.getOnlinePlayers()){
            getConfig().set((r.getName() + ".wykopanebloki"), Listenery.wykopanebloki.get(r));
            getConfig().set((r.getName() + ".wykopanediaxy"), Listenery.wykopanediaxy.get(r));
            getConfig().set((r.getName() + ".postawionebloki"), Listenery.postawionebloki.get(r));
            getConfig().set((r.getName() + ".smierci"), Listenery.smierci.get(r));
            getConfig().set((r.getName() + ".kille"), Listenery.kille.get(r));
            getConfig().set((r.getName() + ".kilometry"), Listenery.kilometry.get(r));
            getConfig().set((r.getName() + ".czasonline"), System.currentTimeMillis() - Listenery.czaswbicia.get(r.getPlayer()) + Listenery.czasonline.get(r));
        }
        saveConfig();
    }

    public static Main getInstance() {
        return instance;
    }

}
