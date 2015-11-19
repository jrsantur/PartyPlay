package com.project.workgroup.partyplay.views.activities;


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

import com.project.workgroup.partyplay.PartyApplication;
import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.injector.components.DaggerPartyComponent;
import com.project.workgroup.partyplay.injector.modules.ActivityModule;
import com.project.workgroup.partyplay.mvp.presenter.EventListPresenter;
import com.project.workgroup.partyplay.views.fragments.EventsFragment;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getName();

    public static Toolbar toolbar;
    public static ActionBarDrawerToggle toggle;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.nav_view) NavigationView navigationView;
    @Inject public static EventListPresenter eventListPresenter;


    public static boolean isCurrentFragmentChild = false;
    public static final int BACKPATTERN_BACK_ANYWHERE = 0;
    public static final int BACKPATTERN_BACK_TO_FIRST = 1;
    private int backPattern = BACKPATTERN_BACK_ANYWHERE;
    private int defaultSectionLoaded = 0;

    public  static  int section = 0;


    private List<Fragment> childFragmentStack;
    private List<String> childTitleStack;


    private View.OnClickListener toolbarToggleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.e(TAG , "click en el toggle");
            if(isCurrentFragmentChild) {
                //onHomeAsUpSelected();
                onBackPressed();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initializeDependencyInjector();
        setSupportActionBar(toolbar);


        //inilialize  variables
        childFragmentStack = new  LinkedList<>();
        childTitleStack = new LinkedList<>();


        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        selectItem("Eventos");
        //navigationView.setNavigationItemSelectedListener(this);

        /*
        if(!PrefUtils.isTosAccepted(this)){
            Intent i = new Intent(this, WelcomeActivity.class);
            startActivity(i);
            finish();
        }*/

        toggle.setToolbarNavigationClickListener(toolbarToggleListener);

    }


    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
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
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();

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
            // Handle the camera action
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



}
