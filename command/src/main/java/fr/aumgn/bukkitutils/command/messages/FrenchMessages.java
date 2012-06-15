package fr.aumgn.bukkitutils.command.messages;

import org.bukkit.ChatColor;

import static java.lang.String.format;

public class FrenchMessages extends Messages {

    @Override
    public String usagePrefix() {
        return ChatColor.GREEN + "Utilisation : " + ChatColor.YELLOW + "/<command> ";
    }

    @Override
    public String permissionMessage() {
        return ChatColor.RED + "Vous n'avez pas la permission d'exécuter cette commande.";
    }

    @Override
    public String playerOnly() {
        return ChatColor.RED + "Cette commande ne peut être utilisée qu'en tant que joueur.";
    }

    @Override
    public String missingEndingQuote(String command) {
        return format("Guillemet fermante manquante : %s.", command);
    }

    @Override
    public String invalidFlag(String flags) {
        return format(ChatColor.RED + "Flag(s) invalide(s) : %s.", flags);
    }

    @Override
    public String missingArguments(int given, int min) {
        return format(ChatColor.RED + "Argument(s) manquant(s) (%d / %d min).", given, min);
    }

    @Override
    public String tooManyArguments(int given, int max) {
        return format(ChatColor.RED + "Argument(s) en trop (%d / %d max).", given, max);
    }

    @Override
    public String notAValidNumber(String token) {
        return format("Nombre invalide : %s.", token);
    }

    @Override
    public String unexpectedError() {
        return "Une erreur innatendue est apparue durant l'execution de le commande.";
    }

    @Override
    public String noSuchColor(String token) {
        return format("La couleur %s n'existe pas.", token);
    }

    @Override
    public String noSuchWorld(String token) {
        return format("Le monde (%s) n'existe pas.", token);
    }

    @Override
    public String moreThanOneWorldFound(String token) {
        return format("Plus d'un monde trouvé pour %s", token);
    }

    @Override
    public String noSuchPlayer(String token) {
        return format("Le joueur (%s) n'existe pas.", token);
    }

    @Override
    public String moreThanOnePlayerFound(String token) {
        return format("Plus d'un joueur trouvé pour %s", token);
    }

    @Override
    public String noSuchMaterial(String token) {
        return format("Le materiel (%s) n'existe pas.", token);
    }

    @Override
    public String invalidMaterialAndDataFormat(String token) {
        return format("Le format de %s est invalide <materiel:data>.", token);
    }

    @Override
    public String notAValidVectorComponent(String token) {
        return format("Composant de vecteur invalide : %s.", token);
    }

    @Override
    public String noSuchPotionEffect(String token) {
        return format("L'effet de potion (%s) n'existe pas.", token);
    }

    @Override
    public String noSuchEnchant(String token) {
        return format("L'enchantement (%s) n'existe pas.", token);
    }

    @Override
    public String noSuchEntityType(String token) {
        return format("L'entitée (%s) n'existe pas.", token);
    }

    @Override
    public String playerNeeded() {
        return "Vous devez specifier un joueur.";
    }

    @Override
    public String worldNeeded() {
        return "Vous devez specifier un monde.";
    }

    @Override
    public String positionNeeded() {
        return "Vous devez specifier une position.";
    }

    public String unknownTimeFormat(String token) {
        return format("Format de temps inconnu %s.", token);
    }

    public String unknownTimePeriod(String token) {
        return format("Période de la journée inconnue %s (am ou pm attendu).", token);
    }

    public String globUnbalancedSquareBracket(String token) {
        return format("Il manque un crochet fermant dans : %s", token);
    }

    public String globaUnbalancedCharRange(String token) {
        return format("L'intervalle de caractère est invalide dans : %s", token);
    }

    public String missingPermissionForOther(String permission) {
        return format("Vous n'avez pas la permission d'utilisez cette commande pour les autres.");
    }
}
