import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
* Graficzny
*/
public class GUI extends JFrame implements ActionListener {
	
	private Plansza plansza = new Plansza();
	private Komunikaty komunikaty = new Komunikaty();
    private kik_AI kikAi = new kik_AI();
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private final JButton bStart, bStop;
	private JButton[][] buttons = {{b1, b2, b3}, {b4, b5, b6}, {b7, b8, b9}};
	private JCheckBox chKomp;
	private JLabel lInfo;
	
	public GUI(){
		setSize(500,300);
		setTitle("Kółko i krzyżyk");
		setLayout(null);
	
		int x = 30;
		int y = 30;
		int szerokosc = 60;
		int wysokosc = 60;
		
		for(int i = 0; i<3; i++){
			for(int j=0; j<3; j++){
				buttons[i][j] = new JButton("-");
				buttons[i][j].setBounds(x, y, szerokosc, wysokosc);
				add(buttons[i][j]);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setEnabled(false);
				x+=70;
			}
			x=30;
			y+=70;
		}
		
		bStart = new JButton("Start");
		bStart.setBounds(280, 30, 80, 40);
		add(bStart);
		
		bStop = new JButton("Stop");
		bStop.setBounds(280, 80, 80, 40);
		add(bStop);
		bStop.setEnabled(false);
		bStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(true);
				bStop.setEnabled(false);
				//DODAC PANEL(?) ZEBY WSZYSTKIE PRZYCISKI ODRAZU USTAWIC!!!!!
				zmien_guziki(false);
			}
		});

		
		bStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);
				bStop.setEnabled(true);
				zmien_guziki(true);
                przygotuj_plansze();
			}
		});
		
		chKomp = new JCheckBox("Gra z komputerem");
		chKomp.setBounds(280, 130, 160, 40);
		add(chKomp);

		
		lInfo = new JLabel("Info:");
		lInfo.setBounds(280, 180, 250, 20);
		lInfo.setForeground(new Color(0, 0, 0));
		lInfo.setFont(new Font("SansSerif", Font.BOLD, 12));
		add(lInfo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}

    public void zmien_guziki(boolean var){
        for(int i = 0; i<3; i++)
            for(int j=0; j<3; j++)
                buttons[i][j].setEnabled(var);
        chKomp.setEnabled(!var);
    }

	/**
	 * Odpowiada za wydarzenia występujące po kliknięciu na przycisk
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
        boolean znal = false;
		Object zrodlo = e.getSource();
		
		for(int i=0; i<3 && !znal; i++){
			for(int j=0; j<3 && !znal; j++){
				if(zrodlo == buttons[i][j]){
					ruchCzlowieka(i,j);
					znal = true;
					buttons[i][j].setEnabled(false);
				}
			}
		}
        if(chKomp.isSelected()&&!plansza.czyKoniecGry()){
            ruchKomputera();
        }
	}

	/*public void start(){
		if (chKomp.isSelected()){
			graZKomputerem();
		}
	}*/


	
	public void ruchKomputera(){
        int[] gdzie = kikAi.podajRuch_latwy(plansza.getKto(), plansza);
        this.buttons[gdzie[0]][gdzie[1]].setText(this.dajZnak(this.plansza.getKto()));
        this.plansza.ruchGracza(gdzie[0],gdzie[1]);
        buttons[gdzie[0]][gdzie[1]].setEnabled(false);
        czyKoniec();
	}
	
	/**
	 * Operacje, które mają być wykonane, gdy gracz wykonuje ruch
	 * @param wiersz 
	 * @param kolumna
	 */
	public void ruchCzlowieka(int wiersz, int kolumna){
		this.buttons[wiersz][kolumna].setText(this.dajZnak(this.plansza.getKto()));
		this.plansza.ruchGracza(wiersz, kolumna);
		czyKoniec();
	}

    void czyKoniec(){
        if(this.plansza.czyKoniecGry()){
            lInfo.setText(this.komunikaty.wygrana(this.plansza.ktoWygral()));
            zmien_guziki(false);
            bStop.setEnabled(false);
            bStart.setEnabled(true);
        }
    }
	
	/**
	 * Zwraca odpowiedni znak w zależności od gracza wykonującego ruch
	 * @param kto kto wykonuje aktualny ruch
	 * @return odpowiedni znak w zależności od gracza
	 */
	public String dajZnak(int kto){
		return kto==-1?"O":"X";
	}
	
	/**
	 * Czyści planszę
	 */
	public final void wyzerujPlansze(){
		for(int i = 0; i<3; i++){
			for(int j=0; j<3; j++){
				buttons[i][j].setText("-");
			}
		}
	}

    void przygotuj_plansze(){
        wyzerujPlansze();
        plansza.wyczysc();
    }

}

