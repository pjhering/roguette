package roguette.games.mouse;

import java.util.List;
import java.util.Objects;
import roguette.AStar;
import roguette.App;
import roguette.Console;
import roguette.Game;
import roguette.Item;
import roguette.Items;
import roguette.Keys;
import roguette.Location;
import roguette.Model;
import roguette.Movement;
import roguette.Occupant;
import roguette.SwingApp;

public class MouseGame implements Game {

    public static final int PLAYER_ACTIVE = 0;
    public static final int PLAYER_WON = 1;
    public static final int PLAYER_LOST = 2;
    
    public static final int CHEESE = 10;
    public static final int FLUFF = 11;
    public static final int HOME = 12;
    public static final int HEALTH = 13;
    public static final int COLLECTED = 14;
    public static final int CAT = 15;

    private final Model model;
    private final AStar astar;
    private final Movement movement;
    private int gameState;
    private int fluffCount;
    private App app;

    public MouseGame() {

        model = new MouseModel();
        astar = new MouseAStar(model);
        movement = new Movement(model);
        app = new SwingApp(this, model.size);
        app.setTimer(5000, 1000);
        app.start();
    }

    public static void main(String[] args) {
        
        MouseGame game = new MouseGame();
        game.app.start();
    }

    @Override
    public void keystroke(int keyId, int modifiers) {

        Occupant player = model.player;

        switch (keyId) {

            case Keys.KEY_UP:
            case Keys.KEY_W:
                player.moved = true;
                player.direction = Location.UP;
                break;

            case Keys.KEY_DOWN:
            case Keys.KEY_S:
                player.moved = true;
                player.direction = Location.DOWN;
                break;

            case Keys.KEY_LEFT:
            case Keys.KEY_A:
                player.moved = true;
                player.direction = Location.LEFT;
                break;

            case Keys.KEY_RIGHT:
            case Keys.KEY_D:
                player.moved = true;
                player.direction = Location.RIGHT;
                break;
        }
    }

    @Override
    public void tick(int timerId, int count) {

        if (gameState == PLAYER_ACTIVE) {

            movePlayer();

            if (count % 3 == 0) {

                moveCats();
            }
        }
    }

    private void moveCats() {

        Occupant player = model.player;

        for (Occupant cat : model.occupants(CAT)) {

            double distance = cat.location.distance(player.location);

            if (distance <= 1) {

                // do attack
                player.attributes.decrement(HEALTH, 5);

                if (player.attributes.value(HEALTH) <= 0) {

                    gameState = PLAYER_LOST;
                    return;
                }

            } else if (distance <= 10) {

                // do persue
                List<Location> path = astar.path(cat.location, player.location);

                if (!path.isEmpty()) {

                    cat.direction = cat.location.direction(path.get(0));
                    model.move(cat);
                }

            } else {

                // do wander
                cat.direction = movement.followLeftWall(cat);
                model.move(cat);
            }
        }
    }

    private void movePlayer() {

        Occupant player = model.player;

        if (player.moved) {

            Occupant cat = model.occupant(player.location, player.direction);

            if (cat == null) {

                model.move(player);

                Items items = model.items(player.location);

                if (items.has(CHEESE)) {

                    int count = items.remove(CHEESE).size();
                    player.attributes.increment(HEALTH, count);

                } else if (!player.location.is(HOME) && items.has(FLUFF)) {

                    player.takeAll(items.remove(FLUFF));
                }
            } else {

                player.decrement(HEALTH, 5);

                if (player.attributes.value(HEALTH) <= 0) {

                    gameState = PLAYER_LOST;
                }
            }

            if (player.location.is(HOME)) {

                Items items = model.items(player.location);
                List<Item> fluff = player.dropAll(FLUFF);
                player.attributes.increment(COLLECTED, fluff.size());
                items.add(fluff);

                if (player.attributes.value(COLLECTED) == fluffCount) {

                    gameState = PLAYER_WON;
                }
            }
        }
    }

    @Override
    public void render(Console console) {

        switch (gameState) {

            case PLAYER_ACTIVE:
                renderActive(console);
                break;

            case PLAYER_WON:
                renderWon(console);
                break;

            case PLAYER_LOST:
                renderLost(console);
                break;
        }
    }

    private void renderActive(Console console) {
    }

    private void renderWon(Console console) {
    }

    private void renderLost(Console console) {
    }
}
