/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.app;

import static java.lang.Double.parseDouble;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author marvyshaker
 */
public class BookstoreApp extends Application {
    int i,j;
    Stage window;
    Scene scene1,scene2,scene3,scene4; //owner scenes
    Scene scene5,scene6,scene7; //customer scenes
    
    //Book Table in Owners View
    TableView<Books> table = new TableView<>();
    ObservableList<Books> tester = FXCollections.observableArrayList(new Books("First Book", 200));
    
    
    TableView<Customer> customTable= new TableView<>();
    ObservableList<Customer> customTester = FXCollections.observableArrayList(new Customer("Marvy","marvyshaker","marvy!",0));
    int tableSize= customTester.size();

    
    @Override
    public void start(Stage primaryStage) {
        
        window= primaryStage;
        
        //username and password fields        
        Label username= new Label("Username:");
        Label password= new Label("Password:");
        Label intro= new Label ("Welcome to our Bookstore App \n");
        TextField userin= new TextField();
        userin.setMaxWidth(150);
        TextField passin= new TextField();
        passin.setMaxWidth(150);
        
        //Login button
        Button login = new Button();
        login.setText("Login");
        
        //looping through customer table to check usernames and passwords (if customer)
        login.setOnAction(e -> {
            for(i=0;i<tableSize;i++){
            if(userin.getText().equals(customTester.get(i).getUsername())&& passin.getText().equals(customTester.get(i).getPassword()))
                window.setScene(scene5);
            
            else if(userin.getText().equals ("admin") && passin.getText().equals ("admin")){
            window.setScene(scene2);}
            
            else{
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Wrong Input");
                errorAlert.setContentText("Username or Password Incorrect, please try again");
                errorAlert.showAndWait();
            }
            }});                
        
        
        //First page layout (common page)
        GridPane root1= new GridPane();
        root1.setPadding(new Insets(10,10,10,10));
        root1.setVgap(10);
        root1.setHgap(10);
        
        root1.add(intro, 4,3);
        root1.add(username,4,4);
        root1.add(userin, 5,4);
        root1.add(password,4,5);
        root1.add(passin,5,5);
        root1.add(login,4,6);
        scene1= new Scene(root1,400,200);
        
        //Owner View
        //Owner: Scene 2 buttons        
        Button books= new Button("Books");
        books.setMinWidth(100);
        books.setOnAction(e-> window.setScene(scene3));
        
        Button customers= new Button("Customers");
        customers.setMinWidth(100);
        customers.setOnAction(e-> window.setScene(scene4));
        
        Button logout= new Button("Logout");
        logout.setMinWidth(100);
        logout.setOnAction(e-> {
            window.setScene(scene1);
            //clearing the inputted user and password
            userin.setText(null);
            passin.setText(null);});
        
        //Owner: Second page layout
        GridPane root2= new GridPane();
        root2.setPadding(new Insets(10,10,10,10));
        root2.setVgap(10);
        root2.setHgap(10);
        //int midwid= ((int) (0.5*root2.getMaxWidth()));
        //int midhei= ((int) (0.5*root2.getMaxHeight()));
        
        root2.add(books,13,5);
        root2.add(customers,13,6);
        root2.add(logout,13,7);

        scene2= new Scene(root2,400,200);
        
        
        //Scene 3
        //Owner: Books Page layout
        //Owner-books-screen
        GridPane root3= new GridPane();
        //root3.setPadding(new Insets(10,10,10,10));
        root3.setVgap(5);
        root3.setHgap(5);
        
        //Table on top part of the page    
        table.setEditable(true);
        TableColumn<Books, String> name= new TableColumn<>("Book Name");
        
        //TableColumn name = new TableColumn("Book Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        TableColumn <Books, Number> price= new TableColumn(" Book Price $");
        //TableColumn price = new TableColumn("Book Price $");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        
        name.setMinWidth(300);
        price.setMinWidth(100);
        
        table.setItems(tester); //adding one book tester
        table.getColumns().addAll(name,price);

        
        root3.add(table,1,1); //add table to pane
        
        
        //TextFields
        TextField nameText= new TextField();
        nameText.setPromptText("Book Name");
        TextField priceText = new TextField();
        priceText.setPromptText("Book Price");
        
        //Labels
        Label bookTitle= new Label("Book Table");

        
        
        //Buttons in Books Screen
        Button back= new Button("Back");
            back.setOnAction(e->window.setScene(scene2));
        
        //Adding a new book to the table
        Button add= new Button("Add");
            add.setOnAction(e->{
                tester.add(new Books(
                                    nameText.getText(),
                                    parseDouble(priceText.getText())));
                nameText.clear();
                priceText.clear();
            });
            
       
       //Deleting the selected book
        Button delete= new Button("Delete");
            delete.setOnAction(e->{
                table.getItems().remove(table.getSelectionModel().getSelectedItem());    
            });
        
        
        
        int tableHei= (int)table.getHeight();
        root3.add(bookTitle, 0,0);
        //root3.add(bookName,1,tableHei+1);
        root3.add(nameText,1,tableHei+2);
        //root3.add(bookPrice,3,tableHei+1);
        root3.add(priceText,2,tableHei+2);
        root3.add(add,3,tableHei+2);
        root3.add(delete,1,tableHei+3);
        root3.add(back,2,tableHei+3);
        
        
        scene3= new Scene(root3,700,700);
        
        
        //Scene 4
        //Owner view: Customers Button
        //Owner-customers-screen
        GridPane root4= new GridPane();
        //root3.setPadding(new Insets(10,10,10,10));
        root4.setVgap(5);
        root4.setHgap(5);
        
        //Table on top part of the page    
        customTable.setEditable(true);
        TableColumn<Customer, String> customName= new TableColumn<>("Username");
        customName.setCellValueFactory(new PropertyValueFactory<>("username"));
        customName.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        TableColumn <Customer, String> customPass= new TableColumn<>("Password");
        customPass.setCellValueFactory(new PropertyValueFactory<>("password"));
        customPass.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        TableColumn<Customer, Number> customPoints= new TableColumn<>("Points");
        customPoints.setCellValueFactory(new PropertyValueFactory<>("point"));
        customPoints.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        
        customName.setMinWidth(100);
        customPass.setMinWidth(100);
        customPoints.setMinWidth(100);
        
        customTable.setItems(customTester); //adding one customer tester
        customTable.getColumns().addAll(customName,customPass,customPoints);

        
        root4.add(customTable,1,1); //add table to pane
        
        
        //TextFields
        TextField customUsername= new TextField();
        customUsername.setPromptText("Username");
        TextField customPassword = new TextField();
        customPassword.setPromptText("Password");
        
        //Labels
        Label customTitle= new Label("Customers Table");

        
        
        //Buttons in Customers Screen
        Button backCustom= new Button("Back");
            backCustom.setOnAction(e->window.setScene(scene2));
        
        //Adding a new customer to the table
        Button addCustom= new Button("Add");
            addCustom.setOnAction(e->{
                customTester.add(new Customer(
                                    customUsername.getText(),
                                    customUsername.getText(),
                                    customPassword.getText(),
                                    0));
                customUsername.clear();
                customPassword.clear();
            });
            
       
       //Deleting the selected book
        Button deleteCustom= new Button("Delete");
            deleteCustom.setOnAction(e->{
                customTable.getItems().remove(customTable.getSelectionModel().getSelectedItem());    
            });
        
        
        
        int tableHeight= (int)customTable.getHeight();
        root4.add(customTitle, 0,0);
        root4.add(customUsername,1,tableHeight+2);
        root4.add(customPassword,2,tableHeight+2);
        root4.add(addCustom,3,tableHeight+2);
        root4.add(deleteCustom,1,tableHeight+3);
        root4.add(backCustom,2,tableHeight+3);
        
        
        scene4= new Scene(root4,700,700);
        
        //Scene 5
        //Customer-start-screen
        GridPane root5= new GridPane();
        root5.setVgap(5);
        root5.setHgap(5);
        
        Label welcome= new Label("Welcome "+customTester.get(i).getName()+". You have "+customTester.get(i).getPoint()+" points. Your status is "+ customTester.get(i).getState()+".");
        
        root5.add(welcome,0,0);
        scene5= new Scene(root5,400,400);
        
        //Starting the app
        window.setScene(scene1);
        window.setTitle("Bookstore App");
        window.show();

        
        
        
    }
                
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }        

    
}
