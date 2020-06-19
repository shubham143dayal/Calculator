package com.shudik.calculator.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.shudik.calculator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DistanceFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Context mContext;
    EditText number;
    String unit;
    TextView mm,cm,dm,m,km,in,ft,yd,mi,nmi,ly;
    NumberFormat numberFormat,longFormat;
    public Double num = 0.0;

    Spinner spinner;

    public DistanceFragment (){

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_distance, container, false);
        spinner = view.findViewById(R.id.unitSpinner);
        number = view.findViewById(R.id.firstUnit);
        mm = view.findViewById(R.id.mm);
        cm = view.findViewById(R.id.cm);
        dm = view.findViewById(R.id.dm);
        m = view.findViewById(R.id.m);
        km = view.findViewById(R.id.km);
        in = view.findViewById(R.id.in);
        ft = view.findViewById(R.id.ft);
        yd = view.findViewById(R.id.yd);
        mi = view.findViewById(R.id.mi);
        nmi = view.findViewById(R.id.nmi);
        ly = view.findViewById(R.id.ly);
        //Answer at four decimal place
        numberFormat = new DecimalFormat("#.#######");
        // if answer is long
        longFormat = new DecimalFormat("0.####E0");

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(mContext,R.array.distance_array,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(DistanceFragment.this);

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!number.getText().toString().equals("")){
                    num = Double.parseDouble(number.getText().toString());
                }else{
                    num = 0.0;
                }
                switch (unit){
                    case "mm":
                        mm.setText(checkFormat(num));
                        cm.setText(checkFormat(num*(0.1)));
                        dm.setText(checkFormat(num*(0.01)));
                        m.setText(checkFormat(num*(0.001)));
                        km.setText(checkFormat(num*(0.000001)));
                        in.setText(checkFormat((num*(0.0393701))));
                        ft.setText(checkFormat(num*(0.00328084)));
                        yd.setText(checkFormat(num*0.0010936133));
                        mi.setText(checkFormat(num*6.2137e-7));
                        nmi.setText(checkFormat(num*5.3996e-7));
                        ly.setText(longFormat.format(num*1.05702e-19));
                        break;
                    case "cm":
                        mm.setText(checkFormat(num*10));
                        cm.setText(checkFormat(num));
                        dm.setText(checkFormat(num*(0.1)));
                        m.setText(checkFormat(num*(0.01)));
                        km.setText(checkFormat(num*(0.00001)));
                        in.setText(checkFormat((num*(0.393701))));
                        ft.setText(checkFormat(num*(0.0328084)));
                        yd.setText(checkFormat(num*0.010936133));
                        mi.setText(checkFormat(num*6.2137e-6));
                        nmi.setText(checkFormat(num*5.3996e-6));
                        ly.setText(longFormat.format(num*1.0570234e-18));
                        break;
                    case "dm":
                        mm.setText(checkFormat(num*100));
                        cm.setText(checkFormat(num*10));
                        dm.setText(checkFormat(num));
                        m.setText(checkFormat(num*(0.1)));
                        km.setText(checkFormat(num*(0.0001)));
                        in.setText(checkFormat((num*(3.93701))));
                        ft.setText(checkFormat(num*(0.328084)));
                        yd.setText(checkFormat(num*0.10936133));
                        mi.setText(checkFormat(num*6.2137e-5));
                        nmi.setText(checkFormat(num*5.3996e-5));
                        ly.setText(longFormat.format(num*1.0570234e-17));
                        break;
                    case "m":
                        mm.setText(checkFormat(num*1000));
                        cm.setText(checkFormat(num*100));
                        dm.setText(checkFormat(num*10));
                        m.setText(checkFormat(num*(1)));
                        km.setText(checkFormat(num*(0.001)));
                        in.setText(checkFormat((num*(39.37007874))));
                        ft.setText(checkFormat(num*(3.28083989)));
                        yd.setText(checkFormat(num*1.0936133));
                        mi.setText(checkFormat(num*6.2137e-4));
                        nmi.setText(checkFormat(num*5.3996e-4));
                        ly.setText(longFormat.format(num*1.0570234e-16));
                        break;
                    case "km":
                        mm.setText(checkFormat((num*1000000)));
                        cm.setText(checkFormat(num*100000));
                        dm.setText(checkFormat(num*10000));
                        m.setText(checkFormat(num*(1000)));
                        km.setText(checkFormat(num*(1)));
                        in.setText(checkFormat((num*(39370.07874015))));
                        ft.setText(checkFormat(num*(3280.839895013)));
                        yd.setText(checkFormat(num*1093.61329833));
                        mi.setText(checkFormat(num*0.621371192));
                        nmi.setText(checkFormat(num*0.53995680));
                        ly.setText(longFormat.format(num*1.0570234e-13));
                        break;
                    case "in":
                        mm.setText(checkFormat(num*25.4));
                        cm.setText(checkFormat(num*2.54));
                        dm.setText(checkFormat(num*0.254));
                        m.setText(checkFormat(num*(0.0254)));
                        km.setText(checkFormat(num*(2.54e-5)));
                        in.setText(checkFormat((num*(1))));
                        ft.setText(checkFormat(num*(0.08333333333)));
                        yd.setText(checkFormat(num*0.0277778));
                        mi.setText(checkFormat(num*1.5783e-5));
                        nmi.setText(checkFormat(num*1.3715e-5));
                        ly.setText(longFormat.format(num*2.684839e-18));
                        break;
                    case "ft":
                        mm.setText(checkFormat(num*304.8));
                        cm.setText(checkFormat(num*30.48));
                        dm.setText(checkFormat(num*3.048));
                        m.setText(checkFormat(num*(0.3048)));
                        km.setText(checkFormat(num*(0.0003048)));
                        in.setText(checkFormat((num*(12))));
                        ft.setText(checkFormat(num*(1)));
                        yd.setText(checkFormat(num*0.333333));
                        mi.setText(checkFormat(num*0.000189394));
                        nmi.setText(checkFormat(num*0.000164579));
                        ly.setText(longFormat.format(num*3.221807355e-17));
                        break;
                    case "yd":
                        mm.setText(checkFormat(num*914.4));
                        cm.setText(checkFormat(num*91.44));
                        dm.setText(checkFormat(num*9.144));
                        m.setText(checkFormat(num*(0.9144)));
                        km.setText(checkFormat(num*(0.0009144)));
                        in.setText(checkFormat((num*(36))));
                        ft.setText(checkFormat(num*(3)));
                        yd.setText(checkFormat(num*1));
                        mi.setText(checkFormat(num*0.000568182));
                        nmi.setText(checkFormat(num*0.000493737));
                        ly.setText(longFormat.format(num*9.66542206e-17));
                        break;
                    case "mi":
                        mm.setText(checkFormat(num*1.609e+6));
                        cm.setText(checkFormat(num*160934));
                        dm.setText(checkFormat(num*16093.4));
                        m.setText(checkFormat(num*(1609.34)));
                        km.setText(checkFormat(num*(1.60934)));
                        in.setText(checkFormat((num*(63360))));
                        ft.setText(checkFormat(num*(5280)));
                        yd.setText(checkFormat(num*1760));
                        mi.setText(checkFormat(num*1));
                        nmi.setText(checkFormat(num*0.868976));
                        ly.setText(longFormat.format(num*1.70111428e-13));
                        break;
                    case "nmi":
                        mm.setText(checkFormat(num*1.852e+6));
                        cm.setText(checkFormat(num*185200));
                        dm.setText(checkFormat(num*18520));
                        m.setText(checkFormat(num*(1852)));
                        km.setText(checkFormat(num*(1.852)));
                        in.setText(checkFormat((num*(72913.4))));
                        ft.setText(checkFormat(num*(6076.12)));
                        yd.setText(checkFormat(num*2025.37));
                        mi.setText(checkFormat(num*1.15078));
                        nmi.setText(checkFormat(num*1));
                        ly.setText(longFormat.format(num*1.957607356e-13));
                        break;
                    case "ly":
                        mm.setText(longFormat.format(num*9.460528e18));
                        cm.setText(checkFormat(num*9.46052840e17));
                        dm.setText(checkFormat(num*9.46052840e16));
                        m.setText(checkFormat(num*(9.46052840e15)));
                        km.setText(checkFormat(num*(9.46052840e12)));
                        in.setText(checkFormat((num*(3.725e+17))));
                        ft.setText(checkFormat(num*(3.104e+16)));
                        yd.setText(checkFormat(num*1.035e+16));
                        mi.setText(checkFormat(num*5.879e+12));
                        nmi.setText(checkFormat(num*5.108e+12));
                        ly.setText(checkFormat(num*1));
                        break;

                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        unit = adapterView.getItemAtPosition(i).toString();
//        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        if(!number.getText().toString().equals("")){
            num = Double.parseDouble(number.getText().toString());
        }else{
            num = 0.0;
        }
        switch (unit){
            case "mm":
                mm.setText(checkFormat(num));
                cm.setText(checkFormat(num*(0.1)));
                dm.setText(checkFormat(num*(0.01)));
                m.setText(checkFormat(num*(0.001)));
                km.setText(checkFormat(num*(0.000001)));
                in.setText(checkFormat((num*(0.0393701))));
                ft.setText(checkFormat(num*(0.00328084)));
                yd.setText(checkFormat(num*0.0010936133));
                mi.setText(checkFormat(num*6.2137e-7));
                nmi.setText(checkFormat(num*5.3996e-7));
                ly.setText(longFormat.format(num*1.05702e-19));
                break;
            case "cm":
                mm.setText(checkFormat(num*10));
                cm.setText(checkFormat(num));
                dm.setText(checkFormat(num*(0.1)));
                m.setText(checkFormat(num*(0.01)));
                km.setText(checkFormat(num*(0.00001)));
                in.setText(checkFormat((num*(0.393701))));
                ft.setText(checkFormat(num*(0.0328084)));
                yd.setText(checkFormat(num*0.010936133));
                mi.setText(checkFormat(num*6.2137e-6));
                nmi.setText(checkFormat(num*5.3996e-6));
                ly.setText(longFormat.format(num*1.0570234e-18));
                break;
            case "dm":
                mm.setText(checkFormat(num*100));
                cm.setText(checkFormat(num*10));
                dm.setText(checkFormat(num));
                m.setText(checkFormat(num*(0.1)));
                km.setText(checkFormat(num*(0.0001)));
                in.setText(checkFormat((num*(3.93701))));
                ft.setText(checkFormat(num*(0.328084)));
                yd.setText(checkFormat(num*0.10936133));
                mi.setText(checkFormat(num*6.2137e-5));
                nmi.setText(checkFormat(num*5.3996e-5));
                ly.setText(longFormat.format(num*1.0570234e-17));
                break;
            case "m":
                mm.setText(checkFormat(num*1000));
                cm.setText(checkFormat(num*100));
                dm.setText(checkFormat(num*10));
                m.setText(checkFormat(num*(1)));
                km.setText(checkFormat(num*(0.001)));
                in.setText(checkFormat((num*(39.37007874))));
                ft.setText(checkFormat(num*(3.28083989)));
                yd.setText(checkFormat(num*1.0936133));
                mi.setText(checkFormat(num*6.2137e-4));
                nmi.setText(checkFormat(num*5.3996e-4));
                ly.setText(longFormat.format(num*1.0570234e-16));
                break;
            case "km":
                mm.setText(checkFormat((num*1000000)));
                cm.setText(checkFormat(num*100000));
                dm.setText(checkFormat(num*10000));
                m.setText(checkFormat(num*(1000)));
                km.setText(checkFormat(num*(1)));
                in.setText(checkFormat((num*(39370.07874015))));
                ft.setText(checkFormat(num*(3280.839895013)));
                yd.setText(checkFormat(num*1093.61329833));
                mi.setText(checkFormat(num*0.621371192));
                nmi.setText(checkFormat(num*0.53995680));
                ly.setText(longFormat.format(num*1.0570234e-13));
                break;
            case "in":
                mm.setText(checkFormat(num*25.4));
                cm.setText(checkFormat(num*2.54));
                dm.setText(checkFormat(num*0.254));
                m.setText(checkFormat(num*(0.0254)));
                km.setText(checkFormat(num*(2.54e-5)));
                in.setText(checkFormat((num*(1))));
                ft.setText(checkFormat(num*(0.08333333333)));
                yd.setText(checkFormat(num*0.0277778));
                mi.setText(checkFormat(num*1.5783e-5));
                nmi.setText(checkFormat(num*1.3715e-5));
                ly.setText(longFormat.format(num*2.684839e-18));
                break;
            case "ft":
                mm.setText(checkFormat(num*304.8));
                cm.setText(checkFormat(num*30.48));
                dm.setText(checkFormat(num*3.048));
                m.setText(checkFormat(num*(0.3048)));
                km.setText(checkFormat(num*(0.0003048)));
                in.setText(checkFormat((num*(12))));
                ft.setText(checkFormat(num*(1)));
                yd.setText(checkFormat(num*0.333333));
                mi.setText(checkFormat(num*0.000189394));
                nmi.setText(checkFormat(num*0.000164579));
                ly.setText(longFormat.format(num*3.221807355e-17));
                break;
            case "yd":
                mm.setText(checkFormat(num*914.4));
                cm.setText(checkFormat(num*91.44));
                dm.setText(checkFormat(num*9.144));
                m.setText(checkFormat(num*(0.9144)));
                km.setText(checkFormat(num*(0.0009144)));
                in.setText(checkFormat((num*(36))));
                ft.setText(checkFormat(num*(3)));
                yd.setText(checkFormat(num*1));
                mi.setText(checkFormat(num*0.000568182));
                nmi.setText(checkFormat(num*0.000493737));
                ly.setText(longFormat.format(num*9.66542206e-17));
                break;
            case "mi":
                mm.setText(checkFormat(num*1.609e+6));
                cm.setText(checkFormat(num*160934));
                dm.setText(checkFormat(num*16093.4));
                m.setText(checkFormat(num*(1609.34)));
                km.setText(checkFormat(num*(1.60934)));
                in.setText(checkFormat((num*(63360))));
                ft.setText(checkFormat(num*(5280)));
                yd.setText(checkFormat(num*1760));
                mi.setText(checkFormat(num*1));
                nmi.setText(checkFormat(num*0.868976));
                ly.setText(longFormat.format(num*1.70111428e-13));
                break;
            case "nmi":
                mm.setText(checkFormat(num*1.852e+6));
                cm.setText(checkFormat(num*185200));
                dm.setText(checkFormat(num*18520));
                m.setText(checkFormat(num*(1852)));
                km.setText(checkFormat(num*(1.852)));
                in.setText(checkFormat((num*(72913.4))));
                ft.setText(checkFormat(num*(6076.12)));
                yd.setText(checkFormat(num*2025.37));
                mi.setText(checkFormat(num*1.15078));
                nmi.setText(checkFormat(num*1));
                ly.setText(longFormat.format(num*1.957607356e-13));
                break;
            case "ly":
                mm.setText(longFormat.format(num*9.460528e18));
                cm.setText(checkFormat(num*9.46052840e17));
                dm.setText(checkFormat(num*9.46052840e16));
                m.setText(checkFormat(num*(9.46052840e15)));
                km.setText(checkFormat(num*(9.46052840e12)));
                in.setText(checkFormat((num*(3.725e+17))));
                ft.setText(checkFormat(num*(3.104e+16)));
                yd.setText(checkFormat(num*1.035e+16));
                mi.setText(checkFormat(num*5.879e+12));
                nmi.setText(checkFormat(num*5.108e+12));
                ly.setText(checkFormat(num*1));
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public String checkFormat(Double n){
        String temp="";
        if(n.toString().length()>13){
            temp = longFormat.format(n);
        }
        else{
            temp= numberFormat.format(n);
        }
        return temp;
    }



}