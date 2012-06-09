package fr.aumgn.bukkitutils.command.messages;

import org.bukkit.ChatColor;

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
    public String missingEndingQuote() {
        return "Guillemet fermante manquante : %s.";
    }

    @Override
    public String invalidFlag() {
        return ChatColor.RED + "Flag(s) invalide(s) : %s.";
    }

    @Override
    public String missingArguments() {
        return ChatColor.RED + "Argument(s) manquant(s) (%d / %d min).";
    }

    @Override
    public String tooManyArguments() {
        return ChatColor.RED + "Argument(s) en trop (%d / %d max).";
    }

    @Override
    public String notAValidNumber() {
        return "Nombre invalide : %s.";
    }

    @Override
    public String unexpectedError() {
        return "Une erreur innatendue est apparue durant l'execution de le commande.";
    }

    @Override
    public String noSuchColor() {
        return "La couleur %s n'existe pas.";
    }

    @Override
    public String noSuchWorld() {
        return "Le monde (%s) n'existe pas.";
    }

    @Override
    public String noSuchPlayer() {
        return "Le joueur (%s) n'existe pas.";
    }

    @Override
    public String noSuchMaterial() {
        return "Le materiel (%s) n'existe pas.";
    }

    @Override
    public String invalidMaterialAndDataFormat() {
        return "Le format de %s est invalide <materiel:data>.";
    }

    @Override
    public String notAValidVectorComponent() {
        return "Composant de vecteur invalide : %s.";
    }

    @Override
    public String noSuchPotionEffect() {
        return "L'effet de potion (%s) n'existe pas.";
    }

    @Override
    public String noSuchEnchant() {
        return "L'enchantement (%s) n'existe pas.";
    }

    @Override
    public String noSuchEntityType() {
        return "L'entitée (%s) n'existe pas.";
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
}
