package by.pwt.plipenko.payments.model.entities;

import java.util.*;

public class User extends Entity {
    private Set<Agreement> agreements;
    private String personalNumber;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String login;
    private String password;
    private UserRole userRole;

    public User() {
        super();
        agreements = new HashSet<Agreement>();
    }

    public User(int id, String login, String firstName, String lastName, String password, String personalNumber,
                UserRole userRole, Date birthDate) {
        super(id);
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.personalNumber = personalNumber;
        this.userRole = userRole;
        this.birthDate = birthDate;

        agreements = new HashSet<Agreement>();

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((personalNumber == null) ? 0 : personalNumber.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (personalNumber == null) {
            if (other.personalNumber != null) {
                return false;
            }
        } else if (!personalNumber.equals(other.personalNumber)) {
            return false;
        }

        if (userRole == null) {
            if (other.userRole != null) {
                return false;
            }
        } else if (!userRole.equals(other.userRole)) {
            return false;
        }

        if (birthDate == null) {
            if (other.birthDate != null) {
                return false;
            }
        } else if (!birthDate.equals(other.birthDate)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
                + ", password=" + password + ", personalNumber=" + personalNumber + ", userRole=" + userRole + ", birthDate=" + birthDate + ", acreements=" + agreements + "]";
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(Set<Agreement> agreements) {
        this.agreements = agreements;
    }

    public void addAgreements(Set<Agreement> agreements) {
        this.agreements.addAll(agreements);
    }

    public List<Agreement> findAccounts(Agreement agreement) {
        List<Agreement> finded = new ArrayList<Agreement>();
        for (Agreement iagreement : agreements) {
            if ((agreement.getNumber() != null && agreement.getNumber().equals(iagreement.getNumber())))
                finded.add(iagreement);
        }
        return finded;
    }

}
