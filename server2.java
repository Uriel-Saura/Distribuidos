import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

class Servidor2{
	static class Worker extends Thread{
		Socket conexion;
		Worker(Socket conexion){
			this.conexion = conexion;
		}

		static void read(DataInputStream f,byte[] b,int posicion, int longitud) throws Exception {
			while (longitud > 0){
				int n = f.read(b,posicion,longitud);
				posicion += n;
				longitud += n;
			}
		}

		public void run(){
			try{		
				DataOutputStream salida = new DataOutputStream(conexion.getOutputStream());
				DataInputStream entrada = new DataInputStream(conexion.getInputStream());

				int n = entrada.readInt();
				System.out.println(n);

				double x = entrada.readDouble();
				System.out.println(x);

				byte[] buffer = new byte[4];
				read(entrada,buffer,0,4);
				System.out.println(new String (buffer, "UTF-8"));
				
				salida.write("Hola".getBytes());

				byte[] a = new byte[5*8];
				read(entrada,a,0,5*8);
				ByteBuffer b = ByteBuffer.wrap(a);
				for (int i = 0; i < 5; i++) System.out.println(b.getDouble());
			}
			catch(Exception e){
				System.err.println(e.getMessage());
			}
			finally{
				try{
					conexion.close();
				}
				catch(Exception e2){
					System.err.println(e2.getMessage());
				}
			}
		}
	}
	public static void main(String[] args) throws Exception{
		ServerSocket servidor = new ServerSocket(50000);
		for(;;){
			Socket conexion = servidor.accept();
			Worker w = new Worker(conexion);
			w.start();
		}
	}
}
