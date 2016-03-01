package jonestech.simpleserviceassistant;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * Created by Josman on 3/1/2016.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Toolbar mActionBar;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        addPreferencesFromResource(R.xml.preferences);
        mActionBar.setTitle(getTitle());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themeString = sharedPreferences.getString("theme", "0");
        int themeInt = Integer.parseInt(themeString);
        if(themeInt == 0){
            mActionBar.setBackgroundColor(getResources().getColor(R.color.material_purple));
        }
        if(themeInt == 1){
            mActionBar.setBackgroundColor(getResources().getColor(R.color.material_blue));
        }
        if(themeInt == 2){
            mActionBar.setBackgroundColor(getResources().getColor(R.color.material_green));
        }
        if(themeInt == 3){
            mActionBar.setBackgroundColor(getResources().getColor(R.color.material_red));
        }
        if(themeInt == 4){
            mActionBar.setBackgroundColor(getResources().getColor(R.color.material_orange));
        }
    }
    @Override
    public void setContentView(int layoutResID) {
        ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.settings_activity, new LinearLayout(this), false);
        mActionBar = (Toolbar) contentView.findViewById(R.id.action_bar);
        mActionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ViewGroup contentWrapper = (ViewGroup) contentView.findViewById(R.id.content_wrapper);
        LayoutInflater.from(this).inflate(layoutResID, contentWrapper, true);
        getWindow().setContentView(contentView);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("theme")){
            String themeString = sharedPreferences.getString("theme", "0");
            int themeInt = Integer.parseInt(themeString);
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(themeInt == 0){
                themeUtils.changeToTheme(this, themeUtils.PURPLE);
                mActionBar.setBackgroundColor(getResources().getColor(R.color.dark_purple));
                startActivity(i);
            }
            if(themeInt == 1){
                themeUtils.changeToTheme(this, themeUtils.BLUE);
                mActionBar.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                startActivity(i);
            }
            if(themeInt == 2){
                themeUtils.changeToTheme(this, themeUtils.GREEN);
                mActionBar.setBackgroundColor(getResources().getColor(R.color.dark_green));
                startActivity(i);
            }
            if(themeInt == 3){
                themeUtils.changeToTheme(this, themeUtils.RED);
                mActionBar.setBackgroundColor(getResources().getColor(R.color.dark_red));
                startActivity(i);
            }
            if(themeInt == 4){
                themeUtils.changeToTheme(this, themeUtils.ORANGE);
                mActionBar.setBackgroundColor(getResources().getColor(R.color.dark_orange));
                startActivity(i);
            }
        }
        if(key.equals("pioneer_credits_toggle")){
            if(!sharedPreferences.getBoolean("pioneer_credits_toggle", false)){
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("pioneer_credits", true).apply();
            }
            if(sharedPreferences.getBoolean("pioneer_credits_toggle", false)){
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("pioneer_credits", false).apply();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}