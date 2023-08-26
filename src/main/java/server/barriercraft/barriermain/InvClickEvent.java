package server.barriercraft.barriermain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;


import java.io.Console;
import java.util.Random;

public class InvClickEvent implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Консоль")){

            if(e.getCurrentItem().getType() == null){
                System.out.println("Null");
                return;
            }
            if(e.getWhoClicked().isOp()) {
                if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                    e.setCancelled(true);
                    ItemStack plrhead = e.getCurrentItem();
                    ItemMeta meta = plrhead.getItemMeta();
                    String plrname = meta.getDisplayName();
                    Player p = Bukkit.getServer().getPlayerExact(plrname);
                    HumanEntity click = e.getWhoClicked();
                    Player clic = Bukkit.getServer().getPlayerExact(click.getName());
                    clic.closeInventory();
                    Inventory inv = Bukkit.createInventory(p, 9, "" + plrname);
                    ItemStack greenwool = new ItemStack(Material.GREEN_WOOL);
                    ItemStack redwool = new ItemStack(Material.RED_WOOL);
                    ItemStack barier = new ItemStack(Material.BARRIER);
                    ItemMeta meta4 = greenwool.getItemMeta();
                    ItemMeta meta5 = redwool.getItemMeta();
                    ItemMeta meta6 = barier.getItemMeta();
                    meta4.setDisplayName("Кикнуть");
                    meta5.setDisplayName("Забанить");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("Нажми ПКМ для варна");
                    meta5.setLore(lore);
                    meta6.setDisplayName("Выйти");
                    greenwool.setItemMeta(meta4);
                    redwool.setItemMeta(meta5);
                    barier.setItemMeta(meta6);
                    inv.setItem(3, greenwool);
                    inv.setItem(4, redwool);
                    inv.setItem(5, barier);
                    clic.openInventory(inv);
                }
            }else{
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
            if (e.getWhoClicked().isOp()){
                String plrname = e.getView().getTitle();
                System.out.println(plrname);
                Player p = Bukkit.getServer().getPlayerExact(plrname);
                HumanEntity click = e.getWhoClicked();
                Player clic = Bukkit.getServer().getPlayerExact(click.getName());
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.kickPlayer(ChatColor.GOLD + "Вы были кикнуты администратором: " + ChatColor.DARK_AQUA + clic.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой кик вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Код кика: " + ChatColor.DARK_AQUA +  "#" + int_random);
                System.out.println("\n================================\n=========WARNING KICK==========\n" + int_random + "\n================================");
                e.setCancelled(true);
                clic.closeInventory();
            }else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.RED_WOOL)){
            if (e.getWhoClicked().isOp()){
                if (e.isRightClick()) {
                    String plrname = e.getView().getTitle();
                    Player p = Bukkit.getServer().getPlayerExact(plrname);
                    HumanEntity c = e.getWhoClicked();
                    Player a = Bukkit.getServer().getPlayerExact(c.getName());
                    p.sendMessage(ChatColor.RED + "======================================\n   Вам было выданно предупреждение!\n======================================");
                    e.setCancelled(true);
                }else {
                    String plrname = e.getView().getTitle();
                    Player p = Bukkit.getServer().getPlayerExact(plrname);
                    HumanEntity c = e.getWhoClicked();
                    Player a = Bukkit.getServer().getPlayerExact(c.getName());
                    a.closeInventory();
                    Inventory inv = Bukkit.createInventory(p, 9, p.getDisplayName());
                    ItemStack green = new ItemStack(Material.GREEN_CONCRETE);
                    ItemStack lime = new ItemStack(Material.LIME_CONCRETE);
                    ItemStack yellow = new ItemStack(Material.YELLOW_CONCRETE);
                    ItemStack orange = new ItemStack(Material.ORANGE_CONCRETE);
                    ItemStack red = new ItemStack(Material.RED_CONCRETE);
                    ItemStack bedrock = new ItemStack(Material.BEDROCK);
                    ItemStack dirt = new ItemStack(Material.GRASS_BLOCK);
                    ItemStack barier = new ItemStack(Material.BARRIER);
                    ItemMeta greenmeta = green.getItemMeta();
                    ItemMeta limemeta = lime.getItemMeta();
                    ItemMeta yellowmeta = yellow.getItemMeta();
                    ItemMeta orangemeta = orange.getItemMeta();
                    ItemMeta redmeta = red.getItemMeta();
                    ItemMeta bedrockmeta = bedrock.getItemMeta();
                    ItemMeta dirtmeta = dirt.getItemMeta();
                    ItemMeta barriermeta = barier.getItemMeta();
                    greenmeta.setDisplayName("на 10 минут");
                    limemeta.setDisplayName("на 1 час");
                    yellowmeta.setDisplayName("на 1 день");
                    orangemeta.setDisplayName("на 7 дней");
                    redmeta.setDisplayName("на 1 месяц");
                    bedrockmeta.setDisplayName("Навсегда");
                    dirtmeta.setDisplayName("на 3 минуты");
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add("Последнее предупреждение");
                    dirtmeta.setLore(lore);
                    barriermeta.setDisplayName("Выйти");
                    green.setItemMeta(greenmeta);
                    lime.setItemMeta(limemeta);
                    yellow.setItemMeta(yellowmeta);
                    orange.setItemMeta(orangemeta);
                    red.setItemMeta(redmeta);
                    bedrock.setItemMeta(bedrockmeta);
                    dirt.setItemMeta(dirtmeta);
                    barier.setItemMeta(barriermeta);
                    inv.setItem(2, green);
                    inv.setItem(3,lime);
                    inv.setItem(4,yellow);
                    inv.setItem(5,orange);
                    inv.setItem(6,red);
                    inv.setItem(1, dirt);
                    inv.setItem(7, bedrock);
                    inv.setItem(8, barier);
                    e.setCancelled(true);
                    a.openInventory(inv);
                }
            } else {
                return;
            }
        }

        if (e.getCurrentItem().getType().equals(Material.BARRIER)){
            HumanEntity click = e.getWhoClicked();
            Player clic = Bukkit.getServer().getPlayerExact(click.getName());
            clic.closeInventory();
        }

        if (e.getCurrentItem().getType().equals(Material.GREEN_CONCRETE)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Время бана 10 мин. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*10));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.LIME_CONCRETE)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Время бана 1 час. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*60));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.YELLOW_CONCRETE)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Время бана 1 день. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*60*24));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.ORANGE_CONCRETE)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Время бана 7 дней. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*60*24*7));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.BEDROCK)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "ВЫ ЗАБАНЕНЫ НАВСЕГДА. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random);
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.RED_CONCRETE)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nОбжаловать свой бан вы можете на нашем дискорд сервере!\n" + ChatColor.RED + "Время бана: 30 дней. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*60*24*30));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.GRASS_BLOCK)){
            if (e.getWhoClicked().isOp()){

                String plrname = e.getView().getTitle();
                OfflinePlayer p = Bukkit.getServer().getOfflinePlayer(plrname);
                HumanEntity c = e.getWhoClicked();
                Player a = Bukkit.getServer().getPlayerExact(c.getName());
                e.setCancelled(true);
                c.closeInventory();
                Random rand = new Random();
                int upperbound = 999999999;
                int int_random = rand.nextInt(upperbound);
                p.banPlayer(ChatColor.GOLD + "Вы были забанены администратором: " + ChatColor.DARK_AQUA + a.getDisplayName() + ChatColor.GOLD + "\nЭто последение предупреждение!\n" + ChatColor.RED + "Время бана: 3 минуты. Код бана: " + ChatColor.DARK_AQUA +  "#" + int_random, new Date(System.currentTimeMillis()+1000*60*3));
                System.out.println("\n================================\n=========WARNING BAN==========\n" + int_random + "\n================================");

            } else {
                return;
            }
        }
        if (e.getCurrentItem().getType().equals(Material.BARRIER)){
            HumanEntity click = e.getWhoClicked();
            Player clic = Bukkit.getServer().getPlayerExact(click.getName());
            clic.closeInventory();
        } else {
            return;
        }

    }
}
