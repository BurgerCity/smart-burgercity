package SimulatorStatement;

import java.io.IOException;
import java.sql.SQLException;

public class SimulationAlert {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, InterruptedException {
		Normal n = new Normal();
		n.GiveStatement("127.0.0.1", 2013, 6);
	}
}