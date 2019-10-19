package pl.iboxx.statystyki;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Komendy implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("Statystyki")){
            sender.sendMessage("");
            sender.sendMessage(" §8» §7Statystyki gracza: §6" + sender.getName());
            sender.sendMessage(" §8» §7Wykopane bloki: §2" + Listenery.wykopanebloki.get(sender));
            sender.sendMessage(" §8» §7Wykopane diaxy: §2" + Listenery.wykopanediaxy.get(sender));
            sender.sendMessage(" §8» §7Postawione bloki: §2" + Listenery.postawionebloki.get(sender));
            //sender.sendMessage("§8» §7Czas online: §2" + (Listenery.czasonline.get(sender) + System.currentTimeMillis() - Listenery.czaswbicia.get(sender)));
            long x = (Listenery.czasonline.get(sender) + System.currentTimeMillis() - Listenery.czaswbicia.get(sender));
            String zer = "";
            String format = "";
            int godziny = 0;
            int minuty = 0;
            int sekundy = 0;
            long wyjsciowa = x;
            while(wyjsciowa >= 3600000){
                godziny++;
                wyjsciowa-= 3600000;
            }
            while(wyjsciowa >= 60000){
                minuty++;
                wyjsciowa-= 60000;
            }
            while(wyjsciowa >= 1000){
                sekundy++;
                wyjsciowa-= 1000;
            }
            if(godziny >= 1){
                format += godziny + " godzin, ";
            }
            if(minuty >= 1){
                format += minuty + " minut, ";
            }
            if(minuty >= 1){
                format += sekundy + " sekund";
            }
            sender.sendMessage(" §8» §7Czas online: §2" + format);
            sender.sendMessage(" §8» §7Śmierci: §2" + Listenery.smierci.get(sender));
            sender.sendMessage(" §8» §7Zabójstwa: §2" + Listenery.kille.get(sender));
            sender.sendMessage(" §8» §7Przebyte kilometry: §2" + Listenery.kilometry.get(sender) * 0.001);
            sender.sendMessage("");

        }
        return false;
    }
}
