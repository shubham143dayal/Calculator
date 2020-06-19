package com.shudik.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    public NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    TextView calculation,answer;
    String sCalculation="",sAnswer="",number_one="",current_operator="",firstNumber="";
    Double Result=0.0,numberOne=0.0,temp=0.0;
    // We need to reformat Answer
    NumberFormat numberFormat,longFormat;

    Boolean dotPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.drawerMenu);
        navigationView.getMenu().getItem(0).setChecked(true);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        calculation = findViewById(R.id.calculation);
        //setting scroll to TextView
        calculation.setMovementMethod(new ScrollingMovementMethod());
        calculation.setText("0");
        answer = findViewById(R.id.answer);
        //Answer at four decimal place
        numberFormat = new DecimalFormat("#.####");
        // if answer is long
        longFormat = new DecimalFormat("0.#E0");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.first:
                        Intent standardCalc = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(standardCalc);
//                        Toast.makeText(MainActivity.this, "First selected", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.second:
                        Intent unitConversion = new Intent(MainActivity.this,UnitConversion.class);
                        startActivity(unitConversion);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.third:
                        Intent ageCalc = new Intent(MainActivity.this,AgeCalculator.class);
                        startActivity(ageCalc);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.about:
                        Intent aboutIntent = new Intent(MainActivity.this,About.class);
                        startActivity(aboutIntent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });
    }


    public void onClickNumber(View view){
        Button btn = (Button) view;
        sCalculation+=btn.getText();
        number_one+=btn.getText();
        numberOne=Double.parseDouble(number_one);
        switch (current_operator){
            case "": //if current operator is null
                temp = Result+numberOne;
                sAnswer=numberFormat.format(temp).toString();
                break;
            case "+":
                temp = Result+numberOne;
                sAnswer=numberFormat.format(temp).toString();
                break;
            case "-":
                temp = Result-numberOne;
                sAnswer=numberFormat.format(temp).toString();
                break;
            case "x":
                temp = Result*numberOne;
                sAnswer=numberFormat.format(temp).toString();
                break;
            case "/":
                try {   //Division by 0 cause Exception
                    temp = Result/numberOne;
                    sAnswer=numberFormat.format(temp).toString();
                }catch (Exception e){
                    sAnswer = e.getMessage();
                }
            break;
        }
        updateCalculation();
    }

    public void onClickOperator(View view){

       Button btnOperator = (Button) view;

       if(sAnswer!=""){  // if answer is null
           //Check last character is operator or not

           if(current_operator!=""){
               char c = getCharFromLast(sCalculation,1);
               if(c == '+' || c == '-' || c == 'x' || c == '/'){
                   sCalculation=sCalculation.substring(0,sCalculation.length()-1);
               }
           }
           sCalculation = sCalculation+""+btnOperator.getText() + "";
           firstNumber = number_one;
           number_one="";
           numberOne=0.0;
           Result=temp;
           current_operator = btnOperator.getText().toString();
           updateCalculation();
       }
        dotPresent=false;
    }

    private char getCharFromLast(String sCalculation, int i) {
        char c = sCalculation.charAt(sCalculation.length()-i);
        return c;
    }

    public void onClickClear(View view){
        sCalculation = "";
        sAnswer = "";
        current_operator = "";
        number_one = "";
        Result = 0.0;
        numberOne = 0.0;
        temp = 0.0;
        updateCalculation();
        dotPresent = false;
    }

    public void updateCalculation(){
        calculation.setText(sAnswer);
        answer.setText(sCalculation);
    }

    public void onDotClick(View view){
        if(!dotPresent){
            //Check the length of Number number_one
            if(current_operator!="" && number_one.length()==0){
                number_one = "0.";
                sCalculation = sCalculation+"0.";
                dotPresent = true;
                updateCalculation();

            }else {
                if (number_one.length() == 0) {
                    number_one = "0.";
                    sCalculation = "0.";
//                sAnswer="0.";
                    dotPresent = true;
                    updateCalculation();
                } else {
                    number_one += ".";
                    sCalculation += ".";
                    dotPresent = true;
                    updateCalculation();
                }
            }
        }
    }

    public void onEqualClick(View view){
        if(sAnswer!=""){
            sCalculation = sAnswer;
            number_one = sAnswer;
            numberOne=Double.parseDouble(number_one);
            answer.setText(sCalculation);
            temp = 0.0;
            Result = 0.0;
            temp = Result+numberOne;
            sAnswer=numberFormat.format(temp).toString();
        }
    }

    public void onDeleteClick(View view){
        if(!sCalculation.equals("")){
            sCalculation=sCalculation.substring(0,sCalculation.length()-1);
            if(sCalculation.length()==0){
                Result = 0.0;
            }
            if(!sCalculation.equals("") && firstNumber.length()>=sCalculation.length()){
                current_operator="";
                Result = Double.parseDouble(sCalculation);
                numberOne = 0.0;
            }

            if(number_one.length()>1){
                number_one = number_one.substring(0,number_one.length()-1);
                numberOne = Double.parseDouble(number_one);

            }
            else {
                number_one = "0";
                numberOne = Double.parseDouble(number_one);
            }
//            if(firstNumber.length()>1) {
//                if (number_one.equals("0")
//                        && sCalculation.length() == firstNumber.length()) {
//                    firstNumber = firstNumber.substring(0, firstNumber.length() - 1);
//                    numberOne = Double.parseDouble(firstNumber);
//                }
//            }
//            else
//            {
//                firstNumber = "0";
//                numberOne = Double.parseDouble(firstNumber);
//            }
            temp = Result+numberOne;
            sAnswer = numberFormat.format(temp).toString();
            updateCalculation();
        }
    }

    public void onModuloClick(View view) {
        if(!sAnswer.equals("") && getCharFromLast(sCalculation,1)!=' '){
            sCalculation+="% ";
            // Value of temp will change
            switch (current_operator){
                case "":
                    temp = temp/100;
                    break;
                case "+":
                    temp = Result+((Result*numberOne)/100);
                    break;
                case "-":
                    temp = Result-((Result*numberOne)/100);
                    break;
                case "x":
                    temp = Result*((numberOne)/100);
                    break;
                case "/":
                    try {
                        temp = Result/((numberOne)/100);
                    }catch (Exception e){
                        sAnswer=e.getMessage();
                    }
                break;
            }
            sAnswer = numberFormat.format(temp).toString();
            if (sAnswer.length()>9){
                sAnswer = longFormat.format(temp);
            }
            Result=temp;
            updateCalculation();
        }
    }

    public void onSignChangeClick(View view) {
        if(!sAnswer.equals("") && getCharFromLast(sCalculation,1)!=' '){
            numberOne=numberOne*(-1);
            number_one = numberFormat.format(numberOne).toString();
            switch (current_operator){
                case"":
                    temp=numberOne;
                    sCalculation=number_one;
                    break;
                case"+":
                    temp=Result-numberOne;
                    //we need to add -ve sign in the starting of string
                    removeUntilChar(sCalculation,' ');
                    sCalculation=number_one;
                    break;
                case"-":
                    temp=Result+numberOne;
                    //we need to add -ve sign in the starting of string
                    removeUntilChar(sCalculation,' ');
                    sCalculation=number_one;
                    break;
                case"x":
                    temp=Result*numberOne;
                    //we need to add -ve sign in the starting of string
                    removeUntilChar(sCalculation,' ');
                    sCalculation=number_one;
                    break;
                case"/":
                    try {
                        temp=Result/numberOne;
                        //we need to add -ve sign in the starting of string
                        removeUntilChar(sCalculation,' ');
                        sCalculation=number_one;
                    }catch (Exception e){
                        sAnswer=e.getMessage();
                    }
                break;
            }
            sAnswer=numberFormat.format(temp).toString();
            updateCalculation();
        }
    }

    public void removeUntilChar(String str,char chr){
        char c = getCharFromLast(str,1);

        if(c!=chr){
            str=removeChar(str,1);
            sCalculation=str;
            updateCalculation();
            removeUntilChar(str,chr);
        }
    }

    public String removeChar(String str, int i) {
        char c =str.charAt(str.length()-i);
        // we have to check if Dot is removed or not
        if(c=='.'){
            dotPresent=false;
        }
        if(c==' '){
            return str.substring(0,str.length()-(i-1));
        }
        return str.substring(0,str.length()-i);
    }


}