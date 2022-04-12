package com.quincynetwork.core.hook;

import com.quincynetwork.core.QuincyCore;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultAPI {

    private static Economy econ = null;

    QuincyCore main;

    public VaultAPI(QuincyCore main){
        this.main = main;
    }

    public void init(){
        if(!setupEconomy()){
            main.getLogger().severe("Vault dependency can't found and plugin disabled");
            main.getServer().getPluginManager().disablePlugin(main);
        }
    }

    public void add(Player p, double amount){
        econ.depositPlayer(p, amount);
    }

    public void remove(Player p, double amount){
        if(econ.getBalance(p) >= amount){
            econ.withdrawPlayer(p, amount);
        }
    }

    private boolean setupEconomy() {
        if (main.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = main.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
