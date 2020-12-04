import java.util.Scanner;

public class Menu {

    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        var menu=new Menu();
        while (true) {
            menu.printMain();
            var next = scanner.next();
            switch (next) {
                case "1" -> Flight.Create();
                case "2" -> {
                    if(Flight.flights.isEmpty()) {
                        System.out.println("Kayıtlı uçuş yok");
                    }
                    else {
                        System.out.println("  Tarih   \tHavayolu \tDestinasyon\tucus tipi\tkoltuk no\tAgirlik");
                        Flight.flights.sort((o1, o2) -> o1.date.compareTo(o2.date));
                        Flight.flights.forEach(flight -> System.out.println(flight));
                    }
                    System.out.println("devam etmek icin enter'a basın");
                    new Scanner(System.in).nextLine();
                }
                case "x" -> System.exit(0);
                default -> System.out.println("tekrar dene");
            }
        }



    }
    void printMain()
    {
        System.out.println("yeni bir uçuş rezervasyonu yapmak için 1 yazın");
        System.out.println("Var olan rezervasyonlarınız görmek için 2 yazın");
        System.out.println("Kapamak için x yazın");
    }
}
