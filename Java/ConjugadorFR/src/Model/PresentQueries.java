package Model;

import View.ConjugatorView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Diana
 */
public class PresentQueries extends DB_Connection {
    
    public String[] list(){
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();//esto es el modelo
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        String sql = "SELECT * FROM presente";
        ConjugatorView view = new ConjugatorView();
        String [] lista = new String[3];
        
        try{
            // Se prepara la sentencia 
            // Statement is prepared
            ps = conn.prepareStatement(sql);
            // Se ejecuta la query
            // Query is executed
            rs = ps.executeQuery();
            
            // Se mandan los registros de la BD al comboBox
            // DB Records are sent to ComboBox
            //view.presentList.addItem("Selecciona un verbo");
            //modeloCombo.addElement("Seleccione un campo");//es el primer registro q mostrara el combo
            //view.presentList.setModel(modeloCombo);//con esto lo agregamos al objeto al jcombobox
            //view.presentList.removeAll();
            
            int i = 0;
            while(rs.next()){
                 //modeloCombo.addElement(rs.getString(2));
                 //view.presentList.setModel(modeloCombo);
                //view.presentList.addItem(rs.getString(2));
                lista[i] = rs.getString(2);
                i++;
                System.out.println("Queries: ");
                System.out.println(rs.getString(2));
                //return true;
            }
            
            System.out.println("Lista: ");
            for(int x = 0; x < 3; x++)
                System.out.println(lista[x]);
            
            
            
            return lista;
            //pre.setVerbo(rs.getString("verbo"));
            //return false;
            
        }catch(SQLException ex){
            System.err.println(ex);
            //return false;
        }finally{
            try{
                conn.close();
            }catch(SQLException ex){
                System.err.println(ex);
            }
        }    
        return lista;
        
    }
}
