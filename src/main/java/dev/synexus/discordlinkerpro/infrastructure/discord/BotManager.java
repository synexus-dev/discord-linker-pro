package dev.synexus.discordlinkerpro.infrastructure.discord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.logging.Logger;

@Slf4j
@NoArgsConstructor
@Getter
public class BotManager {

    private JDA jda;
    private static final Logger logger = Logger.getLogger(BotManager.class.getName());

    public void start(String botToken) {
        try {
            jda = JDABuilder.createDefault(botToken)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.listening("The Server"))
                    .build();
            jda.awaitReady();
            logger.info("Bot Online!");
        } catch (InterruptedException e) {
            logger.severe("Error initializing Bot Manager!");
            throw new RuntimeException(e);
        }
    }
}
