# COMP2042 CrouseWork

## Table of Contents
1. [Github Respository](#github-repository)
2. [Compilation Instructions](#compilation-instructions)
3. [Implemented and Working Properly](#implemented-and-working-properly)
4. [Implemented but Not Working Properly](#implemented-but-not-working-properly)
5. [Features Not Implemented](#features-not-implemented)
6. [New Java Classes](#new-java-classes)
7. [Modified Java Classes](#modified-java-classes)
8. [Unexpected Problems](#unexpected-problems)

## GitHub Repository

- You can always find the latest version of this project at:
[link to GitHub Respository](https://github.com/TimZ21/CW2024)

## Compilation Instructions
### Prerequisites
Before you begin, ensure you have the following installed:
- **Java JDK 17**: Required to compile and run the application. Download it from [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or choose OpenJDK.
- **IntelliJ IDEA**: Required to run the project in IntelliJ IDEA.[Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- **Maven**: Needed for building the application. IntelliJ IDEA includes a bundled Maven. However, if you prefer, you can configure it to use a specific Maven version installed on your system.from [Maven's official site](https://maven.apache.org/download.cgi) and ensure it's added to your PATH.
- **Git**: For cloning the project repository.

### Importing the Project
1. Clone the repository:
    ```bash
    git clone https://github.com/TimZ21/CW2024.git
    ```
2. **Open IntelliJ IDEA** and select **File > Open...**
3. **Navigate** to the directory the downloaded project also containing  `pom.xml` file, select this folder.
4. Click **OK** to open it as a project in IntelliJ IDEA, which will automatically detect and configure it as a Maven project.

### Configuring JDK in IntelliJ IDEA
1. Navigate to **File > Project Structure > Project**.
2. Set the **Project SDK** to JDK 17. If JDK 17 is not listed:
   - Click **New...** and find your JDK installation path.
3. Click **Apply** and **OK**.

### Setting Up Maven in IntelliJ IDEA
- The maven should be automatically set up, if not:
1. Go to **File > Settings > Build, Execution, Deployment > Build Tools > Maven**.
2. Make sure the **Maven home directory** is set correctly (the bundled version or specify another installation).


### Running the Application Using Maven
To run the application using Maven's `javafx:run` goal, follow these steps:
1. In IntelliJ IDEA, open the **Maven** tool window by clicking on the Maven icon on the right side of the workspace.
2. In the Maven tool window, navigate to **Plugins** > **javafx** and double-click on **javafx:run** to execute the application.

## Implemented and Working Properly
### Bug Fixing
1. **Resolve Level Transition Bug:** Amend the file extension of the Shield Image from .jpg to .png and implement null checks to avoid `NullPointerException` during image loading, ensuring smooth transitions between levels.

2. **Fix the shield displaying issue:** To fix the shield display in `LevelTwo`, shield management was integrated directly into the scene and refactored for better initialization and visibility control. The setup was moved from `Boss` to `LevelViewLevelTwo` for clearer separation, enhanced threading safety, and improved code clarity with detailed Javadoc comments.

3. **Memeory leak caused by projectiles:** To address the memory leak issue caused by projectiles not being removed, introduce logic to check the screen boundaries. If a projectile is detected outside these boundaries, it will be automatically removed from memory. This ensures efficient resource management and prevents potential performance degradation.

### Implemented Feature
1. **User Plane Horizontal Movement:** 
Added horizontal movement capabilities to the user-controlled plane allowing it to fly left and right in addition to the existing up and down motions.

2. **Start Menu:** 
The `StartMenu` of the Sky Battle game serves as the primary interface for player interaction, offering a visually appealing layout with a high-resolution background, interactive buttons for game operations like start, tutorial, mute, exit, and advanced audio management. It provides a dynamic and responsive design, ensuring a consistent user experience across various devices, and includes detailed game instructions through a tutorial overlay, enhancing user engagement.

3. **Pause Menu:**
The game will be paused if user press `ESCAPE` and the `PauseMenu` will be shown. The `PauseMenu` enhances gameplay management with options to resume, restart, or quit the game, and comprehensive audio controls for muting and volume adjustment. Styled for visibility and responsiveness, it centralizes essential game and sound settings in a user-friendly interface.

4. **Game Over/Lose Menu:**
The `LoseMenu` offers a streamlined and impactful "Game Over" interface, overlaying the current game scene with options to restart the game or exit. It includes a full-width background, boldly styled "Game Over" text, and responsive buttons that are safeguarded against accidental activation by the space key. Enhanced by audio effects to emphasize the game over, this menu enhances user experience by providing clear and immediate end-game choices.

5. **Win Menu:**
The `WinMenu` offers a streamlined and impactful "=Game Win" interface, overlaying the current game scene with options to restart the game or exit. It includes a full-width background, boldly styled "You Win!!!" text, and responsive buttons that are safeguarded against accidental activation by the space key. Enhanced by audio effects to emphasize the game win, this menu enhances user experience by providing clear and immediate end-game choices.

6. **New Level Tow:**
    - Renaming of LevelTwo: The previous `LevelTwo` has been renamed to `LevelBoss` to align better with its role as a critical boss fight stage.
    - Introduction of a New `LevelTwo`: A new `LevelTwo` has been added, similar in design to `LevelOne`. This level features enemy planes with 2 HP each, and limits the number of enemies on screen to seven at any time.
    - Objective for New `LevelTwo`: The goal in `LevelTwo` is to defeat 15 enemies. Successful completion of this objective grants players access to the next level, `LevelBoss`, enhancing the game's progression and challenge.

7. **New Level Final Boss:**
    - Introduction `LevelFinalBoss`: A new level named `LevelFinalBoss` has been introduced, echoing the design elements of `LevelOne`. This final stage combines the challenges of both the `Boss` and the regular `EnemyPlane` from `LevelOne`. To accommodate the increased difficulty, `UserPlane` are granted additional health, ensuring a balanced yet challenging gameplay experience.
    - Objective for `LevelFinalBoss`: The goal in `LevelFinalBoss` is to defeat the `Boss`.

8. **Boss Health Bar:**
Displayed the `Boss`'s health percentage with a progress bar at the contral top of the screen. This health bar will be shown in `LevelBoss` and `LevelFinalBoss`.

9. **Resource Management:**
Implements automatic resource management to optimize memory usage. Upon entering a new level, resources from the previous level are systematically cleaned up, ensuring efficient memory utilization and enhancing game performance.
10. **Projectiles Collision:**
Introduced a new collision detection feature between a user projectile and an enemy projectile. If a collision happens, both projectiles disappear.

11. **Prevent Continuous Firing:**
The game features a shooting mechanism where continuous firing by holding down the SPACE key is disabled. Instead, the frequency of shots is determined by how quickly the SPACE key is pressed. Due to the addition of projectile collisions, it would be unfair if the user could continuously fire.

12. **Smoother Multiple-Key Input:**
Improved multi-key handling, ensuring smoother gameplay when multiple keys are pressed simultaneously. This feature optimizes the response to complex input combinations, allowing for movement and actions without any lag or jitter, thereby enhancing the overall gaming experience.

13. **Boss Fire Pattern:**
Introduced three firing patterns of `Boss`, employing a Strategy pattern to enable horizontal, vertical, and diagonal attacks. It randomly selects from these patterns, adjusting projectile positioning based on the boss's location, thus varying the combat dynamics and increasing the game's challenge.

14. **Adjusting Hitboxes:**
Refine the hitboxes by trimming any empty spaces from images and adjusting the image height to better fit the game scene. These modifications enhance the gaming experience by ensuring hitboxes accurately reflect the visible elements.

15. **Background Music and Sound Effects:**
Add background music and various sound effects including explosions, button clicks, user and boss shooting, and win or lose notifications. Background music will pause or stop when entering the `PauseMenu`, `LoseMenu`, or `WinMenu`, when game is resumed or restarted the background music will play. Sound effects are triggered in response to specific game events, ensuring audio cues align with the on-screen action.

16. **Mute and Unmute Feature:**
A comprehensive control over both background music and sound effects. A `Mute` button is available in the `StartMenu` and `PauseMenu`. When the user clicks this button, all audio will be muted, and the button's label will change to `Unmute`. Clicking the button again will unmute all audio, and the button will be changed back to `Mute`.

17. **Volume Setting:**
User can access to volume setting by clicking `Volume` button in `StartMenu` and `PauseMenu`. A modal window for adjusting sound volumes will display, including background music and various sound effects. It features sliders for precise control over each audio category, with immediate updates for real-time feedback. This modal window blocks interactions with main game winodw before closing this setting window.

18. **Explosion Visual Effect:**
Displayed explosion image when an enemy unit is destroyed for better game experience.

19. **Game Tutorial:**
User can access to a comprehensive tutorial by clicking `Tutorial` button in `StartMenu`, outlining controls, gameplay mechanics, level objectives, and strategic tips. It features clear text instructions against a semi-transparent background for easy readability and includes a "Close" button to return to the main game view. This setup ensures players are well-equipped to navigate and succeed in the game.


## Implemented but Not Working Properly
### Scalling Issue about UI Components in Menus
I encountered an issue where the `PauseMenu`, `WinMenu`, and `StartMenu` were not centered on screens with scaling settings other than 100%. Initially, I used:
```java
        vbox.setAlignment(Pos.CENTER);
```
The scalling of my laptop is 150% but this center is only work for 100%, but at first I don't know this problem is due to the screen scalling and I find this problem really later. So I try to fix this problem using hard code:
```java
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setPadding(new Insets(250, 600, 0, 0));
```
And then I found that when I play the game on my monitor with scalling 125%, the UI compoment moved to a non-center position. Then I started to be confused. Unitl one day I was setting the screen of my laptop screen and the monitor, I found that the scallings are different, then I realized this problem might due to the scalling. Then I modified the scalling of my monitor to 150%, then the UI component display correctly. Due to I don' know the `vbox.setAlignment(Pos.CENTER);` only works for the 100% scalling, then I write a `ScaleUtils` to adjust positioning for scaling factors of 100% and 125% by hard code, and also `vbox.setAlignment(Pos.TOP_LEFT);` to make relocate easier:
```Java
        vbox.setAlignment(Pos.TOP_LEFT);
        ScaleUtils.updateScale();
        vbox.setPadding(new Insets(ScaleUtils.scaleYRelocate(250), ScaleUtils.scaleXRelocate(600), 0, 0));
```
The method used in ScaleUtils:
```Java
    public static void updateScale() {
        if (scale == 1) {
            incrementX = -650;
            incrementY = 0;
        } else if (scale == 1.25) {
            incrementX = -300;
            incrementY = -50;
        }
    }

    public static double scaleXRelocate(double value) {
        return value + incrementX;
    }

    public static double scaleYRelocate(double value) {
        return value + incrementY;
    }
```
During I wrote this README.md I suddenly thought that the 100% is the most default screen scalling set, so maybe the `vbox.setAlignment(Pos.CENTER);` will work when the screen scalling is 100%, then I modified the scalling to 100% found that it did work. Then I spent two hours tried to find out a general formular to solve this problem for all screen scalling but not for the usually used one, but non of them work. Then I keep the hard code to relocate the UI component
```java
        vbox.setAlignment(Pos.CENTER);
        ScaleUtils.updateScale();
        System.out.println(ScaleUtils.scale+" " + ScaleUtils.incrementX+" " + ScaleUtils.incrementY);
        vbox.setPadding(new Insets(ScaleUtils.incrementY, ScaleUtils.incrementX, 0, 0));
```
Due to the alignment has been set to CENTER, the methods `scaleYRelocate` and `scaleXRelocate` are not needed anymore, can directly use the `incrementY` and `incrementX` to perform the relocate task where the `incrementY` and `incrementX` are defined in `ScaleUtils` by:
```java
    public static double scale = Screen.getPrimary().getOutputScaleX();
    public static double incrementX = 0;
    public static double incrementY = 0;
    
    public static void updateScale() {
        if (scale == 1.25) {
            incrementX = 360;
            incrementY = -100;
        } else if (scale == 1.5) {
            incrementX = 625;
            incrementY = -300;
        }
    }
```
I also consider to make all menu display using a fresh root node and scene, create a `MenuParent` like `LevelParent` and each menu extends this `MenuParent` might could solve this issue, but consider about the increased resource needed and the complexity in state management, and limited time for me before the deadline, I didn't try this potential solution.

In summary, the initial issue of misaligned UI components in the `PauseMenu`, `WinMenu`, and `StartMenu` due to varying screen scaling settings was initially addressed with hardcoded adjustments specific to a 150% scaling. This solution, though effective for a single device, did not generalize well across devices with different scaling, leading to the creation of the ScaleUtils class for dynamic scaling adjustments. However, finding a universal solution for all potential scaling settings proved challenging, resulting in a compromise that effectively manages common scaling scenarios but still lacks a truly scalable formula for all screen sizes and resolutions. This approach, while practical in certain contexts, highlights the need for more flexible and responsive UI scaling methods in JavaFX applications.


## Features Not Implemented
### Features Able to Implement but Not Implemented
- **PauseButton:**
A visual button on the screen that user can click it and pause the game.

   **Reason:**
All operatioins in game playing is using keyboard, so using `ESCAPE` key to puase is easier for player, no need to distracted by clicking the button using the mouse.

### Features Unable to Implement
1. - **Power Up:**
Randomly generated power-ups that increases the user's health, speed, or projectile damage when collide the `Userplane`.

   - **Reason:**
Time Management. Due to the limited time and several deadlines come together, time is not enough.


2. - **Infinite Mode:**
There is no ending of this model, the `Boss` and `EnemyPlane` is random generated and player need to survive as long as possible to get marks. `Boss` is 50 marks and `EnemyPlane` is 1 mark.

   - **Reason:**
Time Management. Due to the limited time and several deadlines come together, time is not enough.

3. - **More Complex Movement Pattern:**
The `EnemyPlane` can move upand down, `Boss` can also move left and right without collision with other `EnemyUnits`.

   - **Reason:**
Time Management. Due to the limited time and several deadlines come together, time is not enough.

4. - **Second Stage of Final Boss:**
When the health of `Boss` in `LevelFinalBoss` drops below 50%, the `Boss` will transit into the 2nd stage that moving faster, more damaging `BossProjectile` attacks on the `UserPlane`. Additionally, the visual representation of the `Boss` will change to reflect this heightened threat level.

   - **Reason:**
Time Management. Due to the limited time and several deadlines come together, time is not enough.



## New Java Classes
1. - **Class Name:**
    `ExceptionHandler`
   - **Package:**
   `src/main/java/com/example/demo/controller`
   - **Description:**
   The `ExceptionHandler` class in the application serves as a centralized mechanism for handling exceptions across various components. It simplifies error management by consistently displaying alerts to users when errors occur and logging the details to the system's standard error stream. The class uses a static method called handle, which accepts an exception as a parameter, displays an alert with the type of exception to the user, and prints the exception's stack trace. This design ensures that errors are not only visible to users but also systematically recorded, facilitating debugging and enhancing the application's robustness.

2. - **Class Name:**
    `LevelController`
   - **Package:**
    `src/main/java/com/example/demo/controller`
   - **Description:**
    The `LevelController` class orchestrates game level management and transitions within the application. It leverages reflection to dynamically load and transition between game levels using class names, ensuring flexible gameplay progression. Integrated with the `StageController`, it handles scene setups and main stage adjustments. The controller also manages the `PauseMenu` for pausing and resuming games, maintaining seamless interaction during gameplay. Additionally, it features robust error handling through an `ExceptionHandler` to manage exceptions during level transitions, enhancing game stability and user experience. This `LevelController` support the `Controller` class in adhering to the Single Responsibility Principle by offloading specific tasks.

3. - **Class Name:**
    `StageController`
   - **Package:**
    `src/main/java/com/example/demo/controller`
   - **Description:**
   The `StageController` class in JavaFX is designed to centralize the management of the primary stage for the application. It handles crucial UI operations such as displaying the initial user interface, managing scene transitions, and adjusting the visibility of the stage. This class provides methods to set new scenes, retrieve stage dimensions, and show or hide the stage, thus facilitating smooth transitions and consistent UI updates throughout the application lifecycle. This approach simplifies stage management by encapsulating these functionalities in one place, enhancing maintainability and scalability of the UI code. This `StageController` support the `Controller` class in adhering to the Single Responsibility Principle by offloading specific tasks.


4. - **Class Name:**
    `LevelTwo`
   - **Package:**
    `src/main/java/com/example/demo/level`
   - **Description:**
   The old `LevelTwo` has been renamed by a more meaningful class name `LevelBoss`. The new `LevelTwo` class in the game represents the new second level, where players encounter more challenging enemies with increased health which is 2. This class, which extends LevelParent, tailors the game's mechanics for this level, including specific enemy spawn rates, health settings, and a unique background environment. It configures the level to feature up to 7 enemies simultaneously on-screen, with a player's progression conditioned on achieving 15 kills to advance to the next level, `LevelBoss`. The class also manages the game environment setup, player initialization, and dynamic enemy spawning, making the level progressively challenging. Additionally, it oversees game-over conditions and transitions based on the player's performance.


5. - **Class Name:**
    `LevelFinalBoss`
   - **Package:**
    `src/main/java/com/example/demo/level`
   - **Description:**
   The `LevelFinalBoss` class represents the ultimate challenge in the game, serving as the last and the most difficult level. Extending from `LevelParent`, it is designed to manage the specific dynamics of this level, including the introduction of a formidable boss and a limited number of enemy planes. The class controls the spawning of these unique enemies and the overall difficulty settings, such as `UserPlane` has more health. It also handles the conditions for winning or losing the level, ensuring that gameplay outcomes are determined by either the player's defeat or the boss's destruction. Custom level-specific UI components are managed through an instance of `LevelViewLevelBoss`.

6. - **Interface Name:**
    `GameComponent `
   - **Package:**
    `src/main/java/com/example/demo/manager`
   - **Description:**
   The `GameComponent` interface standardizes the management of game components within a game loop, encompassing three main functions: `update()` for refreshing component states each cycle, `removeDestroyed()` for cleaning up destroyed components to free resources, and `removeOutOfBounds()` for eliminating components that move beyond the screen bounds. This structured approach enhances performance and maintains code cleanliness in game development.
   
7. - **Class Name:**
    `ActorGroup`
   - **Package:**
    `src/main/java/com/example/demo/manager`
   - **Description:**
   The `ActorGroup` class implements the `GameComponent` interface, managing a collection of `ActiveActorDestructible` actors within a unified framework. This class facilitates the organization and control of game actors by providing methods for updating their state, removing those that are destroyed, and ensuring that actors do not remain on screen once they move out of bounds. Each actor within the group is managed in terms of rendering, updates, and lifecycle, with the class effectively treating individual and collective actor entities in a consistent manner. This implementation aligns with the `Composite` design pattern, enabling streamlined operations on both individual actors and the group as a whole, enhancing the game's overall management of dynamic entities. This `ActorGroup` will be used in `ActorManager` to facilitate the management.

8. - **Class Name:**
    `ActorManager`
   - **Package:**
    `src/main/java/com/example/demo/manager`
   - **Description:**
   The `ActorManager` class functions as a centralized management system for coordinating different groups of game actors, such as friendly units, enemy units, and both user and enemy projectiles. By implementing the `Facade` design pattern, this class simplifies the interactions with these complex subsystems, offering a high-level interface that masks the underlying complexity of individual actor operations. Additionally, it employs a hint of the `Composite` pattern by treating these actor groups uniformly, allowing for consistent management across various types of actors. The `ActorManager` orchestrates updates, removal of destroyed actors, and boundary checks for all actors, ensuring efficient gameplay management and resource optimization across all game components.

9. - **Class Name:**
    `AudioManager`
   - **Package:**
    `src/main/java/com/example/demo/manager`
   - **Description:**
   The `AudioManager` class in a JavaFX application serves as a centralized manager for all audio-related functionalities, including the playback, pausing, stopping, and volume control of background music and various sound effects such as explosions, clicks, user and boss shootings, and game win or lose cues. It implements the `Singleton` design pattern to ensure only one instance manages these audio operations across the application, preventing the creation of multiple audio managers that could lead to inconsistent audio behavior or resource leaks. This class provides methods to mute or unmute all sounds, and dynamically adjust the volume of different sound types based on user interactions or game events, maintaining a consistent and manageable audio environment.

10. - **Class Name:**
    `CollisionHandler`
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `CollisionHandler` class manages collision detection and resolution between actors, following a `Singleton` design pattern to ensure a single instance controls all collision handling operations. This class provides methods to detect and process collisions between two lists of `ActiveActorDestructible` objects, applying damage to actors upon contact and ensuring thread safety in its instance creation. Additional functionality includes managing visual and sound effects for collisions, enhancing the game's interactivity and feedback during destructive interactions between actors such as user projectiles and enemy planes. This centralized approach to collision management promotes clean, maintainable code by isolating collision logic within a single class responsible for all collision-related activities.

11. - **Class Name:**
    `EnemySpawner`
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
   The `EnemySpawner` class in a gaming application is designed to handle the creation, positioning, and management of enemy units within the game environment. It ensures that each enemy unit is placed without overlapping and within the predefined boundaries of the game scene. This class manages a list of enemy units and projectiles, adding them to the game's scene graph. Additionally, it facilitates the firing mechanics of these units, generating projectiles as required. The class also includes methods to randomly relocate enemy units to prevent spawn overlaps and to maintain gameplay balance and challenge.

12. - **Class Name:**
    `ExplosionEffectManager`
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
   The `ExplosionEffectManager` class, an extension of `ImageView`, is designed to handle the display of explosion effects when an enemy is destroyed. It manages the initialization and display of an explosion image at specific screen coordinates within a `Group`. Once instantiated, it sets the size and position of the explosion, adds it to the scene, and ensures it is visible in the foreground. The position of the explosion is where the enemy is destroyed. The explosion is programmed to remain on the screen for a short duration (500 milliseconds) before it is automatically removed. This automated lifecycle management ensures that the visual effects are both impactful and efficiently managed without lingering in the game scene longer than necessary.

13. - **Class Name:**
    `FirePatternManager`
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `FirePatternManager` class manages the firing patterns of `Boss`, implementing a form of the Strategy pattern where each firing pattern is treated as a distinct strategy. It allows for dynamic selection and execution of projectile firing patterns, horizontal, vertical, or diagonal-based on the boss's position on the screen. The class uses a specified Y-axis offset to adjust the positioning of projectiles relative to the boss, enhancing the gameplay experience by varying attack strategies.

14. - **Class Name:**
    `HealthBarManager`
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `HealthBarManager` class in a game application is designed to manage the health bar for the `Boss` character. It oversees the initialization, positioning, updating, and visibility of a health bar represented as a `ProgressBar` within an `HBox` container. The class allows for the dynamic adjustment of the health bar's appearance based on the boss's current health percentage, showing or hiding the bar when `Boss` is defeated. Positioned at the top middle part of screen, the health bar visually communicates the boss's health status to the player, enhancing the interactive gameplay experience.

15. - **Class Name:**
    `KeyStateTracker `
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `KeyStateTracker` class is designed to monitor and manage the state of keyboard keys, specifically tailored for enhancing gameplay where multiple key presses are significant. It uses a synchronized `HashMap` to keep track of each key's state, where a `true` value represents a pressed key and `false` indicates a released one. The class provides methods to register key presses and releases and to check if a specific key is currently pressed, enabling responsive and dynamic control handling in games. A smoother multiple key input also improve the game experience.

16. - **Class Name:**
    `InputHandler `
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `InputHandler` class manages keyboard input specifically for controlling the UserPlane and pausing. It assigns actions to key presses and releases, such as moving the plane, firing projectiles, and toggling the game's pause state. The class leverages a `KeyStateTracker` to monitor and respond to ongoing key states, ensuring smooth and responsive control over the game actions. This setup allows for both immediate reactions to key events and continuous checks to maintain fluid movement and actions, such as continuous plane movement and regulated projectile firing, enhancing the interactive gameplay experience.

17. - **Class Name:**
    `ScaleUtils `
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `ScaleUtils` class provides methods for adjusting the positioning of UI components based on the scaling settings of the primary display screen. This utility class is crucial for ensuring that UI elements appear correctly across different resolution scales, especially when dealing with high-DPI screens. It provides horizontal and vertical offsets (`incrementX` and `incrementY`) based on the screen's scale factor, allowing components to be dynamically positioned to maintain a consistent layout across varying display settings. This will be used in `PauseMenu`, `WinMenu` and `LoseMenu` to adjust the UI components to correct position.

18. - **Class Name:**
    `ShieldManager `
    - **Package:**
    `src/main/java/com/example/demo/manager`
    - **Description:**
    The `ShieldManager` class is responsible for managing the functionality of the shield of `Boss`. It controls the activation and deactivation of the shield, manages its visibility, and updates its position relative to an entity it protects. The class utilizes a `ShieldImage` for the visual representation of the shield, which is displayed or hidden based on the shield's state. The shield's activity is governed by a probability mechanism and a maximum duration, after which it must be deactivated. The manager also tracks how long the shield has been active and handles its positioning updates to align with the `Boss`'s movements. This ensures the shield is visually connected to the `Boss`, enhancing the gameplay interaction and visual coherence.

19. - **Class Name:**
    `LoseMenu `
    - **Package:**
    `src/main/java/com/example/demo/menu`
    - **Description:**
    The `LoseMenu` class in is responsible for managing the display of a "Game Over" screen, providing options for players to restart the game or quit. This class initializes and sets up UI components like background images, buttons, and text overlays, aligning them within the current game scene. It incorporates functionalities from the `AudioManager` for playing loss-related sounds and utilizes `ScaleUtils` to adjust the layout based on screen scaling settings. The class ensures interactive elements, such as buttons, behave correctly, including preventing accidental activation by the space key, and implements event handlers to manage game restarts or exits effectively. And `AudioManager` also provides the sound effect for button clicking in this menu. This setup enriches the end-game user experience by providing a clear and interactive game over interface.

20. - **Class Name:**
    `PauseMenu `
    - **Package:**
    `src/main/java/com/example/demo/menu`
    - **Description:**
    The `PauseMenu` class in a is responsible for managing the display of a pause menu, providing players with options to resume, restart, adjust volume, and quit the game. This class initializes and sets up UI components such as a background image, buttons, and text overlays, aligning them within the game scene. It incorporates functionalities from the `AudioManager` to handle audio-related actions, such as pausing and resuming background music, playing button click sounds, managing and mute/unmute states. The class also utilizes `ScaleUtils` to ensure the layout adapts to screen scaling settings.
    The pause menu includes interactive elements like buttons, which are styled consistently and respond to user actions. Event handlers are implemented to manage game resumption, restarts, and exits effectively.  And `AudioManager` also provides the sound effect for button clicking in this menu. Additionally, the class provides a volume settings menu where players can adjust various audio levels by `AudioManager` which can be accessing by the `Volume` button, enhancing the overall user experience by offering a clear and interactive pause interface.

21. - **Class Name:**
    `StartMenu `
    - **Package:**
    `src/main/java/com/example/demo/menu`
    - **Description:**
    The `StartMenu` class provides the main menu interface for the game. It allows players to start the game, access audio settings including mute and volume setting, view tutorials, or quit the game. This class handles the layout and interaction logic for the start menu, including button actions and audio control. It initializes and sets up UI components such as a background image, buttons, and text overlays, aligning them within the game scene. The class incorporates functionalities from the `AudioManager` to handle audio-related actions, such as playing background music, button click sound effect and managing mute/unmute states. The start menu includes interactive elements like buttons, which are styled consistently and respond to user actions. Event handlers are implemented to manage game starts, exits, and other interactions effectively. Additionally, the class provides a volume settings menu where players can adjust various audio levels by `AudioManager` which can be accessing by the `Volume` button, enhancing the overall user experience by offering a clear and interactive main menu interface.

22. - **Class Name:**
    `WinMenu `
    - **Package:**
    `src/main/java/com/example/demo/menu`
    - **Description:**
    The `WinMenu` class is responsible for displaying a victory screen when the player defeat the `Boss` in `LevelFinalBoss`. This class provides options for players to restart the game or quit. It initializes and sets up UI components such as a background image, buttons, and text overlays, aligning them within the current game scene. The class incorporates functionalities from the `AudioManager` to play victory-related sounds, pause the background music, button click sound effect and play background music if user choose to restart, and utilizes `ScaleUtils` to adjust the layout based on screen scaling settings. The win menu includes interactive elements like buttons, which are styled consistently and respond to user actions. Event handlers are implemented to manage game restarts or exits effectively. Additionally, the class prevents accidental activation of buttons by the space key, ensuring a smooth user experience. This setup enhances the end-game user experience by providing a clear and interactive victory interface.




## Modified Java Classes
1. - **Class Name:**
    `Controller  `
   - **Package:**
    `src/main/java/com/example/demo/controller`
   - **Modification:**
   1. **Separation of Concerns**

        **Old version**:
        - The `Controller` class was responsible for both stage management and level transitions, leading to a monolithic design.
        - It implemented the `Observer` pattern to handle level transitions, which added complexity.

        **New version**:
        - The responsibilities are split into two separate controllers to adhere the Single Responsibility Principle:
            - `StageController`: Manages the stage and scene transitions.
            - `LevelController`: Handles the loading and transitioning between levels.

        **Reason**:
        - **Improved Modularity**: By separating concerns, each class focuses on a specific task, making the codebase easier to understand and maintain, this also make the Controller adhere the Single Responsibility Principle.
        - **Scalability**: Adding new features (e.g., stage settings or level transitions) becomes easier, as changes can be made in the respective controllers without affecting the main Controller class.

    2. **Dynamic Level Loading with Reflection**

        **Old version**:
        - The `Controller` class used reflection to dynamically load levels (`Class.forName`, `getConstructor`, `newInstance`).
        - This approach required handling multiple exceptions (`ClassNotFoundException`, `NoSuchMethodException`, etc.) and made the code more complex.

        **New version**:
        - The `LevelController` class continues to use reflection for dynamic level loading, but the logic is encapsulated within the LevelController.
        - The `LevelController` also handles exceptions internally using the `ExceptionHandler` class, simplifying error handling.

        **Reason**:
        - **Encapsulation**: By encapsulating the reflection logic within the LevelController, the main Controller class remains clean and focused.
        - **Centralized Error Handling**: The ExceptionHandler class centralizes error handling, ensuring that exceptions are managed gracefully and consistently.

    3. **Using Javafx Property instead of Observer Pattern**

        **Old version**:
        - The `Controller` class implemented the `Observer` interface to handle level transitions.
        - It used the `update` method to dynamically switch between levels based on notifications from the `Observable` objects (levels).

        **New version**:
        - The `LevelController` uses a `ChangeListener` on the `nextLevelProperty` of the `LevelParent` class to handle level transitions.
        - This approach is more direct and avoids the complexity of the `Observer` pattern.

        **Reason**:
        - **Simplification**: The `ChangeListener` approach is simpler and more intuitive than the `Observer` pattern, reducing the overall complexity of the code.
        - **Direct Control**: The new approach allows for more direct control over level transitions, making the code easier to understand and maintain.

    4. **Pause and Resume Logic Integration**

        **Old version**:
        - There was no pause and resume functionality in the old version.

        **New version**:
        - The `LevelController` initializes and manages the `PauseMenu` instance, ensuring that the pause menu is seamlessly integrated into the game.
        - The `PauseMenu` is associated with the current level and allows players to pause and resume the game.

        **Reason**:
        - **Enhanced User Experience**: The pause menu provides a better user experience by allowing players to pause or resume the game and make adjustments.
        - **Centralized Management**: By managing the pause menu within the LevelController, the code remains organized and easier to maintain.

    4. **Stage Management**

        **Old version**:
        - The `Controller` class directly managed the stage and scene transitions.

        **New version**:
        - The StageController class is responsible for managing the stage and scene transitions.

        **Reason**:
        - **Encapsulation**: By encapsulating stage management within the StageController, the codebase becomes more modular and easier to maintain.


2. - **Class Name:**
    `Main`
   - **Package:**
    `src/main/java/com/example/demo/controller`
   - **Modification:**
   1. **Display the StartMenu instead of Launching the Game Directly**

        **Old version**:
        - The `Main` class directly created an instance of the `Controller` and called its `launchGame` method to start the game and enter the `LevelOne`.

        **New version**:
        - The `Main` class initializes a `StartMenu` object and displays it as the initial scene of the application.

        **Reason**:
        - **Enhanced User Experience**: By introducing a start menu, the game provides a more polished and user-friendly experience. Players can now choose options before starting the game, such as viewing tutorials or adjusting volume settings.
        - **Scalability**: Adding new features (e.g., stage settings or level transitions) becomes easier, as changes can be made in the respective controllers without affecting the main Controller class.


3. - **Class Name:**
    `ActiveActor `
   - **Package:**
    `src/main/java/com/example/demo/actors`
   - **Modification:**
   1. **Input Validation and Error Handling**

        **Old version**:
        - The constructor did not validate the input parameters (`imageName` and `imageHeight`).
        - This could lead to runtime errors if invalid values were passed (e.g., `null` or empty `imageName`, or non-positive `imageHeight`).

        **New version**:
        - The constructor includes input validation:
            - Checks if imageName is null or empty.
            - Ensures imageHeight is positive.
        - If invalid values are provided, the constructor throws an `IllegalArgumentException`.

        **Reason**:
        - **Robustness**: Input validation ensures that the class is used correctly and prevents runtime errors caused by invalid inputs.
        - **Error Prevention**: By explicitly checking for invalid values, the class avoids potential crashes or undefined behavior.
        - **Usability Improvement**: The validation logic makes it clear what inputs are expected, improving the class's usability.

    2. **Image Loading**

        **Old version**:
        - The image loading logic was directly embedded in the constructor:
        ```java
            this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        ```
        - This approach tightly coupled image loading with the constructor, making it harder to reuse or modify the logic.

        **New version**:
        - The image loading logic is extracted into a separate private method called `loadImage`:
        ```java
            private void loadImage(String imageName, int imageHeight) {
                this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
                this.setFitHeight(imageHeight);
                this.setPreserveRatio(true);
            }
        ```
        - The constructor calls this method after validating the inputs.

        **Reason**:
        - **Encapsulation**: By encapsulating the image loading logic in a separate method, the constructor becomes simpler and more focused.
        - **Reusability**: The `loadImage` method can be reused or modified independently, making the code more modular.
        - **Maintainability**: Changes to the image loading logic such as adding error handling or logging, can be made in one place without affecting the constructor.

    3. **Improved Package Structure**

        **Old version**:
        - The `ActiveActor` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `ActiveActor` class is now placed in a more meaningful package `com.example.demo.actors`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


4. - **Class Name:**
    `ActiveActorDestructible`
   - **Package:**
    `src/main/java/com/example/demo/actors`
   - **Modification:**
   1. **Simplify the Logic of method `setDestroyed`**

        **Old version**:
        - The class included a `setDestroyed`(boolean `isDestroyed`) method, which allowed external modification of the `isDestroyed` flag, but this `setDestroyed` method is only used to set the `idDestroyed` field as `true`.

        **New version**:
        - The `setDestroyed`(boolean `isDestroyed`) method was removed, and the `setDestroyed()` method was introduced, which sets the isDestroyed flag to true without using of any parameter.

        **Reason**:
        - **Encapsulation**: By removing the ability to set the isDestroyed flag to false, the class ensures that once an actor is destroyed, it cannot be "undestroyed." This prevents potential misuse and ensures consistent behavior.
        - **Simplification**: The new setDestroyed() method is simpler and more focused.

   2. **Addition of Absolute Position Methods`**

        **Old version**:
        - The class did not provide methods to calculate the absolute position of the actor.

        **New version**:
        - The class includes two new methods:
            - `getAbsoluteX()`: Calculates the absolute X-coordinate of the actor.
            - `getAbsoluteY()`: Calculates the absolute Y-coordinate of the actor.

        **Reason**:
        - **Enhanced Functionality**: The new methods provide a convenient way to calculate the absolute position of the actor.
        - **Reusability**:  By encapsulating the logic for calculating absolute positions, the methods can be reused across subclasses, reducing code duplication.

    3. **Improved Package Structure**

        **Old version**:
        - The `ActiveActorDestructible` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `ActiveActorDestructible` class is now placed in a more meaningful package `com.example.demo.actors`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.



5. - **Class Name:**
    `Boss`
   - **Package:**
    `src/main/java/com/example/demo/actors/plane`
   - **Modification:**
   1. **Health Bar Management**

        **Old version**:
        - The health bar was not explicitly managed in the old version.

        **New version**:
        - The `HealthBarManager` is used to manage the health bar, including updating its display based on the boss's health and hiding it when the boss is destroyed.

        **Reason**:
        - **Enhanced User Experience**: The health bar provides visual feedback to the player about the boss's remaining health, improving the overall gameplay experience.
        - **Encapsulation**: By encapsulating health bar management in a separate class, the Boss class remains focused on its core behavior.
        - **Maintainability**: Changes to the health bar logic (e.g., styling or behavior) can be made in the HealthBarManager without affecting the Boss class.

   2. **Fire Pattern Management**

        **Old version**:
        - The `Boss` class directly handled projectile firing logic, including determining when to fire and the projectile's initial position. Only have one default fire pattern.

        **New version**:
        - The `FirePatternManager` is used to manage the firing patterns of projectiles, determining when to fire and returning a list of projectiles. Additional, the `FirePatternManager` provides a random select from three different fire patterns.

        **Reason**:
        - **Flexibility**: The `FirePatternManager` allows for more complex and customizable firing patterns, which can be easily modified without affecting the `Boss` class.
        - **Encapsulation**: By encapsulating the firing logic in a separate class, the `Boss` class remains focused on its core behavior. Adhering the Single Responsibility Principle.
        - **Better Game Experience**: The random selection of three differnt fire pattern make the game more interesting. Enhancing the player's game experience.

   3. **Shield Management**

        **Old version**:
        - The shield functionality was directly implemented in the `Boss` class, including activation, deactivation, and tracking the number of frames the shield was active.

        **New version**:
        - The `ShieldManager` is used to manage the shield functionality, including updating its position and determining when to activate or deactivate the shield.

        **Reason**:
        - **Encapsulation**: By encapsulating the shield logic in a separate class, the Boss class remains focused on its core behavior. Adhering the Single Responsibility Principle.
        - **Maintainability**: Changes to the shield logic can be made in the `ShieldManager` without affecting the `Boss` class.

    4. **Simplified Movement Logic**

        **Old version**:
        - The movement logic was directly implemented in the `Boss` class, including tracking the number of consecutive moves in the same direction and shuffling the movement pattern.

        **New version**:
        - The movement logic remains largely the same, but the use of the `movePattern` list and the `getNextMove()` method makes it easier to understand and maintain.

        **Reason**:
        - **Clarity and Readability**: The movement logic is now more clearly separated into its own method, making it easier to understand and modify.

    5. **Improved Package Structure**

        **Old version**:
        - The `Boss` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `Boss` class is now placed in a more meaningful package `com.example.demo.actors.plane`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

   6. **Image Height Adjustment**

        **Old version**:
        - The image height was fixed at 300.

        **New version**:
        - Due to cutting the empty space of the image to adjust the HittingBox.
        - The image height is now set to 75.

        **Reason**:
        - **Visual Consistency**: The new image height aligns with the overall visual style of the game, ensuring that all game elements are scaled appropriately.


6. - **Class Name:**
    `BossProjectile`
   - **Package:**
    `src/main/java/com/example/demo/actors/projectile`
   - **Modification:**
   1. **Introduction of Dynamic Velocity Scaling**

        **Old version**:
        - The `BossProjectile` class had a fixed horizontal velocity (`HORIZONTAL_VELOCITY = -15`) and no vertical velocity.
        - The projectile's movement was limited to horizontal movement only.

        **New version**:
        - The `BossProjectile` class now supports both horizontal and vertical velocities:
            - Horizontal Velocity (`xVelocity`): Determines the horizontal movement of the projectile.
            - Vertical Velocity (`yVelocity`): Determines the vertical movement of the projectile.
        - The class provides two constructors:
            - One with default velocities (`HORIZONTAL_VELOCITY = -15` and `yVelocity = 0`).
            - Another that allows specifying custom horizontal and vertical velocities.

        **Reason**:
        - **Flexibility**: By introducing dynamic velocities, the BossProjectile class can now support more complex movement patterns, such as diagonal.
        - **Reusability**: The ability to specify custom velocities makes the class more versatile and reusable in different scenarios.
        - **Enhanced Gameplay**: The new version allows for more varied and challenging boss attack patterns, improving the overall gameplay experience.

   2. **Change for FPS**

        **Old version**:
        - The horizontal velocity was a fixed value (-15), which did not account for any velocity scaling or changes in the game's frame per second.
        - The `BOSS_FIRE_RATE` was consistent, if the FPS of the game changed, the game can't run correctly.

        **New version**:
        - The horizontal velocity and fire rate are now adjusted by a velocity change factor (VELOCITY_CHANGE) defined in the LevelParent class:
            ```java
                private static final double HORIZONTAL_VELOCITY = (double) -15 / VELOCITY_CHANGE;

                private static final double BOSS_FIRE_RATE = 0.04 / VELOCITY_CHANGE;
            ```

        **Reason**:
        - **Consistency**: Ensures that the projectile's movement and fire rate are consistent with the overall game mechanics, even if the game's FPS changes.
        **Flexibility**: This change allows for easier adjustments to the game's overall speed without modifying individual classes.

   3. **Constructor Overloading**

        **Old version**:
        - The class had a single constructor that only allowed specifying the initial Y-position.

        **New version**:
        - The class provides two constructors:
            - One with default velocities (`HORIZONTAL_VELOCITY` and `yVelocity = 0`).
            - Another that allows specifying custom horizontal and vertical velocities.

        **Reason**:
        - **Flexibility**: The overloaded constructors provide flexibility in creating projectiles with different movement patterns.
        - **Encapsulation**: By allowing custom velocities, the class can be used in more scenarios without requiring external modifications to its behavior.

   4. **Getter Methods for Velocities**

        **Old version**:
        - The class did not provide methods to retrieve the projectile's velocity.

        **New version**:
        - The class includes getter methods for both horizontal (getXVelocity()) and vertical (getYVelocity()) velocities.

        **Reason**:
        - **Testing and Debugging**: The getter methods make it easier to test and debug the projectile's movement behavior. Mainly used in JUnit test.
        - **Encapsulation**: The getter methods allow external classes to access the projectile's velocities without directly accessing the fields.

    5. **Improved Package Structure**

        **Old version**:
        - The `BossProjectile` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `BossProjectile` class is now placed in a more meaningful package `com.example.demo.actors.projectile`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


7. - **Class Name:**
    `EnemyPlane`
   - **Package:**
    `src/main/java/com/example/demo/actors/plane`
   - **Modification:**
   1. **Change for FPS**

        **Old version**:
        - The horizontal velocity of the EnemyPlane was a fixed value (`HORIZONTAL_VELOCITY` = -6).
        - The fire rate was also a fixed value (`FIRE_RATE` = 0.01).

        **New version**:
        - Both the horizontal velocity and fire rate are now scaled by a VELOCITY_CHANGE factor defined in the LevelParent class:
        ```java
            private static final double HORIZONTAL_VELOCITY = -6.0 / VELOCITY_CHANGE;
            private static final double FIRE_RATE = 0.01 / VELOCITY_CHANGE;
        ```

        **Reason**:
        - **Consistency**: Ensures that the enemy plane's behavior is consistent with the overall game mechanics, even if the game's FPS changes.
        - **Flexibility**: This change allows for easier adjustments to the game's overall speed without modifying individual classes.

   2. **Constructor Overloading**

        **Old version**:
        - The class had a single constructor that only allowed specifying the initial X and Y positions.

        **New version**:
        - The class provides two constructors:
            - One with default health and image (IMAGE_NAME).
            - Another that allows specifying custom health and image name.

        **Reason**:
        - **Flexibility**: The overloaded constructors provide flexibility in creating enemy planes with different health levels and appearances. This new customized constructor implemented in new `LevelTwo` to created the new `EnemyPlane` with more health and new image.
        - **Encapsulation**: By allowing custom health and image, the class can be used in more scenarios without requiring external modifications to its behavior.
        - **Reusability**: The ability to specify custom health and image makes the class more versatile and reusable in different levels or enemy types.

   3. **Fire Projectile Return Type**

        **Old version**:
        - The `fireProjectile()` method returned a single `ActiveActorDestructible` object or `null`.

        **New version**:
        - The fireProjectile() method returns a `List<ActiveActorDestructible>`.
            - If a projectile is fired, it adds the projectile to the list.
            - If no projectile is fired, it returns an empty list.

        **Reason**:
        - **Consistency**: Returning a list ensures that the method always returns a collection, even if it is empty. This simplifies the handling of projectiles in the calling code.
        - **Encapsulation**: By returning a list, the method encapsulates the logic for firing projectiles and provides a clear interface for the calling code.
        - **Scalability**: The list-based approach allows for future extensions.

   4. **Image Height Adjustment**

        **Old version**:
        - The image height was fixed at 150.

        **New version**:
        - Due to cutting the empty space of the image to adjust the HittingBox.
        - The image height is now set to 70.

        **Reason**:
        - **Visual Consistency**: The new image height aligns with the overall visual style of the game, ensuring that all game elements are scaled appropriately.

    5. **Improved Package Structure**

        **Old version**:
        - The `EnemyPlane` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `EnemyPlane` class is now placed in a more meaningful package `com.example.demo.actors.plane`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


8. - **Class Name:**
    `EnemyProjectile`
   - **Package:**
    `src/main/java/com/example/demo/actors/projectile`
   - **Modification:**
   1. **Change for FPS**

        **Old version**:
        - The horizontal velocity of the EnemyProjectile was a fixed value (HORIZONTAL_VELOCITY = -10).

        **New version**:
        - The horizontal velocity is now scaled by a VELOCITY_CHANGE factor defined in the LevelParent class:
        ```java
            private static final double HORIZONTAL_VELOCITY = -10.0 / VELOCITY_CHANGE;
        ```

        **Reason**:
        - **Consistency**: Ensures that the projectile's movement is consistent with the overall game mechanics, even if the game's FPS changes.
        **Flexibility**: This change allows for easier adjustments to the game's overall speed without modifying individual classes.
    
   2. **Image Height Adjustment**

        **Old version**:
        - The image height was fixed at 50.

        **New version**:
        - The image height is now set to 30.

        **Reason**:
        - **Visual Consistency**: The new image height aligns with the overall visual style of the game, ensuring that all game elements are scaled appropriately.

    3. **Improved Package Structure**

        **Old version**:
        - The `EnemyProjectile` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `EnemyProjectile` class is now placed in a more meaningful package `com.example.demo.actors.projectile`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.



9. - **Class Name:**
    `FighterPlane`
   - **Package:**
    `src/main/java/com/example/demo/actors/plane`
   - **Modification:**
   1. **Fire Projectile Return Type**

        **Old version**:
        - The `fireProjectile()` method returned a single `ActiveActorDestructible` object or `null`.

        **New version**:
        - The fireProjectile() method returns a List<ActiveActorDestructible>.
            - If projectiles are fired, they are added to the list.
            - If no projectiles are fired, it returns an empty list.

        **Reason**:
        - **Consistency**:Returning a list ensures that the method always returns a collection, even if it is empty. This simplifies the handling of projectiles in the calling code.
        - **Scalability**: The list-based approach allows for future extensions.
        - **Encapsulation**: By returning a list, the method encapsulates the logic for firing projectiles and provides a clear interface for the calling code.


   2. **Health Check Logic**

        **Old version**:
        - he `takeDamage()` method checked if the health was exactly zero (`health == 0`) before destroying the plane.

        **New version**:
        - The `takeDamage()` method checks if the health is less than or equal to zero (`health <= 0`) before destroying the plane.

        **Reason**:
        - **Robustness**:The new condition ensures that the plane is destroyed even if the health drops below zero, which could happen if the plane takes multiple hits in quick succession.
        - **Consistency**:  Ensures that the plane is always destroyed when its health reaches zero or below, avoiding potential bugs where the plane might remain active with negative health.

    3. **Improved Package Structure**

        **Old version**:
        - The `FighterPlane` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `FighterPlane` class is now placed in a more meaningful package `com.example.demo.actors.plane`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.



10. - **Class Name:**
    `Destructible`
    - **Package:**
    `src/main/java/com/example/demo/actors`
    - **Modification:**
    1. **Improved Package Structure**

        **Old version**:
        - The `Destructible` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `Destructible` class is now placed in a more meaningful package `com.example.demo.actors`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


11. - **Class Name:**
    `HeartDisplay `
    - **Package:**
    `src/main/java/com/example/demo/view`
    - **Modification:**
    1. **Null Safety**

        **Old version**:
        - The image loading logic used `getClass().getResource(...)`, which could return `null` if the image file was not found.
        - No explicit handling of `null` was provided, which could lead to a `NullPointerException`.

        **New version**:
        - The `initializeHearts()` method uses `Objects.requireNonNull(...)` to ensure that the resource URL is not `null`:
        ```java
            new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm())
        ```
        - If the resource URL is `null`, `Objects.requireNonNull` throws a `NullPointerException` with a clear message.

        **Reason**:
        - **Null Safety**: By explicitly checking for `null`, the class avoids potential runtime errors and provides clear error messages.
        - **Error Handling**: The `NullPointerException` indicates that the image file is missing, making it easier to debug the issue.

    2. **Visual Representation Adjusting**

        **Old version**:
        - The heart image file name was `heart.png` which is the default image given.

        **New version**:
        - The heart image file name is now `heart_pixel.png` which is a pixel style heart.

        **Reason**:
        - **Better Visual Game Experience**: The style of new image is more suitable for game. Enhanced player's game experience.

    3. **Improved Package Structure**

        **Old version**:
        - The `HeartDisplay` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `HeartDisplay` class is now placed in a more meaningful package `com.example.demo.view`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


12. - **Class Name:**
    `LevelParent`
    - **Package:**
    `src/main/java/com/example/demo/level`
    - **Modification:**
    1. **Split Responsibilities to Managers and Handlers**

        **Old version**:
        - The `LevelParent` class directly handled all game logic, including actor management, collision detection, input handling, and enemy spawning.
        - This led to a monolithic design, where the `LevelParent` class was responsible for too many tasks.

        **New version**:
        - The LevelParent class delegates these responsibilities to separate manager and handler classes:
            - `ActorManager`: Manages all actors (friendly units, enemy units, projectiles) and their updates. This class implements `Composite` and `Facade` design pattern.
            - `CollisionHandler`: Handles collision detection and response. This class implements `Singleton` design pattern.
            - `InputHandler`: Manages user input for controlling the player's plane.
            - `EnemySpawner`: Handles the spawning of enemy units.

        **Reason**:
        - **Separation of Concerns**:  By delegating these responsibilities to separate classes, the LevelParent class becomes simpler and more focused on its core role (managing the level). Adhering the Single Responsibility Principle.
        - **Modularity**: Each manager and handler class handles a specific aspect of the game, making the codebase more modular and easier to maintain.
        - **Reusability**: The manager and handler classes can be reused in other parts of the game or for other levels, reducing code duplication.

    2. **Dynamic Game Speed/Frame Per Second**

        **Old version**:
        - The game loop ran at a fixed interval (`MILLISECOND_DELAY = 50`), which did not account for dynamic game speed adjustments.

        **New version**:
        - The game loop now uses a `TARGET_FPS` of 60, with a corresponding MILLISECOND_DELAY calculated as `1000 / TARGET_FPS`.
        - A `VELOCITY_CHANGE` factor is introduced to dynamically adjust movement speeds based and fire rate on the target FPS.

        **Reason**:
        - **Consistency**:  Ensures that the game runs at a consistent speed across different systems, even if the actual FPS varies.
        - **Dynamic Adjustments**: The `VELOCITY_CHANGE` factor allows for dynamic adjustments to movement speeds, ensuring smooth gameplay regardless of the system's performance.
        - **Gmae Experience**: Higher FPS will provide a smoother game screen, and also improve the visual experience of games.

    3. **Null Safety**

        **Old version**:
        - The image loading logic used `getClass().getResource(...)`, which could return `null` if the image file was not found.
        - No explicit handling of `null` was provided, which could lead to a `NullPointerException`.

        **New version**:
        - The `initializeHearts()` method uses `Objects.requireNonNull(...)` to ensure that the resource URL is not `null`:
        ```java
            new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm())
        ```
        - If the resource URL is `null`, `Objects.requireNonNull` throws a `NullPointerException` with a clear message.

        **Reason**:
        - **Null Safety**: By explicitly checking for `null`, the class avoids potential runtime errors and provides clear error messages.
        - **Error Handling**: The `NullPointerException` indicates that the image file is missing, making it easier to debug the issue.

    4. **Pause and Resume**

        **Old version**:
        - There is no pause and resume functionality in the old version.

        **New version**:
        - The `pauseGame()` and `resumeGame()` methods are introduced to handle pausing and resuming the game.
            - `pauseGame()`: Stops the timeline and displays the pause menu.
            - `resumeGame()`: Resumes the timeline.

        **Reason**:
        - **Enhanced User Experience**: The pause functionality allows players to pause the game and make adjustments or take a break.

    5. **Clean-Up Method**

        **Old version**:
        - No `cleanUp()` method and clean up logic.

        **New version**:
        - The cleanUp() method is introduced to stop the timeline and clear all game entities from the scene:
            - `stopTimeline()`: Stops the game loop.
            - `friendlyUnits.clear()`, `enemyUnits.clear()`, `userProjectiles.clear()`, `enemyProjectiles.clear()`: Clears all actor lists.
            - `root.getChildren().clear()`: Clears all graphical elements from the scene.

        **Reason**:
        - **Resource Management**: Ensures that no residual game elements remain that could affect future gameplay or resource management.
        - **Encapsulation**: By encapsulating the clean-up logic in a separate method, the `LevelParent` class remains focused on its core role
        - **Reusability**: Integrated clean-up logic in single method that no need to repeat the code when clean up is needed. Improve the code reusability.

    6. **ActorGroup for Actor Management**

        **Old version**:
        - The `LevelParent` class directly managed lists of actors (friendly units, enemy units, projectiles) and their interactions.

        **New version**:
        - The `ActorGroup` class is introduced to encapsulate the management of actor lists and their interactions with the scene implementing `Composite` pattern.
            - Each `ActorGroup` manages a specific type of actor (e.g., friendly units, enemy units, projectiles).
            - The `ActorManager` uses these groups to handle updates, removals, and other operations.

        **Reason**:
        - **Encapsulation**: By encapsulating actor management in `ActorGroup`, the `LevelParent` class becomes simpler and more focused.
        - **Reusability**: The `ActorGroup` class can be reused for other types of actors or in other parts of the game.
        - **Scalability**: The use of `ActorGroup` allows for easier addition of new actor types or modifications to existing ones.

    7. **Collision Handling and Explosion Effects**


        **Old version**:
        - Collision detection was handled directly in the `LevelParent` class, with no specific handling for effects (e.g., explosions).

        **New version**:
        - The `CollisionHandler` class includes methods for detecting collisions with effects:
            - `detectCollisions()`: Handle the default collision between actors.
            - `detectCollisionsWithEffect()`: Handles collisions between user projectile and enemies, triggering effects such as explosions and sound effects.

        **Reason**:
        - **Enhanced Gameplay**: Adding effects to collisions improves the visual and auditory feedback for players, enhancing the overall gameplay experience.
        - **Encapsulation**: By delegating collision handling with effects to the `CollisionHandler`, the `LevelParent` class remains focused on its core role.

    8. **Out-of-Bounds Projectile Removal**

        **Old version**:
        - Projectiles that went out of bounds were not explicitly removed, which could lead to memory leak and performance issues over time.

        **New version**:
        - The ActorManager includes a method to remove projectiles that are out of bounds:
            - `removeOutOfBoundsProjectiles(double screenWidth)`: Removes projectiles that have moved beyond the screen boundaries.

        **Reason**:
        - **Resource Management**: Removing out-of-bounds projectiles prevents unnecessary updates and reduces memory usage.
        - **Encapsulation**: By delegating this task to the `ActorManager`, the `LevelParent` class remains focused on its core role.

    9. **Enhanced Enemy Spawning**

        **Old version**:
        - Enemy spawning was handled directly in the `LevelParent` class, with no specific logic for managing enemy projectiles.
        - The enemies might overlapping each other when generated.

        **New version**:
        - The EnemySpawner class is introduced to handle enemy spawning and projectile generation:
            - `generateEnemyFire()`: Handles the generation of projectiles fired by enemies.
            - `relocateEnemy(ActiveActorDestructible enemy)`: Adds the logic to relocate the generated enemy if overlapping with any other enemies.

        **Reason**:
        - **Encapsulation**: By delegating enemy spawning and projectile generation to the EnemySpawner, the `LevelParent` class remains focused on its core role.
        - **Enhanced Gameplay**: Adding checking and relocation for enemies overlapping, enhancing the overall gameplay experience.

    10. **Enhanced Input Handling**

        **Old version**:
        - Input handling was directly embedded in the `LevelParent` class, with no specific logic for managing different types of input.
        - Not smooth when multiple keys are pressed simultaneously.

        **New version**:
        - The `InputHandler` class is introduced to manage user input:
            - `getOnKeyPressedHandler()`: Handles key press events.
            - `getOnKeyReleasedHandler()`: Handles key release events.
            - `update()`: Processes continuous input updates (e.g., movement).
            - `KeyStateTracker`: Integrated `KeyStateTracker` in `InputHandler` to make multiple keys input smoother.
            - Add the logic to prevent continuous shooting.

        **Reason**:
        - **Encapsulation**: By delegating input handling to the `InputHandler`, the `LevelParent` class remains focused on its core role.
        - **Flexibility**: The `InputHandler` allows for easier customization of input handling, such as adding new key bindings or handling complex input scenarios. Such as added using `ESCAPE` to pause the game.
        - **Enhanced Gameplay**: Make multiple keys input more smoother, make it easier to control the user plane movement. Prevent the continuous firing to make suer the game is not too easy due to the addition between projectiles. Enhancing the overall gameplay experience.


    11. **Win and Lose Menus**

        **Old version**:
        - The win and lose conditions were handled directly in the LevelParent class and show the win and lose image, without any interaction with player.
        **New version**:
        - The `winGame()` and `loseGame()` methods now display the WinMenu and LoseMenu, respectively:
            - `winGame()`: Stops the timeline, cleans up the level, and displays the `WinMenu` by calling `winMenu(scene).show()`.
            - `loseGame()`: Stops the timeline, cleans up the level, and displays the `LoseMenu` by calling `loseMenu(scene).show()`.
        - The `WinMenu` and `LoseMenu` prove interactive button for player to choose start a new game or quit.
        **Reason**:
        - **Enhanced User Experience**: Displaying specific menus for win and lose conditions improves the user experience by providing interactive buttons for different choices and a consistent syle menu. This improves the interaction and visual experience of the game.
        - **Encapsulation**: By delegating menu display to the WinMenu and LoseMenu classes, the LevelParent class remains focused on its core role.


    12. **Level Transition**

        **Old version**:
        - The transition to the next level was handled directly in the `LevelParent` class, with no specific logic for dynamically loading the next level.
        **New version**:
        - The `goToNextLevel(String levelName)` method now sets the `nextLevelProperty` to signal the transition to the next level.
        - The `nextLevelProperty` is a `StringProperty` that can be observed to dynamically load the next level.
        **Reason**:
        - **Reactive Programming**: The use of a `StringProperty` allows for reactive handling of level transitions, making the code more declarative and easier to follow.

    12. **Enhanced Game Loop**

        **Old version**:
        - The game loop was handled directly in the `LevelParent` class, with no specific logic for managing different types of updates.

        **New version**:
        - The updateScene() method is enhanced to handle various game logic operations:.
            - spawnEnemyUnits(): Spawns enemy units.
            - `actorManager.updateAllActors()`: Updates the state of all actors.
            - `enemySpawner.generateEnemyFire()`: Generates projectiles fired by enemies.
            - `updateNumberOfEnemies():` Updates the number of enemies.
            - `handleEnemyPenetration():` Handles enemy penetration.
            - `handleUserProjectileCollisions():` Handles collisions between user projectiles and enemy units.
            - `handleEnemyProjectileCollisions():` Handles collisions between enemy projectiles and friendly units.
            - `handlePlaneCollisions():` Handles collisions between planes.
            - `handleProjectileCollision():` Handles collisions between projectiles.
            - `actorManager.removeDestroyedActors()`: Removes destroyed actors.
            - `updateKillCount():` Updates the kill count.
            - `updateLevelView():` Updates the level view.
            - `checkIfGameOver():` Checks if the game is over.
            - `actorManager.removeOutOfBoundsProjectiles(1300)`: Removes out-of-bounds projectiles.
            - `inputHandler.update()`: Processes user input.

        **Reason**: 
        - **Encapsulation**: By delegating game logic operations to the appropriate manager and handler classes, the LevelParent class remains focused on its core role.
        - **Scalability**: The enhanced game loop allows for easier addition of new game logic operations or modifications to existing ones.
        - **Reusability**: The enhanced game loop can be reused in other parts of the game or for other levels.

    13. **Improved Package Structure**

        **Old version**:
        - The `LevelParent` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `LevelParent` class is now placed in a more meaningful package `com.example.demo.level`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


13. - **Class Name:**
    `LevelOne`
    - **Package:**
    `src/main/java/com/example/demo/level`
    - **Modification:**
    1. **Improved Package Structure**

        **Old version**:
        - The `LevelOne` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `LevelOne` class is now placed in a more meaningful package `com.example.demo.level`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

14. - **Class Name:**
    `LevelBoss` (formerly `LevelTwo`)
    - **Package:**
    `src/main/java/com/example/demo/level`
    - **Modification:**
    1. **Class Name Change**

        **Old version**:
        - The class was named `LevelTwo`, which implied it was the second level in the game.

        **New version**:
        - The class is now named `LevelBoss`, which clearly indicates that this level is a boss level.

        **Reason**:
        - **Clarity**: The new name explicitly communicates the purpose of the class, making it easier for developers to understand its role in the game.

    2. **NEXT_LEVEL Field**

        **Old version**:
        - Due to this is the last level in original game, there was no mention of a "next level" or how the game transitions after completing this level.

        **New version**:
        - There is a new level `LevelFinalBoss` after this level.
        - A new field `NEXT_LEVEL` is introduced to specify the fully qualified class name of the next level ("`com.example.demo.level.LevelFinalBoss`").

        **Reason**:
        - **Game Progression**: This field provides a clear indication of the game's progression flow, ensuring that the game knows which level to transition to after the boss is defeated.

    3. **Constructor Modification**

        **Old version**:
        - The constructor simply initialized the boss without passing the `root` by `getRoot()` method.

        **New version**:
        - The `boss` is initialized with the `root` by `getRoot()` method (`boss = new Boss(getRoot());`).

        **Reason**:
        - **Integration**: Passing `getRoot()` to the boss constructor allows the boss to be properly positioned and configured within the game's scene graph, such as health bar.

    4. **Game Over Logic**

        **Old version**:
        - Due to this is the final level in the original game, the `checkIfGameOver` method called `winGame()` if the boss was destroyed.

        **New version**:
        - The `checkIfGameOver` method now calls `goToNextLevel(NEXT_LEVEL)` if the boss is destroyed due to the introduction of new final level `LevelFinalBoss`.

        **Reason**:
        - **Game Progression**: This change ensures that the game transitions to the next level (`LevelFinalBoss`) instead of declaring a win.

    5. **Improved Package Structure**

        **Old version**:
        - The `LevelTwo` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `LevelTwo` class is now placed in a more meaningful package `com.example.demo.level`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


15. - **Class Name:**
    `LevelView`
    - **Package:**
    `src/main/java/com/example/demo/view`
    - **Modification:**
    1. **Removal of Win and Game Over Images**

        **Old version**:
        - The `LevelView` class included `WinImage` and `GameOverImage` components, with methods to display them `showWinImage()` and `showGameOverImage()`.

        **New version**:
        - Due to the introduction of `WinMenu` and `LoseMenu` with new display logic:
            - The `WinImage` and `GameOverImage` components have been removed from the `LevelView` class.
            - The methods `showWinImage()` and `showGameOverImage()` have been removed.

        **Reason**:
        - **Separation of Concerns**: The LevelView class is now focused solely on managing the heart display and other level-specific visual components. The responsibility for displaying win and game over menu has been moved to appropriate classes, which are `WinMenu`, `LoseMenu` and `LevelParent`.
        - **Keep the Code Clean**: Remove the unused code to keep the codebase clean.

    2. **Constructor Simplification**

        **Old version**:
        - The constructor initialized `winImage` and `gameOverImage` along with `heartDisplay`.

        **New version**:
        - Due to the introduction of `WinMenu` and `LoseMenu` with new display logic, there is no need for parameters `winImage` and `gameOverImage`.
        - The constructor now only initializes `heartDisplay`.

        **Reason**:
        - **Simplification**: The constructor is now simpler and focused solely on initializing the heart display without useless parameters.
        - **Encapsulation**: By removing unnecessary components, the constructor becomes easier to understand and maintain.

    3. **Addition of getHeartDisplay() Method**

        **Old version**:
        - There was no method to retrieve the `HeartDisplay` instance.

        **New version**:
        - A new method getHeartDisplay() has been added to return the HeartDisplay instance.

        **Reason**:
        - **Debugging and Testing**: This `getHeartDisplay()` is used for JUnit test, such as checking  the initialization of health value. 
        - **Encapsulation**: This method allows other parts of the code to access the `HeartDisplay` instance without directly exposing the internal structure of the `LevelView` class.

    4. **Improved Package Structure**

        **Old version**:
        - The `LevelView` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `LevelView` class is now placed in a more meaningful package `com.example.demo.view`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

16. - **Class Name:**
    `LevelViewLevelBoss`(formerly `LevelViewLevelTwo`)
    - **Package:**
    `src/main/java/com/example/demo/view`
    - **Modification:**
    1. **Class Name Change**

        **Old version**:
        - The class was named `LevelViewLevelTwo`, which implied it was specific to the second level of the game.
        **New version**:
        - The class is now named `LevelViewLevelBoss`, which clearly indicates that this class is specific to the level with boss.

        **Reason**:
        - **Clarity**: The new name explicitly communicates the purpose of the class, making it easier for developers to understand its role in the game.

    2. **`addImagesToRoot()` Modification**

        **Old version**:
        - The constructor simply initialized the `shieldImage` and called `addImagesToRoot()`.
        ```java
            private void addImagesToRoot() {
                root.getChildren().addAll(shieldImage);
            }
        ```
        **New version**:
        - The constructor now initializes the `shieldImage` and calls `addImagesToRoot()`, but with a focus on the `ShieldImage`'s container.
        ```java
            private void addImagesToRoot() {
                root.getChildren().addAll(shieldImage.getContainer());
            }
        ```

        **Reason**:
        - **Encapsulation**: By using `shieldImage.getContainer()`, the class ensures that only the container (a `Group`) is added to the root, encapsulating the internal structure of the `ShieldImage`.

    3. **Removal of `showShield()` and `hideShield()` Methods**

        **Old version**:
        - The class included `showShield()` and `hideShield()` methods to control the visibility of the shield image and handle the logic directly.

        **New version**:
        - The `showShield()` and `hideShield()` methods have been removed.
        - These logic will be handled by `ShieldManager` and the `LevelViewLevelBoss` will focus on is core functionalities.

        **Reason**:
        - **Separation of Concerns**: The responsibility for controlling the visibility of the shield image has been moved to the `ShieldManager` class.
        - **Encapsulation**: By removing these methods, the `LevelViewLevelBoss` class becomes simpler and more focused, improving encapsulation and maintainability.

    4. **Addition of `getRoot()` Method**

        **Old version**:
        - There was no method to retrieve the root `Group`.

        **New version**:
        - A new method `getRoot()` has been added to return the root Group.

        **Reason**:
        - **Encapsulation**: This method allows other parts of the code to access the root `Group` without directly exposing the internal structure of the `LevelViewLevelBoss` class.
        - **Debugging and Testing**: By providing access to the root Group, other classes can interact with it as needed, in this game it is used for JUnit test.

    5. **Improved Package Structure**

        **Old version**:
        - The `LevelViewLevelBoss` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `LevelViewLevelBoss` class is now placed in a more meaningful package `com.example.demo.view`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


17. - **Class Name:**
    `Projectile`
    - **Package:**
    `src/main/java/com/example/demo/projectile`
    - **Modification:**
    1. **Improved Package Structure**

        **Old version**:
        - The `Projectile` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `Projectile` class is now placed in a more meaningful package `com.example.demo.projectile`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.


18. - **Class Name:**
    `ShieldImage `
    - **Package:**
    `src/main/java/com/example/demo/view`
    - **Modification:**
    1. **Class Structure and Encapsulation**

        **Old version**:
        - The `ShieldImage` class directly extended `ImageView`, making it tightly coupled to the JavaFX `ImageView` class.

        **New version**:
        - The `ShieldImage` class no longer extends `ImageView`. Instead, it encapsulates an `ImageView` within an `HBox` container.

        **Reason**:
        - **Encapsulation**: By encapsulating the `ImageView` within an `HBox`, the class achieves better encapsulation. The `HBox` acts as a container for the `ImageView`, allowing for easier positioning and visibility management.
        - **Separation of Concerns**: The class is now responsible for managing the shield's behavior (e.g., visibility, positioning) rather than directly handling the rendering of the image. The `ShieldManager` will handle other responsibilities.

    2. **Use of `HBox` as a Container**

        **Old version**:
        - The `ShieldImage` class directly managed its own positioning and visibility.

        **New version**:
        - The `ShieldImage` class uses an `HBox` as a container for the ImageView. This container is responsible for positioning and visibility.

        **Reason**:
        - **Flexibility**: The HBox container allows for easier management of the shield's position and visibility. It also provides a clear separation between the shield's behavior and its visual representation.

    3. **Image Loading**

        **Old version**:
        - The image was loaded using a relative path `IMAGE_NAME`.

        **New version**:
        - The image is loaded using a `URL` obtained from the class's resource path.

        **Reason**:
        - **Resource Management**: Using `getClass().getResource()` ensures that the image is loaded from the correct resource path, avoiding issues with relative paths.
        - **Error Handling**: The new version includes error handling to print an error message if the image is not found.

    4. **Modified Management based on HBox**

        **Old version**:
        - The `setVisible()` method was directly called on the `ImageView`.
        - The `setLayoutX()` and `setLayoutY()` methods were directly called on the `ImageView`.

        **New version**:
        - The `setVisible()` method is called on the `HBox` container, which encapsulates the `ImageView`.
        - The `setLayoutX()` and `setLayoutY()` methods are called on the `HBox` container.

        **Reason**:
        - **Encapsulation**: The container approach hides the internal structure of the shield (e.g., the ImageView), making the class easier to maintain.

    4. **Thread Safety**

        **Old version**:
        - No thread safety measures were implemented.

        **New version**:
        - The `Platform.runLater()` method is used to ensure that all UI updates (e.g., visibility, positioning) are performed on the JavaFX application thread.

        **Reason**:
        - **Thread Safety**: JavaFX requires all UI updates to be performed on the JavaFX application thread. Using Platform.runLater() ensures that this requirement is met.

    5. **Error Handling**

        **Old version**:
        - No error handling was implemented for image loading.

        **New version**:
        - The class includes error handling to print an error message if the image is not found.

        **Reason**:
        - **Robustness**: Error handling improves the robustness of the class by providing feedback when an image cannot be loaded.
        - **Debugging**: The error message helps developers identify and resolve issues with missing resources.

    6. **Improved Package Structure**

        **Old version**:
        - The `ShieldImage` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `ShieldImage` class is now placed in a more meaningful package `com.example.demo.view`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

19. - **Class Name:**
    `UserPlane`
    - **Package:**
    `src/main/java/com/example/demo/actors/plane`
    - **Modification:**
    1. **Horizontal Movement**

        **Old version**:
        - The class only supported vertical movement `moveUp()`, `moveDown()` and `stop()`.

        **New version**:
        - The class now supports both vertical and horizontal movement `moveUp()`, `moveDown()`, `moveLeft()`, and `moveRight()`.
        - And stop movement by `stopVerticalMovement()` and `stopHorizontalMovement()` methods.

        **Reason**:
        - **Enhanced Gameplay**:  Adding methods for horizontal movement and stopping specific directions allows for more precise control over the plane's movement.
        - **Flexibility**:  Adding methods for horizontal movement and stopping specific directions allows for more precise control over the plane's movement.

    2. **Velocity Multipliers**

        **Old version**:
        - The class used a single `velocityMultiplier` for vertical movement.

        **New version**:
        - The class now uses separate `verticalVelocityMultiplier` and `horizontalVelocityMultiplier` for vertical and horizontal movement, respectively.

        **Reason**:
        - **Separation of Concerns**: By separating the velocity multipliers, the class can independently control vertical and horizontal movement, improving code clarity and maintainability.
        - **Flexibility**: This change allows for more complex movement patterns in the future, such as diagonal movement.

    3. **Projectile Firing**

        **Old version**:
        - The `fireProjectile()` method returned a single `UserProjectile`.

        **New version**:
        - The `fireProjectile()` method now returns a `List<ActiveActorDestructible>` containing the `UserProjectile`.

        **Reason**:
        - **Consistency**: Returning a list ensures that the method always returns a collection, even if it is empty. This simplifies the handling of projectiles in the calling code.
        - **Encapsulation**: By returning a list, the method encapsulates the logic for firing projectiles and provides a clear interface for the calling code.
        - **Scalability**: The list-based approach allows for future extensions.

    4. **Projectile Firing**

        **Old version**:
        - The `fireProjectile()` method returned a single `UserProjectile`.

        **New version**:
        - The `fireProjectile()` method now returns a `List<ActiveActorDestructible>` containing the `UserProjectile`.

        **Reason**:
        - **Consistency**: Returning a list ensures that the method always returns a collection, even if it is empty. This simplifies the handling of projectiles in the calling code.
        - **Encapsulation**: By returning a list, the method encapsulates the logic for firing projectiles and provides a clear interface for the calling code.
        - **Scalability**: The list-based approach allows for future extensions.

    5. **Velocity Change for FPS**

        **Old version**:
        - The `VERTICAL_VELOCITY` was a fixed value `8`.

        **New version**:
        - The `MOVEMENT_VELOCITY` is now adjusted based on the `VELOCITY_CHANGE` factor from the `LevelParent` class.
        ```java
            private static final double MOVEMENT_VELOCITY = (double) 9 / VELOCITY_CHANGE;
        ```

        **Reason**:
        - **Consistency**: Ensures that the projectile's movement is consistent with the overall game mechanics, even if the game's FPS changes.
        - **Flexibility**: This change allows for easier adjustments to the game's overall speed without modifying individual classes.

    6. **Improved Package Structure**

        **Old version**:
        - The `UserPlane` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `UserPlane` class is now placed in a more meaningful package `com.example.demo.actors.plane`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

19. - **Class Name:**
    `UserProjectile`
    - **Package:**
    `src/main/java/com/example/demo/actors/projectile`
    - **Modification:**
    1. **Velocity Adjustment for FPS**

        **Old version**:
        - The `HORIZONTAL_VELOCITY` was a fixed value `15`.

        **New version**:
        - The `HORIZONTAL_VELOCITY` is now adjusted based on the `VELOCITY_CHANGE` factor from the `LevelParent` class (`20 / VELOCITY_CHANGE`).

        **Reason**:
        - **Consistency**: Adjusting the velocity based on a global factor ensures that the projectile's movement is consistent with other game elements, such as enemies or other projectiles.
        - **Flexibility**: This change allows for easier adjustments to the game's overall speed without modifying individual classes.

    2. ** Image Height Adjustment**

        **Old version**:
        - The `IMAGE_HEIGHT` was set to `125`.

        **New version**:
        - Due to cutting the empty space for adjust the HittingBox, the `IMAGE_HEIGHT` is now set to `6`.

        **Reason**:
        - **Visual Consistency**: The new image height is likely more appropriate for the game's visual style, ensuring that the projectile appears correctly scaled and aligned with other game elements.
        - **Gameplay Balance**: Adjusting the image height may also affect gameplay balance, such as collision detection or visual clarity.

    3. **Improved Package Structure**

        **Old version**:
        - The `UserProjectile` class was placed in a generic package which is `com.example.demo`, which did not reflect its specific role in the application.

        **New version**:
        - The `UserProjectile` class is now placed in a more meaningful package `com.example.demo.actors.projectile`.

        **Reason**:
        - **Organization**: By grouping related classes together, the package structure becomes more intuitive and easier to navigate.

### Removed Classes
1. - **Removed Class:**
    `GameOverImage`
    - **Replace By:**
    `LoseMenu` 
    - **Benefits:**
    Replacing the `GameOverImage` class with the `LoseMenu` class significantly enhances the game over screen's functionality, user experience, and maintainability. The new class provides a dynamic, interactive menu with audio effects, responsive design, and flexible functionality, making the game over screen more engaging and user-friendly. These improvements align with modern software design principles, ensuring a robust and maintainable codebase.


2. - **Removed Class:**
    `WinImage`
    - **Replace By:**
    `WinMenu` 
    - **Benefits:**
    Replacing the `WinImage` class with the `WinMenu` class significantly enhances the win screen's functionality, user experience, and maintainability. The new class provides a dynamic, interactive menu with audio effects, responsive design, and flexible functionality, making the win screen more engaging and user-friendly. These improvements align with modern software design principles, ensuring a robust and maintainable codebase.

## Unexpected Problems
### Problem:
**Uncontrolled Movement of User Plane Post-Pause**
- **Description:** 
- **Analysis:** The `KeyStateTracker` continue tracking key states during the game pause, leading to outdated key states influencing movement after resume.

### Attempted Solutions and Final Resolution:
1. **Initial Approach:**
   - **Action:** Implemented a method `resetKeyStates()` within `KeyStateTracker` to clear all key states.
     ```java
     public void resetKeyStates() {
         keyStateMap.clear();
     }
     ```
   - **Integration:** Invoked `resetKeyStates()` within the pause logic:
     ```java
     if (kc == KeyCode.ESCAPE) {
         if (pauseGameCallback != null) {
             pauseGameCallback.run();
             keyStateTracker.resetKeyStates();
         }
     }
     ```
   - **Outcome:** Post-resume control was regained by pressing the movement keys again, but the plane still initially moved in the old direction.

2. **Adjusting Resume Logic:**
   - **Adjustment:** Attempted to reset key states upon game resumption and ensured `KeyStateTracker` was used as a singleton, assuming a global tracking might resolve the issue.
   - **Code Adjustment:**
     ```java
     private void resumeGame() {
         AudioManager.getInstance().playButtonClickEffect();
         stage.setScene(gameScene);
         AudioManager.getInstance().resumeBackgroundMusic();
         KeyStateTracker.getInstance().resetKeyStates();
         onResume.run();
     }
     ```
   - **Outcome:** The initial direction issue persisted, it means there are other issues.

3. **Stop Movement before Pause:**
   - **Refinement:** Added commands to stop the UserPlane's movement immediately when the pause is triggered

     ```java
     if (kc == KeyCode.ESCAPE) {
         if (pauseGameCallback != null) {
             pauseGameCallback.run();
             keyStateTracker.resetKeyStates();
             userPlane.stopVerticalMovement();
             userPlane.stopHorizontalMovement();
         }
     }
     ```
   - **Outcome:** This resolved the uncontrolled movement after pausing, but introduced a new issue where restarting the game caused uncontrolled movements.

4. **Revising Singleton Implementation:**
   - **Final Adjustment:** Removed the singleton pattern from `KeyStateTracker` and excluded `resetKeyStates()` from the resume logic. Using connment for testing and debugging to findout the problem.
   - **Final Working Solution:** The modifications led to correctly handling key states around game pauses and restarts without uncontrolled movements.

Through a series of iterative debugging and modifications, the issue of uncontrolled movement after game pauses was successfully resolved.


### Problem (Also a funny story):
**Can't fire when flying down right**
- **Description:** When the userplane flying to down left direction, the userplane can't fire when I press the `SPACE`.
- **Analysis:** Something wrong with the input logic

### Attempted Solutions and Final Resolution:
1. **Initial Approach:**
   - **Action:** Check the logic and try to find out the logic error.
   - **Outcome:** After twenty minutes, i didn't find any logic error. Then I let ChatGPT check the code, ChatGPT also didn't find the logic error

2. **The opportunity to find out the reason:**
    - **How I find out:** During the computing lab of DMS after I found this issue, I was using my laptop and suddently I found that the userplane can fire when moving to down left. Then I thought it might be the hardware issue. After class, I tried to use a game keyboard for testing, the issue that can't fire doesn't anymore.

Using a N-Key Rollover keyboard is important for game testing in case of "ghosting".