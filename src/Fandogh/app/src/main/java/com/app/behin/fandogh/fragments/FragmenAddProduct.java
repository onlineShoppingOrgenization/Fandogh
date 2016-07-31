package com.app.behin.fandogh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenAddProduct extends Fragment {
    public FragmenAddProduct() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        G.currentFragment = this;

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
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);

        TextView txx_add_g00d = (TextView) view.findViewById(R.id.txt_add_good);
        txx_add_g00d.setTypeface(G.typeface);

        TextView txt_add_name = (TextView) view.findViewById(R.id.txt_add_name);
        TextView txt_add_amount = (TextView) view.findViewById(R.id.txt_add_amount);
        TextView txt_add_desc = (TextView) view.findViewById(R.id.txt_add_desc);

        txt_add_name.setTypeface(G.typeface);
        txt_add_amount.setTypeface(G.typeface);
        txt_add_desc.setTypeface(G.typeface);

        final EditText edt_add_name = (EditText) view.findViewById(R.id.edt_add_name);
        final EditText edt_add_amount = (EditText) view.findViewById(R.id.edt_add_amount);
        final EditText edt_add_desc = (EditText) view.findViewById(R.id.edt_add_desc);

        edt_add_name.setTypeface(G.typeface);
        edt_add_amount.setTypeface(G.typeface);
        edt_add_desc.setTypeface(G.typeface);

        Button btn_product_registration = (Button) view.findViewById(R.id.btn_product_registration);
        btn_product_registration.setTypeface(G.typeface);


        final Bundle bundle = this.getArguments();
        if (bundle != null) {
            String name = bundle.getString("name", "");
            String amount = bundle.getString("amount", "");
            String desc = bundle.getString("desc", "");

            edt_add_name.setText(name);
            edt_add_amount.setText(amount);
            edt_add_desc.setText(desc);
        }

        btn_product_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenProductList.addItem = true;

                FragmenProductList nextFrag = new FragmenProductList();

                Bundle bundle = new Bundle();
                bundle.putString("name", edt_add_name.getText().toString());
                bundle.putString("amount", edt_add_amount.getText().toString());
                bundle.putString("desc", edt_add_desc.getText().toString());

                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

}
