import java.io.*;

public class Nuke2 {
    public static void main(String[] args)throws Exception{
        BufferedReader keyboard;
        String inputLine;
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        inputLine = keyboard.readLine();
        char[] str = inputLine.toCharArray();
        //toCharArray() returns an Array of chars after converting a String into sequence of characters.

        for ( int i=0 ; i<inputLine.length() ; i++){
            if (i!=1) {
                System.out.print(str[i]);
            }
        }

    }
}
