package com.example.evgeniy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText txtResult;
    private Button btnAdd;
    private Button btnDivide;
    private Button btnMultiply;
    private Button btnSubtract;

    private EnumMap<Symbol,Object> commands = new EnumMap<Symbol, Object>(Symbol.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        txtResult = (EditText)findViewById(R.id.txtResult);
        btnAdd = (Button)findViewById(R.id.butAdd);
        btnDivide = (Button)findViewById(R.id.butDelete);
        btnMultiply= (Button)findViewById(R.id.butMultiply);
        btnSubtract = (Button)findViewById(R.id.butSubtract);
        // к каждой кнопке добавляем тип операции
        btnAdd.setTag(OperationType.ADD);
        btnSubtract.setTag(OperationType.SUBTRACT);
        btnDivide.setTag(OperationType.DIVIDE);
        btnMultiply.setTag(OperationType.MULTIPLY);

    }

    private OperationType operType;

    public void buttonClick(View view){

        switch (view.getId()){

            case R.id.butAdd:
            case R.id.butSubtract:
            case R.id.butDevide:
            case R.id.butMultiply: { // если нажата одна из четырех кнопок
                operType = (OperationType) view.getTag(); // получаем тип операции из кнопки

                if (!commands.containsKey(Symbol.OPERATION)) {

                    if (!commands.containsKey(Symbol.FIRST_DIGIT)) {
                        commands.put(Symbol.FIRST_DIGIT, txtResult.getText());
                    }
                    commands.put(Symbol.OPERATION, operType);
                } else if(!commands.containsKey(Symbol.SECOND_DIGIT)){
                    commands.put(Symbol.SECOND_DIGIT, txtResult.getText());
                    doCalc();
                    commands.put(Symbol.OPERATION, operType);
                    commands.remove(Symbol.SECOND_DIGIT);
                }
                break;
            }
            case R.id.butClear:{ //кнопка очистить
                txtResult.setText("0");//выводит ноль на табло
                commands.clear();//удаляет все из EnumMap
                break;
            }

            case R.id.butComma:{
                if(commands.containsKey(Symbol.FIRST_DIGIT)&& getDouble(txtResult.getText().toString())==getDouble(commands.get(
                        Symbol.FIRST_DIGIT.toString()))){
                    txtResult.setText("0"+view.getContentDescription().toString());
                } else if(!txtResult.getText().toString().contains(",")){
                    txtResult.setText(txtResult.getText()+",");
                }

                break;
            }
            case R.id.butDelete: {
                txtResult.setText(txtResult.getText().delete(txtResult.getText().length() - 1,txtResult.getText().length()));

                if (txtResult.getText().toString().trim().length()==0){
                    txtResult.setText("0");
                }
                break;
            }

            case R.id.butResult:{
                if(commands.containsKey(Symbol.FIRST_DIGIT)&&commands.containsKey(Symbol.OPERATION)){
                    commands.put(Symbol.SECOND_DIGIT,txtResult.getText());
                    doCalc();
                    commands.put(Symbol.OPERATION,operType);
                    commands.remove(Symbol.SECOND_DIGIT);
                }
                break;
            }

            default:{

                if(txtResult.getText().toString().equals("0")
            || (commands.containsKey(Symbol.FIRST_DIGIT) && getDouble(txtResult.getText())
                        == getDouble(commands.get(Symbol.FIRST_DIGIT)))){// если вводится второе число то нужно сбросить текстовое поле
                    txtResult.setText(view.getContentDescription().toString());
                }
                else  {
                    txtResult.setText(txtResult.getText()+view.getContentDescription().toString());
                }
            }
        }
    }

    private void doCalc(){

        OperationType operTypeTmp = (OperationType)commands.get(Symbol.OPERATION);

        double result = calc(operTypeTmp, getDouble(commands.get(Symbol.FIRST_DIGIT)),getDouble(commands.get(Symbol.SECOND_DIGIT)));

        if (result%1 == 0){
            txtResult.setText(String.valueOf((int)result));
        } else {
            txtResult.setText(String.valueOf(result));
        }
        commands.put(Symbol.FIRST_DIGIT,result);//полученный результат записываем в первое число
    }

    private Double calc(OperationType operType, double a, double b){
        switch (operType){
            case ADD:{
                return CalcOperations.add(a,b);
            }
            case DIVIDE:{
                return CalcOperations.divide(a,b);
            }
            case MULTIPLY:{
                return CalcOperations.multiply(a,b);
            }
            case SUBTRACT:{
                return CalcOperations.substract(a,b);
            }
        }
        return null;
    }

    private double getDouble(Object value){
        double result = 0;

        result = Double.valueOf(value.toString().replace(',','.')).doubleValue(); // замена запятой на точку и преобразование стринг в дабл

        return result;
    }
}
