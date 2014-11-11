package forms;

import java.awt.*;

/**
 * Created by SER on 10.11.2014.
 */
public abstract class ManagedForm {
    public void init(){}
    public abstract String getTitle();
    public abstract Container getRootContainer();
}
