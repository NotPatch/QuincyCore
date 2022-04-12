package com.quincynetwork.core.listener;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    ConfigurationSection questions = QuincyCore.getInstance().getDatabaseManager().getQuestions();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String message = e.getMessage();
        Player p = e.getPlayer();


        for(String section : questions.getConfigurationSection("questions").getKeys(false)){
            if(!message.contains(section)){
                return;
            }
            for(String word : questions.getStringList("questions." + section + ".contains")){
                if(message.contains(word)){
                    p.sendMessage(QuincyCore.getInstance().getDatabaseManager().getQuestions().getMessage(section));
                    break;
                }
            }
        }
    }
}
