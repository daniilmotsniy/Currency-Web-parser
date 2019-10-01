package sample;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.lang.*;

import static jdk.nashorn.internal.objects.NativeString.substring;


public class Main extends Application {

    int getCourse(int i, int i1){
        String text;
        double value;
        Document doc = null;
        try {
            doc = Jsoup.connect("https://bank.gov.ua/markets/exchangerates").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = Jsoup.parse(doc.toString()).text();
        value = Double.parseDouble(substring(i, i1));
        return (int) value;
    }

    String getPage(){

        Document doc = null;
        try {
            doc = Jsoup.connect("https://bank.gov.ua/markets/exchangerates").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Jsoup.parse(doc.toString()).text();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        primaryStage.setTitle("Курс резервної валюти");
        primaryStage.setScene(new Scene(root, 500, 500));
        Image icon = new Image(getClass().getResourceAsStream("icons/icon.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.show();

        //Values

        System.out.println(getPage()); // Printing page to realize what u should crop

        int usd = 20; // Use getCoure() to crop value from page
        int gbp = 40;
        int eur = 30;


        XYChart.Data<String, Number> dollar = new XYChart.Data("Долар", usd);
        XYChart.Data<String, Number> euro = new XYChart.Data("Євро", eur);
        XYChart.Data<String, Number> pound = new XYChart.Data("Фунт стерлінгів", gbp);


        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Валюта");
        xAxis.setGapStartAndEnd(true);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Грн, за 100 одиниць");
        yAxis.setTickLabelGap(10);
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100);
        yAxis.setMinorTickCount(0);
        yAxis.setTickUnit(100.0);
        BarChart<String, Number> chart = new BarChart(xAxis,yAxis);
        chart.setLayoutY(0);
        chart.setPrefSize(500, 400);
        chart.setTitle("Резервна валюта");
        chart.setTitleSide(Side.TOP);


        XYChart.Series<String, Number> seriesD = new XYChart.Series();
        seriesD.setName(String.valueOf(usd));
        XYChart.Series<String, Number> seriesE = new XYChart.Series();
        seriesE.setName(String.valueOf(eur));
        XYChart.Series<String, Number> seriesP = new XYChart.Series();
        seriesP.setName(String.valueOf(gbp));

        seriesD.getData().addAll(dollar);
        seriesE.getData().addAll(euro);
        seriesP.getData().addAll(pound);

        chart.getData().addAll(seriesD,seriesE,seriesP);


        Label materials = new Label("www.bank.gov.ua");
        materials.setLayoutY(450);
        materials.setLayoutX(150);
        root.getChildren().addAll(chart, materials);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
