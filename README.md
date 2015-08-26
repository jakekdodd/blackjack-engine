# Blackjack Engine
## Using Gradle
[Gradle](https://gradle.org) is a build automation tool. A full description of Gradle's capabilities is outside our scope here.

For this project, you will use Gradle to manage dependencies, compile the project, and run the tests. The only thing you should need to run Gradle is an installation of the JDK.

To get started:

1. `cd` to the project's topmost directory
2. Build the project with:

  ```bash
  ./gradlew clean build
  ```

## To run from the command line:
```bash
./gradlew clean build
java -cp build/libs/blackjack-engine-jar-with-dependencies.jar \
com.shopstyle.blackjack.App -hand jack four -dealer king
```

If the engine isn't a terrible blackjack player, it should tell you to hit.