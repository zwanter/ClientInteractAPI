# ClientInteractAPI:

![alt text](https://raw.githubusercontent.com/zwanter/ClientInteractAPI/master/images/image1.png?raw=true)

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
    <version>0.0.2</version>
    <scope>provided</scope>
</dependency>
```

## Example:

### Key registration:
#### Registration of keys is necessary in order for the mod to register clicks on them:
```java
public final class YourMainClass extends JavaPlugin {
    
    @Override
    public void onEnable() {
        //RegisterKeys.registerKey(int...);
        RegisterKeys.registerKey(KeyEvent.VK_SPACE, KeyEvent.VK_G, KeyEvent.CTRL_MASK);

    }
}
```
##
## Events:
### Tracking the player's entry with the mod:
```java
public class YourListener implements Listener {
    
        @EventHandler
        public void onJoin(JoinModifiedPlayerEvent event) {
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
    public void onKeyClick(PressKeyboardEvent event) {
        //We get the key and verify that the pressed key is this "J"
        if (event.getKey() == KeyEvent.VK_J) {
            //We display a message about the pressed key, receiving the contents of the key using KeyEvent.getKeyText(key)
            event.getPlayer().sendMessage("You pressed key: " + KeyEvent.getKeyText(event.getKey()));
        }
        //We get it using "event.getMods()" an additional pressed key and check that the pressed key is "CTRL"
        if (event.getMods() == KeyEvent.CTRL_MASK && event.getKey() == KeyEvent.VK_J) {
            event.getPlayer().sendMessage("You have pressed a keyboard shortcut: " +
                    KeyEvent.getKeyText(event.getKey()) +
                    " + " +
                    KeyEvent.getKeyText(event.getMods()));
        }
    }
}
```
