package com.example.evgeniy.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtResult = (EditText)findViewById(R.id.txtResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view){
        switch (view.getId()){
            case R.id.butAdd:
                break;
            case R.id.butDevide:
                break;
            case R.id.butMultiply:
                break;
            case R.id.butSubtract:
                break;
            case R.id.butComma:
                break;
            case R.id.butDelete:
                break;
            case R.id.butClear:
                break;
            case R.id.butResult:
                break;

            default:{
                txtResult.setText(txtResult.getText()+view.getContentDescription().toString());
            }

        }
    }
}
