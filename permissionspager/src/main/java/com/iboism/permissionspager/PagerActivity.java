package com.iboism.permissionspager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity implements RequestPermissionFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private CustomViewPager mViewPager;

    public static final String GRANTED_ARRAY_INTENT_KEY = "kGrantedPermissionsArray";
    public static final String DENIED_ARRAY_INTENT_KEY = "kDeniedPermissionsArray";
    public static final String PERMISSION_ARRAY_INTENT_KEY = "kPermissionArray";
    public static final String RATIONALE_ARRAY_INTENT_KEY = "kRationaleArray";

    private String[] permissions;
    private String[] rationales;
    private ArrayList<String> grantedPermissions = new ArrayList<>();
    private ArrayList<String> deniedPermissions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        permissions = getIntent().getExtras().getStringArray(PERMISSION_ARRAY_INTENT_KEY);
        rationales = getIntent().getExtras().getStringArray(RATIONALE_ARRAY_INTENT_KEY);
        
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPagingEnabled(false);

    }

    @Override
    public void permissionGranted(String permission) {
        grantedPermissions.add(permission);
        if(mViewPager.getCurrentItem() == permissions.length - 1){
            returnResults();
        }

        this.mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }

    @Override
    public void permissionDenied(String permission) {
        deniedPermissions.add(permission);
        if(mViewPager.getCurrentItem() == permissions.length - 1){
            returnResults();
        }

        this.mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
    }

    private void returnResults(){
        Intent resultsIntent = new Intent();
        Bundle b = new Bundle();
        b.putStringArray(GRANTED_ARRAY_INTENT_KEY, grantedPermissions.toArray(new String[0]));
        b.putStringArray(DENIED_ARRAY_INTENT_KEY, deniedPermissions.toArray(new String[0]));
        resultsIntent.putExtras(b);
        setResult(Activity.RESULT_OK, resultsIntent);
        finish();
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return RequestPermissionFragment.newInstance(permissions[position],rationales[position]);
        }

        @Override
        public int getCount() {
            return (permissions != null) ? permissions.length : 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position++;
        }
    }


}
