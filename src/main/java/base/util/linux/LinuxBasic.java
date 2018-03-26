package base.util.linux;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author xus
 * 2017年11月22日下午2:06:49
 * LinuxBasic.java
 */
public class LinuxBasic
{
	/**
	 * 连接Linux，获得connection
	 * @author xus
	 * 2017年11月22日下午1:30:31
	 * @param address
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static Connection connectLinux(String address, String username, String password) throws Exception {
		/* Create a connection instance */
		Connection conn = new Connection(address);

		/* Now connect */
		conn.connect();
		
		/* Authenticate.
		 * If you get an IOException saying something like
		 * "Authentication method password not supported by the server at this stage."
		 * then please check the FAQ.
		 */
		boolean isAuthenticated = conn.authenticateWithPassword(username, password);

		if (isAuthenticated == false)
			throw new IOException("Authentication failed.");
		return conn;
	}
	
	/**
	 * 打开会话
	 * @author xus
	 * 2017年11月22日下午1:55:35
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public static Session openSession(Connection connection) throws Exception {
		return connection.openSession();
	}
	
	/**
	 * 执行命令
	 * @author xus
	 * 2017年11月22日下午2:05:44
	 * @param sess
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public static LinuxResult ExeCmd(Session sess, String cmd) throws Exception {
		System.out.println("执行的命令: " + cmd);
		long start =System.currentTimeMillis();
		sess.execCommand(cmd);

		System.out.println("Here is some information about the remote host:");

		/* 
		 * This basic example does not handle stderr, which is sometimes dangerous
		 * (please read the FAQ).
		 */

		InputStream stdout = new StreamGobbler(sess.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		String line = "";
		String message = "";
		while (true)
		{
			line = br.readLine();
			if (line == null)
				break;
			message += line;
			System.out.println(line);
		}
		
		/* Show exit status, if available (otherwise "null") */

		System.out.println("ExitCode: " + sess.getExitStatus());
		LinuxResult result = new LinuxResult();
		result.setCode(sess.getExitStatus());
		result.setMessage(message);
		System.out.println("执行耗时 : "+(System.currentTimeMillis()-start)/1000f+" 秒 ");
		return result;
	}
	
	
	/**
	 * 关闭回话
	 * @author xus
	 * 2017年11月22日下午1:58:08
	 * @param session
	 */
	public static void closeSession(Session session)  throws Exception {
		session.close();
	}
	
	/**
	 * 关闭连接
	 * @author xus
	 * 2017年11月22日下午1:59:34
	 * @param connection
	 * @throws Exception
	 */
	public static void closeConnection(Connection connection) throws Exception {
		connection.close();
	}
}