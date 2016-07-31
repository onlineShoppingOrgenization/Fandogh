package com.app.behin.fandogh.adapter;

import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.behin.fandogh.settings.G;
import com.app.behin.fandogh.R;
import com.app.behin.fandogh.activities.ActivityMain;
import com.app.behin.fandogh.fragments.FragmenProductList;
import com.app.behin.fandogh.fragments.FragmentAboutUs;
import com.app.behin.fandogh.fragments.FragmentContactUs;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {

    private List<Item> itemList;

    public static int lastID;

    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_name);
            image = (ImageView) view.findViewById(R.id.img_item);

            Typeface type = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");
            name.setTypeface(type);
        }
    }

    public ItemsAdapter(List<Item> itemList) {
        this.itemList = itemList;
        lastID = 0;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_slider_menu, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = ActivityMain.recyclerView.getChildLayoutPosition(view);
                Item item = G.itemList.get(itemPosition);

                if(itemPosition == 0 && lastID != 0){
                    lastID = 0;
                    FragmenProductList nextFrag = new FragmenProductList();

                    G.currentFragment.getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                            .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                            .addToBackStack(null)
                            .commit();
                }else if(itemPosition == 1 && lastID != 1){
                    lastID = 1;
                    FragmentAboutUs nextFrag = new FragmentAboutUs();

                    G.currentFragment.getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                            .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                            .addToBackStack(null)
                            .commit();
                }
                else if(itemPosition == 2 && lastID != 2){
                    lastID = 2;
                    FragmentContactUs nextFrag = new FragmentContactUs();

                    G.currentFragment.getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left)
                            .replace(R.id.relativelayout_for_fragment, nextFrag,"")
                            .addToBackStack(null)
                            .commit();
                }
                ActivityMain.drawer.closeDrawer(GravityCompat.END);

            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.image.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}