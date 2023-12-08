# ClientInteractAPI:

<img src="https://github.com/zwanter/ClientInteractAPI/blob/master/images/image1.png?raw=true">

## Maven:
### Repository:
```xml
<repository>
    <id>ClientInteractAPI-mvn-repo</id>
    <url>https://raw.github.com/zwanter/ClientInteractAPI/mvn-repo/</url>
</repository>
```
### Dependency:
```xml
<dependency>
    <groupId>ru.zwanter</groupId>
    <artifactId>ClientInteractAPI</artifactId>
    <version>0.0.5</version>
    <scope>provided</scope>
</dependency>
```
## Plugin:
### plugin.yml:
```yml
name: YourNamePlugin
version: 'your_version'
main: your.package.YourMain
api-version: 'your-version'
authors: [your]
depend:
  - ClientInteractAPI
```

## Example:
### Key registration:
#### Registration of keys is necessary in order for the mod to register clicks on them:
```java
public final class YourMainClass extends JavaPlugin {
    @Override
    public void onEnable() {
        //RegisterKeys.registerKeyKeyboard(int...);
        RegisterKeys.registerKeyKeyboard(KeyEvent.VK_J);
        //RegisterKeys.registerKeyMouse(int...);
        RegisterKeys.registerKeyMouse(MouseKeys.BUTTON4, MouseKeys.BUTTON5);
    }
}
```
##
## Events:
### Tracking the player's entry with the mod:
```java
public class YourListener implements Listener {
    @EventHandler
    public void onJoin(ModPacketEvent event) {
        Player player = event.getPlayer();
        //We check whether the player has an Op and if not, we cancel the sending of the package packet with the registered keys
        if (!(player.isOp())) {
            event.setCancelled(true);
        }
    }
}
```
### Key press tracking:

```java

public class YourListener implements Listener {
    @EventHandler
    public void onKeyboard(KeyboardEvent event) {
        //We check whether the player is in the inventory
        if (event.getScreen() == Screen.CONTAINER_SCREEN) {
           return;
        }
        if (event.getKey() == KeyEvent.VK_J) {
            //We display a message about the pressed key, receiving the contents of the key using KeyEvent.getKeyText(key)
            event.getPlayer().sendMessage("You pressed key: " + KeyEvent.getKeyText(event.getKey()));
        }
        //We get it using "event.getMods()" an additional pressed key and check that the pressed key is "CTRL"
        if (event.getModifiers() == KeyEvent.CTRL_MASK && event.getKey() == KeyEvent.VK_J) {
            event.getPlayer().sendMessage("You have pressed a keyboard shortcut: " +
                    KeyEvent.getKeyText(event.getKey()) +
                    " + " +
                    KeyEvent.getKeyText(event.getModifiers()));
        }
    }
}
```
### Mouse press tracking:

```java

import ru.zwanter.clientinteractapi.data.version.MinecraftVersion;

public class YourListener implements Listener {
    @EventHandler
    public void onMouse(MouseButtonEvent event) {
        if (event.getVersion() == MinecraftVersion.FABRIC) {
            if (event.getKey() == MouseKeys.BUTTON5) {
                event.getPlayer().sendMessage("You pressed mouse key: " + MouseKeys.getButtonText(event.getKey()))
            }
        }
        if (event.getVersion() == MinecraftVersion.FORGE) {
            if (event.getKey() == MouseKeys.BUTTON4) {
                event.getPlayer().sendMessage("You pressed mouse key: " + MouseKeys.getButtonText(event.getKey()));
            }
        }
    }
}
```