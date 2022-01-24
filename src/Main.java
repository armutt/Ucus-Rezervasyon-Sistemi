public class Main {
    public static void main(String[] args) {
        var menu = new Menu();
        while (true) {
            menu.printMain();
            String next = menu.getScanner().nextLine();
            switch (next) {
                case "1" -> menu.addFlightScreen();
                case "2" -> menu.showFlightsScreen();
                case "x" -> System.exit(0);
                default -> System.out.println("tekrar dene");
            }
        }
    }
}
