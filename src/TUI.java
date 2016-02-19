import java.io.PrintWriter;
import java.util.Scanner;

/**
 * tryb tekstowy
 */
public class TUI {
    private Plansza plansza = new Plansza();
    private Komunikaty komunikaty = new Komunikaty();
    private Scanner sc = new Scanner(System.in);
    private PrintWriter wyj = new PrintWriter(System.out,true);

    public TUI(){
        System.out.println("Działam!!!!!");
        this.wyj.println("Działam");
    }

    /**
     * Rozpoczyna grę
     */
    public void start(){
        System.out.println("NO CO");
        this.wyj.printf("Czy chcesz grać z komputerem? (T/N)");
        String odp = this.sc.next();
        int i = 0;
        while (i < 3 && !(odp.equals("T") || odp.equals("N"))){
            i++;
            this.wyj.println("Podałeś nieprawidłową odpowiedź. Spróbuj jeszcze raz.");
            this.wyj.println("Czy chcesz grać z komputerem? (T/N)");
            odp = sc.next();
        }
        if (!(odp.equals("T") || odp.equals("N")))
            throw new NullPointerException("Nie umiesz grać :'(");

        if (odp.equals("T")) {
            wyj.println("Sory, nie działa");
        } else {
            wyj.println("Grasz z drugą osobą");
            wyj.println("Gracz1 to O, Gracz2 to X");
            this.graZCzlowiekem();
        }
    }

    public void graZCzlowiekem() throws NullPointerException{
        int kto = -1;
        while (!plansza.czyKoniecGry()) {
            this.plansza.drukuj();
            int[] gdzie = this.podajRuch(kto);
            this.plansza.ruchGracza(gdzie[0], gdzie[1], kto);
            //this.komunikaty.infoRuch();
            kto = kto==1?-1:1;
        }
        this.plansza.drukuj();
        this.wyj.println(this.komunikaty.wygrana(this.plansza.ktoWygral()));
    }

    /**
     * Zwraca tablicę adresu
     * tab[0] - wiersz
     * tab[1] - kolumna
     * @param kto kto wykonuje ruch
     * @return tablica adresu ruchu
     */
    public int[] podajRuch(int kto){
        this.wyj.println(this.komunikaty.twojRuch(kto));
        this.wyj.println("podaj wiersz");
        int w = this.sc.nextInt();
        this.wyj.println("podaj kolumnę");
        int k = this.sc.nextInt();
        return new int[] {w,k};


    }




}
