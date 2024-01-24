package com.smu.mscda;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the input string:");

        String input = scanner.nextLine();
        System.out.println("\nCapitalized string is: " + CapitalizeString(input));
        System.out.println("\nMD5 hex is: " + GenerateMD5Hex(input));
    }

    public static String CapitalizeString(String input) {
        return StringUtils.capitalize(input);
    }

    public static String GenerateMD5Hex(String input) {
        return DigestUtils.md5Hex(input);
    }
}
