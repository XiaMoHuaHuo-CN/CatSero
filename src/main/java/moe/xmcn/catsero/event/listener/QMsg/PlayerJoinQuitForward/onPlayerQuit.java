package moe.xmcn.catsero.event.listener.QMsg.PlayerJoinQuitForward;

import me.dreamvoid.miraimc.api.MiraiBot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.NoSuchElementException;

public class onPlayerQuit implements Listener {

    Plugin plugin = moe.xmcn.catsero.Main.getPlugin(moe.xmcn.catsero.Main.class);
    File usc = new File(plugin.getDataFolder(), "usesconfig.yml");
    FileConfiguration usesconfig = YamlConfiguration.loadConfiguration(usc);

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent plqev) {
        if (usesconfig.getBoolean("qmsg.send-player-join-quit.enabled")) {
            Long bot = Long.valueOf(plugin.getConfig().getString("general.bot"));
            Long group = Long.valueOf(plugin.getConfig().getString("general.group"));

            String plqname = plqev.getPlayer().getName();
            String quitmsg = usesconfig.getString("qmsg.send-player-join-quit.format.quit");
            quitmsg = quitmsg.replace("%player%", plqname);
            try {
                MiraiBot.getBot(bot).getGroup(group).sendMessageMirai(quitmsg);
            } catch (NoSuchElementException nse) {
                System.out.println("发送消息时发生异常:\n" + nse);
            }
        }
    }

}