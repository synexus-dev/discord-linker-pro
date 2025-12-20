package dev.synexus.discordlinkerpro.domain.model;

import lombok.Data;

@Data
public class SyncRule {
    private final String permissionNode;  // ex: "synexus.vip.master"
    private final String discordRoleId;   // ex: "987654321098765432"
    private final SyncType type;          // Enum: ADD_ONLY, REMOVE_ONLY, SYNC

    public SyncRule(String permissionNode, String discordRoleId, SyncType type) {
        this.permissionNode = permissionNode;
        this.discordRoleId = discordRoleId;
        this.type = type;
    }
}
