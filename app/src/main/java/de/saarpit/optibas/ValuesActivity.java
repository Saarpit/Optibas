package de.saarpit.optibas;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;

import java.util.List;

import de.saarpit.optibas.core.Constants;
import de.saarpit.optibas.data.user.User;
import de.saarpit.optibas.data.user.UserViewModel;
import de.saarpit.optibas.fragments.GraphFragment;
import de.saarpit.optibas.fragments.ValuesFragment;
import de.saarpit.optibas.view.user.UserListAdapter;

public class ValuesActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private UserViewModel mUserViewModel;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_values);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUser = mUserViewModel.getUserById(1);

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                GraphFragment myFragment = new GraphFragment();

                View rootView = myFragment.getLayoutInflater().inflate(
                        R.layout.fragment_graph,
                        mViewPager,
                        false
                );

                GraphView myGraphView = (GraphView) rootView.findViewById(R.id.graphView);

                myFragment.addData(
                        myGraphView,
                        Constants.BASALVERTEILUNG_KLEINKINDER,
                        "Kleinkinder",
                        getResources().getColor(R.color.graphColorKleinkinder)
                    );

                myFragment.addData(
                        myGraphView,
                        Constants.BASALVERTEILUNG_KINDER,
                        "Kinder",
                        getResources().getColor(R.color.graphColorKinder)
                );

                myFragment.addData(
                        myGraphView,
                        Constants.BASALVERTEILUNG_ERWACHSENE,
                        "Erwachsene",
                        getResources().getColor(R.color.graphColorErwachsene)
                );

                return myFragment;
            } else {
                ValuesFragment myFragment = new ValuesFragment();

                View rootView = myFragment.getLayoutInflater().inflate(
                        R.layout.fragment_graph,
                        mViewPager,
                        false
                );



                return new ValuesFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
