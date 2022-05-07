package Task01;

import java.io.*;
import java.util.*;

public class RPN {
    public static void main(String[] args) throws IOException {
        Stack<String> stack = new Stack<String>();
        String ch;
        int a, b;

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("Task01/Calc1.stk");
            br = new BufferedReader(fr);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        }

        while ((ch = br.readLine()) != null){
            if(!ch.equals("*") && !ch.equals("+") && !ch.equals("-") && !ch.equals("/")){
                stack.push(ch);
            } else {
                a = Integer.parseInt(stack.pop());
                b = Integer.parseInt(stack.pop());

                if(ch.equals("*")){
                    stack.push(Integer.toString(a * b));
                } else if(ch.equals("+")) {
                    stack.push(Integer.toString(a + b));
                } else if(ch.equals("-")){
                    stack.push(Integer.toString(b - a));
                } else if(ch.equals("/")){
                    stack.push(Integer.toString(b / a));
                }
            }
        }

        System.out.println(stack.pop());

        fr.close();
    }
}