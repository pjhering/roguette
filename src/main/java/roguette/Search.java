package roguette;

import java.util.List;


public interface Search<T> {
    
    List<T> getPath(T root);
}
