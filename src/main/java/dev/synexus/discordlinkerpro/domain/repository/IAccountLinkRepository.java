package dev.synexus.discordlinkerpro.domain.repository;

import dev.synexus.discordlinkerpro.domain.model.AccountLink;

public interface IAccountLinkRepository {

    void save(AccountLink accountLink);
}
