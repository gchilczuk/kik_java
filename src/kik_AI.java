/**
 * "Sztuczna inteligencja" do gry w kółko i krzyżyk
 */
public class kik_AI {
    public kik_AI(){}

    /**
     * Podaje ruch komputera na latwym poziomie trudnosci
     * tab[0] - wiersz
     * tab[1] - kolumna
     * @param kto kto wykonuje ruch (1 lub -1)
     * @param plansza plansza gry
     * @return Tablica adresu
     */
    public int[] podajRuch_latwy(int kto, Plansza plansza){
    	int[] ruch = null;
    	while(ruch==null){	
    	    int w = (int) (Math.random()*3);
            int k = (int) (Math.random()*3);
            if (plansza.dajPole(w,k) == 0){
            	ruch = new int[] {w,k};
            }
    	}
    	return ruch;
    }

    /**
     * Podaje ruch komputera na normalnym poziomie trudnosci
     * tab[0] - wiersz
     * tab[1] - kolumna
     * @param kto kto wykonuje ruch (1 lub -1)
     * @param plansza plansza gry
     * @return Tablica adresu
     */
    public int[] podajRuch_normalny(int kto, Plansza plansza){
        int[] ruch = wygraj(kto, plansza);
        if (ruch == null) ruch = blokuj(kto, plansza);
        if (ruch == null) ruch = podajRuch_latwy(kto, plansza);
        return ruch;
    }

    /**
     * Podaje ruch komputera na trudnym poziomie trudnosci
     * tab[0] - wiersz
     * tab[1] - kolumna
     * @param kto kto wykonuje ruch (1 lub -1)
     * @param plansza plansza gry
     * @return Tablica adresu
     */
    public int[] podajRuch_trudny(int kto, Plansza plansza){
        int[] ruch = wygraj(kto, plansza);
        if (ruch == null) ruch = blokuj(kto, plansza);
        if (ruch == null) ruch = podajRuch_latwy(kto, plansza);
        if (ruch == null){
            int liczba_ruchow = 0;
            for (int w = 0; w <3; w++)
                for (int k = 0; k <3; k++)
                    if(plansza.dajPole(w,k) != 0) liczba_ruchow++;
            if (liczba_ruchow % 2 == 0)
                ruch = niePrzegram_pierwszy(kto, plansza);
            else ruch = niePrzegram_drugi(kto, plansza);
        }
        return ruch;
    }

    /**
     * Wybiera ruch wg najlepszej taktyki jeżeli komputer rozpoczyna rozgrywkę
     * @param kto kto wykonuje ruch
     * @param plansza plansza gry
     * @return tablica ruchu
     */
    public int[] niePrzegram_pierwszy(int kto, Plansza plansza){
        return null;
    }

    /**
     * Wybiera ruch wg najlepszej taktyki jeżeli komputer gra jako drugi
     * @param kto kto wykonuje ruch
     * @param plansza plansza gry
     * @return tablica ruchu
     */
    public int[] niePrzegram_drugi(int kto, Plansza plansza){
        return null;
    }


    /**
     * Zwraca ruch wygrywający o ile jest możliwy, jeżeli nie - daje null
     * @param kto kto wykonuje ruch
     * @param plansza plansza gry
     * @return tablica adresu ruchu wygrywającego lub null
     */
    public int[] wygraj(int kto, Plansza plansza){
        int[] ruch = null;
        for (int i = 0; i<3 && ruch == null; i++){
            if (plansza.sumaWiersza(i) == 2*kto){
                for (int j = 0; j < 3 && ruch == null; j++)
                    if (plansza.dajPole(i, j) == 0) ruch = new int[] {i,j};
            }
        }

        for (int i = 0; i<3 && ruch == null; i++){
            if (plansza.sumaKolumny(i) == 2*kto){
                for (int j = 0; j < 3 && ruch == null; j++)
                    if (plansza.dajPole(j, i) == 0) ruch = new int[] {j, i};
            }
        }

        if (ruch == null && plansza.sumaPrzekatnej1()==2*kto)
            ruch = plansza.dajPole(0,0) == 0 ? new int[] {0,0} : plansza.dajPole(1,1) == 0 ? new int[] {1,1} : plansza.dajPole(2,2) == 0 ? new int[] {2,2} : null;

        if (ruch == null && plansza.sumaPrzekatnej2()==2*kto)
            ruch = plansza.dajPole(0,2) == 0 ? new int[] {0,2} : plansza.dajPole(1,1) == 0 ? new int[] {1,1} : plansza.dajPole(2,0) == 0 ? new int[] {2,0} : null;

        return ruch;
    }

    /**
     * Zwraca ruch blokujący wygraną przeciwnika o ile jest możliwy, jeżeli nie - daje null
     * @param kto kto wykonuje ruch
     * @param plansza plansza gry
     * @return tablica adresu ruchu wygrywającego lub null
     */
    public int[] blokuj(int kto, Plansza plansza){
        return wygraj(-kto, plansza);
    }

}
