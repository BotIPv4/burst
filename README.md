# Burst | CommandAPI

A simple command api that is mainly made for Bukkit and will soon have JDA implementation.

# Maven

**Repository**

```xml

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

```

**Dependency**

```xml

	<dependency>
	    <groupId>com.github.VncoDev</groupId>
	    <artifactId>burst</artifactId>
	    <version>LATEST</version>
	</dependency>

```

# Gradle

**Repository**
```gradle

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

**Dependency**
```gradle

	dependencies {
	        implementation 'com.github.VncoDev:burst:LATEST'
	}

```

# Example (Bukkit)

**Command**

```java

public class ExampleCommand implements CommandClass {

    @Command(aliases = {"hello"})
    public void onHelloCommand(BukkitCommandArgs args) {
        args.getSource().sendMessage("Hi!");
    }

}


```

**CommandHandler**

```java

public class ExampleJavaPlugin extends JavaPlugin {

    private BukkitCommandHandler commandHandler;

    @Override public void onEnable() {
        commandHandler = new BukkitCommandHandler();

        commandHandler.register(new ExampleCommand());

        commandHandler.setNoPermissionMessage("&cYou do not have permissions for perform this command.");
        commandHandler.setGameOnlyMessage("&cYou cannot run this command from the console.");

        commandHandler.applyFor(getName());
    }

    @Override public void onDisable() {
        commandHandler.unregisterAll();
    }

}


```
