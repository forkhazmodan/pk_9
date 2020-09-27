package com.kp.chukhnovm.hw9_3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        task3();
    }

    public static void task3() {
        try(BufferedReader f =new BufferedReader(new FileReader("loremIpsum"))){

            List<Symbol> chars = new ArrayList<>();
            int r;
            int total=0;
            while((r = f.read()) != -1) {
                if (!Pattern.matches("\\w", Character.toString(r))) continue;

                Symbol symbol = new Symbol((char)r);

                if(chars.contains(symbol)) {
                    chars.get(chars.indexOf(symbol)).increaseFrequency();
                } else {
                    chars.add(symbol);
                }

                total++;
            }

            System.out.println(String.format("Char | Frequency | Relative Freq."));
            for (Symbol ch: chars) {
                ch.calcRelativeFrequency(total);
                System.out.println(String.format("%c -> %d -> %s", ch.getCh(), ch.getFrequency(), ch.getRelativeFrequency() + " %"));
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
