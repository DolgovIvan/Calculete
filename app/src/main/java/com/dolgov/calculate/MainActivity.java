package com.dolgov.calculate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity {

    private static final String INPUT_TEXT_VIEW = "inputTextView";
    private static final String VALUE_ONE = "valueOne";
    private static final String VALUE_TWO = "valueTwo";
    private static final String RESULT = "result";
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private char actionnull = 0;
    private char action = actionnull;

    SharedPreferences sharedPreferences;

    private double value = 0.0;
    private Double valueOne = value;
    private Double valueTwo = value;
    private String result;

    private DecimalFormat decimalFormat;
    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences(SettingsActivity.PREFERENCES, Context.MODE_PRIVATE);

        decimalFormat = new DecimalFormat("#.######");

        checkNightModeActivated();

        inputTextView = findViewById(R.id.inputTextView);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnDecima = findViewById(R.id.btnDecimal);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMyltiply = findViewById(R.id.btnMultiply);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnEquals = findViewById(R.id.btnEquals);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnSettings = findViewById(R.id.btnSettings);

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
        btnSettings.setOnClickListener(this::onClick);

        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        if (bundle==null){
            return;
        }
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean(SettingsActivity.KEY_THEME, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INPUT_TEXT_VIEW, inputTextView.getText().toString());
        outState.putDouble(VALUE_ONE, valueOne);
        outState.putDouble(VALUE_TWO, valueTwo);
        outState.putString(RESULT, result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        inputTextView.setText(savedInstanceState.getString(INPUT_TEXT_VIEW));
        valueOne = savedInstanceState.getDouble(VALUE_ONE);
        valueTwo = savedInstanceState.getDouble(VALUE_TWO);
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
                if (inputTextView.getText().toString().contains(".")) {
                    inputTextView.setText(inputTextView.getText() + ".");
                }
                break;
            case R.id.btnDivide:
                action = DIVIDE;
                сalculation();
                result = valueOne + "/";
                inputTextView.setText(null);
                break;
            case R.id.btnMultiply:
                action = MULTIPLY;
                сalculation();
                result = valueOne + "*";
                inputTextView.setText(null);
                break;
            case R.id.btnMinus:
                action = MINUS;
                сalculation();
                result = valueOne + "-";
                inputTextView.setText(null);
                break;
            case R.id.btnPlus:
                action = PLUS;
                сalculation();
                result = valueOne + "+";
                inputTextView.setText(null);
                break;
            case R.id.btnEquals:
                сalculation();
                result = result + decimalFormat.format(valueTwo) + "=" + decimalFormat.format(valueOne);
                inputTextView.setText(result);
                valueOne = value;
                action = actionnull;
                break;
            case R.id.btnClear:
                inputTextView.setText("");
                valueOne = value;
                valueTwo = value;
                action = actionnull;
                break;
            case R.id.btnSettings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(intent, RESULT_OK);
                break;
        }
    }

    //Операции калькулятора
    private void сalculation() {
        if (valueOne == 0) {
            valueOne = parseDouble(inputTextView.getText().toString());
        } else {
            valueTwo = parseDouble(inputTextView.getText().toString());
            if (action == PLUS) {
                valueOne = valueOne + valueTwo;
            } else if (action == MINUS) {
                valueOne = valueOne - valueTwo;
            } else if (action == MULTIPLY) {
                valueOne = valueOne * valueTwo;
            } else if (action == DIVIDE) {
                valueOne = valueOne / valueTwo;
            }
        }
    }
}