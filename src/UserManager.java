import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users = new HashMap<>();

    private UserManager() { loadData(); }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Load user data from CSV files
    private void loadData() {
        try {
            loadRegularAndPowerUsers();
            loadAdminUsers();
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private void loadRegularAndPowerUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("User.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String userId = data[0];
            String username = data[1];
            String email = data[2];
            String password = data[3];
            String userType = data[4];

            User user = null;
            switch (userType.toLowerCase()) {
                case "regular":
                    user = new RegularUser(userId, username, email, password);
                    break;
                case "power":
                    user = new PowerUser(userId, username, email, password);
                    break;
                default:
                    System.out.println("Unknown user type: " + userType);
            }
            if (user != null) users.put(username, user);
        }
        br.close();
    }

    private void loadAdminUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Admin.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            String userId = data[0];
            String username = data[1];
            String email = data[2];
            String password = data[3];

            User admin = new AdminUser(userId, username, email, password);
            users.put(username, admin);
        }
        br.close();
    }

    // Authenticate users based on username and password
    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            System.out.println("Authentication successful for user: " + username);
            return user;
        } else {
            System.out.println("Authentication failed for user: " + username);
            return null;
        }
    }

    // Example file operations (based on user permissions)
    public void viewUserData(User user) {
        user.viewUserData();
    }
}

