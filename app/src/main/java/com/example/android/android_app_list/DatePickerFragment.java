package com.example.android.android_app_list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import util.Helpers;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        MainActivity activityMain = (MainActivity) getActivity();

        final Calendar c = activityMain.calendar;

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        MainActivity activityMain = (MainActivity) getActivity();

        View alertaView = activityMain.alertaView;

        EditText lancamento = alertaView.findViewById(R.id.dialogDataLancamento);

        Calendar calendar = Helpers.buildCalendar(year, month, day);

        String dateFormat = Helpers.format(calendar, getString(R.string.formato_data));

        activityMain.calendar = calendar;

        lancamento.setText(dateFormat);

        lancamento.requestFocus();
    }
}