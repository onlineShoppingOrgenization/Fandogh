package com.app.behin.fandogh.fragments;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenDeliveryType extends Fragment {

    static int order_hour;
    static int order_min;

    static boolean active_one_hour = true;
    //public boolean active_wish_hours = false;

    TextView txt_one_hour;
    TextView txt_wish_hours;

    TextView txt_adjusted_hour;
    public static String str_adjusted_hour;

    static String shoppingList = "";

    public static boolean btn_order_clicked = false;
public static String newshoppingList;
    public FragmenDeliveryType() {
// Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        G.currentFragment = this;

        if (FragmenProductList.GoToDeliveryFragment == true) {
            FragmenProductList.GoToDeliveryFragment = false;
            txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            Calendar c = Calendar.getInstance();
            order_hour = c.get(Calendar.HOUR) + 1;
            order_min = c.get(Calendar.MINUTE);
            showTime(order_hour, order_min);
        } else if (FragmentWishHours.set_wish_hour == true) {
            FragmentWishHours.set_wish_hour = false;
            active_one_hour = false;

            txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            Bundle bundle = this.getArguments();
            if (bundle != null) {
                order_hour = bundle.getInt("hour", 0);
                order_min = bundle.getInt("min", 0);
            }
            showTime(order_hour, order_min);
        } else {  // else if (FragmentWishHours.cancel_wish_hour == true)
            FragmentWishHours.cancel_wish_hour = false;
            if (active_one_hour) {
                txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else {
                txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
            showTime(order_hour, order_min);
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmenProductList nextFrag = new FragmenProductList();
                    G.currentFragment.getFragmentManager().beginTransaction()
                            .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_type, container, false);

        TextView txt_delivery = (TextView) view.findViewById(R.id.txt_delivery);
        txt_delivery.setTypeface(G.typeface);

        txt_adjusted_hour = (TextView) view.findViewById(R.id.txt_adjusted_hour);
        txt_adjusted_hour.setTypeface(G.typeface);

        txt_wish_hours = (TextView) view.findViewById(R.id.wish_hours);
        txt_wish_hours.setTypeface(G.typeface);

        txt_one_hour = (TextView) view.findViewById(R.id.one_hour);
        txt_one_hour.setTypeface(G.typeface);

        txt_wish_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                FragmentWishHours wish = new FragmentWishHours();

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, wish, "")
                        .addToBackStack(null)
                        .commit();
            }
        });

        txt_one_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                active_one_hour = true;
                txt_one_hour.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                txt_wish_hours.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                Calendar c = Calendar.getInstance();
                order_hour = c.get(Calendar.HOUR) + 1;
                order_min = c.get(Calendar.MINUTE);
                showTime(order_hour, order_min);
            }
        });

        Button btn_send_order = (Button) view.findViewById(R.id.btn_send_order);
        btn_send_order.setTypeface(G.typeface);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String list = bundle.getString("list", "");
            if (list != "") {
                shoppingList = list;
            }
        }
        btn_send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(G.productList.size() >= 1) {
                    newshoppingList = new String(shoppingList);
                    String deliveryText = "" + "لیست خرید: \n" + newshoppingList + "\n زمان تحویل: " + str_adjusted_hour + "";
                    sendSMS("09381674160", deliveryText);
                    Log.i("LOG", "shoppingList  " + deliveryText);
                }
                btn_order_clicked = true;
                FragmenProductList nextFrag = new FragmenProductList();

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    private void showTime(int hour, int min) {
        String format;
        if (hour == 0) {
            hour += 12;
            format = "قبل از ظهر";
        } else if (hour == 12) {
            format = "بعد از ظهر";
        } else if (hour > 12) {
            hour -= 12;
            format = "بعد از ظهر";
        } else {
            format = "قبل از ظهر";
        }
        String time = "زمان تحویل: " + " " + min + " : " + hour + " " + format;
        txt_adjusted_hour.setText(time);
        str_adjusted_hour = time;
    }

    private void sendSMS(String phoneNumber, String message) {
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(G.context, 0,
                new Intent(G.context, SmsSentReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(G.context, 0,
                new Intent(G.context, SmsDeliveredReceiver.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(G.context, "SMS sending failed...",Toast.LENGTH_SHORT).show();
        }

    }
    public class SmsDeliveredReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent arg1) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS delivered", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, "SMS not delivered", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }
    public class SmsSentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent arg1) {
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();

                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, "SMS generic failure", Toast.LENGTH_SHORT)
                            .show();

                    break;
                case SmsManager.RESULT_ERROR_NO_SERVICE:
                    Toast.makeText(context, "SMS no service", Toast.LENGTH_SHORT)
                            .show();

                    break;
                case SmsManager.RESULT_ERROR_NULL_PDU:
                    Toast.makeText(context, "SMS null PDU", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_RADIO_OFF:
                    Toast.makeText(context, "SMS radio off", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}