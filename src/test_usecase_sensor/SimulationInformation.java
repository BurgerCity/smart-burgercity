package test_usecase_sensor;

import java.io.IOException;
import java.sql.SQLException;

import simulatorStatement.Normal;

public class SimulationInformation {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException, InterruptedException {
		Normal n = new Normal();
		n.GiveStatement("172.31.249.164", 2013, 4);
	}

}
