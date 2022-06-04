package com.maltsev.parser.service.intToDoubleConverter;

import java.util.List;

public class MakeIntArrayToPercentDoubleArray {

    public static void makeIntArrayToPercentDouble(List<String> nameList, List<Double> amountList, int total){
        for (int i = 0; i < nameList.size(); i++){
            total+= amountList.get(i);
        }
        for (int i = 0; i < nameList.size(); i++){
            double x = 100 * amountList.get(i) / total;
            amountList.set(i, x);
        }
    }

}
