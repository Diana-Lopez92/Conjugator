package Model;

import View.ConjugatorView;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Diana
 */
public class PresentQueries extends DB_Connection {
    private ConjugatorView view;
    
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
            
            int i = 0;
            while(rs.next()){
                // Se alamcenan en un arreglo los registros de la BD
                // DB records are storaged in an array
                lista[i] = rs.getString(2);
                i++;
                System.out.println("Queries: ");
                System.out.println(rs.getString(2));
            }
            
            System.out.println("*****Lista: ******");
            for(int x = 0; x < 3; x++)
                System.out.println(lista[x]);
            
            
            
            return lista;           
        }catch(SQLException ex){
            System.err.println(ex);
        }finally{
            try{
                conn.close();
            }catch(SQLException ex){
                System.err.println(ex);
            }
        }    
        return lista;
        
    }
    
    //Métodos para evaluar 
    // Methods to evaluate
    public int[] check(Presente pre){
        // Se reciben los valores del modelo
        // Model values are received
        String verbo = pre.getVerbo();
        String je = pre.getJe();
        String tu = pre.getTu();
        String il = pre.getIl();
        String nous = pre.getNous();
        String vous = pre.getVous();
        String ils = pre.getIls();
        System.out.println("*******Valores de los campos: \n" + verbo + "\n" + je + "\n" + tu + "\n" + il+ "\n" + nous+ "\n" + vous + "\n" + ils);
        
        // Se obtienen las respuestas correctas de la BD
        // Right answers are gotten from BD
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = getConnection();
        String sql = "SELECT * FROM presente WHERE verbo='" + verbo + "'";
        System.out.println(sql);
        // Se crean variables para almacenar los datos recibidos por la BD
        // Variables are created to store the data received from DB
        String jeDB, tuDB, ilDB, nousDB, vousDB, ilsDB;
        // Arreglo para indicar las respuestas buenas y malas
        // Array to indicate the right and wrong answers
        int [] checklist = new int[6];
        
        try{
            // Se prepara la sentencia
            // Statement is prepared
            ps = conn.prepareStatement(sql);
            // Se ejecuta la query
            // Query is executed
            rs = ps.executeQuery();
            // Se almacenan los registros en el arreglo
            // Records are stored in the array
            //System.out.println("\n****************************************************************\n");
            int i = 0;
            
            // Si se encuentra un registro se retornan los datos
            // If a record is found, the data is return
            if(rs.next()){
                jeDB = rs.getString(3);
                tuDB = rs.getString(4);
                ilDB = rs.getString(5);
                nousDB = rs.getString(6);
                vousDB = rs.getString(7);
                ilsDB = rs.getString(8);
                
                //System.out.println(jeDB + "\n" + tuDB + "\n" + ilDB + "\n" + nousDB + "\n" + vousDB + "\n" + ilsDB);
                
                // Contador de respuestas correctas
                // Good answers counter
                int good = 0;
                                
                // Se verifican las respuestas
                // Answers are check them
                if(je.equals(jeDB)){
                    good++;
                    //view.je.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                    checklist[0] = 1;
                }
                else if(!je.equals(jeDB)){
                    checklist[0] = 0;
                }
                
                if(tu.equals(tuDB)){
                    good++;
                    checklist[1] = 1;
                }
                else if(!tu.equals(tuDB)){
                    checklist[1] = 0;
                }
                
                if(il.equals(ilDB)){
                    good++;
                    checklist[2] = 1;
                }
                else if(!il.equals(ilDB)){
                    checklist[2] = 0;
                }
                
                if(nous.equals(nousDB)){
                    good++;
                    checklist[3] = 1;
                }
                else if(!nous.equals(nousDB)){
                    checklist[3] = 0;
                }
                
                if(vous.equals(vousDB)){
                    good++;
                    checklist[4] = 1;
                }
                else if(!vous.equals(vousDB)){
                    checklist[4] = 0;
                }
                
                if(ils.equals(ilsDB)){
                    good++;
                    checklist[5] = 1;
                }
                else if(!ils.equals(ilsDB)){
                    checklist[5] = 0;
                }
                System.out.println("\nAciertos: " + good);
                JOptionPane.showMessageDialog(null, "Aciertos: " + good);
            }
            
        }catch(SQLException ex){
            System.err.println(ex);
        }finally{
            try{
                conn.close();
            }catch(SQLException ex){
                System.err.println(ex);
            }
        }
        return checklist;
    }
}
