package Admin;

import javax.naming.AuthenticationException;

public class admin {
    String username;
    String password;
    Boolean authenticate;

    public admin(String username, String password) {
        this.username = username;
        this.password = password;
        this.authenticate = false;
    }

    public Boolean isAuthenticate(){
        return authenticate;
    }

    public Boolean authenticated(String username,String password) {
        try {
            if (this.username.equals(username) && this.password.equals(password)) {
                authenticate = true;
                return true;
            } else {
                throw new AuthenticationException("User not Authenticated");
            }
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public  void Logout(){
        authenticate=false;
    }
}

