package by.pwt.pilipenko.payments.web.command.client;

import by.pwt.pilipenko.payments.web.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    NEWUSER {
        {
            this.command = new NewUserCommand();
        }
    },
    ADDUSER {
        {
            this.command = new AddUserCommand();// AddUserCommand();
        }
    },
    USERLIST {
        {
            this.command = new UserListCommand();
        }
    },
    EDITUSER {
        {
            this.command = new EditUserCommand();
        }
    },
    UPDATEUSER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    REMOVEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    NEWUSERROLE {
        {
            this.command = new NewUserRoleCommand();
        }
    },
    ADDUSERROLE {
        {
            this.command = new AddUserRoleCommand();
        }
    },
    USERROLELIST {
        {
            this.command = new UserRoleListCommand();
        }
    },
    EDITUSERROLE {
        {
            this.command = new EditUserRoleCommand();
        }
    },
    UPDATEUSERROLE {
        {
            this.command = new UpdateUserRoleCommand();
        }
    },
    REMOVEUSERROLE {
        {
            this.command = new DeleteUserRoleCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}