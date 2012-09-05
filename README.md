BukkitUtils [![Travis CI](https://secure.travis-ci.org/aumgn/BukkitUtils.png)](http://travis-ci.org/#!/aumgn/BukkitUtils)
=============

Personal set of utilities for Bukkit.

Download:
--------------------

You can found the latest release [here](http://maven.aumgn.fr/fr/aumgn/bukkitutils/1.0.1/bukkitutils-1.0.1.jar).

For older and development versions, [see my maven repository](http://maven.aumgn.fr/fr/aumgn/bukkitutils).

Developers :
--------------------

### Installation :

This assumes you use maven for your project.
(If you don't, obviously you like difficulty, so you'll have to 
find out by yourself how to use this library)

To use BukkitUtils, just add my maven repository and the dependency section :

``` xml
<repository>
    <id>aumgn-repo</id>
    <url>http://maven.aumgn.fr</url>
</repository>

<dependency>
    <groupId>fr.aumgn</groupId>
    <artifactId>bukkitutils</artifactId>
    <version>1.0.2</version>
</dependency>
```


BukkitUtils is an external library that must be provided at runtime.
The easiest way to do that is to add a class path entry to your plugin's MANIFEST.
With Maven you just have to configure the maven-jar-plugin as follows:

``` xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-jar-plugin</artifactId>
    <version>2.3.2</version>
    <configuration>
        <archive>
            <manifestEntries>
                <Class-Path>../lib/BukkitUtils.jar</Class-Path>
            </manifestEntries>
        </archive>
    </configuration>
</plugin>
```

Which means the user will have to provide the library at the corresponding path.
You should prefer this path, because this is the one which is used by other plugins.

Another way is to use the maven-shade-plugin to bundle BukkitUtils inside your plugin.
In this case you may want to use its relocation feature to avoid version conflicts with
other plugins using BukkitUtils.

Take a look at my plugin
[DACv2's pom](https://github.com/aumgn/DACv2/blob/3ce63e078dd9333602adcd5e3dd329203c81d5b0/pom.xml#L149-191)
to see how you can use this two solutions together with a maven profile.

### Documentation

The javadoc is available [here](http://bukkitutils.aumgn.fr/apidocs/).

### Usage

BukkitUtils include different utilities :

* localization : Localization utilities
* commands : A set of classes and annotations to write commands more easily (heavily inspired from WorldEdit commands system).
* gson: A tiny gson wrapper with convenient type adaptaters.
* playerref : A class (PlayerRef) and its associated collections to store players based on their name seamlessly.
* and a lot of other utilities class and interface like immutable vectors, directions, timer, etc ...

#### Commands

Here are some examples of how you would write commands with BukkitUtils :

``` java
public class MyCommands extends Commands {

    // ** Simplest command
    @Command(name = "test")
    public void test(CommandSender sender) {
        sender.sendMessage("Foo !");
    }

    // ** Player only command
    @Command(name = "test2")
    pubic void test2(Player sender) {
        sender.sendMessage("Position: " + new Vector(sender));
    }

    // ** Command with two arguments
    @Command(name = "test3", min = 2, max = 2)
    public void test3(CommandSender sender, CommandArg args) {
        sender.sendMessage("Arguments: " + args.get(0) " - " + args.get(1));
    }

    // ** Commands with complex arguments
    // Try to parse the first argument as a partial player name.
    // This will send a message and abort the command if no player 
    // or if more than one player is/are found.
    // If no argument is given (which is accepted since 
    // min is set to 0), the library will return back the
    // sender if it's a player or throw an exception
    // (which will send a nice error message to the user)
    // if it's not.
    @Command(name = "test4", min = 0, max = 1)
    public void test4(CommandSender sender, CommandArgs args) {
        Player player = args.getPlayer(0).valueOr(sender);
        // Note that you can also use match() instead of value()
        // which would returns a list of all matching players.
        sender.sendMessage("Using command on :"
            + player.getDisplayName());
    }

    // ** List arguments
    // Using getPlayers instead of getPlayer will returns a list.
    // For example : "au,notch" will returns a list of 
    // all players whose name matches au or notch
    @Command(name = "test5", min = 0, max = 1)
    public void test5(CommandSender sender, CommandArgs args) {
        List<Player> players = args.getPlayers(0).match(sender);
        sender.sendMessage("Using command on :");
        for (Player player : players) {
            sender.sendMessage(" - " + player.getDisplayName());
        }
    }

    // ** Commands with flags
    // Available flags must be declared in the annotation.
    // If an invalid flag is used the sender will be notified.
    @Command(name = "test6", flags = "at")
    public void test6(CommandSender sender, CommandArgs args) {
        if (args.hasFlag('a')) {
            // Do something
        } else if (args.hasFlag('t')) {
            // Do something else
        }
    }

    // ** Commands with argument flags
    // The syntax is : "/test6 -p=au -w=world"
    @Command(name = "test6", argsFlags = "pw")
    public void test6(CommandSender sender, CommandArgs args) {
        if (args.hasArgFlag('p')) {
            Player player = args.getPlayer('p').value();
            // Do something with player.
        } else if (args.hasArgFlag('w')) {
            World world = args.getWorld('w').value();
            // Do something with world.
        }
    }
}
```

In to order to register your commands you just have to use the class CommandsRegistration :

``` java
    CommandsRegistration registration =
            new CommandsRegistration(plugin, config.getLocale());
    registration.register(new MyCommands());
```

This command system is quite powerful and extensible.
You can add your own arguments parsers using the classes CommandArg and CommandArgFactory.
You'll find examples in the source code or in [my other plugins](https://github.com/aumgn/).

The main advantages of this system over others is that all commands (even nested commands)
are first citizens Bukkit commands. Which means they are declared in the plugin.yml with
their own permission, that also means that users can declare their own aliases in the bukkit.yml file !

#### Localization

Localization example :

``` java
package fr.aumgn.myplugin;

public class MyPlugin extends JavaPlugin {

    private PluginMessages messages;

    @Override
    public void onEnable() {
        Localization localization =
                new Localization(this, Locale.FRANCE);
        messages = localization.get("messages");
        getLogger().info(messages.get("plugin.status", "enabled"));
    }

    @Override
    public void onDisable() {
        getLogger().info(messages.get("plugin.status", "disabled"));
    }

    public PluginMessages getMessages() {
        return messages;
    }
}
```

fr/aumgn/myplugin/messages_en.properties:
``` properties
plugin.status={yellow}Now {0} !
```

This code will look, in this, order, for all available "messages" files in two locations
(in the jar under fr/aumgn/myplugin & in the plugin's data folder)
prefixed with the locales "_en", "_en_US", "_fr", "_fr_FR" in either ".json", ".y[a]ml" or
".properties" format.
Key, message pairs found in the latter file have priority over the ones in the previous files.

Basically the method Plugin#getMessages(String key, Object... arguments) uses the
[MessageFormat](http://docs.oracle.com/javase/6/docs/api/java/text/MessageFormat.html) class.
The only difference is the escape character which is `\` instead of `'`.

Important note: For consistency, all files as read with UTF-8 encoding.
Even the properties files for which it's not the default encoding, you have to be sure
you use the right encoding with your editor because maven will not do it for you.

#### Configuration Example

Here's how you could write using the gson wrapper :

``` java
public class MyConfig {

    private int entry1 = 1;
    private String entry2 = "string";
    private String entry3 = "BLUE";

    public int getEntry1() {
        return entry1;
    }

    public String getEntry2() {
        return entry2;
    }

    public ChatColor getEntry3() {
        try {
            return ChatColor.valueOf(entry3);
        } catch (IllegalArgumentException exc) {
            Logger.getLogger(MyPlugin.class.getName()).severe("Invalid color for entry2.");
            return ChatColor.BLUE;
        }
    }
}

public class MyPlugin extends JavaPlugin {

    private MyConfig config;

    @Override
    public void onEnable() {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        GsonLoader loader = new GsonLoader(gson, this);

        try {
            config = loader.load("config.json", MyConfig.class);
        } catch (GsonLoadException exc) {
            getLogger().log(Level.SEVERE, "Unable to load config.json", exc);
            config = new MyConfig();
        }
    }
}
```
