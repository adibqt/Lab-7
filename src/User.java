import java.io.*;
import java.util.*;
public abstract class User {
    protected String userId;
    protected String username;
    protected String email;
    protected String password;

    public User(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public abstract void viewUserData();
}
