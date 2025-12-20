package dev.synexus.discordlinkerpro.domain.model;

import lombok.Data;
import org.bukkit.entity.Player;

import java.util.Optional;

@Data
public class SynexusPlayer {
    private final Player bukkitPlayer;
    private AccountLink activeLink;
    private boolean isDirty;

    public SynexusPlayer(Player player) {
        this.bukkitPlayer = player;
    }

    public void setLink(AccountLink link) {
        this.activeLink = link;
        this.isDirty = true;
    }

    public boolean isLinked() {
        return activeLink != null;
    }

    public Optional<AccountLink> getLink() {
        return Optional.ofNullable(activeLink);
    }
}