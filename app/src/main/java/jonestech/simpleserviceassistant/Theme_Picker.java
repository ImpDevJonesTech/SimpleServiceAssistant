package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
/**
 * Created by Josiah on 2/27/2015.
 */
public class Theme_Picker extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("THEMES_SELECTION", MODE_PRIVATE);
        int theme = prefs.getInt("Theme_Color", 0);
        themeUtils.onActivityCreateSetTheme(this, theme);
        setContentView(R.layout.theme_picker);
        findViewById(R.id.default_color).setOnClickListener(this);
        findViewById(R.id.blue_color).setOnClickListener(this);
        findViewById(R.id.green_color).setOnClickListener(this);
        findViewById(R.id.red_color).setOnClickListener(this);
        findViewById(R.id.orange_color).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        switch (v.getId()){
            case R.id.default_color:
                themeUtils.changeToTheme(this, themeUtils.PURPLE);
                startActivity(i);
                break;
            case R.id.blue_color:
                themeUtils.changeToTheme(this, themeUtils.BLUE);
                startActivity(i);
                break;
            case R.id.green_color:
                themeUtils.changeToTheme(this, themeUtils.GREEN);
                startActivity(i);
                break;
            case R.id.red_color:
                themeUtils.changeToTheme(this, themeUtils.RED);
                startActivity(i);
                break;
            case R.id.orange_color:
                themeUtils.changeToTheme(this, themeUtils.ORANGE);
                startActivity(i);
                break;
        }
    }
}