package Controller;

import Model.PresentQueries;
import Model.Presente;
import View.ConjugatorView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author Diana
 */
public class PresentCtrl implements ActionListener{
    private Presente pre;
    private PresentQueries modQ;
    private ConjugatorView view;
    
    // Se crea el constructor para recibir el modelo y la vista
    // Constructor is created to receive the model and view
    public PresentCtrl(Presente pre, PresentQueries modQ, ConjugatorView view){
        this.pre = pre;
        this.modQ = modQ;
        this.view = view;
        this.view.presentButton.addActionListener(this);
    }
    
    public void launch(){
        view.setTitle("Conjugateur Français ");
        view.setLocationRelativeTo(null);
        modQ.list();
        //***************************************
        //Obtengo la lista de verbos del modelo / Verbs list is gotten from the model
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
        //view.check1.setIcon(new ImageIcon("imgOk.png"));
        //view.check1.setVisible(true);
        //view.check1.setBounds(10, 10, 10, 10);
        //view.check1.setText("TEST");
        
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.presentButton){
            // Se obtiene y se coloca el valor de cada campo al modelo
            // The value of each field is gotten and set it to the model
            pre.setVerbo((String) view.presentList.getSelectedItem());
            pre.setJe(view.je.getText());
            pre.setTu(view.tu.getText());
            pre.setIl(view.il.getText());
            pre.setNous(view.nous.getText());
            pre.setVous(view.vous.getText());
            pre.setIls(view.ils.getText());
            // Se envia el modelo/objeto al método evaluar y se obtiene el arreglo con las respuestas evaluadas 
            // The model/object is sent it to the check method and the checked answers are gotten
            int [] answersList = modQ.check(pre);
            
            // De acuerdo a la evaluación se le da un color al borde del campo
            // In accordance to the evaluation is given a color to the border field **This is optional**
            // In accordance to the evaluation a gren or red mark is set it
            if(answersList[0] == 1){
                //view.je.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));  
                view.check1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[0] == 0){
                //view.je.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
            if(answersList[1] == 1){
                //view.tu.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                view.check2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[1] == 0){
                //view.tu.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
            if(answersList[2] == 1){
                //view.il.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                view.check3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[2] == 0){
                //view.il.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
            if(answersList[3] == 1){
                //view.nous.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                view.check4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[3] == 0){
                //view.nous.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
            if(answersList[4] == 1){
                //view.vous.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                view.check5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[4] == 0){
                //view.vous.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
            if(answersList[5] == 1){
                //view.ils.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                view.check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgOk.png")));
            }
            else if(answersList[5] == 0){
                //view.ils.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                view.check6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imgNok.png")));
            }
        }
    }
    
}
