import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private PrintStream out = System.out;
    private InputStream in = System.in;
    private int errCount = 0;
    private final int errLimit = 3;

    public PrintStream getOut() {
        return out;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public InputStream getIn() {
        return in;
    }

    public void setIn(InputStream in) {
        scanner.close();
        scanner = new Scanner(in);
        this.in = in;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Menu() {
        scanner=new Scanner(in);
    }

    void addFlightScreen() {
        out.println("Lütfen uçuş icin tarih girin");
        var date = parseDate(scanner.next());
        if (date == null)
            return;
        out.println("Lütfen havayolunun  kodunu yada adini girin");
        Arrays.stream(Havayolu.values()).forEach(havayolu -> out.println(havayolu));
        var havayolu= parseHavayolu(scanner.next());
        if (havayolu==null)
            return;
        out.println("Lütfen kalkış Destinasyonunuzun kodunu yada adını girin");
        Arrays.stream(Destination.values()).forEach(destination -> out.println(destination));
        var departure= parseDestination(scanner.next());
        out.println("Lütfen varış Destinasyonunuzun kodunu yada adını girin");
        var arrival= parseDestination(scanner.next());
        while (arrival==departure)
        {
            out.println("kalkis ve varis ayni olamaz");
            arrival=parseDestination(scanner.next());
        }
        out.println("ucus tipini secin");
        out.println("kargo icin 1'e, yolcu icin 2'ye basin");
        String answer=scanner.next();
        while (!answer.equals("1") && !answer.equals("2"))
        {
            out.println("yeniden deneyin");
            answer=scanner.next();
        }
        if(answer=="1")
        {
            out.println("Kargonun agirligini girin");
            int weight=scanner.nextInt();

            var flight=new CargoFlight(date,havayolu,departure,arrival,weight);
            out.println("yeni ucus olusturuldu");
            out.println(flight);
        }
        else
        {
            out.println("Koltuk Numarasi girin");
            var koltukNo=scanner.next();
            var flight = new YolcuFlight(date,havayolu,departure,arrival,koltukNo);
            out.println("yeni ucus olusturuldu");
            out.println(flight);
        }
        out.println("menuye icin enter'a basın");
        scanner.nextLine();

    }

    Date parseDate(String str) {
        try {
            var date=Flight.DATEFORMAT.parse(str);
            if(date.after(new Date()))
                return date;
            else {
                out.println("tarih bugunden once olamaz");
                return parseDate(scanner.next());
            }
        } catch (ParseException e) {
            if (errCount++ == errLimit) {
                out.println("çok fazla hatalı giriş");
                return null;
            } else
                out.println("Tekrar Deneyin");
            return parseDate(scanner.next());

        }
    }

    Havayolu parseHavayolu(String str) {

        try {
            return Havayolu.Parse(str);
        } catch (ParseException e) {
            if (errCount++ == errLimit) {
                out.println("çok fazla hatalı giriş");
                return null;
            } else
                out.println("Tekrar Deneyin");
            return parseHavayolu(scanner.next());
        }
    }

    Destination parseDestination(String str) {
        try {
            return  Destination.Parse(str);
        } catch (ParseException e) {
            if (errCount++ == errLimit) {
                out.println("çok fazla hatalı giriş");
                return null;
            } else
                out.println("Tekrar Deneyin");
            return parseDestination(scanner.next());
        }
    }

    void showFlightsScreen() {
        Flight[] flights = Flight.getFlights();
        if (Flight.getFlights().length == 0) {
            out.println("Kayıtlı uçuş yok");
        } else {
            out.println("  Tarih   \tHavayolu \t   Kalkış  \t   Varış  \tucus tipi\tkoltuk no\tAgirlik");
            Arrays.sort(flights);
            out.println(Arrays.toString(flights));
        }
        out.println("devam etmek icin enter'a basın");
        scanner.nextLine();
    }

    void printMain() {
        out.println("yeni bir uçuş rezervasyonu yapmak için 1 yazın");
        out.println("Var olan rezervasyonlarınız görmek için 2 yazın");
        out.println("Kapamak için x yazın");
    }
}
