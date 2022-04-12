package com.quincynetwork.core.database;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Database extends YamlConfiguration {

    protected QuincyCore main;
    protected String name;
    protected File file;
    protected File folder;

    public Database(QuincyCore main, String name){
        this.name = name;
        this.main = main;
        folder = new File(QuincyCore.getInstance().getDataFolder() + File.separator);
        file = new File(folder, name);

    }

    private void checkConfig() {
        if(!folder.exists()){
            folder.mkdirs();
        }
        if(!file.exists()){
            file.getParentFile().mkdirs();
            main.saveResource(name, false);
        }
    }

    public void loadConfiguration(){
        checkConfig();
        try {
            load(file);
            main.getLogger().info("QuincyCore | Database " + "'" + name + "'" + " loaded successful!");
        }catch (InvalidConfigurationException | IOException exception){
            main.getLogger().severe("QuincyCore | Database " + "'" + name + "'" + " can't loaded successful!");
            exception.printStackTrace();
        }
    }

    public void saveConfiguration(){
        checkConfig();
        try{
            save(file);
            main.getLogger().info("QuincyCore | Database " + "'" + name + "'" + " saved successful!");
        }catch (IOException exception){
            main.getLogger().severe("QuincyCore | Database " + "'" + name + "'" + " can't saved successful!");
            exception.printStackTrace();
        }
    }

}
