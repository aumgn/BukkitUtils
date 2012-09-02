package fr.aumgn.bukkitutils.command.arg;

public interface CommandArgFactory<T extends CommandArg<?>> {

    /**
     * Factory method which creates the command arg for
     * the given String.
     */
    public abstract T createCommandArg(String string);
}
