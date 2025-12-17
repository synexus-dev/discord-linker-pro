package dev.synexus.discordlinkerpro.infrastructure.config;

import dev.synexus.discordlinkerpro.infrastructure.config.ConfigManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.junit.jupiter.api.Test;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfigManagerTest {

    @Mock
    JavaPlugin plugin;

    @Mock
    FileConfiguration fileConfig;

    @Mock
    Logger logger;

    @Test
    public void shouldEnvironmentVariables() {

        when(plugin.getConfig()).thenReturn(fileConfig);
        when(plugin.getLogger()).thenReturn(logger);

        when(fileConfig.getString("discord.bot-token")).thenReturn("TOKEN_123");
        when(fileConfig.getString("discord.server-id")).thenReturn("SERVER_1");
        when(fileConfig.getString("discord.channels.general")).thenReturn("CHANNEL_9");

        when(fileConfig.getString("database.host")).thenReturn("localhost");
        when(fileConfig.getString("database.port")).thenReturn("3306");
        when(fileConfig.getString("database.db-name")).thenReturn("mydb");
        when(fileConfig.getString("database.user")).thenReturn("user");
        when(fileConfig.getString("database.password")).thenReturn("pass");

        ConfigManager manager = new ConfigManager(plugin);

        manager.setupEnvironment();

        assertEquals("TOKEN_123", manager.getBotToken());
        assertEquals("SERVER_1", manager.getServerId());
        assertEquals("CHANNEL_9", manager.getGeneralChannelId());

        assertEquals("localhost", manager.getDbHost());
        assertEquals("3306", manager.getDbPort());
        assertEquals("mydb", manager.getDbName());
        assertEquals("user", manager.getDbUser());
        assertEquals("pass", manager.getDbPass());

        verify(logger).info("Environment variables successfully loaded!");
        verify(fileConfig).getString("discord.bot-token");
        verify(fileConfig).getString("discord.server-id");
        verify(fileConfig).getString("discord.channels.general");
        verify(fileConfig).getString("database.host");
        verify(fileConfig).getString("database.port");
        verify(fileConfig).getString("database.db-name");
        verify(fileConfig).getString("database.user");
        verify(fileConfig).getString("database.password");
        verifyNoMoreInteractions(logger);
    }
}
