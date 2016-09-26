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
    @FXML
    VBox VBTerrenos;

    int Columnas=0, Filas=0;
    GridPane gpTerreno = new GridPane();

    List<String> numeroArchivo = new ArrayList<String>(); //Guardamos los numeros del txt
    List<String> listTerrenos = new ArrayList<>(); // Lista de terrenos
    ObservableList<String> cbSeleccionTerreno = FXCollections.observableArrayList("Agua","Tierra", "Lodo", "Piedras", "Arbusto", "Camino", "Arena","Hielo");//We declare the items

    ComboBox cb0, cb1, cb2, cb3, cb4, cb5, cb6, cb7;

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

        //Debug
        System.out.println("Lista listTerrenos");
        for(int i=0; i<listTerrenos.size();i++) {
            System.out.println("El valor número " + i + " de la lista es: " + listTerrenos.get(i));
        }
    }

    public void ventana_Seleccion_Terrenos() throws IOException{//Pone los combo Box según la lista de terrenos en la ventana
        if(archivoCargado==true) {

            Parent root = FXMLLoader.load(getClass().getResource("seleccionTerrenos.fxml"));
            Stage SLTerreno = new Stage();
            SLTerreno.setTitle("Seleccione su terreno");
            SLTerreno.setScene(new Scene(root, 800, 600));
            SLTerreno.show();

            //VBTerrenos.getChildren().add(cb0);
            /*
            for (int i = 0; i < listTerrenos.size(); i++) {
                if (listTerrenos.get(i).equals("0")) {
                    VBTerrenos.getChildren().add(cb0);
                    cb0.setPromptText("Terreno 0");
                    cb0.setValue("Camino");
                    cb0.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("1")) {
                    VBTerrenos.getChildren().add(cb1);
                    cb1.setPromptText("Terreno 1");
                    cb1.setValue("Camino");
                    cb1.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("2")) {
                    VBTerrenos.getChildren().add(cb2);
                    cb2.setPromptText("Terreno 2");
                    cb2.setValue("Camino");
                    cb2.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("3")) {
                    VBTerrenos.getChildren().add(cb3);
                    cb3.setPromptText("Terreno 3");
                    cb3.setValue("Camino");
                    cb3.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("4")) {
                    VBTerrenos.getChildren().add(cb4);
                    cb4.setPromptText("Terreno 4");
                    cb4.setValue("Camino");
                    cb4.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("5")) {
                    VBTerrenos.getChildren().add(cb5);
                    cb5.setPromptText("Terreno 5");
                    cb5.setValue("Camino");
                    cb5.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("6")) {
                    VBTerrenos.getChildren().add(cb6);
                    cb6.setPromptText("Terreno 6");
                    cb6.setValue("Camino");
                    cb6.setItems(cbSeleccionTerreno);
                }
                if (listTerrenos.get(i).equals("7")) {
                    VBTerrenos.getChildren().add(cb7);
                    cb7.setPromptText("Terreno 7");
                    cb7.setValue("Camino");
                    cb7.setItems(cbSeleccionTerreno);
                }
            }
            */
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Primero debe cargar un archivo de mapa. Use el botón de Cargar Mapa");
            alert.showAndWait();
        }
    }
}
