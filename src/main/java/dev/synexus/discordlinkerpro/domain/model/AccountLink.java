package dev.synexus.discordlinkerpro.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountLink {

    private UUID playerUuid;
    private String discordId;
    private String discordTag;
    private LocalDateTime linkedAt;

    public boolean isRecent() {
        return linkedAt.isAfter(LocalDateTime.now().minusHours(24)); // last 24h
    }
}
