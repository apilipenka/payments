package by.pwt.pilipenko.payments.web.command.client;

import by.pwt.pilipenko.payments.web.command.*;
import by.pwt.pilipenko.payments.web.command.bank.*;
import by.pwt.pilipenko.payments.web.command.currency.*;
import by.pwt.pilipenko.payments.web.command.exchangerate.*;
import by.pwt.pilipenko.payments.web.command.user.*;
import by.pwt.pilipenko.payments.web.command.userrole.*;

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
    },
    NEWCOMMAND {
        {
            this.command = new NewCommandCommand();
        }
    },
    ADDCOMMAND {
        {
            this.command = new AddCommandCommand();
        }
    },
    COMMANDLIST {
        {
            this.command = new CommandListCommand();
        }
    },
    EDITCOMMAND {
        {
            this.command = new EditCommandCommand();
        }
    },
    UPDATECOMMAND {
        {
            this.command = new UpdateCommandCommand();
        }
    },
    REMOVECOMMAND {
        {
            this.command = new DeleteCommandCommand();
        }


    },
    NEWBANK {
        {
            this.command = new NewBankCommand();
        }
    },
    ADDBANK {
        {
            this.command = new AddBankCommand();
        }
    },
    BANKLIST {
        {
            this.command = new BankListCommand();
        }
    },
    EDITBANK {
        {
            this.command = new EditBankCommand();
        }
    },
    UPDATEBANK {
        {
            this.command = new UpdateBankCommand();
        }
    },
    REMOVEBANK {
        {
            this.command = new DeleteBankCommand();
        }


    },
    NEWCURRENCY {
        {
            this.command = new NewCurrencyCommand();
        }
    },
    ADDCURRENCY {
        {
            this.command = new AddCurrencyCommand();
        }
    },
    CURRENCYLIST {
        {
            this.command = new CurrencyListCommand();
        }
    },
    EDITCURRENCY {
        {
            this.command = new EditCurrencyCommand();
        }
    },
    UPDATECURRENCY {
        {
            this.command = new UpdateCurrencyCommand();
        }
    },
    REMOVECURRENCY {
        {
            this.command = new DeleteCurrencyCommand();
        }


    },
    NEWEXCHANGERATE {
        {
            this.command = new NewExchangeRateCommand();
        }
    },
    ADDEXCHANGERATE {
        {
            this.command = new AddExchangeRateCommand();
        }
    },
    EXCHANGERATELIST {
        {
            this.command = new ExchangeRateListCommand();
        }
    },
    EDITEXCHANGERATE {
        {
            this.command = new EditExchangeRateCommand();
        }
    },
    UPDATEEXCHANGERATE {
        {
            this.command = new UpdateExchangeRateCommand();
        }
    },
    REMOVEEXCHANGERATE {
        {
            this.command = new DeleteExchangeRateCommand();
        }


    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}