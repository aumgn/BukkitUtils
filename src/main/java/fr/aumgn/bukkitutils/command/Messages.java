package fr.aumgn.bukkitutils.command;

import java.util.ResourceBundle;

import static java.text.MessageFormat.format;
import static fr.aumgn.bukkitutils.util.Util.parseColorsMarkup;

public class Messages {

    private final ResourceBundle bundle;

    public Messages(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String usagePrefix() {
        return parseColorsMarkup(bundle.getString("usage-prefix"));
    }

    public String permissionMessage() {
        return parseColorsMarkup(bundle.getString("permission-message"));
    }

    public String playerOnly() {
        return parseColorsMarkup(bundle.getString("player-only"));
    }

    public String missingEndingQuote(String command) {
        return format(bundle.getString("missing-ending-quote"), command);
    }

    public String invalidFlag(String flags) {
        return format(bundle.getString("invalid-flag"), flags);
    }

    public String missingArguments(int given, int min) {
        return format(bundle.getString("missing-arguments"), given, min);
    }

    public String tooManyArguments(int given, int max) {
        return format(bundle.getString("too-many-arguments"), given, max);
    }

    public String notAValidNumber(String token) {
        return format(bundle.getString("invalid-number"), token);
    }

    public String unexpectedError() {
        return bundle.getString("unexpected-error");
    }

    public String noSuchColor(String token) {
        return format(bundle.getString("no-such-color"), token);
    }

    public String noSuchWorld(String token) {
        return format(bundle.getString("no-such-world"), token);
    }

    public String noSuchPlayer(String token) {
        return format(bundle.getString("no-such-player"), token);
    }

    public String moreThanOnePlayerFound(String token) {
        return format(bundle.getString("too-many-players-found"), token);
    }

    public String noSuchMaterial(String token) {
        return format(bundle.getString("no-such-material"), token);
    }

    public String invalidMaterialAndDataFormat(String token) {
        return format(bundle.getString("invalid-itemtype-format"), token);
    }

    public String notAValidVectorComponent(String token) {
        return format(bundle.getString("not-a-valid-vector-component"), token);
    }

    public String noSuchPotionEffect(String token) {
        return format(bundle.getString("no-such-potioneffect"), token);
    }

    public String noSuchEnchant(String token) {
        return format(bundle.getString("no-such-enchantment"), token);
    }

    public String noSuchEntityType(String token) {
        return format(bundle.getString("no-such-entitytype"), token);
    }

    public String playerNeeded() {
        return bundle.getString("player-needed");
    }

    public String worldNeeded() {
        return bundle.getString("world-needed");
    }

    public String positionNeeded() {
        return bundle.getString("position-needed");
    }

    public String unknownTimeFormat(String token) {
        return format(bundle.getString("unknown-time-format"), token);
    }

    public String unknownTimePeriod(String token) {
        return format(bundle.getString("unknown-time-period"), token);
    }

    public String globUnbalancedSquareBracket(String token) {
        return format(bundle.getString("glob-unbalanced-square-bracket"), token);
    }

    public String missingPermissionForOther(String permission) {
        return format(bundle.getString("missing-permission-for-other"));
    }
}
