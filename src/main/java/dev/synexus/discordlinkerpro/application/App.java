package dev.synexus.discordlinkerpro.application;

import dev.synexus.discordlinkerpro.domain.repository.IAccountLinkRepository;
import dev.synexus.discordlinkerpro.infrastructure.config.ConfigManager;
import dev.synexus.discordlinkerpro.infrastructure.discord.BotManager;
import dev.synexus.discordlinkerpro.infrastructure.persistence.DatabaseManager;
import dev.synexus.discordlinkerpro.infrastructure.persistence.repository.AccountLinkRepository;
import org.bukkit.plugin.java.JavaPlugin;

public final class App extends JavaPlugin {

    BotManager bot = new BotManager();
    ConfigManager config = new ConfigManager(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.setupEnvironment();
        bot.start(config.getBotToken());

        DatabaseManager db = new DatabaseManager(config.getDbHost(), config.getDbPort(), config.getDbName(),
                config.getDbUser(), config.getDbPass());
        IAccountLinkRepository accRepo = new AccountLinkRepository(db);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        bot.getJda().shutdown();
    }
}
