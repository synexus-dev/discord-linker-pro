package dev.synexus.discordlinkerpro.domain.model;

import lombok.Data;

@Data
public class SyncRule {
    private final String permissionNode;
    private final String discordRoleId;
    private final SyncType type;

    public SyncRule(String permissionNode, String discordRoleId, SyncType type) {
        this.permissionNode = permissionNode;
        this.discordRoleId = discordRoleId;
        this.type = type;
    }
}
