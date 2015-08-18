package com.cooktools.lo.cooktools.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cooktools.lo.cooktools.R;

import java.text.DecimalFormat;

/**
 * 1rst Tab de la vue Calculatrice: Calculatrice de Conversion.
 * Permet de transformer les unité d'une quantité.
 * Exemple: calculer ce que vaux 3 cuillère à soupe en ml.
 */

public class Tab2 extends Fragment {

    //TextField
    TextView txtNumber, txtResult;

    //Spinner
    Spinner spNum, spResult;

    //Bouton Digit
    Button button0, button1, button2,button3, button4, button5, button6, button7, button8, button9, buttonC, buttonPoint, btnG, btnL, btnC;


    //Variables
    String str ="";
    private boolean update = false, delete = false;
    DecimalFormat dec = new DecimalFormat("#.###");
    String[] aNum, aRes;

    //Adapter
    ArrayAdapter<CharSequence> adapterGramme, adapterLitre, adapterSpoon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);

        txtNumber = (TextView) v.findViewById(R.id.txtNumber);
        txtResult = (TextView) v.findViewById(R.id.txtResult);

        adapterGramme = ArrayAdapter.createFromResource(getActivity(), R.array.unit_gramme, R.layout.spinner_center_item_2);
        adapterLitre = ArrayAdapter.createFromResource(getActivity(), R.array.unit_litre, R.layout.spinner_center_item_2);
        adapterSpoon = ArrayAdapter.createFromResource(getActivity(), R.array.unit_spoon, R.layout.spinner_center_item_2);

        initButtonDigit(v);
        initSpinner(v);

        return v;
    }

    /**
     * Initialisation des spinners en ajoutant toutes les valeurs possibles grâce à des Adapters.
     * @param v
     */
    private void initSpinner(View v) {
        spNum = (Spinner) v.findViewById(R.id.spinner);
        spResult = (Spinner) v.findViewById(R.id.spinner2);

        spNum.setAdapter(adapterGramme);
        spNum.setSelection(0);

        spResult.setAdapter(adapterGramme);
        spResult.setSelection(1);

        spNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Initialisation des boutons de la calculatrice.
     * Ajout des Listeners et des actions lorsque l'utilisateur clique sur un bouton.
     * @param v
     */
    private void initButtonDigit(View v) {
        button0 = (Button) v.findViewById(R.id.button10);
        button1 = (Button) v.findViewById(R.id.button);
        button2 = (Button) v.findViewById(R.id.button2);
        button3 = (Button) v.findViewById(R.id.button3);
        button4 = (Button) v.findViewById(R.id.button4);
        button5 = (Button) v.findViewById(R.id.button5);
        button6 = (Button) v.findViewById(R.id.button11);
        button7 = (Button) v.findViewById(R.id.button7);
        button8 = (Button) v.findViewById(R.id.button8);
        button9 = (Button) v.findViewById(R.id.button9);
        buttonPoint = (Button) v.findViewById(R.id.button6);
        buttonC = (Button) v.findViewById(R.id.button12);
        btnG = (Button) v.findViewById(R.id.btnG);
        btnL = (Button) v.findViewById(R.id.btnL);
        btnC = (Button) v.findViewById(R.id.btnC);

        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetClick();
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spNum.setAdapter(adapterGramme);
                spResult.setAdapter(adapterGramme);
            }
        });

        btnL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spNum.setAdapter(adapterLitre);
                spResult.setAdapter(adapterLitre);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spNum.setAdapter(adapterSpoon);
                spResult.setAdapter(adapterLitre);
            }
        });

        buttonPoint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!txtNumber.getText().toString().contains(".")){
                    chiffreClick(".");
                }
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chiffreClick("9");
            }
        });
    }

    /**
     * Changement des Adapters pour les deux spinners de la calculatrice.
     */
    public void changeSpinner(){
        String strNum = spNum.getSelectedItem().toString();
        String strRes = spResult.getSelectedItem().toString();

        if(strNum.contains("g")){
            convertG(strNum, strRes);
        }else if(strNum.contains("caf")){
            convertC(strNum, strRes, false);
        }else if(strNum.contains("soupe")){
            convertC(strNum, strRes, true);
        }else if(strNum.contains("l") && strRes.contains("l")){
            convertL(strNum, strRes);
        }

    }

    /**
     * Converti la valeur des champs pour des cuillères.
     * @param strNum
     * @param strRes
     * @param soupe
     */
    private void convertC(String strNum, String strRes, boolean soupe) {
        double iNum, iRes;
        int vol;
        if(soupe){
            vol = 15;
        }else{
            vol = 5;
        }
        if(!txtNumber.getText().equals("")) {
            iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue();

            switch (strRes) {
                case "ml":
                    iRes = iNum * vol;
                    break;
                case "cl":
                    iRes = (iNum * vol) / 10;
                    break;
                case "dl":
                    iRes = (iNum * vol) / 100;
                    break;
                default:
                    iRes = (iNum * vol) / 1000;
            }
            txtResult.setText(String.valueOf(dec.format(iRes)));
        }
    }


    /**
     * Converti la valeur des champs pour des Litres.
     * @param strNum
     * @param strRes
     */
    private void convertL(String strNum, String strRes) {
        double iNum, iRes;
        if(!txtNumber.getText().equals("")) {
            if (strNum.equals("ml")) {
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue() / 1000;
            } else if (strNum.equals("cl")) {
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue() / 100;
            } else if (strNum.equals("dl")) {
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue() / 10;
            } else {
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue();
            }

            switch (strRes) {
                case "ml":
                    iRes = iNum * 1000;
                    break;
                case "cl":
                    iRes = iNum * 100;
                    break;
                case "dl":
                    iRes = iNum * 10;
                    break;
                default:
                    iRes = iNum;
            }
            txtResult.setText(String.valueOf(dec.format(iRes)));
        }
    }

    /**
     * Converti la valeur des champs pour des grammes
     * @param strNum
     * @param strRes
     */
    private void convertG(String strNum, String strRes) {
        double iNum, iRes;
        if(!txtNumber.getText().equals("")){
            if(strNum.equals("mg")){
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue() / 1000;
            }else if(strNum.equals("kg")){
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue() * 1000;
            }else{
                iNum = Double.valueOf(txtNumber.getText().toString()).doubleValue();
            }

            switch (strRes){
                case "mg": iRes = iNum*1000;
                    break;
                case "kg": iRes = iNum/1000;
                    break;
                default: iRes = iNum;
            }

            txtResult.setText(String.valueOf(dec.format(iRes)));
        }


    }

    /**
     * Ajout du chiffre dans le champs de la calculatrice.
     * @param str
     */
    public void chiffreClick(String str) {
        double iStr, iRes = 0, spiNum, spiRes;
        if(txtNumber.getText().length() <= 6){
            str = txtNumber.getText().toString() + str;
            txtNumber.setText(str);

            if(spNum.getSelectedItem().toString().contains("g")){
                convertG(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString());
            }else if(spNum.getSelectedItem().toString().contains("soupe")){
                convertC(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString(), true);
            }else if(spNum.getSelectedItem().toString().contains("caf")){
                convertC(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString(), false);
            }else if(spNum.getSelectedItem().toString().contains("l") && spNum.getSelectedItem().toString().contains("l")){
                convertL(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString());
            }
        }else{
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Change unit", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    /**
     * Supprime la dernière entrée du champs de la calculatrice.
     */
    public void resetClick(){
        double iStr, iRes = 0, spiNum, spiRes;
        aNum = spNum.getSelectedItem().toString().split(" ");
        aRes = spResult.getSelectedItem().toString().split(" ");
        if (txtNumber.getText().length() > 1) {
            str = txtNumber.getText().toString().substring(0, txtNumber.getText().toString().length()-1);

            txtNumber.setText(str);

            if(spNum.getSelectedItem().toString().contains("g")){
                convertG(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString());
            }else if(spNum.getSelectedItem().toString().contains("soupe")){
                convertC(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString(), true);
            }else if(spNum.getSelectedItem().toString().contains("caf")){
                convertC(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString(), false);
            }else if(spNum.getSelectedItem().toString().contains("l") && spNum.getSelectedItem().toString().contains("l")){
                convertL(spNum.getSelectedItem().toString(), spResult.getSelectedItem().toString());
            }
        }else{
            txtNumber.setText("");
            txtResult.setText("");
        }
    }
}