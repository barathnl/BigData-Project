import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.*;

public class Tweet_Parse{
	public static void main(String args[]) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("./tweet_data.txt"))) {
		    String linef,line,result;
		    //File operation for output file
			File fout = new File("./tweet_text_only.txt");
			FileOutputStream fos = new FileOutputStream(fout);
		 	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 	while ((linef = br.readLine()) != null) {
		        //Using Regular Expression to extract text only
		    	Pattern p = Pattern.compile("(?=\\btext\\b).*?(?=\\bsource\\b)");
		    	Matcher m = p.matcher(linef);
		    	//List<String> matches = new ArrayList<String>();
		    	while (m.find()) {
		    	  line=m.group();
		    	  //System.out.println("----------->" +line);
		    	  result = line.substring(line.indexOf("text") + 9, line.indexOf(",")-2);
		    	  System.out.println("<***Extracting Text only***> " +result);
		    	  bw.write(result);
		  		  bw.newLine();
		    	  //matches.add(result);
		    	}
		    	//System.out.println(matches);
		    }
		    bw.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		}
	}
