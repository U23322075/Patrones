package App;

import controller.MainController;

public class App {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        new MainController().iniciar();
    }
}
