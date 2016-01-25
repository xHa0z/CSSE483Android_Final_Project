package edu.rosehulman.weny.comewithme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Login_fragment.OnLoginListener, GoogleApiClient.OnConnectionFailedListener{

    private static final int REQUEST_CODR_GOOGLE_LOGIN = 1;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Firebase.setAndroidContext(this);
        }

        GoogleSignInOptions gso =  new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        if(firebase.getAuth()== null || isExpired(firebase.getAuth())){
            switchToLoginFragment();
        }
    }

    private boolean isExpired(AuthData authData) {
        return (System.currentTimeMillis() / 1000) >= authData.getExpires();
    }
    class MyAuthResultHandler implements Firebase.AuthResultHandler
    {

        @Override
        public void onAuthenticated(AuthData authData) {
            //switchToPasswordFragment(Constants.FIREBASE_URL + "/users/" + authData.getUid());
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            showLoginError(firebaseError.getMessage());
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(Constants.TAG, "onConnectionFailed: " + connectionResult.getErrorMessage());
    }

    @Override
    public void onGoogleLogin() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, REQUEST_CODR_GOOGLE_LOGIN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODR_GOOGLE_LOGIN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                String emailAddress = account.getEmail();
                getGoogleOAuthToken(emailAddress);
            }

        }
    }
    private void getGoogleOAuthToken(final String emailAddress) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            String errorMessage = null;

            @Override
            protected String doInBackground(Void... params) {
                String token = null;
                try {
                    String scope = "oauth2:profile email";
                    token = GoogleAuthUtil.getToken(MainActivity.this, emailAddress, scope);
                } catch (IOException transientEx) {
                /* Network or server error */
                    errorMessage = "Network error: " + transientEx.getMessage();
                } catch (UserRecoverableAuthException e) {
                /* We probably need to ask for permissions, so start the intent if there is none pending */
                    Intent recover = e.getIntent();
                    startActivityForResult(recover, MainActivity.REQUEST_CODR_GOOGLE_LOGIN);
                } catch (GoogleAuthException authEx) {
                    errorMessage = "Error authenticating with Google: " + authEx.getMessage();
                }
                return token;
            }
            @Override
            protected void onPostExecute(String token) {
                Log.d("FPK", "onPostExecute, token" + token);
                if(token != null){
                    onGoogleLoginWithToken(token);
                }else{
                    showLoginError(errorMessage);
                }
            }
        };
        task.execute();
    }


    private void onGoogleLoginWithToken(String oAuthToken) {
        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        //firebase.authWithPassword(email, password, new MyAuthResultHandler());
        firebase.authWithOAuthToken("google", oAuthToken, new MyAuthResultHandler());
    }

    private void switchToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, new Login_fragment(), "Login");
        ft.commit();
    }



    private void showLoginError(String message) {
        Login_fragment loginFragment = (Login_fragment) getSupportFragmentManager().findFragmentByTag("Login");
        loginFragment.onLoginError(message);
    }
}
