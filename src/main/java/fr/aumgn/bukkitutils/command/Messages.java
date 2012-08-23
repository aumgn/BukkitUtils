package fr.aumgn.bukkitutils.command;

import fr.aumgn.bukkitutils.localization.PluginMessages;

public class Messages {

    private final PluginMessages messages;

    public Messages(PluginMessages messages) {
        this.messages = messages;
    }

    public String usagePrefix() {
        return messages.get("usage-prefix");
    }

    public String permissionMessage() {
        return messages.get("permission-message");
    }

    public String playerOnly() {
        return messages.get("player-only");
    }

    public String missingEndingQuote(String command) {
        return messages.get("missing-ending-quote", command);
    }

    public String invalidFlag(String flags) {
        return messages.get("invalid-flag", flags);
    }

    public String missingArguments(int given, int min) {
        return messages.get("missing-arguments", given, min);
    }

    public String tooManyArguments(int given, int max) {
        return messages.get("too-many-arguments", given, max);
    }

    public String notAValidNumber(String token) {
        return messages.get("invalid-number", token);
    }

    public String unexpectedError() {
        return messages.get("unexpected-error");
    }

    public String noSuchColor(String token) {
        return messages.get("no-such-color", token);
    }

    public String noSuchWorld(String token) {
        return messages.get("no-such-world", token);
    }

    public String noSuchPlayer(String token) {
        return messages.get("no-such-player", token);
    }

    public String moreThanOnePlayerFound(String token) {
        return messages.get("too-many-players-found", token);
    }

    public String noSuchMaterial(String token) {
        return messages.get("no-such-material", token);
    }

    public String invalidMaterialAndDataFormat(String token) {
        return messages.get("invalid-itemtype-format", token);
    }

    public String notAValidVectorComponent(String token) {
        return messages.get("not-a-valid-vector-component", token);
    }

    public String noSuchPotionEffect(String token) {
        return messages.get("no-such-potioneffect", token);
    }

    public String noSuchEnchant(String token) {
        return messages.get("no-such-enchantment", token);
    }

    public String noSuchEntityType(String token) {
        return messages.get("no-such-entitytype", token);
    }

    public String playerNeeded() {
        return messages.get("player-needed");
    }

    public String worldNeeded() {
        return messages.get("world-needed");
    }

    public String positionNeeded() {
        return messages.get("position-needed");
    }

    public String unknownTimeFormat(String token) {
        return messages.get("unknown-time-format", token);
    }

    public String unknownTimePeriod(String token) {
        return messages.get("unknown-time-period", token);
    }

    public String globUnbalancedSquareBracket(String token) {
        return messages.get("glob-unbalanced-square-bracket", token);
    }

    public String missingPermissionForOther(String permission) {
        return messages.get("missing-permission-for-other");
    }
}
