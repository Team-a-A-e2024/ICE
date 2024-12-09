import Persistens.SeedDB;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Authorization auth = new Authorization();
        //auth.login("Alissa","123456");
        auth.signUp("Alissa", "123");
        //SeedDB.createDB();
    }
}
