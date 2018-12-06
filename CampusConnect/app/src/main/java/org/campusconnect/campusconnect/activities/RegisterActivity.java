package org.campusconnect.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.campusconnect.campusconnect.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";
    private final AppCompatActivity activity = RegisterActivity.this;
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initListeners();

        // ALL SQLITE METHODS HAVE BEEN DEPRECATED IN FAVOR OF FIREBASE AUTHENTICATION!
        //initObjects();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView);
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);
        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = findViewById(R.id.textInputEditTextConfirmPassword);
        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink);
        mStatusTextView = findViewById(R.id.status);
        mDetailTextView = findViewById(R.id.detail);
    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            signIn(textInputEditTextEmail.getText().toString(), textInputEditTextPassword.getText().toString());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void sendEmailVerification() {
        // Disable button
       // findViewById(R.id.verifyEmailButton).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button
                        //findViewById(R.id.verifyEmailButton).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(RegisterActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;

        String name = textInputEditTextName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            textInputEditTextName.setError("Required.");
            valid = false;
        } else {
            textInputEditTextName.setError(null);
        }

        String email = textInputEditTextEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            textInputEditTextEmail.setError("Required.");
            valid = false;
        } else {
            textInputEditTextEmail.setError(null);
        }

        String password = textInputEditTextPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            textInputEditTextPassword.setError("Required.");
            valid = false;
        } else {
            textInputEditTextPassword.setError(null);
        }

        String confirmpassword = textInputEditTextConfirmPassword.getText().toString();
        if (TextUtils.isEmpty( confirmpassword)) {
            textInputEditTextConfirmPassword.setError("Required.");
            valid = false;
        } else {
            textInputEditTextConfirmPassword.setError(null);
        }
        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getDisplayName()));
            findViewById(R.id.appCompatButtonRegister).setVisibility(View.GONE);
            findViewById(R.id.nestedScrollView).setVisibility(View.GONE);
            //findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
            //findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);
            findViewById(R.id.appCompatButtonRegister).setVisibility(View.VISIBLE);
            findViewById(R.id.nestedScrollView).setVisibility(View.VISIBLE);
            //findViewById(R.id.signedInButtons).setVisibility(View.GONE);
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent mainIntent = new Intent(activity, MainActivity.class);
                            startActivity(mainIntent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText(R.string.auth_failed);
                        }
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.appCompatButtonRegister) {
            createAccount(textInputEditTextEmail.getText().toString(), textInputEditTextPassword.getText().toString());
            //postDataToSQLite();
        } else if (i == R.id.appCompatTextViewLoginLink) {
            // Navigate to LoginActivity
            Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intentLogin);
            //} else if (i == R.id.signOutButton) {
            //    signOut();
            //} else if (i == R.id.verifyEmailButton) {
            //    sendEmailVerification();
        }
    }
}
