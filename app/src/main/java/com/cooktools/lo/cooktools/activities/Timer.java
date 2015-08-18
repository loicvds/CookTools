package com.cooktools.lo.cooktools.activities;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cooktools.lo.cooktools.modules.MyTimePickerDialog;
import com.cooktools.lo.cooktools.R;
import com.cooktools.lo.cooktools.modules.TimePicker;

/**
 * Activité contenant les différents minuteurs.
 */

public class Timer extends Fragment {
    public static final String TAG = "timers";

    private long START = 200000;
    private final long INTERVAL = 500;

    private long start1 = START;
    private long start2 = START;
    private long start3 = START;
    private long start4 = START;
    private long start5 = START;

    private int nbChr;
    private TextView timer;


    //Buttons
    Button btn1, btn2, btn3, btn4, btn5;
    Button btnSet1, btnSet2, btnSet3, btnSet4, btnSet5;

    //Chrono
    TextView txtChr1, txtChr2, txtChr3, txtChr4, txtChr5;
    Chr chr1, chr2, chr3, chr4, chr5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_timers, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChr();
        initBtn();
    }


    /**
     * Initialise les Chronomètres.
     */
    private void initChr() {
        txtChr1 = (TextView) getActivity().findViewById(R.id.textChr1);
        txtChr2 = (TextView) getActivity().findViewById(R.id.textChr2);
        txtChr3 = (TextView) getActivity().findViewById(R.id.textChr3);
        txtChr4 = (TextView) getActivity().findViewById(R.id.textChr4);
        txtChr5 = (TextView) getActivity().findViewById(R.id.textChr5);
    }

    /**
     * Initialise les Boutons et ajout les Listeners.
     */
    private void initBtn() {
        btn1 = (Button) getActivity().findViewById(R.id.button1);
        btn2 = (Button) getActivity().findViewById(R.id.button2);
        btn3 = (Button) getActivity().findViewById(R.id.button3);
        btn4 = (Button) getActivity().findViewById(R.id.button4);
        btn5 = (Button) getActivity().findViewById(R.id.button5);

        btnSet1 = (Button) getActivity().findViewById(R.id.btnSet1);
        btnSet2 = (Button) getActivity().findViewById(R.id.btnSet2);
        btnSet3 = (Button) getActivity().findViewById(R.id.btnSet3);
        btnSet4 = (Button) getActivity().findViewById(R.id.btnSet4);
        btnSet5 = (Button) getActivity().findViewById(R.id.btnSet5);

        initCountDown();

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btn1.getText().toString().equals("Start")) {
                    chr1 = new Chr(start1, INTERVAL, txtChr1, 1);
                    chr1.start();
                    btn1.setText("Stop");
                } else {
                    chr1.onPause();
                    btn1.setText("Start");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(btn2.getText().toString().equals("Start")){
                    chr2 = new Chr(start2, INTERVAL, txtChr2, 2);
                    chr2.start();
                    btn2.setText("Stop");
                }else{
                    chr2.onPause();
                    btn2.setText("Start");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btn3.getText().toString().equals("Start")) {
                    chr3 = new Chr(start3, INTERVAL, txtChr3, 3);
                    chr3.start();
                    btn3.setText("Stop");
                } else {
                    chr3.onPause();
                    btn3.setText("Start");
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btn4.getText().toString().equals("Start")) {
                    chr4 = new Chr(start4, INTERVAL, txtChr4, 4);
                    chr4.start();
                    btn4.setText("Stop");
                } else {
                    chr4.onPause();
                    btn4.setText("Start");
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btn5.getText().toString().equals("Start")) {
                    chr5 = new Chr(start5, INTERVAL, txtChr5, 5);
                    chr5.start();
                    btn5.setText("Stop");
                } else {
                    chr5.onPause();
                    btn5.setText("Start");
                }
            }
        });

        btnSet1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbChr = 1;
                timer = txtChr1;
                showPicker(v);
            }
        });

        btnSet2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbChr = 2;
                timer = txtChr2;
                showPicker(v);
            }
        });

        btnSet3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbChr = 3;
                timer = txtChr3;
                showPicker(v);
            }
        });

        btnSet4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbChr = 4;
                timer = txtChr4;
                showPicker(v);
            }
        });

        btnSet5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nbChr = 5;
                timer = txtChr5;
                showPicker(v);
            }
        });
    }

    /**
     * Initialise les Timers pour chacun des chronos.
     */
    private void initCountDown() {
        chr1 = new Chr(START, INTERVAL, txtChr1, 1);
        chr2 = new Chr(START, INTERVAL, txtChr2, 2);
        chr3 = new Chr(START, INTERVAL, txtChr3, 3);
        chr4 = new Chr(START, INTERVAL, txtChr4, 4);
        chr5 = new Chr(START, INTERVAL, txtChr5, 5);
    }

    public class Chr extends CountDownTimer {

        TextView txtView;
        long timeleft;
        int nb;

        public Chr(long startTime, long interval, TextView txtView, int nb) {
            super(startTime, interval);
            this.txtView = txtView;
            this.nb = nb;
        }

        public void onPause(){
            switch (nb){
                case 1: start1 = timeleft;
                case 2: start2 = timeleft;
                case 3: start3 = timeleft;
                case 4: start4 = timeleft;
                case 5: start5 = timeleft;
            }
            cancel();
        }

        @Override
        public void onFinish() {
            switch (nb){
                case 1:
                    onNotify();
                    btn1.setText("Start");
                    start1 = 180000;
                    break;
                case 2:
                    onNotify();
                    btn2.setText("Start");
                    start2 = 180000;
                    break;
                case 3:
                    onNotify();
                    btn3.setText("Start");
                    start3 = 180000;
                    break;
                case 4:
                    onNotify();
                    btn4.setText("Start");
                    start4 = 180000;
                    break;
                case 5:
                    onNotify();
                    btn5.setText("Start");
                    start5 = 180000;
                    break;
            }

            txtView.setText("FINI !");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timeleft = millisUntilFinished;
            long seconds = millisUntilFinished / 1000;
            long minutes = (seconds) / 60;
            long hours = (minutes) / 60;
            txtView.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds % 60));
        }

        public void onNotify() {
            NotificationManager mNotificationManager = (NotificationManager) getActivity()
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            String title = "";
            int icon = R.drawable.ic_menu_check;
            title= "FINISH";

            long when = System.currentTimeMillis();
            Notification notification = new Notification(icon, title, when);

            String contentTitle = "Cooktools Timer";
            String contentText = "Click to remove notification";

            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            Intent notificationIntent = new Intent(getActivity(), Chr.class);

            PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0,
                    notificationIntent, 0);
            notification.setLatestEventInfo(getActivity(), contentTitle, contentText,
                    contentIntent);
            mNotificationManager.notify(0, notification);
        }
    }

    public void showPicker(View v){
        MyTimePickerDialog mTimePicker = new MyTimePickerDialog(getActivity(),R.style.MyAlertDialogTheme ,new MyTimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
                switch (nbChr){
                    case 1:
                        start1 = (hourOfDay*60*60*1000)+(minute*60*1000)+(seconds*1000);
                        break;
                    case 2:
                        start2 = (hourOfDay*60*60*1000)+(minute*60*1000)+(seconds*1000);
                        break;
                    case 3:
                        start3 = (hourOfDay*60*60*1000)+(minute*60*1000)+(seconds*1000);
                        break;
                    case 4:
                        start4 = (hourOfDay*60*60*1000)+(minute*60*1000)+(seconds*1000);
                        break;
                    case 5:
                        start5 = (hourOfDay*60*60*1000)+(minute*60*1000)+(seconds*1000);
                        break;
                }
                timer.setText(String.format("%02d", hourOfDay)+
                        ":" + String.format("%02d", minute) +
                        ":" + String.format("%02d", seconds));
            }
        }, 0, 0, 0, true);
        mTimePicker.show();
    }
}
