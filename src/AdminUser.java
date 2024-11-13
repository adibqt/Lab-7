public class AdminUser extends User{
    public AdminUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void viewUserData() {
        System.out.println("Admin User: Full access to modify settings, add/update user data.");
    }
}
