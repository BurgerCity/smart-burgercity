package usecaseCarbon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class CarbonIhm extends JFrame /* implements ActionListener => va prendre les pourcentage*/{

	InfoVille infoVille;
	CarbonFootprintVehicle infoVehicle;

	CarbonIhm(){
		super();
		infoVille = new InfoVille();
		infoVehicle = new CarbonFootprintVehicle();


		setTitle("CarbonIhm"); 
		setSize(1000,500); 
		setLocationRelativeTo(null); 
		setResizable(true); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FlowLayout fL = new FlowLayout();
		this.setLayout(fL);
		JLabel label1 = new JLabel(infoVille.toString());
		JLabel label2= new JLabel(infoVehicle.toString());
 		this.add(label1);
 		this.add(label2);
 		this.setVisible(true);
 
	}

	public static void main(String[] args) {
		CarbonIhm f1 = new CarbonIhm();
	}

	//On utilisera le UserResponse pour enregistrer les pourcentage donné par l'utilisateur
	//puis cette même classe renverra la conversion de ses donnés en EC.

}

