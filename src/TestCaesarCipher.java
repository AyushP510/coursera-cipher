import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCaesarCipher {
    public static void main(String[] args) throws FileNotFoundException {
        TestCaesarCipher tc=new TestCaesarCipher();
        tc.simpleTests();
    }
    public int[] countLetters(String message) {
        int[] counts = new int[27];
        if (message == null) {
            return counts;
        }
        String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < message.length(); ++i) {
            char ch = message.charAt(i);
            if(Character.isLetter(ch)){
                ch=Character.toLowerCase(ch);
            }
            int index = lowerLetters.indexOf(ch);
            if (index != -1) {
                counts[index]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] counts) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < counts.length; ++i) {
            if (counts[i] > max) {
                max = counts[i];
                index = i;
            }
        }
        return index;
    }

    public void simpleTests() throws FileNotFoundException {
        String fileName = "/home/ayush510/IdeaProjects/Cipher/inputFiles/smallHamlet.txt";
        File f=new File(fileName);
        StringBuilder message=new StringBuilder();
        Scanner scan=new Scanner(f);
        while(scan.hasNextLine()){
            message.append(scan.nextLine());
        }
        CaesarCipher cc=new CaesarCipher(18);
        String encrypted=cc.encrypt(message.toString());
        System.out.println(encrypted);
        System.out.println(breakCaesarCipher(encrypted));
    }

    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int alpha=4;
        int dkey = maxDex - alpha;
        if (maxDex < alpha) {
            dkey = 26 - (alpha - maxDex);
        }
        return dkey;
    }

    public String breakCaesarCipher(String input){
        int key=getKey(input);
        CaesarCipher cc=new CaesarCipher(key);
        return cc.decrypt(input);
    }
}
