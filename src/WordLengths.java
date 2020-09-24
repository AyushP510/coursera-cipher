import java.io.*;
import java.util.*;
public class WordLengths{
    public static void main(String[] args) throws Exception{
        File f=new File("/home/ayush510/IdeaProjects/Cipher/inputFiles/manywords.txt");
        WordLengths wl=new WordLengths();
        int[] arr=new int[31];
        wl.countWordLengths(f,arr);
    }

    public void countWordLengths(File resource, int[] counts) throws Exception{
        Scanner scan=new Scanner(resource);
        while(scan.hasNext()){
            String word=scan.next();
            int count=word.length();
            if(!Character.isLetter(word.charAt(0)))
                count--;
            if(!Character.isLetter(word.charAt(word.length()-1)))
                count--;
            if(count>=0)
            counts[count]++;
        }
        int max=0;
        int index=0;
        for(int i=1;i<counts.length;++i){
            System.out.println(i + " : " + counts[i]);
            if(counts[i]>max){
                max=counts[i];
                index=i;
            }

        }
        System.out.println("Max :" + max + " AT " + index);

    }





}