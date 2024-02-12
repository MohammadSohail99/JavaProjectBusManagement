package UserDataBase;

import UserDataBase.InvalidCredentialsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDatabase {
    private Map<String, String> users;
    private String currentLoggedInUser;

    public UserDatabase() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        users.put(username, password);
    }

    public boolean loginUser(String username, String password) {
        try {
            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentLoggedInUser = username;
                return true;
            } else {
                throw new InvalidCredentialsException();
            }
        } catch (InvalidCredentialsException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean isLoggedIn() {
        return currentLoggedInUser != null;
    }

    public String getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }

    public List<String> getRegisteredUsers() {
        return new ArrayList<>(users.keySet());
    }
}

