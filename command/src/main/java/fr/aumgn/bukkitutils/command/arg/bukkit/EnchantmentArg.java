package fr.aumgn.bukkitutils.command.arg.bukkit;

import org.bukkit.enchantments.Enchantment;

import fr.aumgn.bukkitutils.command.arg.CommandArg;
import fr.aumgn.bukkitutils.command.arg.CommandArgFactory;
import fr.aumgn.bukkitutils.command.exception.CommandError;
import fr.aumgn.bukkitutils.command.messages.Messages;

public class EnchantmentArg extends CommandArg<Enchantment> {

    public static class Factory extends CommandArgFactory<Enchantment> {

        @Override
        public EnchantmentArg createCommandArg(Messages messages, String string) {
            return new EnchantmentArg(messages, string);
        }
    }

    public static class NoSuchEnchantment extends CommandError {

        private static final long serialVersionUID = -4832133406864970323L;

        public NoSuchEnchantment(Messages messages, String name) {
            super(String.format(messages.noSuchEnchant(), name));
        }
    }


    public EnchantmentArg(Messages messages, String string) {
        super(messages, string);
    }

    @Override
    public Enchantment value() {
        String name = string;
        Enchantment enchant = Enchantment.getByName(name.toUpperCase());
        if(enchant == null) {
            throw new NoSuchEnchantment(messages, name);
        }

        return enchant;
    }
}
