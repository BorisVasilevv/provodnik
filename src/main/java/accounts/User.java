package accounts;

import java.util.ArrayList;

public class User {

    private String name;
    private String password;
    private String email;

    public User(String name, String password,String email){
        this.email=email;
        this.name=name;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    private static ArrayList<User> users=new ArrayList<User>();

    public static ArrayList<User> getListOfUsers(){
        return users;
    }

    public static boolean isUserWithEmailExist(String email){
        for (User user: users){
            if(user.getEmail().equals(email)) return true;
        }
        return false;
    }
}
