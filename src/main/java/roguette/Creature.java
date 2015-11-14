package roguette;

import static java.util.UUID.randomUUID;

public class Creature {

    private final int id;
    private final int type;

    public Creature(int type) {

        this.id = randomUUID().hashCode();
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
