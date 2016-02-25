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
    private kik_AI kikAi = new kik_AI();

    public TUI(){}

    /**
     * Rozpoczyna grę
     */
    public void start(){
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
            this.wyj.println("Wybierz poziom trudności: 1 - łatwy, 2 - normalny, 3 - trudny");
            int poziom = this.sc.nextInt();
            if (poziom < 1 || poziom > 3) throw new NullPointerException("Nie ma takiego poziomu trudności");
            this.kim_chcesz();
            this.graZKomputerem(poziom);
        } else {
            wyj.println("Grasz z drugą osobą");
            wyj.println("Gracz1 to O, Gracz2 to X");
            this.kim_chcesz();
            this.graZCzlowiekem();
        }
    }

    public void graZCzlowiekem() throws NullPointerException{
        while (!plansza.czyKoniecGry()) {
            this.plansza.drukuj();
            int[] gdzie = this.podajRuch(plansza.getKto());
            this.plansza.ruchGracza(gdzie[0], gdzie[1]);
            //this.komunikaty.infoRuch();
        }
        this.plansza.drukuj();
        this.wyj.println(this.komunikaty.wygrana(this.plansza.ktoWygral()));
        this.koniec();
    }

    public void graZKomputerem(int poziom) throws NullPointerException{
        while (!plansza.czyKoniecGry()){
            if (plansza.getKto() == -1){
                this.plansza.drukuj();
                int[] gdzie = this.podajRuch(plansza.getKto());
                this.plansza.ruchGracza(gdzie[0], gdzie[1]);
            }else{
                int[] gdzie = poziom == 1 ? this.kikAi.podajRuch_latwy(plansza.getKto(),plansza) : poziom == 2 ? this.kikAi.podajRuch_normalny(plansza.getKto(),plansza) : this.kikAi.podajRuch_trudny(plansza.getKto(),plansza);
                this.plansza.ruchGracza(gdzie[0], gdzie[1]);
            }
        }
        this.plansza.drukuj();
        this.wyj.println(this.komunikaty.wygrana(this.plansza.ktoWygral()));
        this.koniec();
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

    /**
     * Na zakończenie gry
     * sprawdza czy gracz chce grać jeszcze raz
     */
    public void koniec(){
        this.wyj.println("Chcesz zagrać jeszcze raz? (T/N)");
        String czy = this.sc.next();
        if (czy.equals("T")){
            this.plansza.wyczysc();
            this.start();
        }

    }

    public void kim_chcesz(){
        wyj.println("Kim chcesz grać? (O/X)");
        String kim = sc.next();
        if (kim.equals("O")) this.plansza.setKto(-1);
        if (kim.equals("X")) this.plansza.setKto(1);
    }




}
