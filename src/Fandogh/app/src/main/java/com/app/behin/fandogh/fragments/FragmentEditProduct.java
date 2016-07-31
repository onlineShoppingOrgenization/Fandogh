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
public class FragmentEditProduct extends Fragment {

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

    public FragmentEditProduct() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_product, container, false);

        TextView txt_edit_good=(TextView)view.findViewById(R.id.txt_edit_good);
        txt_edit_good.setTypeface(G.typeface);

        TextView txt_edit_name=(TextView)view.findViewById(R.id.txt_edit_name);
        TextView txt_edit_amount=(TextView)view.findViewById(R.id.txt_edit_amount);
        TextView txt_edit_desc=(TextView)view.findViewById(R.id.txt_edit_desc);

        txt_edit_name.setTypeface(G.typeface);
        txt_edit_amount.setTypeface(G.typeface);
        txt_edit_desc.setTypeface(G.typeface);

        final EditText edt_edit_name = (EditText) view.findViewById(R.id.edt_edit_name);
        final EditText edt_edit_amount = (EditText) view.findViewById(R.id.edt_edit_amount);
        final EditText edt_edit_desc = (EditText) view.findViewById(R.id.edt_edit_desc);

        edt_edit_name.setTypeface(G.typeface);
        edt_edit_amount.setTypeface(G.typeface);
        edt_edit_desc.setTypeface(G.typeface);

        final Bundle bundle = this.getArguments();
        if(bundle != null) {
            String name = bundle.getString("name", "");
            String amount = bundle.getString("amount", "");
            String desc = bundle.getString("desc", "");

            edt_edit_name.setText(name);
            edt_edit_amount.setText(amount);
            edt_edit_desc.setText(desc);
        }

        Button btn_product_remove=(Button) view.findViewById(R.id.btn_product_remove);
        btn_product_remove.setTypeface(G.typeface);

        btn_product_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bundle != null && !FragmenProductList.addItem) {
                    int itemPos = bundle.getInt("itemPos", 0);
                    G.productList.remove(itemPos);
                }
                FragmenProductList nextFrag= new FragmenProductList();

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button btn_edit=(Button)view.findViewById(R.id.btn_product_edit);
        btn_edit.setTypeface(G.typeface);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bundle != null && !FragmenProductList.addItem) {
                    int itemPos = bundle.getInt("itemPos", 0);
                    G.productList.remove(itemPos);
                }

                FragmenProductList.addItem = true;
                FragmenProductList nextFrag= new FragmenProductList();
                Bundle bundle = new Bundle();
                bundle.putString("name", edt_edit_name.getText().toString());
                bundle.putString("amount", edt_edit_amount.getText().toString());
                bundle.putString("desc", edt_edit_desc.getText().toString());
                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
