package com.example.task21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner unitSelectSpinner, sourceSpinner, destSpinner;
    EditText unitValueEdit;
    Button convertButton;
    TextView resultText;
    String destUnit = null;
    String sourceUnit = null;
    Double unitValue = null;
    public static final String TEMP_TEXT = "Temperature";
    public static final String LENGTH_TEXT = "Length";
    public static final String WEIGHT_TEXT = "Weight";
    public static final String INCH_TEXT = "Inch";
    public static final String FOOT_TEXT = "Foot";
    public static final String YARD_TEXT = "Yard";
    public static final String MILE_TEXT = "Mile";
    public static final String CM_TEXT = "Cm";
    public static final String KM_TEXT = "Km";
    public static final String POUND_TEXT = "Pound";
    public static final String OUNCE_TEXT = "Ounce";
    public static final String TON_TEXT = "TON";
    public static final String G_TEXT = "G";
    public static final String KG_TEXT = "Kg";
    public static final String CELSIUS_TEXT = "Celsius";
    public static final String FAHRENHEIT_TEXT = "Fahrenheit";
    public static final String KELVIN_TEXT = "Kelvin";

    String unitType=LENGTH_TEXT;


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public Double convertLength(Double value, String sourceUnit, String destUnit){
        if(sourceUnit == destUnit){
            return  value;
        }
        Double valueInCm = -0.1;
        switch(sourceUnit) {
            case INCH_TEXT:
                valueInCm = value * 2.54;
                break;
            case FOOT_TEXT:
                valueInCm = value * 30.48;
                break;
            case YARD_TEXT:
                valueInCm = value * 91.44;
                break;
            case MILE_TEXT:
                valueInCm = value * 160934;
                break;
            case CM_TEXT:
                valueInCm = value;
                break;
            case KM_TEXT:
                valueInCm = value * 100000;
                break;
        }
        Double result = -1.0;
        switch(destUnit) {
            case INCH_TEXT:
                result = valueInCm / 2.54;
                break;
            case FOOT_TEXT:
                result = valueInCm / 30.48;
                break;
            case YARD_TEXT:
                result = valueInCm / 91.44;
                break;
            case MILE_TEXT:
                result = valueInCm / 160934;
                break;
            case CM_TEXT:
                result = valueInCm;
                break;
            case KM_TEXT:
                result = valueInCm / 100000;
                break;
        }
        return result;
    }
    public Double convertWeight(Double value, String sourceUnit, String destUnit){
        if(sourceUnit == destUnit){
            return  value;
        }
        Double valueInG = -0.1;
        switch(sourceUnit) {
            case POUND_TEXT:
                valueInG = value * 0.453592 * 1000;
                break;
            case OUNCE_TEXT:
                valueInG = value * 28.3495;
                break;
            case TON_TEXT:
                valueInG = value * 907.185 * 1000;
                break;
            case G_TEXT:
                valueInG = value;
                break;
            case KG_TEXT:
                valueInG = value * 1000;
                break;
        }
        Double result = -1.0;
        switch(destUnit) {
            case POUND_TEXT:
                result = valueInG / 0.453592 / 1000;
                break;
            case OUNCE_TEXT:
                result = valueInG / 28.3495;
                break;
            case TON_TEXT:
                result = valueInG / 907.185 / 1000;
                break;
            case G_TEXT:
                result = valueInG;
                break;
            case KG_TEXT:
                result = valueInG / 1000;
                break;
        }

        return result;

    }
    public Double convertTemp(Double value, String sourceUnit, String destUnit){
        if(sourceUnit == destUnit){
            return  value;
        }
        Double valueInCelsius = -0.1;
        switch(sourceUnit) {
            case CELSIUS_TEXT:
                valueInCelsius = value;
                break;
            case FAHRENHEIT_TEXT:
                valueInCelsius = (value - 32)/1.8;
                break;
            case KELVIN_TEXT:
                valueInCelsius = value - 273.15;
                break;
        }
        Double result = -1.0;
        switch(destUnit) {
            case CELSIUS_TEXT:
                result = valueInCelsius;
                break;
            case FAHRENHEIT_TEXT:
                result = (valueInCelsius * 1.8)+32;
                break;
            case KELVIN_TEXT:
                result = valueInCelsius + 273.15;
                break;
        }
        return result;
    }

    public void setSourceDestOptions (String type){
        ArrayAdapter<CharSequence>adapter;
        switch (type) {
            case WEIGHT_TEXT:
                adapter = ArrayAdapter.createFromResource(this,R.array.weight,android.R.layout.simple_spinner_item);
                unitType = WEIGHT_TEXT;
                break;
            case TEMP_TEXT:
                adapter = ArrayAdapter.createFromResource(this,R.array.temp,android.R.layout.simple_spinner_item);
                unitType=TEMP_TEXT;
                break;
            default:
                adapter=ArrayAdapter.createFromResource(this, R.array.length, android.R.layout.simple_spinner_item);
                unitType=LENGTH_TEXT;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceSpinner.setAdapter(adapter);
        destSpinner.setAdapter(adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitSelectSpinner = findViewById(R.id.unitSelect);
        sourceSpinner = findViewById(R.id.sourceSelect);
        destSpinner = findViewById(R.id.destSelect);

        unitValueEdit = findViewById(R.id.unitValue);
        convertButton = findViewById(R.id.button);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<CharSequence> unitSelectAdapter = ArrayAdapter.createFromResource(this,R.array.unit_options, android.R.layout.simple_spinner_item);
        unitSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSelectSpinner.setAdapter(unitSelectAdapter);

        unitSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String unitType = adapterView.getItemAtPosition(i).toString();
                if (unitType != null) {
                    setSourceDestOptions(unitType);
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Input",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setSourceDestOptions(LENGTH_TEXT);
            }
        });
        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if (item != null) {
                    sourceUnit = item;
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Source Unit",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Invalid Source Unit",
                        Toast.LENGTH_SHORT).show();
            }
        });

        destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if (item != null) {
                    destUnit = item;
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Source Unit",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Invalid Destination Unit",
                        Toast.LENGTH_SHORT).show();
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String value = unitValueEdit.getText().toString();
                if(!isNumeric(value)){
                    Toast.makeText(MainActivity.this, "Value is not numeric!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                unitValue = Double.parseDouble(value);
                Double result;
                switch(unitType) {
                    case "Temperature":
                        result = convertTemp(unitValue, sourceUnit, destUnit);
                        break;
                    case "Weight":
                        result = convertWeight(unitValue, sourceUnit, destUnit);
                        break;
                    default:
                        result = convertLength(unitValue, sourceUnit, destUnit);
                        break;
                }
                resultText.setText(result + " " + destUnit);
            }
        });
    }


}