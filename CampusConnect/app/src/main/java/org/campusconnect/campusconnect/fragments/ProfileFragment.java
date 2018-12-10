package org.campusconnect.campusconnect.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.campusconnect.campusconnect.R;

public class ProfileFragment extends Fragment {
    private TextView profileUserName;
    private TextView profileEmail;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile,container,false);
        profileUserName = rootView.findViewById(R.id.profilename);
        profileEmail = rootView.findViewById(R.id.profileemail);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        profileUserName.setText(user.getDisplayName());
        profileEmail.setText(user.getEmail());
        return rootView;
    }
}
