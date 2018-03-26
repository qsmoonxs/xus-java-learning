package base.util.linux;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class LinuxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String address = "192.168.88.133";
		String username = "root";
		String password = "differ_888";
		try {
			long start =System.currentTimeMillis();
			Connection connection = LinuxBasic.connectLinux(address, username, password);
			Session session = LinuxBasic.openSession(connection);
			String cmd = "pwd";
			LinuxResult result = LinuxBasic.ExeCmd(session, cmd);
			System.out.println(result.toString());
			LinuxBasic.closeSession(session);
			LinuxBasic.closeConnection(connection);
			System.out.println("总耗时 : "+(System.currentTimeMillis()-start)/1000f+" 秒 ");
			
			//Basic.exeLinuxCmd(address, username, password, cmd);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
