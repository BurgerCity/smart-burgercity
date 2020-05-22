package simulatorStatement;

import java.io.IOException;
import java.sql.SQLException;

public class SimulationIHM {
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, InterruptedException {
		Normal n = new Normal(); //5 to start basic simulation 
		n.GiveStatement("172.31.249.164", 2013, 5);
	}
}
