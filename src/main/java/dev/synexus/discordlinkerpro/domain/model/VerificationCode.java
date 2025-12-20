package dev.synexus.discordlinkerpro.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VerificationCode {

    private String code;
    private UUID playerUuid;
    private LocalDateTime expiredAt;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredAt);
    }
}
