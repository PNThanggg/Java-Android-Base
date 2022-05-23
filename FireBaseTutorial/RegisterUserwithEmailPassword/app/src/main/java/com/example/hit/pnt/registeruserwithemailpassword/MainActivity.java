package com.example.hit.pnt.registeruserwithemailpassword;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hit.pnt.registeruserwithemailpassword.fragment.ChangePasswordFragment;
import com.example.hit.pnt.registeruserwithemailpassword.fragment.FavoriteFragment;
import com.example.hit.pnt.registeruserwithemailpassword.fragment.HistoryFragment;
import com.example.hit.pnt.registeruserwithemailpassword.fragment.HomeFragment;
import com.example.hit.pnt.registeruserwithemailpassword.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public final static int MY_REQUEST_CODE = 10;

    private final static int FRAGMENT_HOME = 0;
    private final static int FRAGMENT_FAVORITE = 1;
    private final static int FRAGMENT_HISTORY = 2;
    private final static int FRAGMENT_PROFILE = 3;
    private final static int FRAGMENT_CHANGE_PASSWORD = 4;

    private int currentFragment = FRAGMENT_HOME;
    private ProfileFragment profileFragment = new ProfileFragment();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ImageView imgAvatar;
    private TextView txtName, txtEmail;

    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();

                if(intent == null) {
                    return;
                }

                Uri uri = intent.getData();
                profileFragment.setmUri(uri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    profileFragment.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_close, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);

        imgAvatar = navigationView.getHeaderView(0).findViewById(R.id.imageAvatar);
        txtName = navigationView.getHeaderView(0).findViewById(R.id.textUsername);
        txtEmail = navigationView.getHeaderView(0).findViewById(R.id.textGmail);

        showUserInformation();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if(currentFragment != FRAGMENT_HOME) {
                    replaceFragment(new HomeFragment());
                    currentFragment = FRAGMENT_HOME;
                }

                break;

            case R.id.navigation_favorite:
                if(currentFragment != FRAGMENT_FAVORITE) {
                    replaceFragment(new FavoriteFragment());
                    currentFragment = FRAGMENT_FAVORITE;

                }

                break;

            case R.id.navigation_history:
                if(currentFragment != FRAGMENT_HISTORY) {
                    replaceFragment(new HistoryFragment());
                    currentFragment = FRAGMENT_HISTORY;
                }

                break;

            case R.id.navigation_sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
                finish();

                break;

            case R.id.navigation_profile:
                if(currentFragment != FRAGMENT_PROFILE) {
                    replaceFragment(profileFragment);
                    currentFragment = FRAGMENT_PROFILE;
                }

                break;

            case R.id.navigation_change_password:
                if(currentFragment != FRAGMENT_CHANGE_PASSWORD) {
                    replaceFragment(new ChangePasswordFragment());
                    currentFragment = FRAGMENT_CHANGE_PASSWORD;
                }

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        // bấm nút quay lại: nếu navigation đang mở thì đóng, còn không thì thoát app
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    public void showUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // -> trả về user đang login
        if(user == null) {
            return;
        }

        String userName = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        if(user == null) {
            txtName.setVisibility(View.GONE);
        } else {
            txtName.setVisibility(View.VISIBLE);
            txtName.setText(userName);
        }

        txtEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.image).into(imgAvatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // -> cho phép sử dụng
                openGallery();
            }
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
}