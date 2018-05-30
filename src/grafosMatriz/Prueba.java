package grafosMatriz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Prueba {

	public static void main(String[] args) throws IOException {
		BufferedReader io= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter rc= new BufferedWriter(new OutputStreamWriter(System.out));
		String mensaje= io.readLine();
		String[] split= mensaje.split(" ");
		while (!split[0].equals("0") && !split[1].equals("0")) {
			int roads = Integer.parseInt(split[1]);
			
			
	     }
	}
}
