package moe.xmcn.catsero.events.cmdboots;

import moe.xmcn.catsero.Updater;
import moe.xmcn.catsero.utils.Config;
import moe.xmcn.catsero.utils.HelpList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class CatSero implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length >= 1) {
            // Ping功能
            if (moe.xmcn.catsero.events.listeners.PingHost.OnGameCommand.onCommand(sender, command, label, args)) {
                // 天气获取
            } else if (moe.xmcn.catsero.events.listeners.WeatherInfo.OnGameCommand.onCommand(sender, command, label, args)) {
                // Punycode
            } else if (moe.xmcn.catsero.events.listeners.Punycode.OnGameCommand.onCommand(sender, command, label, args)) {
                // 重载配置
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("catsero.admin")) {
                    Config.reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + "&a配置文件已重载"));
                    return true;
                }
                // 检查更新
            } else if (args[0].equalsIgnoreCase("update")) {
                if (sender.hasPermission("catsero.admin")) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + "&a开始检查更新..."));
                            sender.sendMessage(Updater.startUpdateCheck(true));
                        }
                    }.runTaskAsynchronously(Config.plugin);
                    return true;
                }
                // 帮助
            } else if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(HelpList.Companion.getList("mc"));
                return true;
                // 传入了参数但是未找到方法
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.undefined-usage")));
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + "This server is running CatSero v" + ChatColor.ITALIC + Config.PluginInfo.getString("version") + ChatColor.RESET + " By " + ChatColor.ITALIC + Config.PluginInfo.getString("author")));
            return true;
        }
        return false;
    }

}
