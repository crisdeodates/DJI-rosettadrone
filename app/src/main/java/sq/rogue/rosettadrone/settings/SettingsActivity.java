package sq.rogue.rosettadrone.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import sq.rogue.rosettadrone.R;
import sq.rogue.rosettadrone.settings.drone.DroneSettingsFragment;

public class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {
    private static final String TAG = SettingsActivity.class.getSimpleName();

    /**
     * @param savedInstanceState Any saved state we are carrying over into the new activity instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SettingsFragment.PAGE_ID);
        if (fragment == null) {
            fragment = new SettingsFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.pref_container, fragment, SettingsFragment.PAGE_ID);
        ft.commit();


//        // Display the fragment as the main content.
//        getFragmentManager().beginTransaction()
//                .replace(android.R.id.content, new SettingsFragment())
//                .commit();
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen preferenceScreen) {
//        Log.d(TAG, "callback called to attach the preference sub screen");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        DroneSettingsFragment fragment = new DroneSettingsFragment();
        Bundle args = new Bundle();
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, preferenceScreen.getKey());
        fragment.setArguments(args);
        ft.replace(R.id.pref_container, fragment, preferenceScreen.getKey());
        ft.addToBackStack(preferenceScreen.getKey());
        ft.commit();
        return true;
    }
}