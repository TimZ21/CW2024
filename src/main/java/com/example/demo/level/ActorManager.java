package com.example.demo.level;

import com.example.demo.actors.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.List;

public class ActorManager {

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;

    public ActorManager(List<ActiveActorDestructible> friendlyUnits,
                        List<ActiveActorDestructible> enemyUnits,
                        List<ActiveActorDestructible> userProjectiles,
                        List<ActiveActorDestructible> enemyProjectiles,
                        Group root) {
        this.friendlyUnits = friendlyUnits;
        this.enemyUnits = enemyUnits;
        this.userProjectiles = userProjectiles;
        this.enemyProjectiles = enemyProjectiles;
        this.root = root;
    }

    // Method to update all actors
    public void updateAllActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    // Method to remove destroyed actors
    public void removeDestroyedActors() {
        removeActorsFromList(friendlyUnits);
        removeActorsFromList(enemyUnits);
        removeActorsFromList(userProjectiles);
        removeActorsFromList(enemyProjectiles);
    }

    // Helper method to remove destroyed actors from a list
    private void removeActorsFromList(List<ActiveActorDestructible> actors) {
        actors.removeIf(ActiveActorDestructible::isDestroyed);
        root.getChildren().removeIf(node -> node instanceof ActiveActorDestructible && ((ActiveActorDestructible) node).isDestroyed());
    }
}
