package by.pwt.pilipenko.payments.web.command.client;

import by.pwt.pilipenko.payments.web.command.ActionCommand;
import by.pwt.pilipenko.payments.web.command.LoginCommand;
import by.pwt.pilipenko.payments.web.command.LogoutCommand;
import by.pwt.pilipenko.payments.web.command.account.*;
import by.pwt.pilipenko.payments.web.command.agreement.*;
import by.pwt.pilipenko.payments.web.command.bank.*;
import by.pwt.pilipenko.payments.web.command.card.*;
import by.pwt.pilipenko.payments.web.command.command.*;
import by.pwt.pilipenko.payments.web.command.currency.*;
import by.pwt.pilipenko.payments.web.command.currencyext.*;
import by.pwt.pilipenko.payments.web.command.exchangerate.*;
import by.pwt.pilipenko.payments.web.command.user.*;
import by.pwt.pilipenko.payments.web.command.userrole.*;
import by.pwt.pilipenko.payments.web.command.userrolecommand.*;

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


    },
    NEWCURRENCYEXT {
        {
            this.command = new NewCurrencyExtCommand();
        }
    },
    ADDCURRENCYEXT {
        {
            this.command = new AddCurrencyExtCommand();
        }
    },
    CURRENCYEXTLIST {
        {
            this.command = new CurrencyExtListCommand();
        }
    },
    EDITCURRENCYEXT {
        {
            this.command = new EditCurrencyExtCommand();
        }
    },
    UPDATECURRENCYEXT {
        {
            this.command = new UpdateCurrencyExtCommand();
        }
    },
    REMOVECURRENCYEXT {
        {
            this.command = new DeleteCurrencyExtCommand();
        }


    },
    NEWAGREEMENT {
        {
            this.command = new NewAgreementCommand();
        }
    },
    ADDAGREEMENT {
        {
            this.command = new AddAgreementCommand();
        }
    },
    AGREEMENTLIST {
        {
            this.command = new AgreementListCommand();
        }
    },
    EDITAGREEMENT {
        {
            this.command = new EditAgreementCommand();
        }
    },
    UPDATEAGREEMENT {
        {
            this.command = new UpdateAgreementCommand();
        }
    },
    REMOVEAGREEMENT {
        {
            this.command = new DeleteAgreementCommand();
        }


    },
    NEWACCOUNT {
        {
            this.command = new NewAccountCommand();
        }
    },
    ADDACCOUNT {
        {
            this.command = new AddAccountCommand();
        }
    },
    ACCOUNTLIST {
        {
            this.command = new AccountListCommand();
        }
    },
    ACCOUNTLISTWITHPAGINATION {
        {
            this.command = new AccountListWithPaginationCommand();
        }
    },
    EDITACCOUNT {
        {
            this.command = new EditAccountCommand();
        }
    },
    UPDATEACCOUNT {
        {
            this.command = new UpdateAccountCommand();
        }
    },
    REMOVEACCOUNT {
        {
            this.command = new DeleteAccountCommand();
        }


    },
    NEWCARD {
        {
            this.command = new NewCardCommand();
        }
    },
    ADDCARD {
        {
            this.command = new AddCardCommand();
        }
    },
    CARDLIST {
        {
            this.command = new CardListCommand();
        }
    },
    EDITCARD {
        {
            this.command = new EditCardCommand();
        }
    },
    UPDATECARD {
        {
            this.command = new UpdateCardCommand();
        }
    },
    REMOVECARD {
        {
            this.command = new DeleteCardCommand();
        }


    },
    NEWUSERROLECOMMAND {
        {
            this.command = new NewUserRoleCommandCommand();
        }
    },
    ADDUSERROLECOMMAND {
        {
            this.command = new AddUserRoleCommandCommand();
        }
    },
    USERROLECOMMANDLIST {
        {
            this.command = new UserRoleCommandListCommand();
        }
    },
    EDITUSERROLECOMMAND {
        {
            this.command = new EditUserRoleCommandCommand();
        }
    },
    UPDATEUSERROLECOMMAND {
        {
            this.command = new UpdateUserRoleCommandCommand();
        }
    },
    REMOVEUSERROLECOMMAND {
        {
            this.command = new DeleteUserRoleCommandCommand();
        }


    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}