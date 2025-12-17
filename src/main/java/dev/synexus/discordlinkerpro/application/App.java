package dev.synexus.discordlinkerpro.application;

import dev.synexus.discordlinkerpro.infrastructure.config.ConfigManager;
import dev.synexus.discordlinkerpro.infrastructure.discord.BotManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class App extends JavaPlugin {

    BotManager bot = new BotManager();
    ConfigManager config = new ConfigManager(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.setupEnvironment();
        bot.start(config.getBotToken());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bot.getJda().shutdown();
    }
}
