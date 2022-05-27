package me.jifeng;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.jifeng.Feng_HealthMessages.main;

public class Cmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args[0].equals("reload")){
            if(sender.hasPermission("fenghealth.reload") || sender.isOp()){
                main.reload();
                sender.sendMessage("插件重载成功！");
            }
        }
        return false;
    }
}
