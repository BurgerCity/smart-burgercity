package SimulatorStatement;

import java.io.IOException;
import java.sql.SQLException;

public class SimulationIHM {
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, InterruptedException {
		Normal n = new Normal(); //5 to start basic simulation 
		n.GiveStatement("127.0.0.1", 2013, 5);
	}
}
