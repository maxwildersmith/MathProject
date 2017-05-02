import java.util.Random;
import java.util.Scanner;

/**
 * Created by Max on 5/1/2017.
 */
public class Encode {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a seed: ");
        int seed = in.nextInt();
        Random random = new Random(seed);
        char[] cipherbet = new char[26];
        for(int i=0;i<26;i++)
            cipherbet[i] = (char)((int)('a')+i);
        for(int i=0;i<26;i++){
            char temp = cipherbet[i];
            int index = random.nextInt(26);
            cipherbet[i] = cipherbet[index];
            cipherbet[index] = temp;
        }
        for(int i=0;i<26;i++)
            System.out.print((char)((int)('a')+i)+" ");
        System.out.println();
        for(char c:cipherbet)
            System.out.print(c+" ");
        System.out.println("\nText to encrypt: ");
        String encoded ="";
        in = new Scanner(System.in);
            encoded = in.nextLine().toLowerCase();
        char[] text = encoded.toCharArray();
        for(int i=0;i<text.length;i++)
            text[i]=encode(text[i],cipherbet);
        String out =  "";
        for(char c:text)
            out+=c;
        System.out.println(out);
        System.out.println("To decrypt: ");
        in = new Scanner(System.in);
        encoded = in.nextLine().toLowerCase();
         text = encoded.toCharArray();
        for(int i=0;i<text.length;i++)
            text[i]=decode(text[i],cipherbet);
        out =  "";
        for(char c:text)
            out+=c;
        System.out.println(out);
    }
    public static char encode(char c, char[] alphabet){
        if((int)c<(int)'a'||(int)c>(int)'z')
            return c;
        return alphabet[((int)c)-((int)'a')];
    }
    public static char decode(char c, char[] cipherbet){
        int i =0;
        if((int)c<(int)'a'||(int)c>(int)'z')
            return c;
        for(i=0;i<cipherbet.length;i++)
            if(cipherbet[i]==c)
                return (char)(i+(int)'a');

        return c;
    }
}
//make him understand me this ~ Dhruv