package org.example.week1;

import java.util.Arrays;

public class MethodReferences {

        interface MyTransformingType {
            int perform(int a);
        }

        interface MyValidatingType {
            boolean perform(int a);
        }

        static int[] map(int[] a, MyTransformingType op) {
            int[] mapResult = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                mapResult[i] = op.perform(a[i]); // Inside of each iteration of the index "i" we call the perform method on the op object
            }
            return mapResult;
        }

        static int[] filter(int[] a, MyValidatingType op) {
            int[] filterResult = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                if (op.perform(a[i])) {
                    filterResult[i] = a[i]; // true
                } else {
                    filterResult[i] = 0; // false
                }
            }
            return filterResult;
        }

    static int doubleValue(int x) {
        return x * 2;
    }

    static boolean isEven(int x) {
        return x % 2 == 0;
    }


        public static void main(String[] args) {
            int[] array = {1,2,3,4,5};

            int[] doubledResult = map(array, MethodReferences::doubleValue);

            int[] filteredResult = filter(array, MethodReferences::isEven);
            System.out.println(Arrays.toString(filteredResult));

            System.out.println(Arrays.toString(doubledResult));



        }
    }


/**
 * PSEUDO CODE FOR MYSELF
 *
 * TRANSFORM METHOD
 *
 * Step 1: Create new Array that will store the transformed vaules and it have to be the same length as the original array.
 * Step 2: Loop through the array
 * Step 3: Transform the values in the array
 * Steo 4: Perform the method using lamda expression
 *
 *
 * FILTER METHOD
 *
 * Step 1: Create new Array that will store the filtered values and it have to be the same length as the original array.
 * Step 2: Loop through the array
 * Step 3: if statement that checks if the value is true or false
 * */

