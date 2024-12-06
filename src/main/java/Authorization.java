import Model.User;

import java.util.ArrayList;

public class Authorization {



    public boolean login(String userName, String password) {
        ArrayList<User> users = new ArrayList<>();

        for(User u : users){
            if(u.getUserName() == userName){
                if(u.getPassword() == password) {
                    System.out.println("Login successful");
                    return true;
                }
                else{
                    System.out.println("password does not match");
                    return false;
                }
            }
            else{
                System.out.println("Username does not match");
                return false;
            }
        }
        return false;
    }
}
