package com.example.android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import java.lang.String;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    String test = "";
    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) // warunek konieczny do spowodowanie nie pokazywania sie klawiatury telefonu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input); //przypisuje wartosc do tekstu (Edit tekst)
        display.setShowSoftInputOnFocus(false); //spowodowanie nie pokazywania sie klawiatury telefonu


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
                //czyszczenie tekstu w polu input
            }
        });
    }

    private void updateText(String strToAdd){
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightStr = oldString.substring(cursorPosition);


        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPosition + 1);// naprawa cofania kursora
        }
        else {
            display.setText(String.format("%s%s%s", leftString, strToAdd, rightStr ));
            display.setSelection(cursorPosition + 1);// naprawa cofania kursora
            //funkcja updatuje i wyświetla położenenie kursora gdziekolwiek jest

        }

    }

    public void zeroBTN(View view) {
        test = "";
        updateText("0");
    }

    public void oneBTN(View view) {
        test = "";
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
        test = "";
    }

    public void threeBTN(View view) {
        updateText("3");
        test = "";
    }
    public void fourBTN(View view) {
        updateText("4");
        test = "";
    }
    public void fiveBTN(View view) {
        updateText("5");
        test = "";
    }
    public void sixBTN(View view) {
        updateText("6");
        test = "";
    }
    public void sevenBTN(View view) {
        updateText("7");
        test = "";
    }
    public void eightBTN(View view) {
        updateText("8");
        test = "";
    }
    public void nineBTN(View view) {
        updateText("9");
        test = "";
    }
    public void multiplyBTN(View view) {
        if (!test.equals("×")) {
            updateText("×");
            test = "×";
        }
    }
    public void divideBTN(View view) {
        if (!test.equals("÷")) {
            updateText("÷");
            test = "×";
        }
    }
    public void subtractBTN(View view) {
        if (!test.equals("-")) {
            updateText("-");
            test = "-";
        }
    }
    public void addBTN(View view) {
        if (!test.equals("+")) {
            updateText("+");
            test = "+";
        }
    }
    public void clearBTN(View view) {
        display.setText("");
    }
    public void parBTN(View view) {
        int cursorPosition = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i =0; i < cursorPosition; i++){

            if (display.getText().toString().substring(i, i+1).equals("(")){
                openPar +=1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                openPar +=1;
            }
        }
        // startuje z indeksa 0
        //sprawdzanie ile zostalo nawiasow zamknietych a ile jest otwartych

        if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
           updateText("(");
           display.setSelection(cursorPosition + 1);
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
            display.setSelection(cursorPosition + 1);
        }
        display.setSelection(cursorPosition + 1);
    }
    public void expBTN(View view) {
        if (!test.equals("^")) {
            updateText("^");
            test = "^";
        }
    }
    public void plusMinusBTN(View view) {
        if (!test.equals("-")) {
            updateText("-");
            test = "-";
        }
    }
    public void decimalBTN(View view) {
        if (!test.equals(".")) {
            updateText(".");
            test = ".";
        }
    }
    public void equalBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void backspaceBTN(View view) {
        int cursorPosition = display.getSelectionStart(); //cursor current position
        int textLen = display.getText().length(); //przypisuje tekst do zmiennej

        if (cursorPosition != 0 && textLen != 0){ //replace characters in string
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition - 1, cursorPosition, ""); //select character that we wanna replace, with empty string
            display.setText(selection);
            display.setSelection(cursorPosition - 1);

        }


    }



}