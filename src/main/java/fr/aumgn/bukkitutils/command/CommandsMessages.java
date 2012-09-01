package fr.aumgn.bukkitutils.command;

import java.text.MessageFormat;
import java.util.Map;

import fr.aumgn.bukkitutils.localization.PluginMessages;

public class CommandsMessages extends PluginMessages {

    public CommandsMessages(Map<String, MessageFormat> map) {
        super(map);
    }

    public String usagePrefix() {
        return get("usage-prefix");
    }

    public String permissionMessage() {
        return get("permission-message");
    }

    public String playerOnly() {
        return get("player-only");
    }

    public String missingEndingQuote(String command) {
        return get("missing-ending-quote", command);
    }

    public String invalidFlag(String flags) {
        return get("invalid-flag", flags);
    }

    public String missingArguments(int given, int min) {
        return get("missing-arguments", given, min);
    }

    public String tooManyArguments(int given, int max) {
        return get("too-many-arguments", given, max);
    }

    public String notAValidNumber(String token) {
        return get("invalid-number", token);
    }

    public String unexpectedError() {
        return get("unexpected-error");
    }

    public String noSuchColor(String token) {
        return get("no-such-color", token);
    }

    public String noSuchWorld(String token) {
        return get("no-such-world", token);
    }

    public String noSuchPlayer(String token) {
        return get("no-such-player", token);
    }

    public String moreThanOnePlayerFound(String token) {
        return get("too-many-players-found", token);
    }

    public String noSuchMaterial(String token) {
        return get("no-such-material", token);
    }

    public String invalidMaterialAndDataFormat(String token) {
        return get("invalid-itemtype-format", token);
    }

    public String notAValidVectorComponent(String token) {
        return get("not-a-valid-vector-component", token);
    }

    public String noSuchPotionEffect(String token) {
        return get("no-such-potioneffect", token);
    }

    public String noSuchEnchant(String token) {
        return get("no-such-enchantment", token);
    }

    public String noSuchEntityType(String token) {
        return get("no-such-entitytype", token);
    }

    public String playerNeeded() {
        return get("player-needed");
    }

    public String worldNeeded() {
        return get("world-needed");
    }

    public String positionNeeded() {
        return get("position-needed");
    }

    public String unknownTimeFormat(String token) {
        return get("unknown-time-format", token);
    }

    public String unknownTimePeriod(String token) {
        return get("unknown-time-period", token);
    }

    public String globUnbalancedSquareBracket(String token) {
        return get("glob-unbalanced-square-bracket", token);
    }

    public String missingPermissionForOther(String permission) {
        return get("missing-permission-for-other");
    }

    public String noSuchPlugin(String token) {
        return get("no-such-plugin", token);
    }
}
