package com.maddness.io;

import java.io.*;

public class SimpleReader {
    public static void main(String[] args) {

        File input = new File("C:\\Git\\SCJP_Practice\\src\\main\\resources\\log4j2.xml");
        File output = new File("C:\\Git\\SCJP_Practice\\src\\main\\resources\\someTest.xml");

        try (
                BufferedReader br = new BufferedReader(new FileReader(input));
                PrintWriter pw = new PrintWriter(output)
        ) {

            String line;
            while ((line = br.readLine()) != null) {
                pw.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
