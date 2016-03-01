package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.WindowManager;

/**
 * Created by Josiah on 2/27/2015.
 */
public class themeUtils {
    public static int cTheme;
    public final static int PURPLE = 0;
    public final static int BLUE = 1;
    public final static int GREEN = 2;
    public final static int RED = 3;
    public final static int ORANGE = 4;
    public static void changeToTheme(Activity activity, int theme){
        SharedPreferences.Editor ed = activity.getSharedPreferences(
                "THEMES_SELECTION", Context.MODE_PRIVATE).edit();
        ed.putInt("Theme_Color", theme).apply();
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences("THEMES_SELECTION", Context.MODE_PRIVATE);
        cTheme = prefs.getInt("Theme_Color", 0);
        switch (cTheme){
            default:
            case PURPLE:
                activity.setTheme(R.style.DefaultTheme);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_purple));
                }
                break;
            case BLUE:
                activity.setTheme(R.style.BlueTheme);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_blue));
                }
                break;
            case GREEN:
                activity.setTheme(R.style.GreenTheme);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_green));
                }
                break;
            case RED:
                activity.setTheme(R.style.RedTheme);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_red));
                }
                break;
            case ORANGE:
                activity.setTheme(R.style.OrangeTheme);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.dark_orange));
                }
                break;
        }
    }
    public static void onDialogCreateSetTheme(Activity activity){
        SharedPreferences prefs = activity.getSharedPreferences("THEMES_SELECTION", Context.MODE_PRIVATE);
        cTheme = prefs.getInt("Theme_Color", 0);
        switch (cTheme){
            default:
            case PURPLE:
                activity.setTheme(R.style.Dialog);
                break;
            case BLUE:
                activity.setTheme(R.style.BlueDialog);
                break;
            case GREEN:
                activity.setTheme(R.style.GreenDialog);
                break;
            case RED:
                activity.setTheme(R.style.RedDialog);
                break;
            case ORANGE:
                activity.setTheme(R.style.OrangeDialog);
                break;
        }
    }
}