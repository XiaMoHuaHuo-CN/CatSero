package moe.xmcn.catsero.events.listeners.QMsg.ChatForward

import me.dreamvoid.miraimc.api.MiraiBot
import moe.xmcn.catsero.utils.Config
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class OnGameChat : Listener {

    @EventHandler
    fun onAsyncGameChat(event: AsyncPlayerChatEvent) {

        if (Config.UsesConfig.getBoolean("qmsg.forward-chat.enabled") && Config.UsesConfig.getBoolean("qmsg.chat-forward.async")) {
            val playermessage = event.message
            val playername = event.player.displayName
            var message = Config.UsesConfig.getString("qmsg.forward-chat.format.to-qq")
            message = message.replace("%player%", playername.toString())
                .replace("%message%", playermessage)
            message = Config.tryToPAPI(event.player, message)

            if (Config.UsesConfig.getBoolean("qmsg.forward-chat.clean-colorcode")) {
                message = message.replace("§1", "")
                    .replace("§2", "")
                    .replace("§3", "")
                    .replace("§4", "")
                    .replace("§5", "")
                    .replace("§6", "")
                    .replace("§7", "")
                    .replace("§8", "")
                    .replace("§9", "")
                    .replace("§0", "")
                    .replace("§a", "")
                    .replace("§b", "")
                    .replace("§c", "")
                    .replace("§d", "")
                    .replace("§e", "")
                    .replace("§f", "")
                    .replace("§k", "")
                    .replace("§l", "")
                    .replace("§m", "")
                    .replace("§n", "")
                    .replace("§o", "")
                    .replace("§r", "")

                val firstzf = message.first()
                if (Config.UsesConfig.getBoolean("qmsg.forward-chat.prefix.enabled") && firstzf.toString() == Config.UsesConfig.getString(
                        "qmsg.forward-chat.prefix.format.to-qq"
                    )
                ) {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message)
                    } catch (nse: NoSuchElementException) {
                        println(
                            Config.getMsgByMsID("general.send-message-qq-error")
                                .replace("%error%", nse.toString() + nse.stackTrace)
                        )
                    }
                } else {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message)
                    } catch (nse: NoSuchElementException) {
                        println(
                            Config.getMsgByMsID("general.send-message-qq-error")
                                .replace("%error%", nse.toString() + nse.stackTrace)
                        )
                    }
                }
            } else {
                val firstzf = message.first()
                if (Config.UsesConfig.getBoolean("qmsg.forward-chat.prefix.enabled") && firstzf.toString() == Config.UsesConfig.getString(
                        "qmsg.forward-chat.prefix.format.to-qq"
                    )
                ) {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message)
                    } catch (nse: NoSuchElementException) {
                        println(
                            Config.getMsgByMsID("general.send-message-qq-error")
                                .replace("%error%", nse.toString() + nse.stackTrace)
                        )
                    }
                } else {
                    try {
                        MiraiBot.getBot(Config.Use_Bot).getGroup(Config.Use_Group).sendMessageMirai(message)
                    } catch (nse: NoSuchElementException) {
                        println(
                            Config.getMsgByMsID("general.send-message-qq-error")
                                .replace("%error%", nse.toString() + nse.stackTrace)
                        )
                    }
                }
            }
        }
    }
}
