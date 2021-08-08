package a.core.gus.gyem;

public class GyemMain extends GyemSystem {

	public static void main(String[] args) {
		try {
			init(args);
			moduleE(M001_E_CUSTOMIZE).e();
			moduleE(M002_E_LAUNCH).e();
		}
		catch(Exception e) {
			fatalMain(e);
		}
	}
}
