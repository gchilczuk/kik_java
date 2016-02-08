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
            throw new NullPointerException("Nieprawidłowy adres komórki");
        }
        this.plansza[wiersz][kolumna] = gracz;
    }

    /**
     * Sprawdza czy gra została zakończona (nieważne czy remisem czy wygraną)
     * @return true jeżeli gra została zakończona remisem LUB wygraną; else: false
     */
    public boolean czyKoniecGry(){
        boolean czy_wygrana = false;
        boolean czy_remis = true;
        if (    Math.abs(sumaWiersza(0)) == 3 ||
                Math.abs(sumaWiersza(1)) == 3 ||
                Math.abs(sumaWiersza(2)) == 3 ||
                Math.abs(sumaKolumny(0)) == 3 ||
                Math.abs(sumaKolumny(1)) == 3 ||
                Math.abs(sumaKolumny(2)) == 3   )
            czy_wygrana = true;
        if (!czy_wygrana){
            for (int w = 0; w < 3; w++){
                for (int k = 0; k < 3; k++){
                    if (plansza[w][k] == 0){
                        czy_remis = false;
                        break;
                    }
                }
            }
        }
        return czy_wygrana || czy_remis;

    }

    /**
     * Metoda pomocnicza
     *
     * Sumuje wierwsz planszy
     * @param i numer wiersza, który ma zostać zsumowany
     * @return suma elementów w wierszu
     */
    private int sumaWiersza(int i){
        int s = 0;
        for (int j = 0; j < 3; j++)
            s += this.plansza[i][j];
        return s;
    }

    /**
     * Sumuje kolumnę planszy
     * @param i kolumna, która ma zostać zsumowana
     * @return suma elementów kolumny
     */
    private int sumaKolumny(int i){
        return this.plansza[0][i] + this.plansza[1][i] + this.plansza[2][i];
    }


}

