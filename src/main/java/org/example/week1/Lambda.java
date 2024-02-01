package org.example.week1;

import java.util.Arrays;


    interface ArithmeticOperation {
        int perform(int a, int b);
    }


    public class Lambda {

        static ArithmeticOperation addition = (int a, int b) -> a+b;

        static ArithmeticOperation subtraction = (int a, int b) -> a-b;

        static ArithmeticOperation  multiplication = (int a, int b) -> a*b;

        static ArithmeticOperation division = (int a, int b) -> a/b;

        static ArithmeticOperation  modulus = (int a, int b) -> a%b;

        static ArithmeticOperation power = (int a, int b) -> (int) Math.pow(a,b);

        int operate(int a, int b, ArithmeticOperation op) {
            return op.perform(a,b);
        }

        int[] operate(int[] a, int[] b, ArithmeticOperation op) {
            if (a.length != b.length) {
                throw new IllegalArgumentException("Arrays must be of equal length");
            }
            int[] result = new int[a.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = op.perform(a[i],b[i]);
            }
            return result;
        }


        public static void main(String[] args) {
            new Lambda().ex1();

            int[] a = {1,2,3,4,5};
            int[] b = {6,7,8,9,10};
            int[] result = new Lambda().operate(a,b,addition);
            System.out.println(Arrays.toString(result)); // Have to be toString else it doesn't print anything readable just the memory address.

        }
        public static void ex1() {

            System.out.println(addition.perform(3,5));
            System.out.println(subtraction.perform(3,5));
            System.out.println(multiplication.perform(3,5));
            System.out.println(division.perform(3,5));
            System.out.println(modulus.perform(3,5));
            System.out.println(power.perform(3,5));


        }

    }
