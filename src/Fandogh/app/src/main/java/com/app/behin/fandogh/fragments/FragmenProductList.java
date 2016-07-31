package com.app.behin.fandogh.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.MyRecyclerScroll;
import com.app.behin.fandogh.activities.ActivityMain;
import com.app.behin.fandogh.adapter.Product;
import com.app.behin.fandogh.adapter.ProductsAdapter;
import com.app.behin.fandogh.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmenProductList extends Fragment {

    public static RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;

    FloatingActionButton fab;

    public static boolean addItem = false;

    public static boolean GoToDeliveryFragment = false;

    @Override
    public void onResume() {
        G.currentFragment = this;
        super.onResume();

        if (FragmenDeliveryType.btn_order_clicked == true) {
            FragmenDeliveryType.btn_order_clicked = false;
            if (G.productList.size() >= 1) {
                G.productList.clear();
                Toast.makeText(G.context, "سفارش شما ثبت گردید", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(G.context, "کالایی برای سفارش انتخاب نگردیده است", Toast.LENGTH_SHORT).show();
            }
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    TextView title = new TextView(G.context);
                    title.setText("خروج از برنامه");
                    title.setPadding(15, 15, 22, 15);
                    title.setGravity(Gravity.RIGHT);
                    title.setTextColor(getResources().getColor(R.color.colorPrimary));
                    title.setTextSize(20);
                    title.setTypeface(G.typeface);

                    AlertDialog dialog = new AlertDialog.Builder(getActivity()).setMessage("آیا از برنامه خارج می شوید؟")
                            .setCustomTitle(title)
                            .setPositiveButton("خروج", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
// continue with delete
                                    getFragmentManager().beginTransaction().remove(G.currentFragment).commit();
                                    G.currentActivity.finish();
                                }
                            })
                            .setNegativeButton("انصراف", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
// do nothing
                                }
                            })
                            .show();
                    TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setTypeface(G.typeface);
                    textView.setTextSize(16);

                    Button btnPositive = dialog.getButton(Dialog.BUTTON_POSITIVE);
                    btnPositive.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnPositive.setTypeface(G.typeface);
                    btnPositive.setTextSize(16);

                    Button btnNegative = dialog.getButton(Dialog.BUTTON_NEGATIVE);
                    btnNegative.setTextColor(getResources().getColor(R.color.colorPrimary));
                    btnNegative.setTypeface(G.typeface);
                    btnNegative.setTextSize(16);

                    int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    } else {
                        View divider = dialog.findViewById(dividerId);
                        divider.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                    return true;
                }
                return false;
            }
        });
    }


    public FragmenProductList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ActivityMain.toolbar_title.setText("فندق");

        Button btn_continue = (Button) view.findViewById(R.id.btn_continue);
        btn_continue.setTypeface(G.typeface);

        TextView header_name = (TextView) view.findViewById(R.id.header_name);
        header_name.setTypeface(G.typeface);
        TextView header_amount = (TextView) view.findViewById(R.id.header_amount);
        header_amount.setTypeface(G.typeface);
        TextView header_desc = (TextView) view.findViewById(R.id.header_desc);
        header_desc.setTypeface(G.typeface);

        TextView txt_shopping = (TextView) view.findViewById(R.id.txt_shopping);
        txt_shopping.setTypeface(G.typeface);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmenDeliveryType nextFrag = new FragmenDeliveryType();
                Bundle bundle = new Bundle();

                String stringOfList = "";
                for (int i = 0; i < G.productList.size(); i++) {
                    Product product = G.productList.get(i);
                    stringOfList += "آیتم " + i + ": " + product.getName() + ", " + product.getAmount() + ", " + product.getDesc() + "\n";
                }
                bundle.putString("list", stringOfList);
                nextFrag.setArguments(bundle);

                GoToDeliveryFragment = true;

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                        .addToBackStack(null)
                        .commit();
            }
        });

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        Animation animation = AnimationUtils.loadAnimation(G.context, R.anim.simple_grow);
        fab.startAnimation(animation);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmenAddProduct nextFrag = new FragmenAddProduct();
                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag, "")
                        .addToBackStack(null)
                        .commit();

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setOnScrollListener(new MyRecyclerScroll() {
            @Override
            public void show() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void hide() {
                fab.animate().translationY(fab.getHeight() + 10).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });

        productsAdapter = new ProductsAdapter(G.productList);

        if (addItem) {
            addItem = false;
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                String name = bundle.getString("name", "");
                String amount = bundle.getString("amount", "");
                String desc = bundle.getString("desc", "");

                if (!name.equals("")) {
                    Product product = new Product(name, amount, desc);
                    G.productList.add(product);

                    productsAdapter.notifyDataSetChanged();
                }
            }
        }


        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(G.context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productsAdapter);

        return view;

    }
}
