package com.example.calculator;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculator.databinding.ActivityMainBinding;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##########");
    private ActivityMainBinding binding;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private boolean isOperatorClicked = false;
    private boolean isEqualsClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeClickListeners();
        updateDisplay();
    }

    private void initializeClickListeners() {
        // Number buttons
        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
        binding.btn7.setOnClickListener(this);
        binding.btn8.setOnClickListener(this);
        binding.btn9.setOnClickListener(this);
        binding.btnDot.setOnClickListener(this);

        // Operator buttons
        binding.btnPlus.setOnClickListener(this);
        binding.btnMinus.setOnClickListener(this);
        binding.btnMultiply.setOnClickListener(this);
        binding.btnDivide.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);

        // Function buttons
        binding.btnClear.setOnClickListener(this);
        binding.btnPercent.setOnClickListener(this);
        binding.btnPlusMinus.setOnClickListener(this); // This will be backspace
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == binding.btn0.getId()) {
            appendNumber("0");
        } else if (id == binding.btn1.getId()) {
            appendNumber("1");
        } else if (id == binding.btn2.getId()) {
            appendNumber("2");
        } else if (id == binding.btn3.getId()) {
            appendNumber("3");
        } else if (id == binding.btn4.getId()) {
            appendNumber("4");
        } else if (id == binding.btn5.getId()) {
            appendNumber("5");
        } else if (id == binding.btn6.getId()) {
            appendNumber("6");
        } else if (id == binding.btn7.getId()) {
            appendNumber("7");
        } else if (id == binding.btn8.getId()) {
            appendNumber("8");
        } else if (id == binding.btn9.getId()) {
            appendNumber("9");
        } else if (id == binding.btnDot.getId()) {
            appendDecimal();
        } else if (id == binding.btnPlus.getId()) {
            setOperator("+");
        } else if (id == binding.btnMinus.getId()) {
            setOperator("-");
        } else if (id == binding.btnMultiply.getId()) {
            setOperator("×");
        } else if (id == binding.btnDivide.getId()) {
            setOperator("÷");
        } else if (id == binding.btnEqual.getId()) {
            calculateResult();
        } else if (id == binding.btnClear.getId()) {
            clearAll();
        } else if (id == binding.btnPercent.getId()) {
            calculatePercent();
        } else if (id == binding.btnPlusMinus.getId()) {
            backspace();
        }
    }

    private void appendNumber(String number) {
        if (isEqualsClicked) {
            clearAll();
        }

        if (isOperatorClicked) {
            currentInput = "";
            isOperatorClicked = false;
        }

        if (currentInput.equals("0") && !number.equals("0")) {
            currentInput = number;
        } else if (!currentInput.equals("0")) {
            currentInput += number;
        }

        updateDisplay();
    }

    private void appendDecimal() {
        if (isEqualsClicked) {
            clearAll();
        }

        if (isOperatorClicked) {
            currentInput = "0";
            isOperatorClicked = false;
        }

        if (currentInput.isEmpty()) {
            currentInput = "0";
        }

        if (!currentInput.contains(".")) {
            currentInput += ".";
            updateDisplay();
        }
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            if (!operator.isEmpty() && !isOperatorClicked) {
                calculateResult();
            }

            firstOperand = Double.parseDouble(currentInput);
            operator = op;
            isOperatorClicked = true;
            isEqualsClicked = false;

            updateResultDisplay();
        }
    }

    private void calculateResult() {
        if (!operator.isEmpty() && !currentInput.isEmpty() && !isOperatorClicked) {
            secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        binding.etvalue.setText("Error");
                        binding.tvresult.setText("Cannot divide by zero");
                        return;
                    }
                    break;
            }

            currentInput = formatResult(result);
            binding.etvalue.setText(currentInput);

            // Show the calculation in result TextView
            String calculation = formatOperand(firstOperand) + " " + operator + " " + formatOperand(secondOperand);
            binding.tvresult.setText(calculation);

            operator = "";
            isEqualsClicked = true;
            isOperatorClicked = false;
        }
    }

    private void calculatePercent() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            double result = value / 100;
            currentInput = formatResult(result);
            updateDisplay();
            binding.tvresult.setText(formatOperand(value) + " %");
        }
    }

    private void backspace() {
        if (!currentInput.isEmpty() && !isOperatorClicked) {
            if (currentInput.length() == 1) {
                currentInput = "0";
            } else {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
            }
            updateDisplay();
        }
    }

    private void clearAll() {
        currentInput = "";
        operator = "";
        firstOperand = 0;
        secondOperand = 0;
        isOperatorClicked = false;
        isEqualsClicked = false;
        binding.etvalue.setText("0");
        binding.tvresult.setText("");
    }

    private void updateDisplay() {
        String displayText = currentInput.isEmpty() ? "0" : currentInput;
        binding.etvalue.setText(displayText);
    }

    private void updateResultDisplay() {
        if (!operator.isEmpty()) {
            String calculation = formatOperand(firstOperand) + " " + operator;
            binding.tvresult.setText(calculation);
        }
    }

    private String formatResult(double result) {
        // Remove trailing zeros and unnecessary decimal point
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return decimalFormat.format(result);
        }
    }

    private String formatOperand(double operand) {
        if (operand == (long) operand) {
            return String.valueOf((long) operand);
        } else {
            return decimalFormat.format(operand);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}