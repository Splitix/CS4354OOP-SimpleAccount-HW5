package View;

/**
 * Created by Splitix on 4/17/16.
 */
import Model.Model;
import Controller.Controller;

public interface View {
    Controller getController();
    void setController(Controller controller);
    Model getModel();
    void setModel(Model model);
}
