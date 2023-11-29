package com.example.dependencyInjection;

import java.util.Scanner;

class CustomException extends Exception {
    CustomException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {
    public static void main(String args[]) throws CustomException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the first number:");
            int a = sc.nextInt();
            System.out.println("Enter the second number:");
            int b = sc.nextInt();
// 7/ 0 AE
            if (divider(a,b) < 5) {
                throw new CustomException("Expecting the divided value to be greater than 5");
            }
        }

        catch (ArithmeticException ex) {
            System.out.println("AE exception: " + ex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("I know I have handled the exception");
        }
        finally {
            System.out.println("Terminate all connections");
            System.out.println("Close all files");
            System.out.println("Final cleanup");
        }
        System.out.println("hello");
    }

    public static int divider(int a, int b) {
        try {
            int result = a/b;
            System.out.println(result);
            return result;
        } catch (ArithmeticException ae) {
            System.out.println("Handled ae and rethrowing");
            throw ae;
        }
    }
}
