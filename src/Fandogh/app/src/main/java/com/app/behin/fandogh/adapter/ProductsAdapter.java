package com.app.behin.fandogh.adapter;

/**
 * Created by najme_sa on 24/07/2016.
 */
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;
import com.app.behin.fandogh.fragments.FragmenProductList;
import com.app.behin.fandogh.fragments.FragmentEditProduct;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {

    private List<Product> productsList;

    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, amount, desc;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            amount = (TextView) view.findViewById(R.id.amount);
            desc = (TextView) view.findViewById(R.id.desc);

            Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");
            name.setTypeface(type);
            amount.setTypeface(type);
            desc.setTypeface(type);
        }
    }

    public ProductsAdapter(List<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = FragmenProductList.recyclerView.getChildLayoutPosition(view);
                Product item = G.productList.get(itemPosition);

                FragmentEditProduct nextFrag= new FragmentEditProduct();

                Bundle bundle = new Bundle();
                bundle.putInt("itemPos", itemPosition);
                bundle.putString("name",item.getName());
                bundle.putString("amount", item.getAmount());
                bundle.putString("desc", item.getDesc());
                nextFrag.setArguments(bundle);

                G.currentFragment.getFragmentManager().beginTransaction()
                        .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productsList.get(position);
        holder.name.setText(product.getName());
        holder.amount.setText(product.getAmount());
        holder.desc.setText(product.getDesc());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}