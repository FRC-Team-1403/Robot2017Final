package raspVision;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class raspInit {
	public Process p;
	public raspInit(String plink_path, String run_config) {

		InputStream std;
		OutputStream out;

		try {
			String[] command = new String[] { plink_path, "-load", run_config };

			p = new ProcessBuilder(command).start();

			std = p.getInputStream();
			out = p.getOutputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(std));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			
			writer.write("raspberry");
			writer.newLine();
			writer.flush();
			Thread.sleep(10000);
			
			writer.write("cd Desktop/VP/VP");
			writer.newLine();
			writer.flush();
			Thread.sleep(10000);
			
			writer.write("python __init__.py");
			writer.newLine();
			writer.flush();
			writer.close();

			
			Thread.sleep(10000);

			Scanner scanner = new Scanner(reader);
	        while (scanner.hasNextLine()) {
	            System.out.println(scanner.nextLine());
	        }
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void release(Process p)
	{
		p.destroy ();
	}
}