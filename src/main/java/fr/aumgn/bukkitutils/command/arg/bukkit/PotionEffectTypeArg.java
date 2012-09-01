package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.potion.PotionEffectType;

import fr.aumgn.bukkitutils.command.CommandsMessages;
import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;

public class PotionEffectTypeArg extends CommandArg<PotionEffectType> {

    public static class Factory extends CommandArgFactory<PotionEffectTypeArg> {

        @Override
        public PotionEffectTypeArg createCommandArg(
                CommandsMessages messages, String string) {
            return new PotionEffectTypeArg(messages, string);
        }
    }

    public static class NoSuchPotionEffect extends CommandError {
        private static final long serialVersionUID = 6849291638184124428L;

        public NoSuchPotionEffect(CommandsMessages messages, String name) {
            super(messages.noSuchPotionEffect(name));
        }
    }

    public PotionEffectTypeArg(CommandsMessages messages, String string) {
        super(messages, string);
    }

    @Override
    public PotionEffectType value() {
        PotionEffectType effect = PotionEffectType.getByName(string);
        if(effect == null) {
            throw new NoSuchPotionEffect(messages, string);
        }

        return effect;
    }
}
