import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
* Graficzny
*/
public class GUI extends JFrame implements ActionListener {
	
	private Plansza plansza = new Plansza();
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, bStart, bStop;
	private JButton[][] buttons = {{b1, b2, b3}, {b4, b5, b6}, {b7, b8, b9}};
	private JCheckBox chKomp;
	
	public GUI(){
		setSize(500,300);
		setTitle("Kółko i krzyżyk");
		setLayout(null);
		
		bStart = new JButton("Start");
		bStart.setBounds(280, 30, 80, 40);
		add(bStart);
		
		bStop = new JButton("Stop");
		bStop.setBounds(280, 80, 80, 40);
		add(bStop);
		
		chKomp = new JCheckBox("Gra z komputerem");
		chKomp.setBounds(280, 130, 160, 40);
		add(chKomp);
		
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
				x+=70;
			}
			x=30;
			y+=70;
		}	
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
					//plansza.ruchGracza(i, j, kto);
					buttons[i][j].setText("X");
					znal = true;
				}
			}
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

}

