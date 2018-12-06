package org.campusconnect.campusconnect.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.campusconnect.campusconnect.R;

public class SettingsFragment extends Fragment {
    @Nullable

    SwitchCompat switch_1,switch_2,switch_3,switch_4;

    boolean stateSwitch1,stateSwitch2,stateSwitch3,stateSwitch4;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final SharedPreferences preferences = this.getActivity().getSharedPreferences("PREFS",0);

        stateSwitch1=preferences.getBoolean("switch1",false);
        stateSwitch2=preferences.getBoolean("switch2",false);
        stateSwitch3=preferences.getBoolean("switch3",false);
        stateSwitch4=preferences.getBoolean("switch4",false);

        switch_1= rootView.findViewById(R.id.switch_1);
        switch_2= rootView.findViewById(R.id.switch_2);
        switch_3= rootView.findViewById(R.id.switch_3);
        switch_4= rootView.findViewById(R.id.switch_4);

        switch_1.setChecked(stateSwitch1);
        switch_2.setChecked(stateSwitch2);
        switch_3.setChecked(stateSwitch3);
        switch_4.setChecked(stateSwitch4);

        switch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateSwitch1=!stateSwitch1;
                switch_1.setChecked(stateSwitch1);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch1",stateSwitch1);
                editor.apply();
            }
        });

        switch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateSwitch2=!stateSwitch2;
                switch_2.setChecked(stateSwitch2);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch1",stateSwitch2);
                editor.apply();
            }
        });

        switch_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateSwitch3=!stateSwitch3;
                switch_3.setChecked(stateSwitch3);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch3",stateSwitch3);
                editor.apply();
            }
        });

        switch_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateSwitch4=!stateSwitch4;
                switch_4.setChecked(stateSwitch4);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("switch4",stateSwitch4);
                editor.apply();
            }
        });
        return rootView;
    }
}
