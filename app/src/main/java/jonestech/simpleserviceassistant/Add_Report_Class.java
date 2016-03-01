package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Calendar;
import java.util.TimeZone;
/**
 * Created by Josiah on 12/12/2014.
 */
public class Add_Report_Class extends Activity {
    private InterstitialAd interstitial;
    DatePicker dP;
    Report report;
    Cursor c;
    Integer h, m, br, bo, t, rv, s, pc;
    String co, year, monthyear;
    Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    int currentYear, pioneeryear;
    int currentDayOfMonth;
    String month;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        themeUtils.onDialogCreateSetTheme(this);
        report = new Report(this);
        c = report.queryAll();
        setContentView(R.layout.add_report_dialog);
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-6509134419013533/6898417609");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public void save(View view){
        Integer i2 = 0;
        Report db = new Report(this);
        //Add content from layout
        EditText hour = (EditText)findViewById(R.id.hours);
        EditText mags = (EditText)findViewById(R.id.mags);
        EditText broch = (EditText)findViewById(R.id.broch);
        EditText book = (EditText)findViewById(R.id.books);
        EditText tract = (EditText)findViewById(R.id.tracts);
        EditText rvs = (EditText)findViewById(R.id.rvs);
        EditText study = (EditText)findViewById(R.id.studies);
        EditText comment = (EditText)findViewById(R.id.description);
        EditText pioncred = (EditText)findViewById(R.id.pc);
        dP = (DatePicker)findViewById(R.id.date_picker);
        if(dP.getMonth() == 0){month = getString(R.string.jan);}
        else if(dP.getMonth() == 1){month = getString(R.string.feb);}
        else if(dP.getMonth() == 2){month = getString(R.string.march);}
        else if(dP.getMonth() == 3){month = getString(R.string.april);}
        else if(dP.getMonth() == 4){month = getString(R.string.may);}
        else if(dP.getMonth() == 5){month = getString(R.string.june);}
        else if(dP.getMonth() == 6){month = getString(R.string.july);}
        else if(dP.getMonth() == 7){month = getString(R.string.aug);}
        else if(dP.getMonth() == 8){month = getString(R.string.sep);}
        else if(dP.getMonth() == 9){month = getString(R.string.oct);}
        else if(dP.getMonth() == 10){month = getString(R.string.nov);}
        else if(dP.getMonth() == 11){month = getString(R.string.dec);}
        else{month = ""+dP.getMonth();}
        currentDayOfMonth = dP.getDayOfMonth();
        Integer m1 = dP.getMonth()+1;
        currentYear = dP.getYear();
        if(dP.getMonth() > 7){
            pioneeryear = dP.getYear()+1;
        }else{
            pioneeryear = dP.getYear();
        }
        String date = month + " " + currentDayOfMonth + ", " + currentYear;
        year = pioneeryear+"";
        monthyear = m1+""+currentYear;
        long getid = Calendar.getInstance().getTimeInMillis();
        //get values from content in layout
        if(hour.getText().toString().isEmpty()){h = 0;}else
        if(!(hour.getText().toString().contains("."))){
            Integer i = Integer.parseInt(hour.getText().toString());
            h = i*3600000;
        }
        else{
            String hs = hour.getText().toString();
            String[] ha = hs.split("\\.");
            String harray = ha[0];
            try{
                String marray = ha[1];
                if(marray.equals("")) marray = "0";
                if(marray.length()==1&&!(marray.equals("0"))) marray = marray+"0";
                i2 = Integer.parseInt(marray)*60000;
            }catch (Exception ignore){
                //ignore
            }
            if(harray.equals("")) harray = "0";
            Integer i1 = Integer.parseInt(harray)*3600000;
            h = i1+i2;
        }
        if(mags.getText().toString().isEmpty()){m = 0;}else{m = Integer.parseInt(mags.getText().toString());}
        if(broch.getText().toString().isEmpty()){br = 0;}else{br = Integer.parseInt(broch.getText().toString());}
        if(book.getText().toString().isEmpty()){bo = 0;}else{bo = Integer.parseInt(book.getText().toString());}
        if(tract.getText().toString().isEmpty()){t = 0;}else{t = Integer.parseInt(tract.getText().toString());}
        if(rvs.getText().toString().isEmpty()){rv = 0;}else{rv = Integer.parseInt(rvs.getText().toString());}
        if(study.getText().toString().isEmpty()){s = 0;}else{s = Integer.parseInt(study.getText().toString());}
        if(comment.getText().toString().isEmpty()){co = "";}else{co = comment.getText().toString();}
        if(pioncred.getText().toString().isEmpty()){pc = 0;}else
        if(!(pioncred.getText().toString().contains("."))){
            Integer i = Integer.parseInt(pioncred.getText().toString());
            pc = i*3600000;
        }
        else {
            String p_c = pioncred.getText().toString();
            String[] p_c_a = p_c.split("\\.");
            String pc_h = p_c_a[0];
            try{
                String pc_m = p_c_a[1];
                if(pc_m.equals("")) pc_m = "0";
                if(pc_m.length()==1&&!(pc_m.equals("0"))) pc_m = pc_m+"0";
                i2 = Integer.parseInt(pc_m)*60000;
            }catch (Exception ignore){
                //ignore
            }
            if(pc_h.equals("")) pc_h = "0";
            Integer i1 = Integer.parseInt(pc_h)*3600000;
            pc = i1+i2;
        }
        if(hour.getText().toString().isEmpty() && mags.getText().toString().isEmpty() &&
                broch.getText().toString().isEmpty() && book.getText().toString().isEmpty() &&
                tract.getText().toString().isEmpty() && rvs.getText().toString().isEmpty() &&
                study.getText().toString().isEmpty() && pioncred.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.no_report), Toast.LENGTH_LONG).show();
        }else{
            Integer id = (int) (getid);
            db.Insert(id, date, monthyear, year, h, m, br, bo, t, rv, s, co, pc);
            c.requery();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            hour.setText("");
            mags.setText("");
            broch.setText("");
            book.setText("");
            tract.setText("");
            rvs.setText("");
            study.setText("");
            comment.setText("");
            pioncred.setText("");
            startActivity(intent);
            displayInterstitial();
        }
    }
    public void cancel(View view){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT", true);
        startActivity(i);
        displayInterstitial();
    }
    public void displayInterstitial(){
        if(interstitial.isLoaded()){
            interstitial.show();
        }
    }
}