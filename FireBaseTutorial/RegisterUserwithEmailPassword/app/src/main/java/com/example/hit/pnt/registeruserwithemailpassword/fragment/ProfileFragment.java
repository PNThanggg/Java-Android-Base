package com.example.hit.pnt.registeruserwithemailpassword.fragment;

import static com.example.hit.pnt.registeruserwithemailpassword.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hit.pnt.registeruserwithemailpassword.MainActivity;
import com.example.hit.pnt.registeruserwithemailpassword.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileFragment extends Fragment {

    private ImageView imgAvatar;
    private EditText edtFullName, edtEmail;

    private Uri mUri;
    private MainActivity mainActivity;
    private ProgressDialog progressDialog;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgAvatar = view.findViewById(R.id.img_avatar);
        edtFullName = view.findViewById(R.id.edt_fullname);
        edtEmail = view.findViewById(R.id.edt_email);

        Button btUpdateProfile = view.findViewById(R.id.btUpdateProfile);
        Button btUpdateEmail = view.findViewById(R.id.btUpdateEmail);

        mainActivity = (MainActivity) getActivity();
        progressDialog = new ProgressDialog(getActivity());

        setUserInformation();

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateProfile();
            }
        });

        btUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateEmail();
            }
        });

        return view;
    }

    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        edtFullName.setText(user.getDisplayName());
        edtEmail.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.image).into(imgAvatar);
    }

    private void onClickRequestPermission() {
        if(mainActivity == null) {
            return;
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { // từ android 6 trở xuống thì gọi luôn hàm openGalley();
            mainActivity.openGallery();
            return;
        }

        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mainActivity.openGallery();
        } else {
            String [] permisstions = {Manifest.permission.READ_EXTERNAL_STORAGE};

            getActivity().requestPermissions(permisstions, MY_REQUEST_CODE);
        }
    }

    public void setBitmapImageView(Bitmap bitmap) {
        imgAvatar.setImageBitmap(bitmap);
    }

    private void onClickUpdateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            return;
        }

        progressDialog.show();

        String strFullName = edtFullName.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(mUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Update profile success", Toast.LENGTH_SHORT).show();
                            // Cập nhật ở menu
                            mainActivity.showUserInformation();
                        }
                    }
                });
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void onClickUpdateEmail() {
        String newEmail = edtEmail.getText().toString().trim();

        progressDialog.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "User email address updated.", Toast.LENGTH_SHORT).show();
                            mainActivity.showUserInformation();
                        }
                    }
                });
    }
}