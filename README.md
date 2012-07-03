BukkitUtils [![Travis CI](https://secure.travis-ci.org/aumgn/BukkitUtils.png)](http://travis-ci.org/#!/aumgn/BukkitUtils)
=============

Personal set of utilities for Bukkit.

Usage
-------------

BukkitUtils is an external library that must be provided at runtime.
The easiest way to do that is to add a class path entry to your plugin's MANIFEST.
With Maven you just have to configure the the maven jar plugin as follows: 

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

Which means the user will have to provide the library at the corresponding path.

Note : An auto-download/update feature may be available in the future.

Content
-------------

BukkitUtils include different utilities : 
   - mockbukkit : A wip Bukkit test library
   - localization : Localization utilities
   - commands : A set of classes and annotations to write commands more easily (heavily inspired from WorldEdit commands system).
   - gconf: A tiny gson wrapper with convenient type adaptaters.
   - playerId : A class (PlayerId) and its associated collections to manage players based on their names.
   - and a lot of others utilities class and interface like immutable vectors, directions, timer class, etc ...

Commands Example
-------------

Here are some examples of how you would write commands with BukkitUtils : 

    public class MyCommands extends Commands {
    
        // Simplest command
        @Command(name = "test")
        public void test(CommandSender sender) {
            sender.sendMessage("Foo !");
        }
    
        // Player only command
        @Command(name = "test2")
        pubic void test2(Player sender) {
            sender.sendMessage("Position: " + new Vector(sender));
        }
    
        // Command with two arguments
        @Command(name = "test3", min = 2, max = 2)
        public void test3(CommandSender sender, CommandArg args) {
            sender.sendMessage("Arguments: " + args.get(0) " - " + args.get(1));
        }
    
        // Commands with complex arguments
        // Try to parse the first argument as a partial player name.
        // This will send a message and abort the command if no player 
        // or if more than one player is/are found.
        // If no argument is given (which is accepted since min is set to 0),
        // the library will return back the sender if it's a player
        // or throw an exception (which will send a nice error message to the user)
        // if it's not.
        @Command(name = "test4", min = 0, max = 1)
        public void test4(CommandSender sender, CommandArgs args) {
            Player player = args.getPlayer(0).value(sender);
            // Note that you can also use match() instead of value()
            // which would returns a list of all matching players.
            sender.sendMessage("Utilisation de la commande sur :" + player.getDisplayName());
        }

        // List arguments
        // Using getPlayers instead of getPlayer will returns a list.
        // For example : "au,notch" will returns a list of 
        // all players whose name matches au or notch
        @Command(name = "test5", min = 0, max = 1)
        public void test5(CommandSender sender, CommandArgs args) {
            List<Player> players = args.getPlayers(0).match(sender);
            sender.sendMessage("Utilisation de la commande sur :");
            for (Player player : players) {
                sender.sendMessage(" - " + player.getDisplayName());
            }
        }

        // Command with flags
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

        // Command with argument flags
        // The syntax is : "/test6 -p=au -w=world"
        @Command(name = "test6", argsFlags = "pw")
        public void test6(CommandSender sender, CommandArgs args) {
            if (args.hasFlag('p')) {
                Player player = args.get('p', Player.class).value();
                // Do something with player.
            } else if (args.hasFlag('w')) {
                World world = args.get('w', World.class).value();
                // Do something with world.
            }
        }
    }

In to order to register your commands you just have to use the class CommandsRegistration : 

    CommandsRegistration registration = new CommandsRegistration(plugin, Locale.getDefault());
    registration.register(new MyCommands());

This command system is quite powerful and extensible.
You can add your own arguments parsers using the classes CommandArg and CommandArgsFactory. See example in the source code.

The main adavntages of this system over others is that all commands event nested commands are first citizens Bukkit commands.
Which means they are declared in the plugin.yml wit their own permission, that also means that users can declare their own aliases in the bukkit.yml file !

Configuration Example
-------------

Here's how you could write using the gson wrapper : 

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
            GConfLoader loader = new GConfLoader(gson, this);

            try {
                config = loader.load("config.json", MyConfig.class);
            } catch (GConfLoadException exc) {
                getLogger().log(Level.SEVERe, "Unable to load config.json", exc);
                config = new MyConfig();
            }
        }
    }


