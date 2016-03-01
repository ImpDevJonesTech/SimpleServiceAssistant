package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
/**
 * Created by Josiah on 12/22/2014.
 */
public class Edit_Report_Class extends Activity {
    private InterstitialAd interstitial;
    Integer mid;
    Report report;
    Cursor c;
    Integer h, m, br, bo, t, rv, s, pc;
    String co, date;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        themeUtils.onDialogCreateSetTheme(this);
        report = new Report(this);
        c = report.queryAll();
        setContentView(R.layout.edit_report_dialog);
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-6509134419013533/6898417609");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText hour = (EditText)findViewById(R.id.hours);
        EditText mags = (EditText)findViewById(R.id.mags);
        EditText broch = (EditText)findViewById(R.id.broch);
        EditText book = (EditText)findViewById(R.id.books);
        EditText tract = (EditText)findViewById(R.id.tracts);
        EditText rvs = (EditText)findViewById(R.id.rvs);
        EditText study = (EditText)findViewById(R.id.studies);
        EditText comment = (EditText)findViewById(R.id.description);
        TextView pc_tv = (TextView)findViewById(R.id.pc_tv);
        EditText ev = (EditText)findViewById(R.id.pc);
        TextView tV = (TextView)findViewById(R.id.date_picker_click);
        Intent mintent = getIntent();
        date = mintent.getStringExtra("date");
        mid = mintent.getIntExtra("id", 0);h = mintent.getIntExtra("hour", 0);m = mintent.getIntExtra("mag", 0);
        br = mintent.getIntExtra("br", 0);bo = mintent.getIntExtra("bo", 0);t = mintent.getIntExtra("t", 0);
        rv = mintent.getIntExtra("rv", 0);s = mintent.getIntExtra("s", 0);co = mintent.getStringExtra("c");pc = mintent.getIntExtra("pc", 0);
        //Log.d("report transfer", "id "+mid+", hour "+h+", mag "+m+", br "+br+", bo "+bo+", t "+t+", rv "+rv+", s "+s+", co: "+co+".");
        if(h == 0){hour.setText("");}else{
            Integer ha = h/3600000;
            Integer ma = (h/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){
                hour.setText("");
            }else if(ha.equals(0)){
                hour.setText("0."+ma);
            }else if(ma.equals(0)){
                hour.setText(""+ha);
            }else{
                hour.setText(ha+"."+ma);
            }
        }
        if(m == 0){mags.setText("");}else{mags.setText(m+"");}
        if(br == 0){broch.setText("");}else{broch.setText(br+"");}
        if(bo == 0){book.setText("");}else{book.setText(bo+"");}
        if(t == 0){tract.setText("");}else{tract.setText(t+"");}
        if(rv == 0){rvs.setText("");}else{rvs.setText(rv+"");}
        if(s == 0){study.setText("");}else{study.setText(s+"");}
        if(co.isEmpty()){comment.setText("");}else{comment.setText(co);}
        pc_tv.setVisibility(View.GONE);
        ev.setVisibility(View.GONE);
        SharedPreferences preferences = getSharedPreferences("Pioneer_toggle", Context.MODE_PRIVATE);
        boolean bool = preferences.getBoolean("pioneer_credits", false);
        if(bool == true || (pc != 0)){
            pc_tv.setVisibility(View.VISIBLE);
            ev.setVisibility(View.VISIBLE);
        }else if(bool == false){
            pc_tv.setVisibility(View.GONE);
            ev.setVisibility(View.GONE);
        }
        if(pc == 0){ev.setText("");}else{
            Integer pch = pc/3600000;
            Integer pcm = (pc/60000)-(pch*60);
            if(pch.equals(0)&&pcm.equals(0)){
                ev.setText("");
            }else if(pch.equals(0)){
                ev.setText("0."+pcm);
            }else if(pcm.equals(0)){
                ev.setText(""+pch);
            }else{
                ev.setText(pch+"."+pcm);
            }
        }
        tV.setText(date);
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
        //get values from content in layout
        if(hour.getText().toString().isEmpty()){h = 0;}else
        if(!(hour.getText().toString().contains("."))){
            Integer i = Integer.parseInt(hour.getText().toString());
            h = i*3600000;}
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
            pc = i*3600000;}
        else{
            String pcs = pioncred.getText().toString();
            String[] pca = pcs.split("\\.");
            String pch = pca[0];
            try{
                String pcm = pca[1];
                if(pcm.equals("")) pcm = "0";
                if(pcm.length()==1&&!(pcm.equals("0"))) pcm = pcm+"0";
                i2 = Integer.parseInt(pcm)*60000;
            }catch (Exception ignore){
                //ignore
            }
            if(pch.equals("")) pch = "0";
            Integer i1 = Integer.parseInt(pch)*3600000;
            pc = i1+i2;
        }
        if(hour.getText().toString().isEmpty() && mags.getText().toString().isEmpty() &&
                broch.getText().toString().isEmpty() && book.getText().toString().isEmpty() &&
                tract.getText().toString().isEmpty() && rvs.getText().toString().isEmpty() &&
                study.getText().toString().isEmpty() && pioncred.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.no_report, Toast.LENGTH_LONG).show();
        }else{
            db.updateDetail(mid, h, m, br, bo, t, rv, s, co, pc);
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
