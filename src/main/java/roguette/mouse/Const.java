package roguette.mouse;

import java.util.Random;

public interface Const {

    int PLAY = 0;
    int WON = 1;
    int LOST = 2;

    int COLUMNS = 91;
    int ROWS = 91;

    int WALL = 0;
    int FLOOR = 1;
    int HOME = 2;
    int CHEESE = 3;
    int FLUFF = 4;
    int CAT = 5;
    int MOUSE = 6;
    
    int SEEKING_WALL = 0;
    int FOLLOWING_WALL = 1;
    int PURSUING = 2;
    int ATTACKING = 3;
    
    int NORTH = 0;
    int WEST  = 1;
    int SOUTH = 2;
    int EAST  = 3;
    
    int RIGHT    = 1;
    int FORWARD = 0;
    int LEFT     = -1;
    
    Random RANDOM = new Random();
    
    String[] YOU_WIN = new String[]{
        "                                          ",
        " #   #  ###  #   #   #   #  ### #   #   # ",
        "  # #  #   # #   #   #   #   #  ##  #   # ",
        "   #   #   # #   #   # # #   #  # # #   # ",
        "   #   #   # #   #   # # #   #  #  ##     ",
        "   #    ###   ###    ## ##  ### #   #   # ",
        "                                          "
    };
    
    String[] YOU_LOSE = new String[]{
        "                                                 ",
        " #   #  ###  #   #   #      ###   #### #####   # ",
        "  # #  #   # #   #   #     #   # #     #       # ",
        "   #   #   # #   #   #     #   #  ###  ###     # ",
        "   #   #   # #   #   #     #   #     # #         ",
        "   #    ###   ###    #####  ###  ####  #####   # ",
        "                                                 "
    };
}
