package com.project.workgroup.partyplay.views.activities;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.project.workgroup.partyplay.PartyApplication;
import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.injector.components.DaggerPartyComponent;
import com.project.workgroup.partyplay.injector.modules.ActivityModule;
import com.project.workgroup.partyplay.mvp.presenter.EventListPresenter;
import com.project.workgroup.partyplay.utils.PrefUtils;
import com.project.workgroup.partyplay.views.fragments.EventsFragment;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getName();

    public  Toolbar toolbar;
    public static ActionBarDrawerToggle toggle;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;

    public static ImageView  imgEventDeDeatails;
    @Inject public EventListPresenter eventListPresenter;


    public boolean isCurrentFragmentChild = false;
    public static final int BACKPATTERN_BACK_ANYWHERE = 0;
    public static final int BACKPATTERN_BACK_TO_FIRST = 1;
    private int backPattern = BACKPATTERN_BACK_ANYWHERE;
    private int defaultSectionLoaded = 0;

    public  static  int section = 0;


    private List<Fragment> fragmentStack;
    private List<String> titleStack;


    private View.OnClickListener toolbarToggleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.e(TAG , "click en el toggle");
            if(isCurrentFragmentChild) {
                //onHomeAsUpSelected();
                toggle.setDrawerIndicatorEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                setTitle("Eventos");
                onBackPressed();



            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!PrefUtils.isTosAccepted(this)){
            startActivity(new Intent(this, XiaoMaIntroDemo.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //imgEventDeDeatails = (ImageView) findViewById(R.id.image_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeDependencyInjector();
        setSupportActionBar(toolbar);

        //inilialize  variables
        fragmentStack = new  LinkedList<>();
        titleStack = new LinkedList<>();


        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (navigationView != null) {
           // navigationView.setNavigationItemSelectedListener(this);
            setupDrawerContent(navigationView);
        }

        toggle.setToolbarNavigationClickListener(toolbarToggleListener);

    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            String title = menuItem.getTitle().toString();
            selectItem(title);
            return true;
        });

    }
    private void selectItem(String title){
        Bundle args = new Bundle();
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(title.equals("Eventos")){
            args.putString(EventsFragment.ARG_SECTION_TITLE, title);
            fragment = EventsFragment.newInstance(title);
            fragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
            section = 1;
        }

        drawer.closeDrawers();
        setTitle(title);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if(!isCurrentFragmentChild){
            switch (backPattern) {
                case BACKPATTERN_BACK_TO_FIRST:
                    if (section!=1){

                    }
                    else {
                        super.onBackPressed();
                    }

                    break;
            }
        }else{
            Fragment fragment= EventsFragment.newInstance("Eventos");
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main_content, fragment).commit();
        }
    }

    public void setBackPattern(int backPattern) {
        this.backPattern = backPattern;
    }


    private void  initializeDependencyInjector(){
        PartyApplication app = (PartyApplication)getApplication();
        DaggerPartyComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(app.getAppComponent())
                .build().inject(this);
        Log.e(TAG, "Se inicio las dependencias");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            item.setChecked(true);
            //setFragment(EventsFragment.newInstance() , item.getTitle().toString());
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*
    @Override
    public void onElementClick(int position, View v, ImageView characterImageView) {
        //intent para la nueva actividad
    }
    */


    public void setDefaultSectionLoaded(int sectionNumber) {
        defaultSectionLoaded = sectionNumber;
    }


    @SuppressWarnings("deprecation")
    public boolean networkConnection(){

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;

    }


    public void setFragment(Fragment fragment, String title){

        if(!isCurrentFragmentChild){
            if(fragmentStack.size()==0){
                fragmentStack.add(fragment);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_content, fragment)
                        .commit();
                titleStack.add(title);
                setTitle(title);
            }
        }
    }



}
