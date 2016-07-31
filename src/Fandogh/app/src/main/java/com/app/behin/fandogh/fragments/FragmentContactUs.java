package com.app.behin.fandogh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.behin.fandogh.activities.ActivityMain;
import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;
import com.app.behin.fandogh.adapter.ItemsAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContactUs extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        G.currentFragment=this;

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    ItemsAdapter.lastID = 0;
                    FragmenProductList nextFrag= new FragmenProductList();
                    G.currentFragment.getFragmentManager().beginTransaction()
                            .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                            .addToBackStack(null)
                            .commit();

                    return true;

                }

                return false;
            }
        });
    }

    public FragmentContactUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_contact_us, container, false);

        ActivityMain.toolbar_title.setText("تماس با ما");

        TextView txt1=(TextView)view.findViewById(R.id.txt_contact_us1);
        TextView txt2=(TextView)view.findViewById(R.id.txt_contact_us2);
        TextView txt3=(TextView)view.findViewById(R.id.txt_contact_us3);
        TextView txt4=(TextView)view.findViewById(R.id.txt_contact_us4);
        TextView txt5=(TextView)view.findViewById(R.id.txt_contact_us5);
        TextView txt6=(TextView)view.findViewById(R.id.txt_contact_us6);
        TextView txt7=(TextView)view.findViewById(R.id.txt_contact_us7);

        txt1.setTypeface(G.typeface);
        txt2.setTypeface(G.typeface);
        txt3.setTypeface(G.typeface);
        txt4.setTypeface(G.typeface);
        txt5.setTypeface(G.typeface);
        txt6.setTypeface(G.typeface);
        txt7.setTypeface(G.typeface);

        return view;
    }
}
