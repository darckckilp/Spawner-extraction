package astralcraft.mcmagic.space.astralcraft;

import astralcraft.mcmagic.space.astralcraft.commands.reloadcommand;
import astralcraft.mcmagic.space.astralcraft.events.spawner;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Astralcraft extends JavaPlugin {

    private Material requiredTool;
    private static Astralcraft instans;
    private Sound successSound;

    @Override
    public void onEnable() {
        instans = this;
        new reloadcommand();
        loadConfig();
        Bukkit.getPluginManager().registerEvents(new spawner(requiredTool, successSound), this);
    }
    public void loadConfig() {
        saveDefaultConfig();
        String toolName = getConfig().getString("item");
        String soundName = getConfig().getString("sound");
        String soundPlay = getConfig().getString("sound-play");

        assert toolName != null;
        if (Material.getMaterial(toolName) != null) {
            requiredTool = Material.getMaterial(toolName);
            getLogger().info("Using items: " + requiredTool.name());
        } else {
            getLogger().warning("items \"" + toolName + "\" not found! check items name.");
        }
        if (Objects.equals(soundPlay, "true")){
            try {
                successSound = Sound.valueOf(soundName);
                getLogger().info("Using sound: " + successSound.name());
                getLogger().info("sound set to true");
            } catch (IllegalArgumentException e) {
                getLogger().warning("sound " + soundName + " not found! check sound name.");
                successSound = null;
            }
        } if(Objects.equals(soundPlay, "false")) {
            getLogger().info("Sound is set to false, no sounds will be played");
        }
    }

    public static Astralcraft getInstance() {
        return instans;
    }
}
