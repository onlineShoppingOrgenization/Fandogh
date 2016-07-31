package com.app.behin.fandogh.settings;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.behin.fandogh.R;
import com.app.behin.fandogh.adapter.Item;
import com.app.behin.fandogh.adapter.Product;

import java.util.ArrayList;
import java.util.List;


public class G extends Application {

    public static Context context;
    public static LayoutInflater inflater;
    public static Activity currentActivity;
    public static Fragment currentFragment;
    public static final Handler HANDLER = new Handler();
    public static  List<Item> itemList = new ArrayList<>();
    public static  List<Product> productList = new ArrayList<>();
    public static Typeface typeface;

    public static ViewGroup myLayout;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        typeface = Typeface.createFromAsset(G.context.getAssets(), "fonts/font_sanz.ttf");

        Item item = new Item("لیست خرید", R.drawable.menu_list_shopping);
        itemList.add(item);
        item = new Item("درباره ما", R.drawable.menu_about_us);
        itemList.add(item);
        item = new Item("تماس با ما", R.drawable.menu_contact_us);
        itemList.add(item);

    }
}
