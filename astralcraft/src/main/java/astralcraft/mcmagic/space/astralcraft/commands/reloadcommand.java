package astralcraft.mcmagic.space.astralcraft.commands;

import astralcraft.mcmagic.space.astralcraft.Astralcraft;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class reloadcommand extends AbstractCommand {
    public reloadcommand() {
        super("astralcraft");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.GRAY +"Reload plugins:" + " /" + label + " reload");
            return;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            if(!sender.hasPermission( "astralcraft.reload")){
                sender.sendMessage(ChatColor.DARK_RED + "you don't have permission.");
                return;
            }

            Astralcraft.getInstance().reloadConfig();
            Astralcraft.getInstance().loadConfig();
            sender.sendMessage(ChatColor.GREEN + "Plugin reloaded");
            return;
        }

        sender.sendMessage(ChatColor.GREEN + "Unknow command:" + " " + ChatColor.DARK_RED + args[0]);


    }
    @Override
    public List<String> complete(CommandSender sender, String[] args){
        if(args.length == 1) return Lists.newArrayList("reload");
        return Lists.newArrayList();
    }
}