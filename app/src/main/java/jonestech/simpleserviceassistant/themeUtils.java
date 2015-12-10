package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Intent;
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
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity){
        switch (cTheme){
            default:
            case PURPLE:
                activity.setTheme(R.style.DefaultTheme);
                break;
            case BLUE:
                activity.setTheme(R.style.BlueTheme);
                break;
            case GREEN:
                activity.setTheme(R.style.GreenTheme);
                break;
            case RED:
                activity.setTheme(R.style.RedTheme);
                break;
            case ORANGE:
                activity.setTheme(R.style.OrangeTheme);
                break;
        }
    }
    public static void onDialogCreateSetTheme(Activity activity){
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