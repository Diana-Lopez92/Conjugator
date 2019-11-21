package Controller;

import Model.PresentQueries;
import Model.Presente;
import View.ConjugatorView;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 *
 * @author Diana
 */
public class PresentCtrl {
    private Presente mod;
    private PresentQueries modQ;
    private ConjugatorView view;
    
    // Se crea el constructor para recibir el modelo y la vista
    // Constructor is created to receive the model and view
    public PresentCtrl(Presente mod, PresentQueries modQ, ConjugatorView view){
        this.mod = mod;
        this.modQ = modQ;
        this.view = view;
    }
    
    public void launch(){
        view.setTitle("Conjugateur Français ");
        view.setLocationRelativeTo(null);
        modQ.list();
        //***************************************
        String [] lista = modQ.list();
        System.out.println("*****************Test lista: ");
        view.presentList.removeAllItems();
        for(int i = 0; i < lista.length; i++){
            view.presentList.addItem(lista[i]);
            System.out.println(lista[i]);
        }
        //*************************

        int cont = 0;
        String prueba;
        
        prueba = Arrays.toString(modQ.list());
        
        //lista [] = Arrays.toString(modQ.list());
        System.out.println("Valor de retorno: ");
        System.out.println(Arrays.toString(modQ.list()));
        System.out.println("*******String: ");
        System.out.println(prueba);
        //view.presentList.removeAllItems();
        //view.presentList.addItem("Hola");
        //view.presentList.addItem(Arrays.toString(modQ.list()));
    }
}
