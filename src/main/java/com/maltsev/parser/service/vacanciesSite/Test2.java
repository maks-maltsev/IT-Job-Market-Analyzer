package com.maltsev.parser.service.vacanciesSite;

public class Test2 {
    public static void main(String[] args) {
        int [] test = {2, 3 , 1, 7, 4, 10, 12};
        String [] strings = {"q", "w", "e", "a", "s", "d", "z"};

        for (int i = 0; i < test.length-1; i++){
            for (int j = 0; j < test.length-1; j++) {
                if (test[j + 1] > test[j]) {
                    int x = test[j];
                    test[j] = test[j + 1];
                    test[j + 1] = x;

                    String s = strings[j];
                    strings[j] = strings[j+1];
                    strings[j+1] = s;
                }
            }
        }
        for (int x: test) {
            System.out.println(x);
        }
        for(String x : strings){
            System.out.println(x);
        }
    }
}
