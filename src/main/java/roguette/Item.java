package roguette;

import java.util.HashMap;
import java.util.Map;

public class Item {

    private final int type;
    private final Map<String, Object> properties;

    public Item(int type) {

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
}
