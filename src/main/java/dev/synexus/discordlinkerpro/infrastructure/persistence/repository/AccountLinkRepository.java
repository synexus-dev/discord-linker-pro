package dev.synexus.discordlinkerpro.infrastructure.persistence.repository;

import dev.synexus.discordlinkerpro.domain.model.AccountLink;
import dev.synexus.discordlinkerpro.domain.repository.IAccountLinkRepository;
import dev.synexus.discordlinkerpro.infrastructure.persistence.DatabaseManager;

import java.sql.*;
import java.util.logging.Logger;

public class AccountLinkRepository implements IAccountLinkRepository {

    private final DatabaseManager databaseManager;
    private static final Logger logger = Logger.getLogger(AccountLinkRepository.class.getName());

    public AccountLinkRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.createSynexusLinksTable();
    }

    public void createSynexusLinksTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS synexus_links (
                    player_uuid VARCHAR(36) NOT NULL PRIMARY KEY,
                    discord_id VARCHAR(100) NULL,
                    discord_tag VARCHAR(100) NULL,
                    linked_at TIMESTAMP NOT NULL
                )
                """;
        try(Connection conn = databaseManager.getConnection()) {
            Statement st = conn.createStatement();
            st.execute(sql);
            logger.info("Table synexus_links created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(AccountLink accountLink) {
        String sql = "INSERT INTO synexus_links(player_uuid, discord_id, discord_tag, linked_at) " +
                "VALUES (?,?,?,?)";

        try(Connection conn = databaseManager.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString( 1,accountLink.getPlayerUuid().toString());
            st.setString(2, accountLink.getDiscordId());
            st.setString(3, accountLink.getDiscordTag());
            st.setTimestamp(4, Timestamp.valueOf(accountLink.getLinkedAt()));
            st.executeQuery();
            logger.info("Account linked: " + accountLink.getPlayerUuid());
        } catch (SQLException e) {
            logger.severe("Account does not linked!\nError Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
