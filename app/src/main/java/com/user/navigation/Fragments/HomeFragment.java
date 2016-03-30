package com.user.navigation.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.user.navigation.R;

public class HomeFragment extends Fragment {

    EditText editText;
    String input = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        editText = (EditText) view.findViewById(R.id.edittext);
        editText.requestFocus();
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        return view;
    }

    private void showToast() {
        input = editText.getText().toString();
        if (input != null) {
            String data = editText.getText().toString();
            Toast.makeText(getActivity(), "Hello " + input, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Error ! ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
