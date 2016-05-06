package Controller;

/**
 * Created by Splitix on 4/17/16.
 */
import Model.Model;
import View.View;

public interface Controller {
    void setModel(Model model);
    Model getModel();
    View getView();
    void setView(View view);
}
