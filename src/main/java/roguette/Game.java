package roguette;

public interface Game {
    
    public void keystroke(int keyId, int modifiers);
    
    public void tick(int timerId, int count);
    
    public void render(Console console);
}
