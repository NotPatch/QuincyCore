package com.quincynetwork.core.manager;

import com.quincynetwork.core.QuincyCore;
import com.quincynetwork.core.configuration.Config;
import com.quincynetwork.core.configuration.Messages;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private QuincyCore main;
    private List<Config> configs = new ArrayList<>();
    private Messages messages;

    public ConfigManager(QuincyCore main){
        this.main = main;
        configs.add(messages = new Messages(main));
    }

    public void loadConfigs(){
        for(Config config : configs){
            config.loadConfiguration();
        }
    }

    public void saveConfigs(){
        for(Config config : configs){
            config.saveConfiguration();
        }
    }

    public Messages getMessages() {
        return messages;
    }
}
