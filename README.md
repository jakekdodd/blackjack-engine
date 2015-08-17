# Blackjack Engine
## To run from the command line:
```bash
mvn clean package
java -cp target/blackjack-engine-1.0-SNAPSHOT-jar-with-dependencies.jar \
com.shopstyle.blackjack.App -hand jack four -dealer king
```

If the engine isn't a terrible blackjack player, it should tell you to hit.