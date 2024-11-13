public class RegularUser extends User{
    public RegularUser(String userId, String username, String email, String password) {
        super(userId, username, email, password);
    }

    @Override
    public void viewUserData() {
        System.out.println("Regular User: Viewing data with read-only access.");
    }
}
