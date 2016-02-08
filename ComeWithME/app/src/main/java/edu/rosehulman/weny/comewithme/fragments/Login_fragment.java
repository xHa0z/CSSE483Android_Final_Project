package edu.rosehulman.weny.comewithme.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

import edu.rosehulman.weny.comewithme.R;


public class Login_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBE
    private View mProgressSpinner;
    private boolean mLoggingIn;
    private SignInButton mGoogleSignInButton;
    private OnLoginListener mListener;

    private final String TAG = "login";

    public Login_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoggingIn = false;
        Log.d(TAG, "111");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login_fragment, container, false);
        mProgressSpinner = rootView.findViewById(R.id.login_progress);
        mGoogleSignInButton = (SignInButton) rootView.findViewById(R.id.google_sign_in_button);

        mGoogleSignInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        mGoogleSignInButton.setSize(SignInButton.SIZE_WIDE);
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithGoogle();
            }
        });

        return rootView;

    }

    private void loginWithGoogle() {
        if (mLoggingIn) {
            return;
        }

        showProgress(true);
        mLoggingIn = true;
        mListener.onGoogleLogin();
    }

    public void onLoginError(String message) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getActivity().getString(R.string.login_error))
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();

        showProgress(false);
        mLoggingIn = false;
    }
    private void showProgress(boolean show) {
        mProgressSpinner.setVisibility(show ? View.VISIBLE : View.GONE);
       // mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mGoogleSignInButton.setVisibility(show ? View.GONE : View.VISIBLE);
    }



    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLoginListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnLoginListener {
        void onGoogleLogin();

    }
}
