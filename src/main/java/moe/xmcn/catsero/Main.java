package moe.xmcn.catsero;

import moe.xmcn.catsero.event.command.CatSero;
import moe.xmcn.catsero.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    FileConfiguration config = getConfig();
    @Override // 加载插件
    public void onLoad() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        saveResource("usesconfig.yml", false);
        System.out.println("[CatSero] 正在加载CatSero插件");
        if (config.getBoolean("utils.allow-start-warn")) {
            System.out.println(ChatColor.YELLOW + "请确保正在使用CatSero官方的构建版本,本人只为官方版本提供支持");
        }
    }

    @Override
    public void onEnable() {

        /*
         * 注册事件
         * 所有Listener监听器的事件和CommandExecutor监听器的事件均由此注册
         */

            System.out.println("正在注册事件 -> 监听器:CommandExecutor");
            // 指令注册
            Bukkit.getPluginCommand("catsero").setExecutor(new CatSero());


            System.out.println("正在注册事件 -> 监听器:Listener");
            // PingHost功能
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.PingHost.onGroupMessage(), this);

            // ChatForward聊天转发功能
            //getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.QMsg.ChatForward.onGroupMessage(), this);

            // QBanPlayer封禁功能
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.QBanPlayer.onGroupMessage(), this);

            // WeatherInfo天气获取功能
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.WeatherInfo.onGroupMessage(), this);

            // PlayerJoinQuitForward玩家加入/退出消息->QQ功能
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.QMsg.PlayerJoinQuitForward.onPlayerJoin(), this);
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.QMsg.PlayerJoinQuitForward.onPlayerQuit(), this);

            // GroupMemberChangeMessage群成员变更消息
            //getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.GroupMemberChangeMessage.onGroupMemberAdd(), this);

            // JoinQuitMessage玩家加入/退出消息自定义
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.JoinQuitMessage.onPlayerJoin(), this);
            getServer().getPluginManager().registerEvents(new moe.xmcn.catsero.event.listener.JoinQuitMessage.onPlayerQuit(), this);

        System.out.println("CatSero插件加载成功");
        if (config.getString("utils.allow-bstats") == "true") {
            // All you have to do is adding the following two lines in your onEnable method.
            // You can find the plugin ids of your plugins on the page https://bstats.org/what-is-my-plugin-id
            int pluginId = 14767;
            new Metrics(this, pluginId);
        }

        Updater.onEnable("1.1-pre1");
    }

    @Override
    public void onDisable() {
        System.out.println("正在卸载CatSero插件");
    }

    /*
     * Plugin plugin = moe.xmcn.catsero.Main.getPlugin(moe.xmcn.catsero.Main.class);
     * File usc = new File(plugin.getDataFolder(), "usesconfig.yml");
     * FileConfiguration usesconfig = YamlConfiguration.loadConfiguration(usc);
     */
}