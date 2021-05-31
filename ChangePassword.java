import java.util.*;
import java.io.*;
import java.security.*;

public class ChangePassword
{
    private final static JKS j = new JKS();

    public static void main(String[] args) throws Exception
    {
        String oldF = "D:\\dev\\bx\\Code\\Android\\yzgj-pos\\tgj_release.jks";
        String newF =  "D:\\dev\\bx\\Code\\Android\\yzgj-pos\\tgj_release1111111.jks";
        String keystoreFilename = oldF;
        String newFilename = newF;
        InputStream in = new FileInputStream(keystoreFilename);
        String passwd = promptForPassword("keystore");

        System.out.printf("Changing password on '%s', writing to '%s'...\n", keystoreFilename, newFilename);

        j.engineLoad(in, passwd.toCharArray());
        in.close();

        passwd = promptForPassword("new keystore");

        OutputStream out = new FileOutputStream(newFilename);
        j.engineStore(out, passwd.toCharArray());
        out.close();
    }

    private static String promptForPassword(String which)
    {
        System.out.printf("Enter %s password: ", which);
        Scanner kbd = new Scanner(System.in);
        return kbd.nextLine();
    }
}