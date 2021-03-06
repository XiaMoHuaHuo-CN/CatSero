package moe.xmcn.catsero.events.listeners.Punycode

import me.dreamvoid.miraimc.bukkit.event.message.passive.MiraiGroupMessageEvent
import moe.xmcn.catsero.utils.Config
import moe.xmcn.catsero.utils.Punycode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class OnGroupMessage : Listener {
    @EventHandler
    fun onMiraiGroupMessageEvent(event: MiraiGroupMessageEvent) {
        if (Config.UsesConfig.getBoolean("punycode.enabled") && event.groupID == Config.Use_Group && event.botID == Config.Use_Bot) {
            val msg = event.message
            val args = msg.split(" ")
            if (args[0] == "catsero" && args[1] == "punycode") {
                if (args.size == 4 && args[3] == "urlmode") {
                    Config.sendMiraiGroupMessage(Punycode.encodeURL(args[2]))
                } else {
                    Config.sendMiraiGroupMessage(Punycode.encode(args[2]))
                }
            }
        }
    }

}