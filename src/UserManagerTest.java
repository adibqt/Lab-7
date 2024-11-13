import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserManagerTest {
    private static UserManager userManager;

    @BeforeAll
    public static void setup() {
        // Initialize the UserManager instance (Singleton)
        userManager = UserManager.getInstance();
    }

    @Test
    public void testAuthenticateRegularUser() {
        User user = userManager.authenticate("regularUser", "password123");
        assertNotNull(user, "Regular user should be authenticated successfully");
        assertTrue(user instanceof RegularUser, "Authenticated user should be of type RegularUser");
        userManager.viewUserData(user);  // Should print read-only access message
    }

    @Test
    public void testAuthenticatePowerUser() {
        User user = userManager.authenticate("powerUser", "password456");
        assertNotNull(user, "Power user should be authenticated successfully");
        assertTrue(user instanceof PowerUser, "Authenticated user should be of type PowerUser");
        userManager.viewUserData(user);  // Should print read and write access message
    }

    @Test
    public void testAuthenticateAdminUser() {
        User user = userManager.authenticate("adminUser", "password789");
        assertNotNull(user, "Admin user should be authenticated successfully");
        assertTrue(user instanceof AdminUser, "Authenticated user should be of type AdminUser");
        userManager.viewUserData(user);  // Should print full access message
    }

    @Test
    public void testFailedAuthentication() {
        User user = userManager.authenticate("invalidUser", "wrongPassword");
        assertNull(user, "Authentication should fail for invalid credentials");
    }

    @Test
    public void testAdminUserPrivileges() {
        User adminUser = userManager.authenticate("adminUser", "password789");
        assertNotNull(adminUser, "Admin user should be authenticated successfully");
        assertTrue(adminUser instanceof AdminUser, "Authenticated user should be of type AdminUser");

        // Assuming we have a method in AdminUser to modify settings or add users
        if (adminUser instanceof AdminUser) {
            ((AdminUser) adminUser).viewUserData(); // Simulate Admin-specific privilege
        }
    }

    @Test
    public void testPowerUserPrivileges() {
        User powerUser = userManager.authenticate("powerUser", "password456");
        assertNotNull(powerUser, "Power user should be authenticated successfully");
        assertTrue(powerUser instanceof PowerUser, "Authenticated user should be of type PowerUser");

        // Test that PowerUser can add user details (simulate permission)
        if (powerUser instanceof PowerUser) {
            ((PowerUser) powerUser).viewUserData(); // Should print message for PowerUser privileges
        }
    }

    @Test
    public void testRegularUserPrivileges() {
        User regularUser = userManager.authenticate("regularUser", "password123");
        assertNotNull(regularUser, "Regular user should be authenticated successfully");
        assertTrue(regularUser instanceof RegularUser, "Authenticated user should be of type RegularUser");

        // Test that RegularUser has read-only access
        if (regularUser instanceof RegularUser) {
            ((RegularUser) regularUser).viewUserData(); // Should print message for RegularUser privileges
        }
    }
}
