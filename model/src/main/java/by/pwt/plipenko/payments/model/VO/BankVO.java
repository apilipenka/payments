package by.pwt.plipenko.payments.model.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class BankVO extends EntityVO {
    private String UNN;
    private String name;

    public BankVO() {
        super();
    }

    public BankVO(int id, String UNN, String name) {
        super(id);
        this.UNN = UNN;
        this.name = name;
    }

    public String getUNN() {
        return UNN;
    }

    public void setUNN(String UNN) {
        this.UNN = UNN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
