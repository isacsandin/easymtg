package com.magicplayers.easymtg.ui;

import java.io.InputStreamReader;
import java.sql.SQLException;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.magicplayers.easymtg.R;
import com.magicplayers.easymtg.model.Card;
import com.magicplayers.easymtg.model.CardContainer;
import com.magicplayers.easymtg.model.DatabaseHelper;
import com.magicplayers.easymtg.ui.tabs.DummySectionFragment;
import com.magicplayers.easymtg.ui.tabs.ListViewFragment;
import com.magicplayers.easymtg.ui.tabs.SearchFragment;

public class MainActivity extends FragmentActivity implements TabListener {

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
     
//    	populateDataBase();
    }

	@SuppressWarnings("unused")
	private void populateDataBase() throws JsonSyntaxException, JsonIOException {
		Gson gson;    	
    	CardContainer response;
    	DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
    	try {
			helper.deleteAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	for(int i = 0; i <= 3; i++){
    		String url = "/com/magicplayers/easymtg/resources/AllCards-x."+i+".json";
        	InputStreamReader reader = new InputStreamReader(MainActivity.class.getResourceAsStream(url));
        	gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        	response = gson.fromJson(reader, CardContainer.class);
        	Log.w(" [BLABLA] ",response.cards.get(0).toString());
        	for(Card c:response.cards){
        			try {
						helper.addCard(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	}
    	}
    	helper.close();
	}

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new ListViewFragment();
                case 1:
                	return new SearchFragment();
                default:
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
    }


 	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}