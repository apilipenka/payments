package by.pwt.plipenko.payments.model.VO;

import java.util.Date;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class UserVO extends EntityVO {
    private String personalNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String login;
    private String password;
    private int userRoleID;
    private int userRoleName;

    public UserVO() {
        super();
    }

    public UserVO(int id, String personalNumber, String firstName, String lastName, Date birthDate, String login, String password, int userRoleID, int userRoleName) {
        super(id);
        this.personalNumber = personalNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.password = password;
        this.userRoleID = userRoleID;
        this.userRoleName = userRoleName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public int getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(int userRoleName) {
        this.userRoleName = userRoleName;
    }
}
