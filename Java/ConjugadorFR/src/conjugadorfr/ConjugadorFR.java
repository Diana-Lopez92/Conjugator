package conjugadorfr;

import Controller.PresentCtrl;
import Model.DB_Connection;
import Model.PresentQueries;
import Model.Presente;
import View.ConjugatorView;

/**
 *
 * @author Diana
 */
public class ConjugadorFR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*DB_Connection test= new DB_Connection();
        test.getConnection();*/
        Presente mod = new Presente();
        PresentQueries modQ = new PresentQueries();
        ConjugatorView view = new ConjugatorView();
        
        PresentCtrl ctrl = new PresentCtrl(mod, modQ, view);
        ctrl.launch();
        view.setVisible(true);
    }
    
}
