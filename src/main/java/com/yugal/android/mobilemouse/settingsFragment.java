package com.yugal.android.mobilemouse;


import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class settingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        bindPreferenceSummaryToValue(findPreference("ip_addr"));
    }

    @Override
    public boolean onPreferenceChange(Preference pref,Object obj){

        String value=obj.toString();
        if(pref instanceof EditTextPreference){
            pref.setSummary(value);
        }
        return true;
    }

    public void bindPreferenceSummaryToValue(Preference preference){
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(),""));
    }



}
