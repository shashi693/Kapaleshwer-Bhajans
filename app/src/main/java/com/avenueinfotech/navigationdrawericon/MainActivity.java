package com.avenueinfotech.navigationdrawericon;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by suken on 07-01-2017.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;
//    @BindView(R.id.drawer_recyclerView) RecyclerView drawerRecyclerView;
    private Drawer.Result navigationDrawerLeft;
    private Drawer.Result getNavigationDrawerRight;
    private AccountHeader.Result headerNavigationLeft;
    private AccountHeader.Result headerNavigationRight;
    private int mPositionClicked;
    private ViewPager mViewPager;
    private int mItemDrawerSelected;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity.this, "OnCheckedChangeListener: "+( b ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mtoolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(false);

//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
//                drawerLayout.setDrawerListener(drawerToggle);
//                drawerToggle.syncState();

//        List<String> rows= new ArrayList<>();
//        rows.add("option 1");
//        rows.add("opton 2: A longer option");
//        rows.add("option 3: The longest option of all");
//
//        DrawerAdapter drawerAdapter = new DrawerAdapter(rows);
//        drawerRecyclerView.setAdapter(drawerAdapter);
//        drawerRecyclerView.setHasFixedSize(true);
//        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        headerNavigationRight = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.kapaleshwer)
                .addProfiles(
                        new ProfileDrawerItem().withName("श्री कपालेश्वर").withEmail("कपालेश्वर भजनी मंडळ").withIcon(getResources().getDrawable(R.drawable.palkhi))

                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
//                        Toast.makeText(MainActivity.this, "onProfileChanged: "+iProfile.getName(), Toast.LENGTH_SHORT).show();
                        headerNavigationLeft.setBackgroundRes(R.drawable.palkhi);
                        return false;
                    }
                })
                .build();

        //End Right
        getNavigationDrawerRight = new Drawer()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.RIGHT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationRight)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        mViewPager.setCurrentItem(i);


                        Fragment frag = null;
                        mItemDrawerSelected = i;

                        if(i == 0){ // ALL CARS
                            frag = new PatraFragment();
                        }
                        else if(i == 1){ // LUXURY CAR
                            frag = new AparadFragment();
                        }
                        else if(i == 2){ // SPORT CAR
                            frag = new KalabhiravFragment();
                        }
                        else if(i == 3){ // OLD CAR
                            frag = new RudraastakFragment();
                        }
//                        else if(i == 4){ // POPULAR CAR
//                            frag = new PopularCarFragment();
//                        }
//                        else if(i == 5){ // LUXURY CAR
//                            frag = new DarbareFragment();
//                        }
//                        else if(i == 6){ // SPORT CAR
//                            frag = new DigambaraFragment();
//                        }
//                        else if(i == 7){ // OLD CAR
//                            frag = new OldCarFragment();
//                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();

//                        mToolbar.setTitle( ((PrimaryDrawerItem) iDrawerItem).getName() );
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        Toast.makeText(MainActivity.this, "onItemLongClick: "+i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();
        getNavigationDrawerRight.addItem(new PrimaryDrawerItem().withName("पत्र").withIcon(getResources().getDrawable(R.drawable.shiva)));
        getNavigationDrawerRight.addItem(new PrimaryDrawerItem().withName("अपराध शामा").withIcon(getResources().getDrawable(R.drawable.shiva)));
        getNavigationDrawerRight.addItem(new PrimaryDrawerItem().withName("श्री कालभैरव अष्टक").withIcon(getResources().getDrawable(R.drawable.shiva)));
        getNavigationDrawerRight.addItem(new PrimaryDrawerItem().withName("रूद्राअष्टक").withIcon(getResources().getDrawable(R.drawable.shiva)));
//                .addDrawerItems(
//                        new SecondaryDrawerItem().withName("Carros Esportives").withIcon(getResources().getDrawable(R.drawable.bmw)),
//                        new SecondaryDrawerItem().withName("Audi Delecus").withIcon(getResources().getDrawable(R.drawable.flayer)),
//                        new SecondaryDrawerItem().withName("Ferrai dex").withIcon(getResources().getDrawable(R.drawable.sport)),
//                        new SecondaryDrawerItem().withName("Mercedes dex").withIcon(getResources().getDrawable(R.drawable.mercedes))
//                )
//                .withDisplayBelowToolbar(true)
//                .withActionBarDrawerToggleAnimated(true)
//                .withDrawerGravity(Gravity.END)
//                .withSavedInstance(savedInstanceState)
//                .withSelectedItem(-1)
////                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
////                    @Override
////                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
////                        Toast.makeText(MainActivity.this, "onItemClick: "+i, Toast.LENGTH_SHORT).show();
////                    }
////                })
//                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        Toast.makeText(MainActivity.this, "onItemLongClick: "+i, Toast.LENGTH_SHORT).show();
//                        return false;
//                    }
//                })
//                .build();

        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withThreeSmallProfileImages(true)
                .withHeaderBackground(R.drawable.pradosh)
                .addProfiles(
                        new ProfileDrawerItem().withName("|| श्री कपालेश्वर ||").withEmail("कपालेश्वर भजनी मंडळ").withIcon(getResources().getDrawable(R.drawable.kapaleshwer))

                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean b) {
//                        Toast.makeText(MainActivity.this, "onProfileChanged: "+iProfile.getName(), Toast.LENGTH_SHORT).show();
                        headerNavigationLeft.setBackgroundRes(R.drawable.pradosh);
                        return false;
                    }
                })
                .build();


        //navigation Drawer
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(mtoolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(headerNavigationLeft)
//                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
//                    @Override
//                    public boolean onNavigationClickListener(View view) {
//                        return false;
//                    }
//                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        mViewPager.setCurrentItem(i);


                        Fragment frag = null;
                        mItemDrawerSelected = i;

                        if(i == 0){ // ALL CARS
                            frag = new CarFragment();
                        }
                        else if(i == 1){ // LUXURY CAR
                            frag = new LuxuryCarFragment();
                        }
                        else if(i == 2){ // SPORT CAR
                            frag = new SportCarFragment();
                        }
                        else if(i == 3){ // OLD CAR
                            frag = new OldCarFragment();
                        }
                        else if(i == 4){ // POPULAR CAR
                            frag = new PopularCarFragment();
                        }
                        else if(i == 5){ // LUXURY CAR
                            frag = new DarbareFragment();
                        }
                        else if(i == 6){ // SPORT CAR
                            frag = new DigambaraFragment();
                        }
                        else if(i == 7){ // OLD CAR
                            frag = new BhamshfaketaFragment();
                        }else if(i == 8){ // OLD CAR
                            frag = new KanekundaleFragment();
                        }else if(i == 9){ // OLD CAR
                            frag = new KhadavvajeeFragment();
                        }

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
                        ft.commit();

//                        mToolbar.setTitle( ((PrimaryDrawerItem) iDrawerItem).getName() );
                    }
                })
//                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
//                        for (int count = 0, tam = navigationDrawerLeft.getDrawerItems().size();count < tam; count++){
//                            if (count == mPositionClicked && mPositionClicked <= 3){
//                                PrimaryDrawerItem aux = (PrimaryDrawerItem) navigationDrawerLeft.getDrawerItems().get(count);
//                                aux.setIcon(getResources().getDrawable( getCorretcDrawerIcon( count, false)));
//                                break;
//                            }
//                        }
//
//                        if(i <= 3){
//                            ((PrimaryDrawerItem) iDrawerItem).setIcon(getResources().getDrawable( getCorretcDrawerIcon( i, true)));
//                        }
//
//                        mPositionClicked = i;
//                        navigationDrawerLeft.getAdapter().notifyDataSetChanged();
//                    }
//                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick: "+i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();

            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("श्री गजानना").withIcon(getResources().getDrawable(R.drawable.ganesha)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("मातेचा जोगवा ").withIcon(getResources().getDrawable(R.drawable.jagdamba)));
       //     navigationDrawerLeft.addItem(new DividerDrawerItem());
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("असा कसा भोळा शंकर").withIcon(getResources().getDrawable(R.drawable.shiva)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("हे भोळा शंकर").withIcon(getResources().getDrawable(R.drawable.shiva)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("रूप भरा मेरे दिल मे भोलेनाथ का").withIcon(getResources().getDrawable(R.drawable.shiva)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("दरबारी आले नाथ गुरूचा").withIcon(getResources().getDrawable(R.drawable.karanji)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("दिगंबरा दिगंबरा").withIcon(getResources().getDrawable(R.drawable.karanji)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("भषम फेकता").withIcon(getResources().getDrawable(R.drawable.maharaj)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("कानी कुंडले").withIcon(getResources().getDrawable(R.drawable.maharaj)));
            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("खडावं वाजे पाऊल औमटले").withIcon(getResources().getDrawable(R.drawable.maharaj)));
//        //     navigationDrawerLeft.addItem(new DividerDrawerItem());
//            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Ferrai dex").withIcon(getResources().getDrawable(R.drawable.ferrai)));
//            navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Mercedes dex").withIcon(getResources().getDrawable(R.drawable.mercedes)));
//            navigationDrawerLeft.addItem(new SectionDrawerItem().withName("configuration"));
//            navigationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notification").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));
//            navigationDrawerLeft.addItem(new ToggleDrawerItem().withName("News").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));


    }

//    private int getCorretcDrawerIcon(int position, boolean isSelected) {
//        switch (position){
//            case 0:
//                return ( isSelected ? R.drawable.dagaduseth : R.drawable.main);
//            case 1:
//                return ( isSelected ? R.drawable.ferrai: R.drawable.bmw);
//            case 3:
//                return ( isSelected ? R.drawable.flayer : R.drawable.mercedes);
//        }
//        return (0);
//    }


    boolean twice;
    @Override
    public void onBackPressed() {

        if (twice == true) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        twice = true;


        Toast.makeText(MainActivity.this, "पुन्हा एकदा प्रेस करा", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;

            }
        }, 6000);

    }
}
