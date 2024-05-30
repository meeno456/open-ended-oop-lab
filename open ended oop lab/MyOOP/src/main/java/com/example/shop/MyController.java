package com.example.shop;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.nio.file.Paths;

public class MyController {
    @FXML
    private GridPane productGrid;

    @FXML
    public void initialize() {
        String url = "jdbc:mysql://localhost:3306/store";
        String user = "root"; //
        String password = "zarmeena";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            int column = 0;
            int row = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String imagePath = resultSet.getString("image_path");

                // Convert the image path to URI
                String formattedImagePath = Paths.get(imagePath).toUri().toString();

                // Create UI elements
                ImageView imageView = new ImageView(new Image(formattedImagePath));
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);

                Text descText = new Text(description);
                descText.setStyle("-fx-fill: white; -fx-font-weight: bold;");

                Text nameText = new Text(name);
                nameText.setStyle("-fx-fill: white; -fx-font-weight: bold;");

                VBox productBox = new VBox(5, imageView, descText, nameText);
                productBox.setAlignment(Pos.CENTER);

                productGrid.add(productBox, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
