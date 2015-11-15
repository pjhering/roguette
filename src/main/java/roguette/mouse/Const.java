package roguette.mouse;

import java.util.Random;

public interface Const {

    int PLAY = 0;
    int WON = 1;
    int LOST = 2;

    int COLUMNS = 85;
    int ROWS = 85;

    int WALL = 0;
    int FLOOR = 1;
    int HOME = 2;
    int CHEESE = 3;
    int FLUFF = 4;
    int CAT = 5;
    int MOUSE = 6;
    
    int PATROLLING = 0;
    int PURSUING = 1;
    int ATTACKING = 2;
    
    int NORTH = 0;
    int WEST = 1;
    int SOUTH = 2;
    int EAST = 3;
    
    int RIGHT = 0;
    int LEFT = 1;
    
    Random RANDOM = new Random();
}
