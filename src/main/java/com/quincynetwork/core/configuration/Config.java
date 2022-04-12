package com.quincynetwork.core.configuration;

import com.quincynetwork.core.QuincyCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config extends YamlConfiguration {

    protected QuincyCore main;
    protected String name;
    protected File file;

    public Config(QuincyCore main, String name){
        this.name = name;
        this.main = main;
        file = new File(QuincyCore.getInstance().getDataFolder(), name);
    }

    private void checkConfig() {
        if(!file.exists()){
            file.getParentFile().mkdirs();
            main.saveResource(name, false);
        }
    }

    public void loadConfiguration(){
        checkConfig();
        try {
            load(file);
            main.getLogger().info("QuincyCore | Configuration " + "'" + name + "'" + " loaded successful!");
        }catch (InvalidConfigurationException | IOException exception){
            main.getLogger().severe("QuincyCore | Configuration " + "'" + name + "'" + " can't loaded successful!");
            exception.printStackTrace();
        }
    }

    public void saveConfiguration(){
        checkConfig();
        try{
            save(file);
            main.getLogger().info("QuincyCore | Configuration " + "'" + name + "'" + " saved successful!");
        }catch (IOException exception){
            main.getLogger().severe("QuincyCore | Configuration " + "'" + name + "'" + " can't saved successful!");
            exception.printStackTrace();
        }
    }

}
