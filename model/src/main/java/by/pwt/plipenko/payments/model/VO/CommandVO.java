package by.pwt.plipenko.payments.model.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class CommandVO extends EntityVO {
    private String command;
    private String url;
    private String label;
    private String comment;
    private int userRoleId;
    private String userRoleName;

    public CommandVO() {
        super();
    }

    public CommandVO(int id, String command, String url, String label, String comment, int userRoleId, String userRoleName) {
        super(id);
        this.command = command;
        this.url = url;
        this.label = label;
        this.comment = comment;
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
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

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
