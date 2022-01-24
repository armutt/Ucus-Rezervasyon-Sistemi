import java.util.Date;

public class YolcuFlight extends Flight {
    String koltukNo;

    public String getKoltukNo() {
        return koltukNo;
    }

    public YolcuFlight(Date date, Havayolu havayolu, Destination departure, Destination arrival, String koltukNo) {
        super(date, havayolu, departure, arrival);
        this.koltukNo = koltukNo;
    }

    @Override
    public String toString() {
        return super.toString() + '\t' + koltukNo;
    }
}
