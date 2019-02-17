package com.example.melmaska.weatherapplication.app.addWeather;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.melmaska.weatherapplication.R;

public class AddWeatherFragment  extends DialogFragment implements AddWeatherContrator.View{

    private AddWeatherContrator.Presenter mPresenter;
    EditText mCity;
    ProgressBar mProgressBar;
    public static String TAG = "AddWeatherFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_add_city, null);
        mCity = view.findViewById(R.id.et_new_city);
        mProgressBar =  view.findViewById(R.id.pb_new_city);
        new AddWeatherPresenter(this);
        builder.setView(view)
                .setPositiveButton(R.string.add, null)
                .setNegativeButton(R.string.cancel, null);

        return builder.create();
    }



    @Override
    public void onStart() {

        super.onStart();
        final AlertDialog d = (AlertDialog) getDialog();

        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> mPresenter.addNewCity(mCity.getText().toString()));
        }
    }


    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
    @Override
    public void showErrorMessage(int stringRessource) {
        mCity.setError(getString(stringRessource));
    }

    @Override
    public void dismissDialog() {
        this.getDialog().dismiss();
    }

    @Override
    public void setPresenter(AddWeatherContrator.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
