public class CaesarCipher{
    private String alphabets="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String small="abcdefghijklmnopqrstuvwxyz";
    private String keyalp;
    private String keysm;
    private String keyalp2;
    private String keysm2;
    private int mainKey;
    private int mainKey2;
    public CaesarCipher(int key){
        this.mainKey=key;
        this.keyalp=alphabets.substring(key) + alphabets.substring(0,key);
        this.keysm=small.substring(key) + small.substring(0,key);
    }
    public CaesarCipher(int key1, int key2){
        this.mainKey=key1;
        this.mainKey2=key2;
        this.keyalp=alphabets.substring(key1) + alphabets.substring(0,key1);
        this.keysm=small.substring(key1) + small.substring(0,key1);
        this.keyalp2=alphabets.substring(key2) + alphabets.substring(0,key2);
        this.keysm2=small.substring(key2) + small.substring(0,key2);
    }
    public static void main(String[] args){
        String message="Can you imagine life WITHOUT the internet AND computers in your pocket?\n" +
                "\n";

        /*CaesarCipher cipher=new CaesarCipher(15);
        System.out.println(cipher.encrypt(message));*/
        CaesarCipher cipher1=new CaesarCipher(21,8);
        System.out.println(cipher1.encryptTwoKeys(message));
    }

    public String encrypt(String input){
        StringBuilder mess=new StringBuilder(input);
        for(int i=0;i<mess.length();i=i+1){
            char ch=mess.charAt(i);
            int index=alphabets.indexOf(ch);
            int smindex=small.indexOf(ch);
            if(index!=-1){
                char newch=keyalp.charAt(index);
                mess.setCharAt(i,newch);

            }
            else if(smindex!=-1){
                char newch=keysm.charAt(smindex);
                mess.setCharAt(i,newch);
            }
        }
        return mess.toString();
    }

    public String encryptTwoKeys(String input){
        StringBuilder mess=new StringBuilder(input);
        for(int i=0;i<mess.length();i=i+2){
            char ch=mess.charAt(i);
            int index=alphabets.indexOf(ch);
            int smindex=small.indexOf(ch);
            if(index!=-1){
                char newch=keyalp.charAt(index);
                mess.setCharAt(i,newch);
            }
            else if(smindex!=-1){
                char newch=keysm.charAt(smindex);
                mess.setCharAt(i,newch);
            }
        }
        for(int i=1;i<mess.length();i=i+2){
            char ch=mess.charAt(i);
            int index=alphabets.indexOf(ch);
            int smindex=small.indexOf(ch);
            if(index!=-1){
                char newch=keyalp2.charAt(index);
                mess.setCharAt(i,newch);

            }
            else if(smindex!=-1){
                char newch=keysm2.charAt(smindex);
                mess.setCharAt(i,newch);
            }
        }
        return mess.toString();
    }

    public String decrypt(String message){
        CaesarCipher cc=new CaesarCipher(26-mainKey);
        return cc.encrypt(message);
    }

    public String decryptTwo(String message){
        CaesarCipher cc=new CaesarCipher(26-mainKey,26-mainKey2);
        return cc.encryptTwoKeys(message);
    }

}