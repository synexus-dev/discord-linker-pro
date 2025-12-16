package infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class ConfigManager {

    private final JavaPlugin plugin;

    private String botToken;
    private String serverId;
    private String generalChannelId;

    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPass;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setupEnvironment() {
        this.botToken = this.plugin.getConfig().getString("discord.bot-token");
        this.serverId = this.plugin.getConfig().getString("discord.server-id");
        this.generalChannelId = this.plugin.getConfig().getString("discord.channels.general");

        this.dbHost = this.plugin.getConfig().getString("database.host");
        this.dbPort = this.plugin.getConfig().getString("database.port");
        this.dbName = this.plugin.getConfig().getString("database.db-name");
        this.dbUser = this.plugin.getConfig().getString("database.user");
        this.dbPass = this.plugin.getConfig().getString("database.password");

        this.plugin.getLogger().info("Environment variables successfully loaded!");
    }

    @Override
    public String toString() {
        return "ConfigManager {\n" +
                "  plugin = " + (plugin != null ? plugin.getName() : "null") + ",\n" +
                "  botToken = " + mask(botToken) + ",\n" +
                "  serverId = '" + serverId + "',\n" +
                "  generalChannelId = '" + generalChannelId + "',\n" +
                "  database = {\n" +
                "    host = '" + dbHost + "',\n" +
                "    port = '" + dbPort + "',\n" +
                "    name = '" + dbName + "',\n" +
                "    user = '" + dbUser + "',\n" +
                "    pass = " + mask(dbPass) + "\n" +
                "  }\n" +
                "}";
    }

    private String mask(String value) {
        if (value == null || value.isEmpty()) {
            return "null";
        }
        return "****";
    }
}
