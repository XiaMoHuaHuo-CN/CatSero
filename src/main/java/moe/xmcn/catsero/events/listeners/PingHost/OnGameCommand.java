package moe.xmcn.catsero.events.listeners.PingHost;

import moe.xmcn.catsero.utils.Config;
import moe.xmcn.catsero.utils.Punycode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.net.UnknownHostException;
import java.util.Objects;

public class OnGameCommand {

    public static boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args[0].equalsIgnoreCase("ping") && Config.UsesConfig.getBoolean("pinghost.enabled")) {
            if (Config.UsesConfig.getBoolean("pinghost.op-only")) {
                if (sender.hasPermission("catsero.admin")) {
                    if (args.length == 2) {
                        try {
                            sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.doing"))));
                            String result = Utils.PingHostUtils(args[1]);
                            if (Objects.equals(result, "Error")) {
                                sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.error"))));
                            } else {
                                long flag = Long.parseLong(result);
                                String message = Config.tryToPAPI(sender, Config.getMsgByMsID("minecraft.pinghost.success")
                                        .replace("%address_original%", args[1])
                                        .replace("%address_punycode%", Punycode.encodeURL(args[1]))
                                        .replace("%withdraw%", String.valueOf(flag))
                                        .replace("%lost%", String.valueOf(4 - flag))
                                        .replace("%lost_percent%", String.valueOf((4 - flag) * 100 / 4)));
                                //sender.sendMessage(args[1] + "(" + (Punycode.encodeURL(args[1])) + ")" + " ???  Ping ???????????????\n   ????????????????????? = 4??? ????????? = " + flag + " ,?????? = " + (4 - flag) + "(" + (4 - flag) * 100 / 4 + "% ??????)");
                                sender.sendMessage(message);
                            }
                        } catch (UnknownHostException e) {
                            sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.failed"))));
                        }
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + "&c????????????????????????"));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.no-permission")));
                }
            } else if (args.length == 2) {
                try {
                    sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.doing"))));
                    String result = Utils.PingHostUtils(args[1]);
                    if (Objects.equals(result, "Error")) {
                        sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.error"))));
                    } else {
                        long flag = Long.parseLong(result);
                        String message = Config.tryToPAPI(sender, Config.getMsgByMsID("minecraft.pinghost.success")
                                .replace("%address_original%", args[1])
                                .replace("%address_punycode%", Punycode.encodeURL(args[1]))
                                .replace("%withdraw%", String.valueOf(flag))
                                .replace("%lost%", String.valueOf(4 - flag))
                                .replace("%lost_percent%", String.valueOf((4 - flag) * 100 / 4)));
                        //sender.sendMessage(args[1] + "(" + (Punycode.encodeURL(args[1])) + ")" + " ???  Ping ???????????????\n   ????????????????????? = 4??? ????????? = " + flag + " ,?????? = " + (4 - flag) + "(" + (4 - flag) * 100 / 4 + "% ??????)");
                        sender.sendMessage(message);
                    }
                } catch (UnknownHostException e) {
                    sender.sendMessage(Config.tryToPAPI(sender, ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + Config.getMsgByMsID("minecraft.pinghost.failed"))));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.Prefix_MC + "&c????????????????????????"));
            }
            return true;
        }
        return true;
    }

}
