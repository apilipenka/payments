package by.pwt.plipenko.payments.model.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class UserRole extends Type {

    private static final long serialVersionUID = -2060426102854785438L;
    Set<Command> commands;

    public UserRole() {
        super();
        commands = new HashSet<Command>();
    }

    public UserRole(int id, String name, String dsecription) {
        super(id, name, dsecription);
        commands = new HashSet<Command>();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        return result;
    }

    public Set<Command> getCommands() {
        return commands;
    }

    public void setCommands(Set<Command> commands) {
        this.commands = commands;
    }

    public void addRolePermissions(Set<Command> commands) {
        this.commands.addAll(commands);
    }

    public List<Command> findAccounts(Command command) {
        List<Command> finded = new ArrayList<Command>();
        for (Command icommand : commands) {
            if ((command.getCommand() != null && command.getCommand().equals(icommand.getCommand())))
                finded.add(icommand);
        }
        return finded;
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
        return true;
    }

    @Override
    public String toString() {
        return "UserRole [id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", commands=" + commands + "]";

    }

}
