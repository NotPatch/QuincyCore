package com.quincynetwork.core.listener;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!QuincyCore.getInstance().getDatabaseManager().getAutoSell().hasData(p)){
            QuincyCore.getInstance().getDatabaseManager().getAutoSell().createData(p);
        }
    }

}
