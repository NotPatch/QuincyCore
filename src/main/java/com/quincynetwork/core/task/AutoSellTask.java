package com.quincynetwork.core.task;

import com.quincynetwork.core.QuincyCore;
import com.quincynetwork.core.hook.VaultAPI;
import net.brcdev.shopgui.ShopGuiPlusApi;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoSellTask extends BukkitRunnable {

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(p -> {
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "DIAMOND")){
                ItemStack diamond = new ItemStack(Material.DIAMOND, getAmount(p, new ItemStack(Material.DIAMOND)));
                if(diamond.getAmount() > 0){
                    double price = ShopGuiPlusApi.getItemStackPriceSell(new ItemStack(Material.DIAMOND));
                    double add = price * diamond.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(diamond);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(diamond.getAmount()) ,"Elmas", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "EMERALD")){
                ItemStack diamond = new ItemStack(Material.EMERALD, getAmount(p, new ItemStack(Material.EMERALD)));
                if(diamond.getAmount() > 0){
                    double price = ShopGuiPlusApi.getItemStackPriceSell(new ItemStack(Material.EMERALD));
                    double add = price * diamond.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(diamond);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(diamond.getAmount()) ,"Zümrüt", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "GOLD_INGOT")){
                ItemStack diamond = new ItemStack(Material.GOLD_INGOT, getAmount(p, new ItemStack(Material.GOLD_INGOT)));
                if(diamond.getAmount() > 0){
                    double price = ShopGuiPlusApi.getItemStackPriceSell(new ItemStack(Material.GOLD_INGOT));
                    double add = price * diamond.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(diamond);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(diamond.getAmount()) ,"Altın", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "IRON_INGOT")){
                ItemStack diamond = new ItemStack(Material.IRON_INGOT, getAmount(p, new ItemStack(Material.IRON_INGOT)));
                if(diamond.getAmount() > 0){
                    double price = ShopGuiPlusApi.getItemStackPriceSell(new ItemStack(Material.IRON_INGOT));
                    double add = price * diamond.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(diamond);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(diamond.getAmount()) ,"Demir", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "COAL")){
                ItemStack diamond = new ItemStack(Material.COAL, getAmount(p, new ItemStack(Material.COAL)));
                if(diamond.getAmount() > 0){
                    double price = ShopGuiPlusApi.getItemStackPriceSell(new ItemStack(Material.COAL));
                    double add = price * diamond.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(diamond);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(diamond.getAmount()) ,"Kömür", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
            if(QuincyCore.getInstance().getAutoSellData().getState(p, "LAPIS")){
                ItemStack lapis = new ItemStack(Material.INK_SACK);
                lapis.setDurability((short) 4);
                lapis.setAmount(getAmount(p, lapis));
                
                if(lapis.getAmount() > 0){
                    lapis.setAmount(1);
                    double price = ShopGuiPlusApi.getItemStackPriceSell(lapis);
                    lapis.setAmount(getAmount(p, lapis));

                    double add = price * lapis.getAmount();
                    QuincyCore.getInstance().getEconomyManager().add(p, add);
                    p.getInventory().removeItem(lapis);
                    if(QuincyCore.getInstance().getAutoSellData().getNotify(p)){
                        QuincyCore.getInstance().getConfigManager().getMessages().sendMessageF(p, "sell.success",String.valueOf(lapis.getAmount()) ,"Lapis", String.valueOf(add), String.valueOf(price));
                    }
                }
            }
        });
    }

    public static int getAmount(Player arg0, ItemStack arg1) {
        if (arg1 == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = arg0.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(arg1))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }
}
