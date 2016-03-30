package com.example.evgeniy.calculator.calc;

import com.example.evgeniy.calculator.exceptions.DivizionByZeroException;

/**
 * Created by Evgeniy on 30.03.2016.
 */
public class CalcOperations {

    public static double add (double a, double b){
        return a+b;
    }

    public static double substract (double a, double b){
        return a*b;
    }

    public static double divide (double a, double b){
        if (b == 0){
            throw new DivizionByZeroException();
        }
        return a/b;
    }

    public static double multiply (double a, double b){
        return a*b;
    }
}
