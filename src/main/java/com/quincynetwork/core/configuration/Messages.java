package com.quincynetwork.core.configuration;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.MissingFormatArgumentException;

public class Messages extends Config{

    public Messages(QuincyCore main) {
        super(main, "messages.yml");
    }


    public void sendMessageF(CommandSender sender, String reference, String... replacement){
        String message = getMessage(reference);
        if(message.equals("")) return;

        try {
            message = String.format(message, replacement);
        }catch (MissingFormatArgumentException e){
            e.printStackTrace();
        }
        sender.sendMessage(message);

    }

    private String getMessage(String reference){
        return ChatColor.translateAlternateColorCodes('&', getString(reference));
    }

}
