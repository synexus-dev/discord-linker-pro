package application;

import infrastructure.config.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class App extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigManager configManager = new ConfigManager(this);
        configManager.setupEnvironment();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
