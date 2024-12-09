# COMP2042 CrouseWork

## Table of Contents
1. [Github Respository](#github-repository)
2. [Compilation Instructions](#compilation-instructions)

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
1. **Resolve Level Transition Bug::** Amend the file extension of the Shield Image from .jpg to .png and implement null checks to avoid `NullPointerException` during image loading, ensuring smooth transitions between levels.

2. **Fix the shield displaying issue:** To fix the shield display in `LevelTwo`, shield management was integrated directly into the scene and refactored for better initialization and visibility control. The setup was moved from `Boss` to `LevelViewLevelTwo` for clearer separation, enhanced threading safety, and improved code clarity with detailed Javadoc comments.

3. **Memeory leak caused by projectiles:** To address the memory leak issue caused by projectiles not being removed, introduce logic to check the screen boundaries. If a projectile is detected outside these boundaries, it will be automatically removed from memory. This ensures efficient resource management and prevents potential performance degradation.

### Implemented Feature
1. **User Plane Horizontal Movement:** 
Added horizontal movement capabilities to the user-controlled plane allowing it to fly left and right in addition to the existing up and down motions.

2. **Start Menu:** 
The `StartMenu` of the Sky Battle game serves as the primary interface for player interaction, offering a visually appealing layout with a high-resolution background, interactive buttons for game operations like start, tutorial, mute, exit, and advanced audio management. It provides a dynamic and responsive design, ensuring a consistent user experience across various devices, and includes detailed game instructions through a tutorial overlay, enhancing user engagement.

3. **Pause Menu Features:**
The `PauseMenu` enhances gameplay management with options to resume, restart, or quit the game, and comprehensive audio controls for muting and volume adjustment. Styled for visibility and responsiveness, it centralizes essential game and sound settings in a user-friendly interface.


