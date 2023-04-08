package comands;

import java.io.IOException;

public interface Command {
    void execute(String par) throws IOException;
    void description();
}
