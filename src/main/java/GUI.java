import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GUI extends Application {
    ResourceBundle rb;
    ShoppingCart sc = new ShoppingCart();

    Label enterNumber;
    Button enterItems;
    Button calculateTotal;
    Label total;

    Label enterPrice;
    Label enterQuantity;


    List<TextField> priceFields = new ArrayList<>();
    List<TextField> quantityFields = new ArrayList<>();

    List<Label> priceLabels = new ArrayList<>();
    List<Label> quantityLabels = new ArrayList<>();

    int totalAmount = 0;
    VBox root;




    @Override
    public void start(Stage primaryStage){
        rb = ResourceBundle.getBundle("MessagesBundle", sc.getLocale("english"));

        root = new VBox();

        root.setPadding(new Insets(20));
        root.setSpacing(10);

        Label selectLanguage = new Label("Select the language:");
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("English", "Finnish", "Swedish", "Japanese", "Arabic");
        dropdown.setValue("English");
        Button confirm = new Button("Confirm Language");

        confirm.setOnAction(event ->{
           String select = dropdown.getValue();
           rb = ResourceBundle.getBundle("MessagesBundle", sc.getLocale(select));

           updateTexts();
           updateOrientation(select);
        });


        Separator separator = new Separator();
        separator.setPadding(new Insets(10, 0, 10, 0));



        enterNumber = new Label(rb.getString("prompt1"));
        TextField tf = new TextField();
        enterItems = new Button(rb.getString("button1"));

        VBox inputElements = new VBox();
        inputElements.setSpacing(10);

        inputElements.getChildren().addAll(
                enterNumber,
                tf,
                enterItems
        );

        enterItems.setOnAction(event ->{
            for (int i = 0; i<Integer.parseInt(tf.getText()); i++){
                VBox price = new VBox();

                enterPrice = new Label(rb.getString("prompt2"));
                priceLabels.add(enterPrice);

                TextField newTextField = new TextField();
                priceFields.add(newTextField);


                price.getChildren().addAll(
                        enterPrice,
                        newTextField
                );


                VBox amount = new VBox();

                enterQuantity = new Label(rb.getString("prompt3"));
                quantityLabels.add(enterQuantity);


                TextField newTextField2 = new TextField();
                quantityFields.add(newTextField2);

                amount.getChildren().addAll(
                        enterQuantity,
                        newTextField2
                );

                 HBox item = new HBox();
                 item.getChildren().addAll(
                         price,
                         amount
                 );

                inputElements.getChildren().add(item);
            }
            enterItems.setDisable(true);
        });




        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);


        calculateTotal = new Button(rb.getString("calculateTotal"));
        total = new Label(rb.getString("prompt4") + totalAmount);

        calculateTotal.setOnAction(event -> {
            sc.setSumTotalCart();

            for (int i = 0; i < priceFields.size(); i++) {
                try {
                    int price = Integer.parseInt(priceFields.get(i).getText());
                    int quantity = Integer.parseInt(quantityFields.get(i).getText());

                    sc.calculateTotal(price, quantity);

                } catch (NumberFormatException e) {
                    total.setText("Invalid input!");
                    return;
                }
            }

            totalAmount = sc.getTotal();
            total.setText(rb.getString("prompt4") + totalAmount);
        });


        root.getChildren().addAll(
                selectLanguage, dropdown, confirm,
                separator,
                inputElements,
                spacer,
                calculateTotal, total
        );

        Scene scene = new Scene(root, 400,550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mehdi Zaidane /Calculator App");

        primaryStage.show();


    }

    private void updateTexts() {
        enterNumber.setText(rb.getString("prompt1"));
        enterItems.setText(rb.getString("button1"));
        calculateTotal.setText(rb.getString("calculateTotal"));
        total.setText(rb.getString("prompt4"));

        total.setText(rb.getString("prompt4") + totalAmount);

        for(Label label : priceLabels){
            label.setText(rb.getString("prompt2"));
        }
        for(Label label : quantityLabels){
            label.setText(rb.getString("prompt3"));
        }


    }

    private void updateOrientation(String language) {
        if(language.equalsIgnoreCase("Arabic")) {
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
    }
}