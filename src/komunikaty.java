/**
 * Moduł zawierający komunikaty do gry
 */
public class komunikaty {

    public komunikaty() {}

    /**
     * Zwraca komunikat o tym kto wygrał
     * @param kto -1 - kółko, 1 - krzyżyk, 0 - remis
     * @return komunikat o zwycięzcy
     */
    public static String wygrana(int kto){
        String wynik;
        switch (kto){
            case -1:
                wynik = "Wygrało KÓŁKO. Gratulacje!";
                break;
            case 1:
                wynik = "Wygrał KRZYŻYK. Gratulacje!";
                break;
            case 0:
                wynik = "REMIS!";
                break;
            default:
                throw new NullPointerException("Nieprawidłowa wartość gracza wygrywającego");
        }

        return wynik;
    }

    /** DUŻE PYTANIE: CZY -1 i 1 PRZYPISANE MAJĄ BYĆ NA STAŁE DO KÓŁKA I KRZYŻYKA
     *  CZY DO Gracz1 i Gracz2, którzy mogą zamieniać się znakami
     */
    /**
     * Komunikat o tym kto teraz wykonuje ruch
     * @param kto -1 kółko, 1 krzyżyk, 0 komputer (AI)
     * @return komunikat o tym kto ma wykonać ruch
     */
    public static String twojRuch(int kto){
        String ruch;
        switch (kto){
            case -1:
                ruch = "Ruch gracza O";
                break;
            case 1:
                ruch = "Ruch gracza X";
                break;
            case 0:
                ruch = "Ruch wykonuje komputer";
                break;
            default:
                throw new NullPointerException("Nieprawidłowa wartość gracza");
        }

        return ruch;
    }

    public static String infoRuch(int kto, int k, int )
}
