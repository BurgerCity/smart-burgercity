package client;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import tram.wind;
import javax.swing.JLabel;


public class CarbonIhm extends JFrame implements ActionListener{

	CityInfo infoVille;
	CarbonFootprintVehicle infoVehicle;
	UserResponse user;
	JTextField info = new JTextField();
	JTextField carbone = new JTextField();
	JTextField response = new JTextField();
	JTextField result = new JTextField();
	JTextField result2 = new JTextField();
	
	//Percentage
	String v1S;
	String v2S;
	String v3S;
	int v1I;
	int v2I;
	int v3I;
	private String[] percents = new String[100];
	private JComboBox<String> liste1;
	private JComboBox<String> liste2;
	private JComboBox<String> liste3;

	JButton bouton;
	JPanel panel;
	String ec;
	String finalEc;
	String finalEcInh;
	float pop;
	CarbonIhm() throws ClassNotFoundException, IOException, SQLException, InterruptedException{
		super();
		infoVille = new CityInfo(); // 100 valeur test, a aller chercher dans BDD
		infoVehicle = new CarbonFootprintVehicle();
		user = new UserResponse();
		pop = infoVille.getPopulationSize();	
		response.setText(user.UserResponseString);
		carbone.setText(infoVehicle.FootprintString);
		info.setText(user.getInfo().infoString);
		info.setEditable(false);
		carbone.setEditable(false);
		response.setEditable(false);
		
		setTitle("CarbonIhm"); 
		setSize(1000,500); 
		setLocationRelativeTo(null); 
		setResizable(true); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		panel = new JPanel(new FlowLayout());
		panel.add(info);
		panel.add(carbone);
		panel.add(response);
		for(int i = 1; i <= 100;i++) {
			percents[i-1] = String.valueOf(i);
		}
		
		liste1 = new JComboBox<String>(percents);
		liste2 = new JComboBox<String>(percents);
		liste3 = new JComboBox<String>(percents);
		

				
		JTextArea car = new JTextArea("Entrez le pourcentage d'utilisation de VOITURE souhaité");
		car.setEditable(false);
		panel.add(car);
		panel.add(liste1);
		
		JTextArea velib = new JTextArea("Entrez le pourcentage d'utilisation de VELIB souhaité");
		velib.setEditable(false);
		panel.add(velib);
		panel.add(liste2);
		
		JTextArea tramway = new JTextArea("Entrez le pourcentage d'utilisation de TRAMWAY souhaité");
		tramway.setEditable(false);
		panel.add(tramway);
		panel.add(liste3);
		
		bouton = new JButton("Confirmer");
		panel.add(bouton);
		bouton.addActionListener(this);
		this.add(panel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		   if (source == this.bouton) {
			   v1S = (String)liste1.getSelectedItem();
			   v1I = Integer.parseInt(v1S);
			   
			   v2S = (String)liste2.getSelectedItem();
			   v2I = Integer.parseInt(v2S);
			   
			   v3S = (String)liste3.getSelectedItem();
			   v3I = Integer.parseInt(v3S);
			   user.init();
			   user.response(v1I,v2I,v3I);
			   ec = String.valueOf(user.ec);
			   finalEc = "City Carbon FootPrint : " + ec;
			   finalEcInh = "City Carbon FootPrint/Inhabitant : " + user.ec/pop;
			   result.setText(finalEc);
			   result2.setText(finalEcInh);
			   try {
				user.getInfo().getClb().RequestInsert(ec);
				System.out.println("Insert Result");
			} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e) {
				e.printStackTrace();
			}
			   panel.add(result);
			   panel.add(result2);
			   result.setEditable(false);
			   result2.setEditable(false);
			   response.setText(user.UserResponseString); //
			   info.setText(user.getInfo().infoString);
			   this.add(panel);
			   this.setVisible(true);
		   }
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		CarbonIhm f1 = new CarbonIhm();
	}
	//We will use the UserResponse to record the percentage given by the user then
	//this same class will return the conversion of its data in EC.

}

