package by.pwt.plipenko.payments.model.entities;

import by.pwt.plipenko.payments.model.VO.CommandVO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class Command extends Entity {

    private String command;
    private String url;
    private String label;
    private String comment;

    public Command() {
        super();
    }

    public Command(int id, String command, String url, String label, String comment, UserRole userRole) {
        super(id);
        this.command = command;
        this.url = url;
        this.label = label;
        this.comment = comment;
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass())
            return false;
        Command that = (Command) o;

        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment != null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Command [" +
                "id=" + getId() +
                ", command=" + command +
                ", url=" + url +
                ", label=" + label +
                ", comment=" + comment +
                ']';
    }

    public CommandVO createCommandVO() {
        CommandVO commandVO = new CommandVO();
        commandVO.setId(getId());
        commandVO.setCommand(command);
        commandVO.setUrl(url);
        commandVO.setLabel(label);
        commandVO.setComment(comment);
        return commandVO;
    }
}
