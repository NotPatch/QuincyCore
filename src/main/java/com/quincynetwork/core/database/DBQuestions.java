package com.quincynetwork.core.database;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.ChatColor;

public class DBQuestions extends Database{

    public DBQuestions(QuincyCore main) {
        super(main, "hawk/questions.yml");
    }

    public String getMessage(String section){
        String reply = getString("questions." + section + ".message");
        reply = ChatColor.translateAlternateColorCodes('&', reply);
        return reply;
    }

}
