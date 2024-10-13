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
            getLogger().info("Используется предмет: " + requiredTool.name());
        } else {
            getLogger().warning("Предмет \"" + toolName + "\" не найден! Проверьте название предмета.");
        }
        if (Objects.equals(soundPlay, "true")){
        try {
            successSound = Sound.valueOf(soundName);
            getLogger().info("Используется звук: " + successSound.name());
            getLogger().info("Установлено значение true у sound");
        } catch (IllegalArgumentException e) {
            getLogger().warning("Звук " + soundName + " не найден! Проверьте название звука.");
            successSound = null;
            }
        } if(Objects.equals(soundPlay, "false")) {
            getLogger().info("Установлено значение false у sound, звуки не будут воспроизводится");
        }


    }



    public static Astralcraft getInstance() {
        return instans;
    }

    public Material getRequiredTool() {
        return requiredTool;
    }

    // Метод для получения текущего звука
    public Sound getSuccessSound() {
        return successSound;
    }

    // Метод для обновления инструмента после перезагрузки конфига
    public void setRequiredTool(Material tool) {
        this.requiredTool = tool;
    }

    // Метод для обновления звука после перезагрузки конфига
    public void setSuccessSound(Sound sound) {
        this.successSound = sound;
    }
}