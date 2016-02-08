package kms.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import kms.prototype.Fragment.TimeLineFragment;
import kms.prototype.Fragment.RecommendFragment;
import kms.prototype.Fragment.MyPageFragment;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;

    public final static int FRAGMENT_RECOMMEND = 0;
    public final static int FRAGMENT_TIMELINE = 1;
    public final static int FRAGMENT_MYPAGE = 2;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.custom_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        // 서버에서 기본 정보를 받아 저장.
        //Network.getInstance().HttpPostData();
    }



    // 옵션 설정 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Tab 이벤트 핸들러
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        private Fragment getFragment(int idx) {
            Fragment newFragment = null;
            switch (idx) {
                case FRAGMENT_RECOMMEND:
                    newFragment = new RecommendFragment();
                    break;
                case FRAGMENT_TIMELINE:
                    newFragment = new TimeLineFragment();
                    break;
                case FRAGMENT_MYPAGE:
                    newFragment = new MyPageFragment();
                    break;
                default:
                    Log.d("getFragment", "no Fragment");
                    break;
            }
            return newFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case FRAGMENT_RECOMMEND:
                    return getString(R.string.title_section1);
                case FRAGMENT_TIMELINE:
                    return getString(R.string.title_section2);
                case FRAGMENT_MYPAGE:
                    return getString(R.string.title_section3);
            }
            return null;
        }
    }
}


