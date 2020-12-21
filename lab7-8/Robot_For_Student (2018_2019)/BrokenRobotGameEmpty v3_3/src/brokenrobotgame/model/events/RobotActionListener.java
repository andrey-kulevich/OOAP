package brokenrobotgame.model.events;

import java.util.EventListener;

public interface RobotActionListener extends EventListener {
    void robotMadeMove(RobotActionEvent e);
}
