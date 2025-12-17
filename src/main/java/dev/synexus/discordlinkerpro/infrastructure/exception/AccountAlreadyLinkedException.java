package dev.synexus.discordlinkerpro.infrastructure.exception;

public class AccountAlreadyLinkedException extends RuntimeException {
    public AccountAlreadyLinkedException(String message) {
        super(message);
    }
}
