package ca.mcgill.ecse321.grocerystoresystem;

/**
 * Holds information for the logged in customer.
 */
public final class LoginCustomer {

    //Singleton class, since only one customer can be logged in.
    public static final LoginCustomer INSTANCE = new LoginCustomer();

    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private LoginCustomer() {

    }

    /**
     * Checks to see if we have login info. The info could be invalid though!
     *
     * @return true if we have info, false otherwise
     */
    public boolean isLoggedIn() {
        return id == 0;
    }

    /**
     * Sets the status to login, assumes the supplied inputs are valid.
     * @param id user id
     * @param email email
     * @param password password
     * @param firstName first name
     * @param lastName last name
     */
    public void login(int id, String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Sets the status to logout
     */
    public void logout() {
        email = null;
        firstName = null;
        lastName = null;
        password = null;
    }

    /**
     * Getter for the email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the first name
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the last name
     *
     * @return username
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }


}
