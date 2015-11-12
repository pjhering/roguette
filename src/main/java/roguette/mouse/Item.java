package roguette.mouse;

public class Item implements Types {

    private final int type;
    
    public Item(int type) {
        
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
