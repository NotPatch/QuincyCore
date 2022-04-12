package com.quincynetwork.core.database;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.entity.Player;

import java.io.IOException;

public class DBAutoSell extends Database{

    public DBAutoSell(QuincyCore main) {
        super(main, "database/DBAutoSell.yml");
    }

    public boolean getState(Player p, String material){
        return getBoolean("data." + p.getName() + "." + material);
    }

    public void toggleState(Player p, String material){
        set("data." + p.getName() + "." + material, !getState(p, material));
    }

    public void createData(Player p){
        set("data." + p.getName() + ".DIAMOND", false);
        set("data." + p.getName() + ".EMERALD", false);
        set("data." + p.getName() + ".IRON_INGOT", false);
        set("data." + p.getName() + ".GOLD_ORE", false);
        set("data." + p.getName() + ".COAL", false);
        set("data." + p.getName() + ".LAPIS", false);
        set("data." + p.getName() + ".NOTIFY", false);
        try{
            save(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public boolean getNotify(Player p){
        return getBoolean("data." + p.getName() + ".NOTIFY");
    }

    public boolean hasData(Player p){
        if(getString("data." + p.getName()) == null){
            return false;
        }
        return true;
    }

}
