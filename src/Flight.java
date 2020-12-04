import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Flight {
    public static ArrayList<Flight> flights = new ArrayList<>();
    Date date;
    static final SimpleDateFormat DATEFORMAT=new SimpleDateFormat("dd.MM.yyyy");
    Havayolu havayolu;
    FlightType type;
    destination kalkis;
    String koltukNo;
    Integer agirlik;

    //koltuk no yada tasinan agirlik
    static Flight Create() {
        var temp = new Flight();
        System.out.println("Lütfen uçuş icin tarih girin");
        temp.setDate();
        System.out.println("Lütfen havayolunun  kodunu yada adini girin");
        for (Havayolu value : Havayolu.values()) {
            System.out.println(value);
        }
        temp.setHavayolu();
        System.out.println("Lütfen Destinasyonunuzun kodunu yada adını girin");
        for (destination value : destination.values()) {
            System.out.println(value);
        }
        temp.setKalkis();
        System.out.println("Lütfen uçuş tipi seçiniz");
        temp.setType();
        flights.add(temp);
        return temp;
    }

    void setDate() {
        Date date = null;
        Scanner scanner = new Scanner(System.in);
        var s = scanner.nextLine();

        try {
            DATEFORMAT.setLenient(false);
            date = DATEFORMAT.parse(s);

        } catch (ParseException e) {
            System.err.println("hatalı format.Lütfen tarihi DD.MM.YYYY şeklinde giriniz");
            setDate();
        }
        for(var flight :flights)
            if(date.equals(flight.date))
            {
                System.out.println("bu tarihte farklı bir uçuş var.\nLütfen yeni bir tarih seçin");
                setDate();
            }
        this.date=date;

    }
    void setHavayolu() {
        Scanner scanner = new Scanner(System.in);
        var s = scanner.nextLine();
        try {
            havayolu=Havayolu.Parse(s);
        } catch (ParseException e) {
            System.out.println("Girdiğiniz bilgilerle eşleşen bir havayolu bulunamadi \nyeniden deneyin");
            setHavayolu();
        }
    }
    void setKalkis(){
        Scanner scanner = new Scanner(System.in);
        var s = scanner.nextLine();
        try {
            kalkis=destination.Parse(s);
        } catch (ParseException e) {
            System.out.println("Girdiginiz bilgilerle eslesen bir havalimani bulunamadi \nyeniden deneyin");
            setKalkis();
        }

    }
    void setType(){
        System.out.println("Yolcu tipi uçuş icin yolcu yada Y yazın");
        System.out.println("Kargo tipi uçuş icin kargo yada K yazın");
        Scanner scanner = new Scanner(System.in);
        var s=scanner.nextLine();
        if(s.equalsIgnoreCase("yolcu")||s.equalsIgnoreCase("y")) {
            type = FlightType.Yolcu;
            System.out.println("Lütfen Koltuk no girin");
            setKoltukNo();
        }
        else if(s.equalsIgnoreCase("kargo")||s.equalsIgnoreCase("k")) {
            type = FlightType.Kargo;
            System.out.println("Lütfen Kargonun ağırlığını Kg şeklinde girin");
            setAgirlik();
        }
        else
        {
            System.out.println("tekrar deneyin");
            setType();
        }
    }
    private void setKoltukNo() {
        Scanner scanner = new Scanner(System.in);
        var s = scanner.nextLine();
        if(s.matches("^\\d{2}[A-Fa-f]")) {
            koltukNo = s.toUpperCase();
        }
        else {
            System.out.println("Hatalı giriş yeniden deneyin");
            setKoltukNo();
        }
    }
    private void setAgirlik() {
        Scanner scanner = new Scanner(System.in);
        try {
            int a = scanner.nextInt();
            if(a>100&&a<5000)
            {
                agirlik=a;
            }
            else {
                System.out.println("Kargonuz havayolu tarafından belirlenen ağırlık aralığının dışında");
                setAgirlik();
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("Lütfen sayi olarak girin");
            setAgirlik();
        }

    }

    @Override
    public String toString() {
        String s="";
        s+=DATEFORMAT.format(date);
        s+="\t  "+havayolu.code+"    ";
        s+="\t    "+kalkis.code+"    ";
        s+="\t "+type+"   ";
        s+="\t  "+(koltukNo==null?"---":koltukNo)+"    ";
        s+="\t  "+(agirlik==null?"---":agirlik)+"  ";
        return s;
    }
}
enum Havayolu {
    klm("KLM", "klm airlines"),
    lh("LH", "Lufthansa Airlines"),
    thy("THY", "turkish airlines");
    public final String code;
    public final String name;

    Havayolu(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static Havayolu Parse(String s) throws ParseException {
        for (var value : Havayolu.values()) {
            if (s.toUpperCase().equals(value.code) || s.toLowerCase().equals(value.name))
                return value;
        }
        throw new ParseException(s, 0);
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }
}//
enum destination {
    isl("ISL", "istanbul"),
    ams("AMS", "schiphol"),
    fra("FRA", "frankfurt");
    public final String code;
    public final String name;

    destination(String code, String name) {
        this.code = code;
        this.name = name;
    }

    static destination Parse(String s) throws ParseException {
        for (var value : destination.values()) {
            if (s.toUpperCase().equals(value.code) || s.toLowerCase().equals(value.name))
                return value;
        }
        throw new ParseException(s,0);
    }

    @Override
    public String toString() {
        return code + "-" + name;
    }
}
enum FlightType {
    Yolcu,Kargo
}
