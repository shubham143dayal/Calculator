package com.shudik.calculator.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shudik.calculator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class AreaFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private Context mContext;
    EditText number;
    int unit;
    TextView mm,cm,dm,m,km,in,ft,yd,mi;
    NumberFormat numberFormat,longFormat;
    public Double num = 0.0;
    Spinner spinner;


    public AreaFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_area, container, false);
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
        //Answer at four decimal place
        numberFormat = new DecimalFormat("#.#######");
        // if answer is long
        longFormat = new DecimalFormat("0.####E0");

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(mContext,R.array.area_array,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(AreaFragment.this);

        TextView mm2 = view.findViewById(R.id.mm2);
        mm2.setText(Html.fromHtml("mm<sup><small>2</small></sup>"));
        TextView cm2 = view.findViewById(R.id.cm2);
        cm2.setText(Html.fromHtml("cm<sup><small>2</small></sup>"));
        TextView m2 = view.findViewById(R.id.m2);
        m2.setText(Html.fromHtml("m<sup><small>2</small></sup>"));
        TextView km2 = view.findViewById(R.id.km2);
        km2.setText(Html.fromHtml("km<sup><small>2</small></sup>"));
        TextView ft2 = view.findViewById(R.id.ft2);
        ft2.setText(Html.fromHtml("ft<sup><small>2</small></sup>"));
        TextView yd2 = view.findViewById(R.id.yd2);
        yd2.setText(Html.fromHtml("yd<sup><small>2</small></sup>"));

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
                    case 0:
                        mm.setText(checkFormat(num));
                        cm.setText(checkFormat(num*(0.01)));
                        dm.setText(checkFormat(num*(1e-6)));
                        m.setText(checkFormat(num*(1e-8)));
                        km.setText(checkFormat(num*(1e-10)));
                        in.setText(checkFormat((num*(1e-12))));
                        ft.setText(checkFormat(num*(1.076391041670e-5)));
                        yd.setText(checkFormat(num*1.195990046301e-6));
                        mi.setText(checkFormat(num*2.4710538146e-10));
                        break;
                    case 1:
                        mm.setText(checkFormat(num*100));
                        cm.setText(checkFormat(num));
                        dm.setText(checkFormat(num*(0.0001)));
                        m.setText(checkFormat(num*(1e-6)));
                        km.setText(checkFormat(num*(1e-8)));
                        in.setText(checkFormat((num*(1e-10))));
                        ft.setText(checkFormat(num*(0.0010763910417)));
                        yd.setText(checkFormat(num*0.00011959004630));
                        mi.setText(checkFormat(num*2.4710538147e-8));

                        break;
//                    case "dm":
//                        mm.setText(checkFormat(num*100));
//                        cm.setText(checkFormat(num*10));
//                        dm.setText(checkFormat(num));
//                        m.setText(checkFormat(num*(0.1)));
//                        km.setText(checkFormat(num*(0.0001)));
//                        in.setText(checkFormat((num*(3.93701))));
//                        ft.setText(checkFormat(num*(0.328084)));
//                        yd.setText(checkFormat(num*0.10936133));
//                        mi.setText(checkFormat(num*6.2137e-5));
//
//                        break;
//                    case "m":
//                        mm.setText(checkFormat(num*1000));
//                        cm.setText(checkFormat(num*100));
//                        dm.setText(checkFormat(num*10));
//                        m.setText(checkFormat(num*(1)));
//                        km.setText(checkFormat(num*(0.001)));
//                        in.setText(checkFormat((num*(39.37007874))));
//                        ft.setText(checkFormat(num*(3.28083989)));
//                        yd.setText(checkFormat(num*1.0936133));
//                        mi.setText(checkFormat(num*6.2137e-4));
//
//                        break;
//                    case "km":
//                        mm.setText(checkFormat((num*1000000)));
//                        cm.setText(checkFormat(num*100000));
//                        dm.setText(checkFormat(num*10000));
//                        m.setText(checkFormat(num*(1000)));
//                        km.setText(checkFormat(num*(1)));
//                        in.setText(checkFormat((num*(39370.07874015))));
//                        ft.setText(checkFormat(num*(3280.839895013)));
//                        yd.setText(checkFormat(num*1093.61329833));
//                        mi.setText(checkFormat(num*0.621371192));
//
//                        break;
//                    case "in":
//                        mm.setText(checkFormat(num*25.4));
//                        cm.setText(checkFormat(num*2.54));
//                        dm.setText(checkFormat(num*0.254));
//                        m.setText(checkFormat(num*(0.0254)));
//                        km.setText(checkFormat(num*(2.54e-5)));
//                        in.setText(checkFormat((num*(1))));
//                        ft.setText(checkFormat(num*(0.08333333333)));
//                        yd.setText(checkFormat(num*0.0277778));
//                        mi.setText(checkFormat(num*1.5783e-5));
//
//                        break;
//                    case "ft":
//                        mm.setText(checkFormat(num*304.8));
//                        cm.setText(checkFormat(num*30.48));
//                        dm.setText(checkFormat(num*3.048));
//                        m.setText(checkFormat(num*(0.3048)));
//                        km.setText(checkFormat(num*(0.0003048)));
//                        in.setText(checkFormat((num*(12))));
//                        ft.setText(checkFormat(num*(1)));
//                        yd.setText(checkFormat(num*0.333333));
//                        mi.setText(checkFormat(num*0.000189394));
//
//                        break;
//                    case "yd":
//                        mm.setText(checkFormat(num*914.4));
//                        cm.setText(checkFormat(num*91.44));
//                        dm.setText(checkFormat(num*9.144));
//                        m.setText(checkFormat(num*(0.9144)));
//                        km.setText(checkFormat(num*(0.0009144)));
//                        in.setText(checkFormat((num*(36))));
//                        ft.setText(checkFormat(num*(3)));
//                        yd.setText(checkFormat(num*1));
//                        mi.setText(checkFormat(num*0.000568182));
//
//                        break;
//                    case "mi":
//                        mm.setText(checkFormat(num*1.609e+6));
//                        cm.setText(checkFormat(num*160934));
//                        dm.setText(checkFormat(num*16093.4));
//                        m.setText(checkFormat(num*(1609.34)));
//                        km.setText(checkFormat(num*(1.60934)));
//                        in.setText(checkFormat((num*(63360))));
//                        ft.setText(checkFormat(num*(5280)));
//                        yd.setText(checkFormat(num*1760));
//                        mi.setText(checkFormat(num*1));
//
//                        break;
//                    case "nmi":
//                        mm.setText(checkFormat(num*1.852e+6));
//                        cm.setText(checkFormat(num*185200));
//                        dm.setText(checkFormat(num*18520));
//                        m.setText(checkFormat(num*(1852)));
//                        km.setText(checkFormat(num*(1.852)));
//                        in.setText(checkFormat((num*(72913.4))));
//                        ft.setText(checkFormat(num*(6076.12)));
//                        yd.setText(checkFormat(num*2025.37));
//                        mi.setText(checkFormat(num*1.15078));
//
//                        break;
//                    case "ly":
//                        mm.setText(longFormat.format(num*9.460528e18));
//                        cm.setText(checkFormat(num*9.46052840e17));
//                        dm.setText(checkFormat(num*9.46052840e16));
//                        m.setText(checkFormat(num*(9.46052840e15)));
//                        km.setText(checkFormat(num*(9.46052840e12)));
//                        in.setText(checkFormat((num*(3.725e+17))));
//                        ft.setText(checkFormat(num*(3.104e+16)));
//                        yd.setText(checkFormat(num*1.035e+16));
//                        mi.setText(checkFormat(num*5.879e+12));
//
//                        break;

                }
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        unit = adapterView.getSelectedItemPosition();
//        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
        if(!number.getText().toString().equals("")){
            num = Double.parseDouble(number.getText().toString());
        }else{
            num = 0.0;
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