package jonestech.simpleserviceassistant;
import android.app.Application;
/**
 * Created by Josiah on 1/2/2015.
 */
public class myApplication extends Application {
    @Override
    public void onCreate(){
        try{
            Class.forName("android.os.AsyncTask");
        }catch (Throwable ignore){}
        super.onCreate();
    }
}