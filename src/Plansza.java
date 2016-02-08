import java.io.*;

/**
 * Reprezentuje planszę do gry w kółko i  krzyżyk
 */
public class Plansza {
    private int[][] plansza;
    PrintWriter wyj = new PrintWriter(System.out, true);

    public Plansza() {
        plansza = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    }

    /**
     * Wyświetla planszę w terminalu
     */
    public void drukuj(){
        for(int i=0; i<plansza.length; i++){
            for(int j=0; j<plansza[0].length; j++){
                wyj.printf("%3d%s",plansza[i][j]," ");
            }
            wyj.println();
        }
    }

    /**
     * Zapisuje ruch gracza w planszy
     *
     * @param kolumna kolumna w której gracz wykonuje ruch
     * @param wiersz wiersz w którym gracz wykonuje ruch
     * @param gracz 'wartość' gracza (1 lub -1)
     */
    public void ruchGracza(int kolumna, int wiersz, int gracz) {
        if ((kolumna < 0) || (kolumna > 2) || (wiersz < 0) || (wiersz > 2)) {
            throw NullPointerException("Nieprawidłowy adres komórki");
        }
        this.plansza[wiersz][kolumna] = gracz;
    }


}

