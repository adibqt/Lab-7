public class PowerUser extends User {
    public PowerUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void viewUserData() {
        System.out.println("Power User: Viewing and adding user data.");
    }
}
