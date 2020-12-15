import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

/**
 * Class hex2base64 to get Base64 out of a string.
 * 
 * @author ankarpets
 * @version 15.12.2020
 */
public class hex2base64
{
        
static String hexToBin(String hex) {    //convert hex to binary using BigInteger
    int len = hex.length() * 4;
    String binary = new BigInteger(hex, 16).toString(2);

    if(binary.length() < len){
        int diff = len - binary.length();
        String s = "";
        for(int i = 0; i < diff; ++i){
            s = s.concat("0");
        }
        binary = s.concat(binary);
    }
    return binary;
}
    
    public void main() {
    String str = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
    String binStr = hexToBin(str);  //to convert String to binary
    base64(binStr);                 //to convert binary to base64
    //System.out.println();    
    }
   
   public void base64(String bin){
       HashMap<String, String> map = new HashMap<String, String>(); //new HashMap to store key and values according to Base64 table
       
        String csvFile = "base64.txt"; //table with base64 values (symbol, binary number) separeted by comma
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ","; //separeted by comma

        String result = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
         
            while ((line = br.readLine()) != null) {                  
                String[]symbol = line.split(cvsSplitBy);
                String tmpSym = symbol[1].substring(1,symbol[1].length());  //skip space in the beginning of the value
                map.put(symbol[0], tmpSym);             
            }  
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
/* for every substring we look for the match
 * in HashMap values and if it is a case we add
 * its key (e.g. letter/number/sign) to the resulting string
 */
        
            for (int i = 0; i != bin.length(); i = i + 6) {
            System.out.println("substr = " + bin.substring(i, i+6));
            for (String s : map.keySet()) {
            if (map.get(s).equals(bin.substring(i, i+6))) {
                result = result + s;
              //  System.out.println("result intermediate = " + result);
            }
           
        }    
        }   
      System.out.println("result = " + result); 
    }


}
