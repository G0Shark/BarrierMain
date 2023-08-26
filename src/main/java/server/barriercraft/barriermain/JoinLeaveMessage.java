package server.barriercraft.barriermain;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class JoinLeaveMessage implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getDisplayName());
        } else{
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "\\(-_-)/" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getDisplayName());
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getDisplayName());
    }
    @EventHandler
    public void onKick(PlayerKickEvent e){
        Player p = e.getPlayer();
        e.setLeaveMessage(ChatColor.GRAY + "[" + ChatColor.RED + "X" + ChatColor.GRAY + "] " + ChatColor.GOLD + p.getDisplayName());
    }
    @EventHandler
    public void onPing(PaperServerListPingEvent e){

        Random rand = new Random();
        int upperbound = 6;
        int int_random = rand.nextInt(upperbound);
        switch (int_random){
            case 1: e.setMotd(ChatColor.GOLD + "Главное ограничение: БАРЬЕР!");
            case 2: e.setMotd(ChatColor.RED + "Не используй читы!");
            case 3: e.setMotd(ChatColor.AQUA + "Точно без модов!");
            case 4: e.setMotd(ChatColor.BLUE + "Всё ещё без корабликов!");
            case 5: e.setMotd(ChatColor.LIGHT_PURPLE + "Когда нибудь это надо будет пофиксить...");
        }

    }
}
