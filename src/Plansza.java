import java.io.*;


/**
 * Reprezentuje plansze do gry w kolko i  krzyzyk
 */
public class Plansza {
    /**
     * wartosc kolka; zeby zapamietac i sie nie mylilo
     */
    static int _O = -1;

    /**
     * wartosc krzyzyka; zeby zapamietac i sie nie mylilo
     */
    static int _X = 1;

    private int kto = 1;

    /**
     * Tablica przechowujaca stan planszy
     */
    private int[][] plansza;

    /**
     * PrintWriter standardowego wyjscia
     */
    PrintWriter wyj = new PrintWriter(System.out, true);

    public Plansza() {
        plansza = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    }

    /**
     * Wyswietla plansze w terminalu
     */
    public void drukuj(){
        for(int i=0; i<plansza.length; i++){
            for(int j=0; j<plansza[0].length; j++){
                String komorka = plansza[i][j]==-1?"O":plansza[i][j]==1?"X":"—";
                wyj.printf("%3s%s",komorka," ");
            }
            wyj.println();
        }
    }

    /**
     * Zapisuje ruch gracza w planszy
     *
     * @param kolumna kolumna w ktorej gracz wykonuje ruch
     * @param wiersz wiersz w ktorym gracz wykonuje ruch
     */
    public void ruchGracza(int wiersz, int kolumna) {
        if ((kolumna < 0) || (kolumna > 2) || (wiersz < 0) || (wiersz > 2)) {
            throw new NullPointerException("Nieprawidlowy adres komorki");
        }
        else if (this.plansza[wiersz][kolumna] != 0){
        	throw new NullPointerException("To pole nie jest puste");
        }
        this.plansza[wiersz][kolumna] = this.kto;
        this.kto = -this.kto;
    }

    /**
     * Sprawdza czy gra zostala zakonczona (niewazne czy remisem czy wygrana)
     * @return true jezeli gra zostala zakonczona remisem LUB wygrana; else: false
     */
    public boolean czyKoniecGry(){
        boolean czy_wygrana = false;
        boolean czy_remis = true;
        if (    Math.abs(sumaWiersza(0)) == 3 ||
                Math.abs(sumaWiersza(1)) == 3 ||
                Math.abs(sumaWiersza(2)) == 3 ||
                Math.abs(sumaKolumny(0)) == 3 ||
                Math.abs(sumaKolumny(1)) == 3 ||
                Math.abs(sumaKolumny(2)) == 3 ||
                Math.abs(sumaPrzekatnej1()) == 3 ||
                Math.abs(sumaPrzekatnej2()) == 3)
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
     * Sprawdza, ktory gracz wygral
     * @return wartosc gracza, ktory zwyciezyl(1 lub -1) lub 0 (gdy remis)
     */
    public int ktoWygral(){
    	int wynik = 0;
    	if (    sumaWiersza(0) == 3 ||
    			sumaWiersza(1) == 3 ||
    			sumaWiersza(2) == 3 ||
    			sumaKolumny(0) == 3 ||
    			sumaKolumny(1) == 3 ||
    			sumaKolumny(2) == 3 ||
    			sumaPrzekatnej1() == 3 ||
    			sumaPrzekatnej2() == 3)
    		wynik = 1;
    	else if (    sumaWiersza(0) == -3 ||
    			     sumaWiersza(1) == -3 ||
    			     sumaWiersza(2) == -3 ||
    			     sumaKolumny(0) == -3 ||
    			     sumaKolumny(1) == -3 ||
    			     sumaKolumny(2) == -3 ||
    			     sumaPrzekatnej1() == -3 ||
    			     sumaPrzekatnej2() == -3)
    		wynik = -1;
    	return wynik;
    }
    

    /**
     * Czysci plansze
     */
    public void wyczysc(){
        this.plansza = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    }
    
    /**
     * Metoda pomocnicza
     *
     * Sumuje wiersz planszy
     * @param i numer wiersza, ktory ma zostac zsumowany
     * @return suma elementow w wierszu
     */
    public int sumaWiersza(int i){
        int s = 0;
        for (int j = 0; j < 3; j++)
            s += this.plansza[i][j];
        return s;
    }

    /**
     * Sumuje kolumne planszy
     * @param i kolumna, ktora ma zostac zsumowana
     * @return suma elementow kolumny
     */
    public int sumaKolumny(int i){
        return this.plansza[0][i] + this.plansza[1][i] + this.plansza[2][i];
    }
    
    /**
     * Sumuje "pierwsza" przekatna planszy
     * @return suma elementow przekatnej
     */
    public int sumaPrzekatnej1(){
    	return this.plansza[0][0] + this.plansza[1][1] + this.plansza[2][2];
    }
    
    /**
     * Sumuje druga przekatna
     * @return suma elementow przekatnej
     */
    public int sumaPrzekatnej2(){
    	return this.plansza[2][0] + this.plansza[1][1] + this.plansza[0][2];
    }

    /**
     * Getter, zwraca wartość liczbową wskazanego pola planszy
     * @param w wiersz
     * @param k kolumna
     * @return wartość liczbowa wskazanego pola planszy
     */
    public int dajPole(int w, int k) {
        return plansza[w][k];
    }

    /**
     * Informuje czyja jest teraz kolej gry
     * @return czy ruch
     */
    public int getKto() {
        return this.kto;
    }
}



