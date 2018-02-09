import java.io.*; 
import java.util.*; 

/* 
 *  The program reads an input file "data.txt"  that has entries of the form 
 *  0	0.000000	I	536	98.190	92.170	92.170
 *  4	133.333330	P	152	98.190	92.170	92.170
 * 	1	33.333330	B	136	98.190	92.170	92.170
 *
 * The file is read line-by-line, values are parsed and assigned to variables,
 * values are  displayed, and then written to a file with name "output.txt"  
 */

class ReadFileWriteFile {  
	public static void main (String[] args) { 
		
		
		BufferedReader bis = null; 
		String currentLine = null; 
		PrintStream pout = null;
		int nIFrameCount = 0;
		int nPFrameCount = 0;
		int nBFrameCount = 0;
		long nISize = 0;
		long nPSize = 0;
		long nBSize = 0;
		try {  
			
			/*
			 * Open input file as a BufferedReader
			 */ 
			File fin = new File("/nfs/ug/homes-5/l/laiyong/workspace/lab2/src/data.txt"); 
			FileReader fis = new FileReader(fin);  
			bis = new BufferedReader(fis);  
			
			/*
			 * Open file for output 
			 */
			FileOutputStream fout =  new FileOutputStream("/nfs/ug/homes-5/l/laiyong/workspace/lab2/src/output.txt");
			pout = new PrintStream (fout);
			/*
			 *  Read file line-by-line until the end of the file 
			 */
			while ( (currentLine = bis.readLine()) != null) { 
				
				/*
				 *  Parse line and break up into elements 
				 */
				StringTokenizer st = new StringTokenizer(currentLine); 
				String col1 = st.nextToken(); 
				String col2 = st.nextToken(); 
				String col3  = st.nextToken(); 
				String col4 = st.nextToken(); 
				
				/*
				 *  Convert each element to desired data type 
				 */
				int SeqNo 	= Integer.parseInt(col1);
				float Ftime 	= Float.parseFloat(col2);
				String Ftype 	= col3;
				int Fsize 	= Integer.parseInt(col4);
				
				
				/*
				 *  Display content of file 
				 */
				System.out.println("SeqNo:  " + SeqNo); 
				System.out.println("Frame time:   " + Ftime); 
				System.out.println("Frame type:        " + Ftype); 
				System.out.println("Frame size:       " + Fsize + "\n"); 
				
				
				/*
				 *  Write line to output file 
				 */
				pout.println(SeqNo+ "\t"+  Ftime + "\t" + Ftype + "\t" + Fsize); 
				
				if(Ftype.equals("I"))
				{
					nIFrameCount++;
					nISize += Fsize;
				}
				if(Ftype.equals("P"))
				{
					nPFrameCount++;
					nPSize += Fsize;
				}
				if(Ftype.equals("B"))
				{
					nBFrameCount++;
					nBSize += Fsize;
				}
			} 
			System.out.println("The average size of an I frame is " + (double)nISize / (double)nIFrameCount); 
			System.out.println("The average size of a P frame is " + (double)nPSize / (double)nPFrameCount); 
			System.out.println("The average size of a B frame is " + (double)nBSize / (double)nBFrameCount); 
		} catch (IOException e) {  
			// catch io errors from FileInputStream or readLine()  
			System.out.println("IOException: " + e.getMessage());  
		} finally {  
			// Close files   
			if (bis != null) { 
				try { 
					bis.close(); 
					pout.close();
				} catch (IOException e) { 
					System.out.println("IOException: " +  e.getMessage());  
				} 
			} 
		} 
	}  
}
