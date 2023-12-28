package practicumopdracht;

import data.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicumopdracht.controllers.Controller;
import practicumopdracht.controllers.VolleybalteamController;
import views.SpelerView;
import views.View;
import views.VolleybalteamView;


public class MainApplication extends Application {
    private static Stage stage;
    private static VolleybalteamDAO volleybalteamDAO;
    private static SpelerDAO spelerDAO;

    @Override
    public void start(Stage stage) {
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        this.stage = stage;
        volleybalteamDAO = new BinaryVolleybalteamDAO();
        spelerDAO = new TextSpelerDAO();

        volleybalteamDAO.load();
        spelerDAO.load();
        switchController(new VolleybalteamController());
        stage.setTitle(String.format("Practicumopdracht OOP2 - %s", Main.studentNaam));
        stage.setWidth(640);
        stage.setHeight(480);
//        View volleybalteam.txt = new VolleybalteamView();
//        Scene scene1 = new Scene(volleybalteam.txt.getRoot(), 640, 480);
//        stage.setScene(scene1);
//        stage.show();
    }

    public static void switchController(Controller controller) {
        stage.setScene(new Scene(controller.getView().getRoot()));
        stage.show();
    }

    public static VolleybalteamDAO getVolleybalteamDAO() {
        return volleybalteamDAO;
    }

    public static SpelerDAO getSpelerDAO() {
        return spelerDAO;
    }
}
