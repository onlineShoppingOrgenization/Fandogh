package com.app.behin.fandogh.fragments;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWishHours extends Fragment {

    private TimePicker timePicker;

    public static boolean set_wish_hour = false;
    public static boolean cancel_wish_hour = false;

    public FragmentWishHours() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        G.currentFragment = this;
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    FragmenProductList.GoToDeliveryFragment = true;
                    FragmenDeliveryType nextFrag = new FragmenDeliveryType();
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
        View view = inflater.inflate(R.layout.fragment_wish_hours, container, false);

        Resources system = Resources.getSystem();
        int hourNumberPickerId = system.getIdentifier("hour", "id", "android");
        int minuteNumberPickerId = system.getIdentifier("minute", "id", "android");
        int ampmNumberPickerId = system.getIdentifier("amPm", "id", "android");

        timePicker = (TimePicker) view.findViewById(R.id.timePicker1);
        NumberPicker hourNumberPicker = (NumberPicker) timePicker.findViewById(hourNumberPickerId);
        NumberPicker minuteNumberPicker = (NumberPicker) timePicker.findViewById(minuteNumberPickerId);
        NumberPicker ampmNumberPicker = (NumberPicker) timePicker.findViewById(ampmNumberPickerId);

        setDividerColor(hourNumberPicker, getResources().getColor(R.color.colorAccent));
        setDividerColor(minuteNumberPicker, getResources().getColor(R.color.colorAccent));
        setDividerColor(ampmNumberPicker, getResources().getColor(R.color.colorAccent));

        TextView txt_wish_hour = (TextView) view.findViewById(R.id.txt_wish_hour);
        txt_wish_hour.setTypeface(G.typeface);

        Button btn_set_hour = (Button) view.findViewById(R.id.btn_set_hour);
        btn_set_hour.setTypeface(G.typeface);

        btn_set_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_wish_hour = true;

                timePicker.clearFocus();

                FragmenDeliveryType nextFrag = new FragmenDeliveryType();

                Bundle bundle = new Bundle();
                bundle.putInt("hour", timePicker.getCurrentHour());
                bundle.putInt("min", timePicker.getCurrentMinute());

                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button btn_cancel_hour = (Button) view.findViewById(R.id.btn_cancel_hour);
        btn_cancel_hour.setTypeface(G.typeface);

        btn_cancel_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel_wish_hour = true;

                FragmenDeliveryType next = new FragmenDeliveryType();
                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, next, "")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
