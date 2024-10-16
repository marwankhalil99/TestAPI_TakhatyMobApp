package Utils.Registeration;

public class ClientUser {
    private int userRole;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String address;
    private int gender;
    private String phoneNumber;
    private String token;
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ClientUser(ClientUser user) {
        this.userRole = user.getUserRole();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
    }
    public ClientUser(int userRole, String password, String email, String firstName, String lastName, String country, String city, String address, int gender, String phoneNumber) {
        this.userRole = userRole;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }





}
