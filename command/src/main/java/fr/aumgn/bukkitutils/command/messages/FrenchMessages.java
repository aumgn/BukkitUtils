package fr.aumgn.bukkitutils.command.messages;

import org.bukkit.ChatColor;

public class FrenchMessages extends Messages {

    public String usagePrefix() {
        return ChatColor.GREEN + "Utilisation : " + ChatColor.YELLOW + "/<command> ";
    }

    public String permissionMessage() {
        return ChatColor.RED + "Vous n'avez pas la permission d'exécuter cette commande.";
    }

    public String playerOnly() {
        return ChatColor.RED + "Cette commande ne peut être utilisée qu'en tant que joueur.";
    }

    public String invalidFlag() {
        return ChatColor.RED + "Flag(s) invalide(s) : %s.";
    }

    public String missingArguments() {
        return ChatColor.RED + "Argument(s) manquant(s) (%d / %d min).";
    }

    public String tooManyArguments() {
        return ChatColor.RED + "Argument(s) en trop (%d / %d max).";
    }

    public String notAValidNumber() {
        return "Nombre attendu pour l'argument %d.";
    }

    public String unexpectedError() {
        return "Une erreur innatendue est apparue durant l'execution de le commande.";
    }

    public String noSuchWorld() {
        return "Le monde (%s) n'existe pas.";
    }

    public String noSuchPlayer() {
        return "Le joueur (%s) n'existe pas.";
    }

    public String noSuchMaterial() {
        return "Le materiel (%s) n'existe pas.";
    }

    public String invalidMaterialAndDataFormat() {
        return "Le format de %s est invalide <materiel:data>.";
    }

}
