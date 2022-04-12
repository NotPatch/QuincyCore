package com.quincynetwork.core;

import com.quincynetwork.core.command.QuincyCommand;
import com.quincynetwork.core.database.DBAutoSell;
import com.quincynetwork.core.hook.VaultAPI;
import com.quincynetwork.core.manager.ConfigManager;
import com.quincynetwork.core.manager.DatabaseManager;
import com.quincynetwork.core.task.AutoSellTask;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public final class QuincyCore extends JavaPlugin {

    private static QuincyCore instance;

    private VaultAPI economyManager;

    private ConfigManager configManager;
    private DatabaseManager databaseManager;

    public static QuincyCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        economyManager = new VaultAPI(this);
        economyManager.init();

        saveDefaultConfig();

        databaseManager = new DatabaseManager(this);
        databaseManager.loadDatabases();

        configManager = new ConfigManager(this);
        configManager.loadConfigs();

        registerCommands();
        registerListeners();

        new AutoSellTask().runTaskTimer(this, 20*60, 20*30);

    }

    @Override
    public void onDisable() {
        databaseManager.saveDatabases();
        configManager.saveConfigs();
    }

    public VaultAPI getEconomyManager() {
        return economyManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DBAutoSell getAutoSellData() { return databaseManager.getAutoSell(); }

    public void registerCommands() {
        String packageName = getClass().getPackage().getName();

        for (Class<? extends QuincyCommand> clazz : new Reflections(packageName + ".command").getSubTypesOf(QuincyCommand.class)) {
            try {
                QuincyCommand quincyCommand = clazz.getDeclaredConstructor().newInstance();
                getCommand(quincyCommand.getCommandInfo().name()).setExecutor(quincyCommand);
            } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerListeners(){
        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName + ".listener").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            }catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

}
