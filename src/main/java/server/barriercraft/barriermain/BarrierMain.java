package server.barriercraft.barriermain;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public final class BarrierMain extends JavaPlugin implements Listener {
    List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("\n/===============================\n===BarrierCraft Main Plugin====\n======Создан _GoodShark_=======\n===============================");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessage(), this);
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //======================================================================================================================================
        // TTC - ПРОВЕРКА ИГРОКА НА ЧИТЫ, ОТПРАВЛЯЕТ НА 100000 БЛОКОВ ВВЕРХ СОХРАННЯЯ КООРДИАТЫ
        if (command.getName().equalsIgnoreCase("ttc")) {
            if (sender instanceof Player) {
                Player p = ((Player) sender).getPlayer();
                if (!p.hasPermission("BarrierCraft.ttc")) {
                    p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "У тебя нет разрешения чтобы использовать эту комманду!");
                    return true;
                }

                if (!(args.length == 2)) {
                    p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "Ты не указал кого проверять / сколько времени потребуется на проверку!\nПример: /ttc <Игрок / Жертва> <Время на проверку>");
                } else {
                    Location ploc = p.getLocation();
                    Location plocsave = p.getLocation();
                    String v = args[0];
                    int time = Integer.parseInt(args[1]);
                    Player vp = Bukkit.getServer().getPlayer(v);
                    Location vloc = vp.getLocation();
                    p.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "✔" + ChatColor.DARK_GREEN + "]" + ChatColor.GOLD + " Твои координаты:\nX:" + ChatColor.DARK_AQUA + ploc.getX() + ChatColor.GOLD + "\nY:" + ChatColor.DARK_AQUA + ploc.getY() + ChatColor.GOLD + "\nZ:" + ChatColor.DARK_AQUA + ploc.getZ() + ChatColor.GOLD + "\nКоординаты жертвы:" + ChatColor.RED + "\nX: " + ChatColor.YELLOW + vloc.getX() + ChatColor.RED + "\nY: " + ChatColor.YELLOW + vloc.getY() + ChatColor.RED + "\nZ: " + ChatColor.YELLOW + vloc.getZ());
                    ploc.setY(100000);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, time*20, 255));
                    p.teleport(ploc);
                    vp.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, time*20, 255));
                    vp.teleport(ploc);
                    p.setInvulnerable(true);
                    vp.setInvulnerable(true);
                    vp.sendMessage(ChatColor.RED + "======================================\n    Вы были вызваны на проверку читов\n            Игроком: " + ChatColor.BLUE + p.getDisplayName() + ChatColor.RED + "\n         Ваши координаты сохраненны\n======================================");
                    Bukkit.getScheduler ().runTaskLater (this, () -> wait(p, vp, plocsave, vloc), time*20);
                }
            } else {
                System.out.println("Эта команда взаимодействует с игроком, а ты не игрок!");
            }
        }
        //======================================================================================================================================
        // /ADMIN - АДМИН КОНСОЛЬ, БАНЫ КИКИ И ТД
        if (command.getName().equalsIgnoreCase("admin")) {
            list = new ArrayList<Player>(Bukkit.getOnlinePlayers());
            if (sender instanceof Player) {
                Player pl = ((Player) sender).getPlayer();
                if (!pl.hasPermission("BarrierCraft.con")) {
                    pl.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "У тебя нет разрешения чтобы использовать эту комманду!");
                    return true;
                }
                Player p = (Player) sender;
                int x = 9;
                int y = 0;
                FoundSize(list, x, y);
                Inventory BanList = Bukkit.createInventory(p, x, ChatColor.RED + "Консоль");
                List<ItemStack> plrheads = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    Player plr = list.get(i);
                    ItemStack plrHead = getPLRHead(plr);
                    plrheads.add(plrHead);
                    BanList.addItem(plrHead);
                }
                p.openInventory(BanList);
            }else {
                System.out.println("Эта команда взаимодействует с игроком, а ты не игрок!");
            }
            return true;
        }
        //======================================================================================================================================
        // /INVSEE - ПРОВЕРИТЬ ИНВЕНТАРЬ ИГРОКА
        if (command.getName().equalsIgnoreCase("invsee")) {

            if (sender instanceof Player) {
                Player p = ((Player) sender).getPlayer();
                if (!p.hasPermission("BarrierCraft.invsee")) {
                    p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "У тебя нет разрешения чтобы использовать эту комманду!");
                    return true;
                }

                if (!(args.length == 1)) {
                    p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "Ты не указал у кого посмотреть инвентарь!\nПример: /invee <Игрок>");
                } else {
                    String v = args[0];
                    Player vp = Bukkit.getServer().getOfflinePlayer(v).getPlayer();
                    Inventory inv = vp.getInventory();
                    p.openInventory(inv);
                }
            } else {
                System.out.println("Эта команда взаимодействует с игроком, а ты не игрок!");
            }

        }
        //======================================================================================================================================
        // /TEST - КОМАНДА ДЛЯ ТЫСТА ФУНКЦИЙ
        if (command.getName().equalsIgnoreCase("test")){
            if (sender instanceof Player){
                Player p = ((Player) sender).getPlayer();
                if (!p.hasPermission("BarrierCraft.test")){
                    p.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "X" + ChatColor.DARK_RED + "] " + ChatColor.GOLD + "У тебя нет разрешения чтобы использовать эту комманду!");
                    return true;
                } else{
                    p.chat("/admin");
                }

            }else {
                System.out.println("Эта команда взаимодействует с игроком, а ты не игрок!");
            }
        }
        return true;
    }
    //======================================================================================================================================
    void wait(Player p, Player vp, Location plocsave, Location vloc) {
        vp.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + "✔" + ChatColor.DARK_GREEN + "]" + ChatColor.GOLD + " Время проверки окончилось\nНа данный момент " + ChatColor.BLUE + p.getDisplayName() + ChatColor.GOLD + " выдаёт вам вердикт\nХорошего дня!");
        p.teleport(plocsave);
        vp.teleport(vloc);
        vp.removePotionEffect(PotionEffectType.LEVITATION);
        p.removePotionEffect(PotionEffectType.LEVITATION);
        p.setInvulnerable(false);
        vp.setInvulnerable(false);
    }
    ItemStack getPLRHead(Player plr) {
        ItemStack plrhead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) plrhead.getItemMeta();
        meta.setOwningPlayer((OfflinePlayer) plr);
        meta.setDisplayName(plr.getDisplayName());
        plrhead.setItemMeta((ItemMeta) meta);
        return plrhead;
    }
    void FoundSize(List list, int x, int y){
        y = list.size();
        if(y <= 9){
            x = 9;
        } else if (y <= 18) {
            x = 18;
        }
    }

    //======================================================================================================================================
}