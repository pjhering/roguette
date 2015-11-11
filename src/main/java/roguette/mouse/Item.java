package roguette.mouse;

public class Item {
    
    public static final int CHEESE = 0;
    public static final int FLUFF = 1;

    private final int type;
    
    public Item(int type) {
        
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
