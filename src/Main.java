import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Max on 5/1/2017.
 */
public class Main {

    public static void main(String[] args){
        Letter[] letters;

        try {
            Scanner in = new Scanner(new File(Thread.currentThread().getContextClassLoader().getResource("freq.txt").getFile()));
            String s = "";
            while(in.hasNext())
                s+=in.next();
            letters = stringToLetter(s);
            System.out.println("Printing frequencies...");

            for(Letter l:letters)
                System.out.println(l);
            in = new Scanner(System.in);
            String cipherText = "";
            System.out.println("Enter ciphertext: ");
            cipherText = in.nextLine().toLowerCase();

            Letter[] message = getFreq(cipherText.toCharArray());
            for(Letter letter:message)
                System.out.println(letter);
            ArrayList<Letter> cipherFreq = new ArrayList<>();
            for(Letter l:message)
                cipherFreq.add(l);
            ArrayList<Letter> freq = new ArrayList<>();
            for(Letter l: letters)
                freq.add(l);

            System.out.println(decode(freq,cipherFreq,cipherText.toCharArray()));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Letter[] stringToLetter(String s){
        s = s.substring(s.indexOf('{')+1,s.lastIndexOf('}'));
        String[] dataRow = s.split(",");
        Letter[] letters = new Letter[dataRow.length];
        for(int i=0;i<dataRow.length;i++)
            letters[i] = new Letter(dataRow[i].substring(dataRow[i].indexOf("\"") + 1, dataRow[i].lastIndexOf("\"")).toCharArray()[0], Double.parseDouble(dataRow[i].substring(dataRow[i].indexOf(":")+1)));

            return letters;
    }
    public static Letter[] getFreq(char[] in){
        int total = in.length;
        ArrayList<Letter> letters = new ArrayList<>();
        for(char c: in)
            if (letters.contains(new Letter(c)))
                letters.get(letters.indexOf(new Letter(c))).freqInc();
            else
                letters.add(new Letter(c));

        for(int i=0;i<letters.size();i++)
            letters.get(i).percent(total);
        letters.sort(Letter::compareTo);
        return letters.toArray(new Letter[letters.size()]);

    }
    public static String decode(ArrayList<Letter> freq, ArrayList<Letter> cipherText, char[] text){
        String out="";
        cipherText.remove(new Letter(' '));
        char[] cipherbet = new char[26];
        freq.sort(Letter::compareTo);
        cipherText.sort(Letter::compareTo);
        for(int i=0;i<26;i++) {
            System.out.println((char)( (int) 'a' + i)+ "  is " +cipherText.get(freq.indexOf(new Letter((char) ((int) 'a' + i)))).getLetter());
            cipherbet[i] = cipherText.get(freq.indexOf(new Letter((char) ((int) 'a' + i)))).getLetter();
        }

        for(char c: cipherbet)
            System.out.print(c);
        System.out.println();
        for(char c: text)
            out+=Encode.decode(c,cipherbet);
        return out;
    }
}
