package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

//PUTO
//putos todos

public class Controller{
    @FXML
    Button btnCargar;
    @FXML
    BorderPane bpVentana;

    int Columnas=0, Filas=0;
    GridPane gpTerreno = new GridPane();

    public List<String> numeroArchivo = new ArrayList<String>(); //Guardamos los numeros del txt
    public List<String> listTerrenos = new ArrayList<String>(); // Lista de terrenos
    public static List<String> numeroArchivo_Copia = new ArrayList<String>();
    public static List<String> listTerrenos_Copia = new ArrayList<String>(); // Lista de terrenos


    boolean archivoCargado=false;

    public void cargaMapa () throws IOException{ //Cargamos y leemos el mapa para presentar el grid

        cargarArchivo();//Llamaos al método de carga de archivo y conteo de fila y columnas

        bpVentana.setCenter(gpTerreno);


        gpTerreno.setGridLinesVisible(true);
        gpTerreno.getColumnConstraints().clear(); //Elimina columnas y filas
        gpTerreno.getRowConstraints().clear();

        for(int i=0; i<Columnas; i++) { //Agrega las columnas deseadas
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setHgrow(Priority.ALWAYS);
            colConst.setPercentWidth(100.0/Columnas);
            gpTerreno.getColumnConstraints().add(colConst);
        }

        for(int i=0; i<Filas; i++) { //Agrega las filas deseadas
            RowConstraints rowConst = new RowConstraints();
            rowConst.setVgrow(Priority.ALWAYS);
            rowConst.setPrefHeight(100.0/Filas);
            gpTerreno.getRowConstraints().add(rowConst);
        }

        terrenoPreliminar();
        pruebaLista();
        archivoCargado=true;
    }

    public void cargarArchivo () throws ArrayIndexOutOfBoundsException, IOException{ //Cargamos el archivo y almacenamos lo que tiene
        Filas=0;
        Columnas=0;
        numeroArchivo.clear(); //Limpia la lista antes de agregar

        File archivo;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione el mapa a cargar");
        archivo = fileChooser.showOpenDialog(new Stage());

        if(archivo!= null) {
            FileInputStream stream = new FileInputStream(archivo);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
            String linea=null; //Contiene cada linea
            String[] Columna=new String[100];//Un valor para inicializar el arreglo
            int filas=0;
            boolean conteoColumaas=false;
            while ((linea=entrada.readLine()) != null) {//Leemos
                if(conteoColumaas==false) {
                    Columna=linea.split(",");//Borra la ,
                    conteoColumaas=true;
                }
                String[] numeros=linea.split(",");

                for(int i=0; i<Columna.length;i++)//Usamos el valor de Columnas y añadimos a la lista del arreglo de numeros
                    numeroArchivo.add(numeros[i]);

                filas++;
            }
            //Ponemos lso valores
            Filas=filas;
            Columnas=Columna.length;
        }
    }

    public void terrenoPreliminar() throws IOException { //Agregar imageviews t cargar previamente las imágenes
        int numColumna=0, numFila=0;

        /*
        for(int i=0; numColumna<Columnas; i++){//Contador auxiliar para circular por la lista y agregar el terreno
            //correspondiente, verificado si el contenido es lo que el usuario puso como tal. Por ejemplo
            //Si en la lista lee un 3 y el usuario indicó que el 3 fuera agua, se pone el agua en ese lugar
            //También se podría generar una matriz con los terrenos para un uso posterior con la IA, esto para verificar
            //Si está en esa posición haga lo de los costos
            numFila=0;
            for(int j=0; numFila<Filas; j++) {//Llena la cuadrícula del grif
                Image imagen1 = new Image(getClass().getResourceAsStream("pj.jpg"));
                Label terreno1 = new Label();
                terreno1.setGraphic(new ImageView(imagen1));
                gpTerreno.add(terreno1, numColumna, numFila);
                numFila++;
            }
            numColumna++;
        }
        */

        int aux=0;
        while (numFila<Filas){
            numColumna=0;
            while (numColumna<Columnas){

                //Labels preliminares
                Label lb0 = new Label("Terreno 0");
                Label lb1 = new Label("Terreno 1");
                Label lb2 = new Label("Terreno 2");
                Label lb3 = new Label("Terreno 3");
                Label lb4 = new Label("Terreno 4");
                Label lb5 = new Label("Terreno 5");
                Label lb6 = new Label("Terreno 6");
                Label lb7 = new Label("Terreno 7");

                //Obtenemos el valor de la lista por columnas y compaaramos para agregar el número preliminar
                if(numeroArchivo.get(aux).equals("0"))
                    gpTerreno.add(lb0,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("1"))
                    gpTerreno.add(lb1,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("2"))
                    gpTerreno.add(lb2,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("3"))
                    gpTerreno.add(lb3,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("4"))
                    gpTerreno.add(lb4,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("5"))
                    gpTerreno.add(lb5,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("6"))
                    gpTerreno.add(lb6,numColumna,numFila);
                if(numeroArchivo.get(aux).equals("7"))
                    gpTerreno.add(lb7,numColumna,numFila);
                numColumna++;
                aux++;
            }
            numFila++;
        }
    }

    public void pruebaLista() { //Pruebas de la lista. Solo para debug de la lista
        System.out.println("Tamaño de la lista: "+ numeroArchivo.size());
        System.out.println("Lista numeroArchivo");
        for(int i=0; i<numeroArchivo.size();i++) {
            System.out.println("El valor número " + i + " de la lista es: " + numeroArchivo.get(i));
        }

        seleccionTerrenos();
    }

    public void seleccionTerrenos(){
        System.out.println("Tamaño numeroArchivo antes del desmadre: "+numeroArchivo.size());
        for(int i=0;i<numeroArchivo.size();i++)
            numeroArchivo_Copia.add(numeroArchivo.get(i));
        //numeroArchivo_Copia=numeroArchivo;
        //Lista para la seleccion de terrenos
        for(int i = 0; i < numeroArchivo.size(); i++) { //Agrega los tipos de terrenos a una lista
            if(listTerrenos.contains(numeroArchivo.get(i))) //Compara si existe en la lista, si no agrega
            {
                System.out.println("Existia");
            }
            else
            {
                listTerrenos.add(numeroArchivo.get(i));
            }
        }
        Collections.sort(listTerrenos); //Ordena la lista de terrenos
        listTerrenos_Copia=listTerrenos;

        //Debug
        System.out.println("Lista listTerrenos");
        for(int i=0; i<listTerrenos.size();i++) {
            System.out.println("El valor número " + i + " de la lista es: " + listTerrenos.get(i));
        }
        System.out.println("Tamaño listTerrenos: "+listTerrenos.size());
    }

    public void ventana_Seleccion_Terrenos() throws IOException{//Abre la otra ventana
        if(archivoCargado==true) {//Carga el otro fxml

            Parent root = FXMLLoader.load(getClass().getResource("seleccionTerrenos.fxml"));
            Stage SLTerreno = new Stage();
            SLTerreno.setTitle("Seleccione su terreno");
            SLTerreno.setScene(new Scene(root, 800, 600));
            SLTerreno.show();
        }
        else{//Ventana de tipo alert para advertir al usuario de su error
            Alert alert = new Alert(Alert.AlertType.WARNING, "Primero debe cargar un archivo de mapa. Use el botón de Cargar Mapa");
            alert.showAndWait();
        }
    }





    //Declaramos elementos visuales. El de Border no se usa
    @FXML
    BorderPane bpTerrenos;
    @FXML
    VBox VBTerrenos;

    Label lb = new Label("Hola");
    ObservableList<String> cbSeleccionTerreno = FXCollections.observableArrayList(
            "Agua",
            "Tierra",
            "Lodo",
            "Piedras",
            "Arbusto",
            "Camino",
            "Arena",
            "Hielo");//Declaramos los elementos que tendrán los combos

    ComboBox cb0=new ComboBox(cbSeleccionTerreno);//Declaramos combos y le indicamos que tendrán
    ComboBox cb1=new ComboBox(cbSeleccionTerreno);
    ComboBox cb2=new ComboBox(cbSeleccionTerreno);
    ComboBox cb3=new ComboBox(cbSeleccionTerreno);
    ComboBox cb4=new ComboBox(cbSeleccionTerreno);
    ComboBox cb5=new ComboBox(cbSeleccionTerreno);
    ComboBox cb6=new ComboBox(cbSeleccionTerreno);
    ComboBox cb7=new ComboBox(cbSeleccionTerreno);

    public void colocarCombo(){//Agregamos los combos acorde a la lista al Vbox
        //VBTerrenos.getChildren().add(cb0);
        for (int i = 0; i<listTerrenos_Copia.size();i++) {//Recore lista para ver cual combo se agrega
            if(listTerrenos_Copia.get(i).equals("0")) {//Verificamos el valor de la lista
                VBTerrenos.getChildren().add(cb0);//Añadimos al VBOX
                cb0.setPromptText("Terreno 0");//Texto a mostrar
                //cb0.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("1")) {
                VBTerrenos.getChildren().add(cb1);
                cb1.setPromptText("Terreno 1");
                //cb1.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("2")) {
                VBTerrenos.getChildren().add(cb2);
                cb2.setPromptText("Terreno 2");
                //cb1.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("3")) {
                VBTerrenos.getChildren().add(cb3);
                cb3.setPromptText("Terreno 3");
                //cb0.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("4")) {
                VBTerrenos.getChildren().add(cb4);
                cb4.setPromptText("Terreno 4");
                //cb1.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("5")) {
                VBTerrenos.getChildren().add(cb5);
                cb5.setPromptText("Terreno 5");
                //cb1.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("6")) {
                VBTerrenos.getChildren().add(cb6);
                cb6.setPromptText("Terreno 6");
                //cb1.setValue("Hielo");
            }
            if(listTerrenos_Copia.get(i).equals("7")) {
                VBTerrenos.getChildren().add(cb7);
                cb7.setPromptText("Terreno 7");
                //cb1.setValue("Hielo");
            }

            ///TODO LO DE ABAJO ES PARA DEBUG
            System.out.println("Valor listTerrenos: "+i);
        }
        System.out.println("Lista listTerrenos comobo");
        System.out.println("Tamaño list terrenos copia: "+listTerrenos_Copia.size());
        System.out.println("Tamaño numeroArchivo copia: "+numeroArchivo_Copia.size());
        for(int i=0; i<listTerrenos.size();i++) {
            System.out.println("El valor número " + i + " de la lista es: " + listTerrenos.get(i));
        }
        if (numeroArchivo_Copia.isEmpty())
            System.out.println("Vacia");
    }

    String Terreno0,Terreno1,Terreno2,Terreno3,Terreno4,Terreno5,Terreno6,Terreno7;
    public void obtenerRespuestaCombo(){//Obtenemos y guardamos los valores del usuario. Falta el paso intermedio de
        //acomodarlo por número
        /*
        Terreno0=(String) cb0.getValue();
        Terreno1=(String) cb1.getValue();
        Terreno2=(String) cb2.getValue();
        Terreno3=(String) cb3.getValue();
        Terreno4=(String) cb4.getValue();
        Terreno5=(String) cb5.getValue();
        Terreno6=(String) cb6.getValue();
        Terreno7=(String) cb7.getValue();
        */

        /*
        "Agua",
            "Tierra",
            "Lodo",
            "Piedras",
            "Arbusto",
            "Camino",
            "Arena",
            "Hielo")
         */

        //Verifico si no seleccionó algún tipo tira error
        if(cb0.getSelectionModel().isEmpty() || cb1.getSelectionModel().isEmpty() || cb2.getSelectionModel().isEmpty() || cb3.getSelectionModel().isEmpty() || cb4.getSelectionModel().isEmpty() || cb5.getSelectionModel().isEmpty() || cb6.getSelectionModel().isEmpty() || cb7.getSelectionModel().isEmpty()) {
            //Ventana de tipo alert para advertir al usuario de su error
            Alert alert = new Alert(Alert.AlertType.WARNING, "Debe seleccionar un tipo de terreno. Olvidó seleccionar alguno");
            alert.showAndWait();
        }
        else{
            ///VERIFICO VALORES DE LOS COMBOS. Luego los guardo
            //TERRENO 0
            if (cb0.getValue().equals("Agua"))
                Terreno0 = "0";
            if (cb0.getValue().equals("Tierra"))
                Terreno0 = "1";
            if (cb0.getValue().equals("Lodo"))
                Terreno0 = "2";
            if (cb0.getValue().equals("Piedras"))
                Terreno0 = "3";
            if (cb0.getValue().equals("Arbusto"))
                Terreno0 = "4";
            if (cb0.getValue().equals("Camino"))
                Terreno0 = "5";
            if (cb0.getValue().equals("Arena"))
                Terreno0 = "6";
            if (cb0.getValue().equals("Hielo"))
                Terreno0 = "7";

            //TERRENO 1
            if (cb1.getValue().equals("Agua"))
                Terreno1 = "0";
            if (cb1.getValue().equals("Tierra"))
                Terreno1 = "1";
            if (cb1.getValue().equals("Lodo"))
                Terreno1 = "2";
            if (cb1.getValue().equals("Piedras"))
                Terreno1 = "3";
            if (cb1.getValue().equals("Arbusto"))
                Terreno1 = "4";
            if (cb1.getValue().equals("Camino"))
                Terreno1 = "5";
            if (cb1.getValue().equals("Arena"))
                Terreno1 = "6";
            if (cb1.getValue().equals("Hielo"))
                Terreno1 = "7";

            //TERRENO 2
            if (cb2.getValue().equals("Agua"))
                Terreno2 = "0";
            if (cb2.getValue().equals("Tierra"))
                Terreno2 = "1";
            if (cb2.getValue().equals("Lodo"))
                Terreno2 = "2";
            if (cb2.getValue().equals("Piedras"))
                Terreno2 = "3";
            if (cb2.getValue().equals("Arbusto"))
                Terreno2 = "4";
            if (cb2.getValue().equals("Camino"))
                Terreno2 = "5";
            if (cb2.getValue().equals("Arena"))
                Terreno2 = "6";
            if (cb2.getValue().equals("Hielo"))
                Terreno2 = "7";

            //TERRENO 3
            if (cb3.getValue().equals("Agua"))
                Terreno3 = "0";
            if (cb3.getValue().equals("Tierra"))
                Terreno3 = "1";
            if (cb3.getValue().equals("Lodo"))
                Terreno3 = "2";
            if (cb3.getValue().equals("Piedras"))
                Terreno3 = "3";
            if (cb3.getValue().equals("Arbusto"))
                Terreno3 = "4";
            if (cb3.getValue().equals("Camino"))
                Terreno3 = "5";
            if (cb3.getValue().equals("Arena"))
                Terreno3 = "6";
            if (cb3.getValue().equals("Hielo"))
                Terreno3 = "7";

            //TERRENO 4
            if (cb4.getValue().equals("Agua"))
                Terreno4 = "0";
            if (cb4.getValue().equals("Tierra"))
                Terreno4 = "1";
            if (cb4.getValue().equals("Lodo"))
                Terreno4 = "2";
            if (cb4.getValue().equals("Piedras"))
                Terreno4 = "3";
            if (cb4.getValue().equals("Arbusto"))
                Terreno4 = "4";
            if (cb4.getValue().equals("Camino"))
                Terreno4 = "5";
            if (cb4.getValue().equals("Arena"))
                Terreno4 = "6";
            if (cb4.getValue().equals("Hielo"))
                Terreno4 = "7";

            //TERRENO 5
            if (cb5.getValue().equals("Agua"))
                Terreno5 = "0";
            if (cb5.getValue().equals("Tierra"))
                Terreno5 = "1";
            if (cb5.getValue().equals("Lodo"))
                Terreno5 = "2";
            if (cb5.getValue().equals("Piedras"))
                Terreno5 = "3";
            if (cb5.getValue().equals("Arbusto"))
                Terreno5 = "4";
            if (cb5.getValue().equals("Camino"))
                Terreno5 = "5";
            if (cb5.getValue().equals("Arena"))
                Terreno5 = "6";
            if (cb5.getValue().equals("Hielo"))
                Terreno5 = "7";

            //TERRENO 6
            if (cb6.getValue().equals("Agua"))
                Terreno6 = "0";
            if (cb6.getValue().equals("Tierra"))
                Terreno6 = "1";
            if (cb6.getValue().equals("Lodo"))
                Terreno6 = "2";
            if (cb6.getValue().equals("Piedras"))
                Terreno6 = "3";
            if (cb6.getValue().equals("Arbusto"))
                Terreno6 = "4";
            if (cb6.getValue().equals("Camino"))
                Terreno6 = "5";
            if (cb6.getValue().equals("Arena"))
                Terreno6 = "6";
            if (cb6.getValue().equals("Hielo"))
                Terreno6 = "7";

            //TERRENO 7
            if (cb7.getValue().equals("Agua"))
                Terreno7 = "0";
            if (cb7.getValue().equals("Tierra"))
                Terreno7 = "1";
            if (cb7.getValue().equals("Lodo"))
                Terreno7 = "2";
            if (cb7.getValue().equals("Piedras"))
                Terreno7 = "3";
            if (cb7.getValue().equals("Arbusto"))
                Terreno7 = "4";
            if (cb7.getValue().equals("Camino"))
                Terreno7 = "5";
            if (cb7.getValue().equals("Arena"))
                Terreno7 = "6";
            if (cb7.getValue().equals("Hielo"))
                Terreno7 = "7";

            System.out.println("Terreno 0:" + Terreno0);
            System.out.println("Terreno 1:" + Terreno1);
            System.out.println("Terreno 2:" + Terreno2);
            System.out.println("Terreno 3:" + Terreno3);
            System.out.println("Terreno 4:" + Terreno4);
            System.out.println("Terreno 5:" + Terreno5);
            System.out.println("Terreno 6:" + Terreno6);
            System.out.println("Terreno 7:" + Terreno7);
        }
    }
}
