package proceso;
import java.io.*;

public class PipeExample {

	public static void main(String[] args) throws IOException {
		
		final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream input = new PipedInputStream(output);
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					output.write("Hola desde el hilo 1!".getBytes());
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int data = input.read();
					while (data != -1) {
					System.out.print((char) data);
					data = input.read();
				}
				input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
}