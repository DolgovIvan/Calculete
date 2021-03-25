package com.dolgov.calculate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.text.DecimalFormat;


public class MainActivity<e> extends AppCompatActivity {

    private static final String INPUT_TEXT_VIEW = "inputTextView";
    private static final String VALUE_ONE = "valueOne";
    private static final String VALUE_TWO = "valueTwo";
    private static final String RESULT = "result";
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private char ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo = Double.NaN;
    private String result = "";

    private DecimalFormat decimalFormat;
    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.##########");
        inputTextView = findViewById(R.id.inputTextView);

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btnDecima = (Button) findViewById(R.id.btnDecimal);
        Button btnDivide = (Button) findViewById(R.id.btnDivide);
        Button btnMyltiply = (Button) findViewById(R.id.btnMultiply);
        Button btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnPlus = (Button) findViewById(R.id.btnPlus);
        Button btnEquals = (Button) findViewById(R.id.btnEquals);
        Button btnClear = (Button) findViewById(R.id.btnClear);

        btn0.setOnClickListener(this::onClick);
        btn1.setOnClickListener(this::onClick);
        btn2.setOnClickListener(this::onClick);
        btn3.setOnClickListener(this::onClick);
        btn4.setOnClickListener(this::onClick);
        btn5.setOnClickListener(this::onClick);
        btn6.setOnClickListener(this::onClick);
        btn7.setOnClickListener(this::onClick);
        btn8.setOnClickListener(this::onClick);
        btn9.setOnClickListener(this::onClick);
        btnDecima.setOnClickListener(this::onClick);
        btnDivide.setOnClickListener(this::onClick);
        btnMyltiply.setOnClickListener(this::onClick);
        btnMinus.setOnClickListener(this::onClick);
        btnPlus.setOnClickListener(this::onClick);
        btnEquals.setOnClickListener(this::onClick);
        btnClear.setOnClickListener(this::onClick);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INPUT_TEXT_VIEW, inputTextView.getText().toString());
        outState.putString(VALUE_ONE, String.valueOf(valueOne));
        outState.putString(VALUE_TWO, String.valueOf(valueTwo));
        outState.putString(RESULT, result);
        //outState.putString(ACTION);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputTextView.setText(savedInstanceState.getString(INPUT_TEXT_VIEW));
        valueOne = Double.parseDouble(savedInstanceState.getString(VALUE_ONE));
        valueTwo = Double.parseDouble(savedInstanceState.getString(VALUE_TWO));
        result = savedInstanceState.getString(RESULT);
    }

    //Обработка кнопок
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                inputTextView.setText(inputTextView.getText() + "0");
                break;
            case R.id.btn1:
                inputTextView.setText(inputTextView.getText() + "1");
                break;
            case R.id.btn2:
                inputTextView.setText(inputTextView.getText() + "2");
                break;
            case R.id.btn3:
                inputTextView.setText(inputTextView.getText() + "3");
                break;
            case R.id.btn4:
                inputTextView.setText(inputTextView.getText() + "4");
                break;
            case R.id.btn5:
                inputTextView.setText(inputTextView.getText() + "5");
                break;
            case R.id.btn6:
                inputTextView.setText(inputTextView.getText() + "6");
                break;
            case R.id.btn7:
                inputTextView.setText(inputTextView.getText() + "7");
                break;
            case R.id.btn8:
                inputTextView.setText(inputTextView.getText() + "8");
                break;
            case R.id.btn9:
                inputTextView.setText(inputTextView.getText() + "9");
                break;
            case R.id.btnDecimal:
                inputTextView.setText(inputTextView.getText() + ".");
                break;
            case R.id.btnDivide:
                ACTION = DIVIDE;
                Calculation();
                result = valueOne + "/";
                inputTextView.setText(null);
                break;
            case R.id.btnMultiply:
                ACTION = MULTIPLY;
                Calculation();
                result = valueOne + "*";
                inputTextView.setText(null);
                break;
            case R.id.btnMinus:
                ACTION = MINUS;
                Calculation();
                result = valueOne+ "-";
                inputTextView.setText(null);
                break;
            case R.id.btnPlus:
                ACTION = PLUS;
                Calculation();
                result = valueOne + "+";
                inputTextView.setText(null);
                break;
            case R.id.btnEquals:
                Calculation();
                result = result + decimalFormat.format(valueTwo) + "=" + decimalFormat.format(valueOne);
                inputTextView.setText(result);
                valueOne = Double.NaN;
                ACTION = '0';
                break;
            case R.id.btnClear:
                inputTextView.setText("");
                valueOne = Double.NaN;
                valueTwo = Double.NaN;
                ACTION = '0';
                break;
        }
    }

    //Операции калькулятора
    private void Calculation() {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(inputTextView.getText().toString());
            inputTextView.setText(null);
            if (ACTION == PLUS) {
                valueOne = valueOne + valueTwo;
            } else if (ACTION == MINUS) {
                valueOne = valueOne - valueTwo;
            } else if (ACTION == MULTIPLY) {
                valueOne = valueOne * valueTwo;
            } else if (ACTION == DIVIDE) {
                valueOne = valueOne / valueTwo;
            }
        } else {
            valueOne = Double.parseDouble(inputTextView.getText().toString());
        }
    }
}