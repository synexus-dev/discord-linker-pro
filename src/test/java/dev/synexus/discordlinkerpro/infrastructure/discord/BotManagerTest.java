package dev.synexus.discordlinkerpro.infrastructure.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BotManagerTest {

    @Test
    void start_shouldBuildJdaAwaitReady_andStoreInstance() throws Exception {
        String token = "fake-token";

        JDA jda = mock(JDA.class);

        JDABuilder builder = mock(JDABuilder.class);
        when(builder.enableIntents(eq(GatewayIntent.MESSAGE_CONTENT))).thenReturn(builder);
        when(builder.setActivity(any())).thenReturn(builder);
        when(builder.build()).thenReturn(jda);

        try (MockedStatic<JDABuilder> mocked = mockStatic(JDABuilder.class)) {
            mocked.when(() -> JDABuilder.createDefault(token)).thenReturn(builder);

            BotManager manager = new BotManager();
            manager.start(token);

            assertSame(jda, manager.getJda());
            verify(jda).awaitReady();
            verify(builder).enableIntents(GatewayIntent.MESSAGE_CONTENT);
            verify(builder).setActivity(any());
            verify(builder).build();
        }
    }

    @Test
    void start_whenAwaitReadyInterrupted_shouldThrowRuntimeException() throws Exception {
        String token = "fake-token";

        JDA jda = mock(JDA.class);
        doThrow(new InterruptedException("interrupted")).when(jda).awaitReady();

        JDABuilder builder = mock(JDABuilder.class);
        when(builder.enableIntents(eq(GatewayIntent.MESSAGE_CONTENT))).thenReturn(builder);
        when(builder.setActivity(any())).thenReturn(builder);
        when(builder.build()).thenReturn(jda);

        try (MockedStatic<JDABuilder> mocked = mockStatic(JDABuilder.class)) {
            mocked.when(() -> JDABuilder.createDefault(token)).thenReturn(builder);

            BotManager manager = new BotManager();

            RuntimeException ex = assertThrows(RuntimeException.class, () -> manager.start(token));
            assertTrue(ex.getCause() instanceof InterruptedException);

            // Mesmo com exceção, ele chegou a construir o JDA e chamar awaitReady
            verify(builder).build();
            verify(jda).awaitReady();
        }
    }
}
