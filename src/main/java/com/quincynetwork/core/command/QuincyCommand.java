package com.quincynetwork.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class QuincyCommand implements CommandExecutor {

    private final CommandInfo commandInfo;

    public QuincyCommand(){
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "");
    }

    public CommandInfo getCommandInfo(){
        return commandInfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(!commandInfo.permission().isEmpty()){
                if(!sender.hasPermission(commandInfo.permission())){
                    sender.sendMessage("§7ᄽ§fQuincyNW§7ᄿ §cBunu yapmak için yeterli yetkin yok");
                    return true;
                }
            }
            execute((Player) sender, args);
        }
        execute(sender, args);
        return false;
    }

    public void execute(Player p, String[] args) {}
    public void execute(CommandSender sender, String[] args) {}
}
