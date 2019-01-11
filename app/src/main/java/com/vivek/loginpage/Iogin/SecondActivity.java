 package com.vivek.loginpage.Iogin;

 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.support.v7.widget.Toolbar;
 import android.view.View;

 import com.google.firebase.auth.FirebaseAuth;
 import com.mikepenz.materialdrawer.AccountHeader;
 import com.mikepenz.materialdrawer.AccountHeaderBuilder;
 import com.mikepenz.materialdrawer.Drawer;
 import com.mikepenz.materialdrawer.DrawerBuilder;
 import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
 import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
 import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
 import com.mikepenz.materialdrawer.model.interfaces.IProfile;
 import com.vivek.loginpage.R;

 public class SecondActivity extends AppCompatActivity {

     private FirebaseAuth firebaseAuth;

     Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("HOME");

        // Create the AccountHeader
       AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("YEF").withEmail("yefindia@gmail.com").withIcon(getResources().getDrawable(R.drawable.logoyef))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

//Now create your drawer and pass the AccountHeader.Result
       /* new DrawerBuilder()
                .withAccountHeader(headerResult)
                //additional Drawer setup as shown above
                //...
                .build();*/

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Profile");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("YEF HOME");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Charity First Center");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Notification");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Contact Us");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("LOGOUT");

//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1, item2, item3, item4, item5, item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        switch (position){
                            case 1: startActivity(new Intent(SecondActivity.this, ProfileActivity.class)); // we may do intent here to go to new activity
                            case 2: break;
                            case 3: break;
                            case 4: break;
                            case 5: break;
                            case 6: firebaseAuth.signOut();
                                     finish();
                                     startActivity(new Intent(SecondActivity.this, MainActivity.class));
                        }
                        return true;
                    }
                })
                .build();

    }
}
