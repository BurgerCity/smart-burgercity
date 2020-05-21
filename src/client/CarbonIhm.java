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



/*
public class CarbonIhm extends JFrame {

	private CityInfo infoVille;
	private CarbonFootprintVehicle infoVehicle;
	private UserResponse ur;
	private Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int height = (int)dimension.getHeight();
	private int width  = (int)dimension.getWidth();
	//
	//private JComboBox liste1;
	//private Object[] percents = new Object[100];
	//
	//Panel panel = new Panel();
	
	CarbonIhm(){
		//
		//for(int i = 1; i <= 100;i++) {
		//	percents[i-1] = i;
		//}
		//
		//liste1 = new JComboBox(percents);
		//
		ur = new UserResponse();
		ur.init();
		infoVehicle = new CarbonFootprintVehicle();
		//percents = new int[100];
		setTitle("CarbonIhm"); 
		setSize(width,height); 
		setLocationRelativeTo(null); 
		setResizable(true); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container contentPane = this.getContentPane();
	    contentPane.add(new CarbonJPanel());
	    //contentPane.add(panel);
	    this.setVisible(true);
	}
	
	public static void main(String[] args) {
		CarbonIhm c = new CarbonIhm();
		
	}
}


*/  
public class CarbonIhm extends JFrame implements ActionListener{

	CityInfo infoVille;
	CarbonFootprintVehicle infoVehicle;
	UserResponse user;
	JTextField info = new JTextField();
	JTextField carbone = new JTextField();
	JTextField response = new JTextField();
	JTextField result = new JTextField();
	JTextField result2 = new JTextField();
	
	//Pourcentage
	String v1S;
	String v2S;
	String v3S;
	int v1I;
	int v2I;
	int v3I;
	//int cpt = 0;

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
		
	//	ec = String.valueOf(user.ec);
	//	finalEc = "City Carbon FootPrint : " + ec;
		
		response.setText(user.UserResponseString);
		carbone.setText(infoVehicle.FootprintString);
		info.setText(user.getInfo().infoString);
		info.setEditable(false);
		carbone.setEditable(false);
		response.setEditable(false);
		
	//	result.setText(finalEc);
	//	result.setText(String.valueOf(cpt));

		setTitle("CarbonIhm"); 
		setSize(1000,500); 
		setLocationRelativeTo(null); 
		setResizable(true); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new FlowLayout());
		panel.add(info);
		panel.add(carbone);
		panel.add(response);
		//liste1 Create
		
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
		/*
		panel.add(result);
		result.setEditable(false);
		this.setVisible(true);
		 */
		/*
		 /*CarbonJPanel cj = new CarbonJPanel();
		cj.add(panel);
		this.add(cj);
		 */
		this.add(panel);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		   if (source == this.bouton) {
			  // user.response(v1,v2,v3);
			   //ec = String.valueOf(user.ec);
			 //  int v = v1 + v2 + v3;
			  // ec =  String.valueOf(v);
			   v1S = (String)liste1.getSelectedItem();
			   v1I = Integer.parseInt(v1S);
			   
			   v2S = (String)liste2.getSelectedItem();
			   v2I = Integer.parseInt(v2S);
			   
			   v3S = (String)liste3.getSelectedItem();
			   v3I = Integer.parseInt(v3S);
			   user.init();
			   user.response(v1I,v2I,v3I);
			   ec = String.valueOf(user.ec);
			  // info.setText(user.getInfo().infoString);
			   finalEc = "City Carbon FootPrint : " + ec;
			   finalEcInh = "City Carbon FootPrint/Inhabitant : " + user.ec/pop;
			  // finalEc = String.valueOf(v1I + v2I + v3I);  
			   //finalEc = String.valueOf(v1I);
			   result.setText(finalEc);
			   result2.setText(finalEcInh);
			   try {
				user.getInfo().getClb().RequestInsert(ec);
				System.out.println("Resultat inserer");
			} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  // info.setText(user.getInfo().infoString);
			   
			   //pannel.add();
			   panel.add(result);
			   panel.add(result2);
			   result.setEditable(false);
			   result2.setEditable(false);
			   response.setText(user.UserResponseString); //
			   //info.setText(user.getInfo().infoString); //
			   info.setText(user.getInfo().infoString);
			  // panel.add(info);
			   this.add(panel);
			 //  result.setText(finalEc);
			  // ec =  String.valueOf(v1+v2+v3);
			   /*
			   cpt++;
			   finalEc = String.valueOf(cpt);
			   result.setText(finalEc);
			   panel.add(result);
			   result.setEditable(false);
			   */
			   this.setVisible(true);
		   }
		   if(source == this.liste1) {
			 //  v1S = (String)liste1.getSelectedItem();
			  // v1I = Integer.parseInt(v1S);
		   }
		   if(source == this.liste2) {
			 //  v2S = (String)liste2.getSelectedItem();
			 //  v2I = Integer.parseInt(v2S);
		   }
		   if(source == this.liste3) {
			 //  v3S = (String)liste3.getSelectedItem();
			 //  v3I = Integer.parseInt(v3S);
		   }
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, InterruptedException {
		CarbonIhm f1 = new CarbonIhm();
		//System.out.println(f1.v1);
		//System.out.println(f1.v2);
		System.out.println(f1.liste1.getSelectedItem());
		System.out.println(f1.liste2.getSelectedItem());
		System.out.println(f1.liste3.getSelectedItem());
		String a = (String)f1.liste1.getSelectedItem();
		System.out.println("a : " + a);
		int b = Integer.parseInt(a);
		b = b+1;
		System.out.println("b :" + b);
		System.out.println("end : " + Integer.parseInt(a) + 1);
		System.out.println(f1.v1I);
		System.out.println(f1.v2I);
		System.out.println(f1.v3I);	
	}
	//On utilisera le UserResponse pour enregistrer les pourcentage donné par l'utilisateur
	//puis cette même classe renverra la conversion de ses donnés en EC.

}

