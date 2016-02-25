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
				for(int i = 0; i<3; i++){
					for(int j=0; j<3; j++){
						buttons[i][j].setEnabled(false);
					}
				}
			}
		});
		
		
		bStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);
				bStop.setEnabled(true);
				for(int i = 0; i<3; i++){
					for(int j=0; j<3; j++){
						buttons[i][j].setEnabled(true);
					}
				}
				
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

	/**
	 * Odpowiada za wydarzenia występujące po kliknięciu na przycisk
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean znal = false;
		Object zrodlo = e.getSource();
		
		for(int i=0; i<3 && !znal; i++){
			for(int j=0; j<3 && !znal; j++){
				if(zrodlo == buttons[i][j]){
					ruchCzlowieka(i,j);
					znal = true;
				}
			}
		}
	}

	public void start(){
		if (chKomp.isSelected()){
			graZKomputerem();
		}
		else {
			if (plansza.czyKoniecGry()){
				lInfo.setText(this.komunikaty.wygrana(this.plansza.ktoWygral()));
				this.plansza.wyczysc();
				this.wyzerujPlansze();
				bStart.setEnabled(true);
				bStop.setEnabled(false);
			}
		}
	}
	
	public void graZKomputerem(){
		
	}
	
	/*
	 * Nie wiem, czy ta metoda ma sens, czy wszystko od razu w start()
	 * 
	public void graZCzlowiekiem(){
		if (plansza.czyKoniecGry()){
			lInfo.setText(this.komunikaty .wygrana(this.plansza.ktoWygral()));
		}
	}
	*/
	
	/**
	 * Operacje, które mają być wykonane, gdy gracz wykonuje ruch
	 * @param wiersz 
	 * @param kolumna
	 */
	public void ruchCzlowieka(int wiersz, int kolumna){
		this.plansza.ruchGracza(wiersz, kolumna);
		this.buttons[wiersz][kolumna].setText(this.dajZnak(this.plansza.getKto()));
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
	public void wyzerujPlansze(){
		for(int i = 0; i<3; i++){
			for(int j=0; j<3; j++){
				buttons[i][j].setText("-");
			}
		}
	}

}

