import java.text.SimpleDateFormat;
import java.util.*;

public class Flight implements Comparable<Flight>{
    private static ArrayList<Flight> flights = new ArrayList<>();
    final Date date;
    static final SimpleDateFormat DATEFORMAT=new SimpleDateFormat("dd.MM.yyyy");
    Havayolu havayolu;
    Destination departure;
    Destination arrival;

    public Date getDate() {
        return date;
    }
    public Havayolu getHavayolu() {
        return havayolu;
    }
    public Destination getDeparture() {
        return departure;
    }
    public Destination getArrival() {
        return arrival;
    }
    public static Flight[] getFlights(){
        return flights.toArray(new Flight[0]);
    }
    protected Flight(Date date, Havayolu havayolu, Destination kalkis, Destination varis) {
        this.date = date;
        this.havayolu = havayolu;
        this.departure = kalkis;
        this.arrival = varis;
        flights.add(this);
    }


   /*
    static Flight Create() {
        var temp = new Flight(date, havayolu, kalkis, varis);
        System.out.println("Lütfen uçuş icin tarih girin");
        temp.setDate();
        System.out.println("Lütfen havayolunun  kodunu yada adini girin");
        for (Havayolu value : Havayolu.values()) {
            System.out.println(value);
        }
        temp.setHavayolu();
        System.out.println("Lütfen kalkış Destinasyonunuzun kodunu yada adını girin");
        for (Destination value : Destination.values()) {
            System.out.println(value);
        }
        temp.setKalkis();
        System.out.println("Lütfen varış Destinasyonunuzun kodunu yada adını girin");
        temp.setVaris();
        System.out.println("Lütfen uçuş tipi seçiniz");
        temp.setType();
        flights.add(temp);
        return temp;
    }
    */



    @Override
    public String toString() {
        var builder= new StringBuilder();
        builder.append(DATEFORMAT.format(date));
        builder.append("\t"+havayolu.code);
        builder.append("\t"+ departure.code);
        builder.append("\t"+ arrival.code);
        return builder.toString();
    }

    @Override
    public int compareTo(Flight o) {
        return date.compareTo(o.date);
    }
}

