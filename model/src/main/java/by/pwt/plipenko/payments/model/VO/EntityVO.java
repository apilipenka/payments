package by.pwt.plipenko.payments.model.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public abstract class EntityVO {
    int id;

    public EntityVO() {
        super();
    }

    public EntityVO(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
