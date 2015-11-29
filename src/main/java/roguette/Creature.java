package roguette;

import java.util.HashMap;
import java.util.Map;
import static java.util.UUID.randomUUID;

public class Creature {

    private final int id;
    private final int type;
    private final Map<String, Object> properties;

    public Creature(int type) {

        this.id = randomUUID().hashCode();
        this.type = type;
        this.properties = new HashMap<>();
    }
    
    public <T> T get(String key, Class<T> c) {
        return (T) properties.get(key);
    }
    
    public <T> T set(String key, T value) {
        return (T) properties.put(key, value);
    }
    
    public Object remove(String key) {
        return properties.remove(key);
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
