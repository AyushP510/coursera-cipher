import java.io.*;
import java.util.Scanner;

public class CaesarBreaker {
    public static void main(String[] args) throws Exception {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecryptTwoKeys();
        //cb.testDecrypt();
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

    public String decrypt(String encrypted) {
        int dkey=getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.encrypt(encrypted);
    }

    public String decryptTwoKeys(String encrypted){
        String first=halfOfString(encrypted,0);
        String second=halfOfString(encrypted,1);
        int dkey1=getKey(first);
        int dkey2=getKey(second);
        System.out.println("Key1: " + dkey1 + "\n" + "Key2: " + dkey2);

        CaesarCipher cc= new CaesarCipher(26-dkey1,26-dkey2);
        return cc.encryptTwoKeys(encrypted);
        //return cc.encryptTwoKeys(encrypted,26-2,26-20);
    }

    public void testDecrypt() throws Exception{
        File f=new File("/home/ayush510/IdeaProjects/Cipher/inputFiles/lotsOfWords.txt");
        Scanner scan=new Scanner(f);
        StringBuilder encrypted=new StringBuilder();
        while(scan.hasNextLine()){
            encrypted.append(scan.nextLine());
        }
        System.out.println(decrypt(encrypted.toString()));
    }

    public void testDecryptTwoKeys() throws FileNotFoundException {
        File f=new File("/home/ayush510/IdeaProjects/Cipher/inputFiles/mysteryTwoKeysQuiz.txt");
        Scanner scan=new Scanner(f);
        StringBuilder encrypted=new StringBuilder();
        while(scan.hasNextLine()){
            encrypted.append(scan.nextLine());
        }
        System.out.println(decryptTwoKeys(encrypted.toString()));
       // System.out.println(decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
    }

    public String halfOfString(String message, int start){
        StringBuilder half= new StringBuilder();
        for(int i=start;i<message.length();i=i+2){
            half.append(message.charAt(i));
        }
        return half.toString();
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
}
