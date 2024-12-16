import Persistens.SeedDB;
import util.Menu;
public class Main {
    public static void main(String[] args) {
        SeedDB.createDB();
        Menu.barcodeMenu();
    }
}