import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.lang.*;
import java.util.Arrays;

public class Eth_generator {
	public static void main(String[] args) throws IOException {
		//file stuff
		BufferedReader bis = null; 
		String currentLine = null;
		File fin = new File("Eth.TL");
		FileReader fis = new FileReader(fin);
		bis = new BufferedReader(fis);
		
		//network stuff
		InetAddress addr = InetAddress.getByName(args[0]);
		DatagramSocket socket = new DatagramSocket();
		
		currentLine = bis.readLine();
        long nTime = -1;
        double nLasttime = 0.0;
		while (currentLine != null)
		{
			StringTokenizer st = new StringTokenizer(currentLine); 
			String col1 = st.nextToken(); 
			String col2 = st.nextToken(); 
			
			double fTime = Float.parseFloat(col1);
			int fSize = Integer.parseInt(col2);
            
            double nGap = (long)((fTime - nLasttime)*1000);
            // Check if the delay is long enough. If not, go into the while lock.
            while(nTime != -1 && System.nanoTime() - nTime < nGap);
			if(nTime == -1)
                nTime = System.nanoTime();
                
            while(fSize > 1024){
                byte[] buf = new byte[1024];
                Arrays.fill(buf, (byte)'a');
                DatagramPacket packet =
                     new DatagramPacket(buf, buf.length, addr, 4444);
                socket.send(packet);
                fSize = fSize - 1024;
            }
			byte[] buf = new byte[fSize];
			Arrays.fill(buf, (byte)'a');
			DatagramPacket packet =
	                 new DatagramPacket(buf, buf.length, addr, 4444);
            socket.send(packet);
            nLasttime = fTime;
			nTime = System.nanoTime();
			currentLine = bis.readLine();
		}
		System.out.println("finished!");
		if(bis != null)
			bis.close();
		if(socket != null)
			socket.close();
	}
}
