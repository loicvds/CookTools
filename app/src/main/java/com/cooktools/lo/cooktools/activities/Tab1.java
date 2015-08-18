package com.cooktools.lo.cooktools.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cooktools.lo.cooktools.R;

import java.text.DecimalFormat;

/**
 * 1rst Tab de la vue Calculatrice: Calculatrice de Proportion.
 * Permet de transformer une quantité pour xPersonnes en yPersonnes.
 */

public class Tab1 extends Fragment {

    //TextField
    TextView txtNumber, txtResult, txtUnit1, txtUnit2;

    //Spinner
    Spinner spNum, spResult;

    //Bouton Digit
    Button button0, button1, button2,button3, button4, button5, button6, button7, button8, button9, buttonC, buttonPoint;

    //Bouton Unit
    Button buttonSolide, buttonMG, buttonCG, buttonG, buttonKG;
    Button buttonLiquide, buttonML, buttonCL, buttonDL, buttonL;
    Button buttonVolume, buttonCup, buttonSpoon, buttonPiece;

    //Variables
    String str ="";
    private boolean update = false, delete = false;
    DecimalFormat dec = new DecimalFormat("#.#");
    String[] aNum, aRes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

        txtNumber = (TextView) v.findViewById(R.id.txtNumber);
        txtResult = (TextView) v.findViewById(R.id.txtResult);

        initButtonDigit(v);
        initSpinner(v);

        return v;
    }

    /**
     * Initialisation des spinners de la calculatrice.
     * @param v
     */
    private void initSpinner(View v) {
        spNum = (Spinner) v.findViewById(R.id.spinner);
        spResult = (Spinner) v.findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.number_people, R.layout.spinner_center_item_2);
        spNum.setAdapter(adapter);
        spNum.setSelection(0);

        spResult.setAdapter(adapter);
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

        buttonC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetClick();
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
        double iStr, iRes = 0, spiNum, spiRes;
        aNum = spNum.getSelectedItem().toString().split(" ");
        aRes = spResult.getSelectedItem().toString().split(" ");
        if(!txtNumber.getText().equals("")){
            iStr = Double.valueOf(txtNumber.getText().toString()).doubleValue();
            spiNum = Double.valueOf(aNum[0]).doubleValue();
            spiRes = Double.valueOf(aRes[0]).doubleValue();
            iRes = (iStr/spiNum)*spiRes;

            if(iRes != 0){
                txtResult.setText(String.valueOf(dec.format(iRes)));
            }
        }
    }

    /**
     * Ajout du chiffre dans le champs de la calculatrice.
     * @param str
     */
    public void chiffreClick(String str) {
        double iStr, iRes = 0, spiNum, spiRes;
        aNum = spNum.getSelectedItem().toString().split(" ");
        aRes = spResult.getSelectedItem().toString().split(" ");
        if(txtNumber.getText().length() <= 6){
            if(txtNumber.getText().toString().equals("") && str.equals(".")){
                str = "0.";
            }
            str = txtNumber.getText() + str;
            iStr = Double.valueOf(str).doubleValue();
            spiNum = Double.valueOf(aNum[0]).doubleValue();
            spiRes = Double.valueOf(aRes[0]).doubleValue();
            iRes = (iStr / spiNum) * spiRes;

            txtNumber.setText(str);
            if(iRes != 0){
                txtResult.setText(String.valueOf(dec.format(iRes)));
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

            iStr = Double.valueOf(str).doubleValue();
            spiNum = Double.valueOf(aNum[0]).doubleValue();
            spiRes = Double.valueOf(aRes[0]).doubleValue();
            iRes = (iStr/spiNum)*spiRes;

            txtNumber.setText(str);
            if(iRes != 0){
                txtResult.setText(String.valueOf(dec.format(iRes)));
            }
        }else{
            txtNumber.setText("");
            txtResult.setText("");
        }
    }
}
