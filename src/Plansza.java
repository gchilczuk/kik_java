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


}

