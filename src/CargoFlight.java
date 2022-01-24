import java.util.Date;

public class CargoFlight extends Flight{
   int weight;

    public int getWeight() {
        return weight;
    }
    public CargoFlight(Date date, Havayolu havayolu, Destination departure, Destination arrival,int weight) {
        super(date, havayolu, departure, arrival);
        this.weight=weight;
    }

    @Override
    public String toString() {
        return super.toString()+"\t"+weight;
    }
}
