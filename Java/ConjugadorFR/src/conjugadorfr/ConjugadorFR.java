package conjugadorfr;

import Model.DB_Connection;

/**
 *
 * @author Diana
 */
public class ConjugadorFR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB_Connection test= new DB_Connection();
        test.getConnection();
    }
    
}
