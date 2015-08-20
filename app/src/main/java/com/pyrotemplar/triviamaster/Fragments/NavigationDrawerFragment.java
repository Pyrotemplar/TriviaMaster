package com.pyrotemplar.triviamaster.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyrotemplar.triviamaster.Activities.HighScoreActivity;
import com.pyrotemplar.triviamaster.Objects.NavigationItem;
import com.pyrotemplar.triviamaster.Adapters.NavigationItemAdapter;
import com.pyrotemplar.triviamaster.Activities.ProfilesActivity;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pyrotemplar on 8/14/2015.
 */
public class NavigationDrawerFragment extends android.support.v4.app.Fragment implements NavigationItemAdapter.ClickListener {

    public static final String PREF_FILE_NAME = "triviaMasterPref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    RecyclerView recyclerView;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationItemAdapter navigationItemAdapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(loadFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);
        navigationItemAdapter = new NavigationItemAdapter(getActivity(), getItem());
        navigationItemAdapter.setClickListener(this);
        recyclerView.setAdapter(navigationItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static List<NavigationItem> getItem(){
        List<NavigationItem> itemList = new ArrayList<>();

        int[] icons = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        int tempIcon = R.drawable.ic_launcher;
        String[] tittles= {"Home", "Hight Score", "Profiles", "Exit"};

        for (int i=0; i <tittles.length && i < icons.length; i++){
            NavigationItem item = new NavigationItem();

            item.setIconId(tempIcon);
            item.setTittle(tittles[i]);

            itemList.add(item);
        }
        return itemList;

    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        containerView = getActivity().findViewById(getId());
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, Boolean.toString(mUserLearnedDrawer));
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.commit();
    }

    public static String loadFromPreferences(Context context, String preferenceName, String defaultValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {

        switch (position){
            case 0:
                startActivity(new Intent(getActivity(), ProfilesActivity.class));
                break;
            case 1:
                mDrawerLayout.closeDrawers();
                break;
            case 2:
                startActivity(new Intent(getActivity(), HighScoreActivity.class));
                break;
            case 3:
                startActivity(new Intent(getActivity(), ProfilesActivity.class));
                break;
            case 4:
                getActivity().finish();
                break;
        }
    }
}
