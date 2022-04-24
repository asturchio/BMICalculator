package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Class Variables; are also called 'Fields'
    TextView resultText;
    Button calculateButton;
    EditText weightEditText;
    EditText feedEditText;
    EditText inchesEditText;
    EditText ageEditText;
    RadioButton maleButton;
    RadioButton femaleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        /*we stuck all this junk in the findViews method.
        TextView resultText = findViewById(R.id.text_view_result);

        Button calculateButton = findViewById(R.id.button_calculate);

        EditText weightEditText = findViewById(R.id.edit_text_weight);
        EditText feedEditText = findViewById(R.id.edit_text_feet);
        EditText inchesEditText = findViewById(R.id.edit_text_inches);
        EditText ageEditText = findViewById(R.id.edit_text_age);

        RadioButton maleButton = findViewById(R.id.radio_button_male);
        RadioButton femaleButton = findViewById(R.id.radio_button_female);
        */
        setupButtonClickListener();


        //use this code to make an alert
        //String alertText = "Wow, I can make an alert pop up!";
        //Toast.makeText(this,alertText,Toast.LENGTH_LONG).show();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        calculateButton = findViewById(R.id.button_calculate);
        weightEditText = findViewById(R.id.edit_text_weight);
        feedEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        ageEditText = findViewById(R.id.edit_text_age);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBMI();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                }else{
                    displayGuidance(bmiResult);
                }



            }
        });
    }



    private double calculateBMI() {
        String feetText = feedEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        //converting the number 'Strings' into int variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        double totalInches = (feet*12)+inches;

        //height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        //bmi formula weight in kg divided by height in meters squared
        return (weight / (totalInches * totalInches))*703;
    }

    private void displayResult(double bmi) {
        //this replaces string.format
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if(bmi < 18.5){
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if (bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";
        }else{
            //Display Healthy
            fullResultString = bmiTextResult + " - You are a healthy";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if(maleButton.isChecked()){
            //Display boy guiance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your dctor for a healthy range for boys";
        }else if (femaleButton.isChecked()){
            //Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your dctor for a healthy range for girls";
        }else{
            //Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your dctor for a healthy range";
        }
        resultText.setText(fullResultString);

    }
}