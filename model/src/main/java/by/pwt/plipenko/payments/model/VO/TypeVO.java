package by.pwt.plipenko.payments.model.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class TypeVO extends EntityVO {

    private String name;
    private String description;

    public TypeVO() {
        super();
    }

    public TypeVO(int id, String name, String dsecription) {
        super(id);
        this.name = name;
        this.description = dsecription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
