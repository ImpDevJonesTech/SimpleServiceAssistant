package jonestech.simpleserviceassistant;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.TimeZone;
/**
 * Created by Josiah on 1/12/2015.
 */
public class Email_Report_Class extends Activity {
    Report report;
    String empty = "";
    String phours, pmagazines, pbrochures, pbooks, ptracts, preturnvisits, pbiblestudies, p_pioncred = "";
    Integer ph, pm, pbr, pbo, pt, prv, ps, p_pc = 0;
    String chours, cmagazines, cbrochures, cbooks, ctracts, creturnvisits, cbiblestudies, c_pioncred = "";
    Integer ch, cm, cbr, cbo, ct, crv, cs, c_pc = 0;
    int cmonth, pmonth, cyear, pyear = 0;
    String currentmonth, prevmonth = "";
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        themeUtils.onDialogCreateSetTheme(this);
        report = new Report(this);
        setContentView(R.layout.send_email_dialog);
        final CheckBox pmonthcheckbox = (CheckBox)findViewById(R.id.prevmonth_checkbox);
        final CheckBox cmonthcheckbox = (CheckBox)findViewById(R.id.curmonth_checkbox);
        TextView pmonthtv = (TextView)findViewById(R.id.prevmonth_tv);
        TextView cmonthtv = (TextView)findViewById(R.id.curmonth_tv);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cmonthcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked = true){
                    pmonthcheckbox.setChecked(false);
                }else{
                    cmonthcheckbox.setChecked(true);
                }
            }
        });
        pmonthcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked = true) {
                    cmonthcheckbox.setChecked(false);
                } else {
                    pmonthcheckbox.setChecked(true);
                }
            }
        });
        pmonthcheckbox.setText(getString(R.string.report_for)+" "+pmonthyearmethod());
        pmonthtv.setText(prevmonthmethod(pmymethod()+""));
        cmonthcheckbox.setText(getString(R.string.report_for)+" "+cmonthyearmethod());
        cmonthtv.setText(curmonthmethod(cmymethod()+""));
    }
    public void send(View view){
        final CheckBox pmonthcheckbox = (CheckBox)findViewById(R.id.prevmonth_checkbox);
        final CheckBox cmonthcheckbox = (CheckBox)findViewById(R.id.curmonth_checkbox);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        if(pmonthcheckbox.isChecked()){
            if(prevmonthmethod(pmymethod()+"").equals(getString(R.string.no_email))){
                Toast.makeText(this, getString(R.string.no_email), Toast.LENGTH_LONG).show();
            }else{
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.report_for) + " " + pmonthyearmethod());
                intent.putExtra(Intent.EXTRA_TEXT, prevmonthmethod(pmymethod() + ""));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, getString(R.string.no_email_app), Toast.LENGTH_LONG).show();
                }
            }
        }
        if(cmonthcheckbox.isChecked()){
            if(curmonthmethod(cmymethod()+"").equals(getString(R.string.no_email))){
                Toast.makeText(this, getString(R.string.no_email), Toast.LENGTH_LONG).show();
            }else {
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.report_for) + " " + cmonthyearmethod());
                intent.putExtra(Intent.EXTRA_TEXT, curmonthmethod(cmymethod() + ""));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, getString(R.string.no_email_app), Toast.LENGTH_LONG).show();
                }
            }
        }
        if(!(pmonthcheckbox.isChecked())&&!(cmonthcheckbox.isChecked())){
            Toast.makeText(this, getString(R.string.no_checks), Toast.LENGTH_LONG).show();
        }
    }
    public void cancel(View view){
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("EXIT", true);
        startActivity(i);
    }
    public String prevmonthmethod(String monthyear){
        String pmonthmethodstring = "";
        ph = report.queryTotalmHours(monthyear);
        pm = report.queryTotalmMags(monthyear);
        pbr = report.queryTotalmBroch(monthyear);
        pbo = report.queryTotalmBook(monthyear);
        pt = report.queryTotalmTract(monthyear);
        prv = report.queryTotalmRV(monthyear);
        ps = report.queryTotalmStudies(monthyear);
        p_pc = report.queryTotalmCredits(monthyear);
        if(ph == 0){phours = empty;}else{
            Integer ha = ph/3600000;
            Integer ma = (ph/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){phours = empty;
            }else if(ha.equals(0)){
                if(pm == 0 && pbr == 0 && pbo == 0 && pt == 0 && prv == 0 && ps == 0 && p_pc == 0){
                    phours = "0."+ma+" "+getString(R.string.h)+" ";
                }else {phours = "0." + ma + " " + getString(R.string.h) + ", ";}
            }else if(ma.equals(0)){
                if(pm == 0 && pbr == 0 && pbo == 0 && pt == 0 && prv == 0 && ps == 0 && p_pc == 0){
                    phours = ha+" "+getString(R.string.h)+" ";
                }else{phours = ha+" "+getString(R.string.h)+", ";}
            }else{
                if(pm == 0 && pbr == 0 && pbo == 0 && pt == 0 && prv == 0 && ps == 0 && p_pc == 0){
                    phours = ha+"."+ma+" "+getString(R.string.h)+" ";
                }else {phours = ha + "." + ma + " " + getString(R.string.h) + ", ";}
            }
        }
        if(pm == 0){pmagazines = empty;}else{
            if(pbr == 0 && pbo == 0 && pt == 0 && prv == 0 && ps == 0 && p_pc == 0){pmagazines = pm+" "+getString(R.string.m)+" ";
            }else {pmagazines = pm + " " + getString(R.string.m) + ", ";}}
        if(pbr == 0){pbrochures = empty;}else{
            if(pbo == 0 && pt == 0 && prv == 0 && ps == 0 && p_pc == 0){pbrochures = pbr+" "+getString(R.string.br)+" ";
            }else {pbrochures = pbr + " " + getString(R.string.br) + ", ";}}
        if(pbo == 0){pbooks = empty;}else{
            if(pt == 0 && prv == 0 && ps == 0 && p_pc == 0){pbooks = pbo+" "+getString(R.string.bo)+" ";
            }else {pbooks = pbo + " " + getString(R.string.bo) + ", ";}}
        if(pt == 0){ptracts = empty;}else{
            if(prv == 0 && ps == 0 && p_pc == 0) {ptracts = pt + " " + getString(R.string.t) + " ";
            }else {ptracts = pt + " " + getString(R.string.t) + ", ";}}
        if(prv == 0){preturnvisits = empty;}else{
            if(ps == 0 && p_pc == 0){preturnvisits = prv+" "+getString(R.string.r_v)+" ";
            }else {preturnvisits = prv + " " + getString(R.string.r_v) + ", ";}}
        if(ps == 0){pbiblestudies = empty;}else{
            if(p_pc == 0){pbiblestudies = ps+" "+getString(R.string.b_s)+" ";
            }else{pbiblestudies = ps+" "+getString(R.string.b_s)+", ";}}
        if(p_pc == 0){p_pioncred = empty;}else{
            Integer ha = p_pc/3600000;
            Integer ma = (p_pc/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){p_pioncred = empty;
            }else if(ha.equals(0)){
                p_pioncred = "0."+ma+" "+getString(R.string.p_c_h_m)+" ";
            }else if(ma.equals(0)){
                p_pioncred = ha+" "+getString(R.string.p_c)+" ";
            }else{
                p_pioncred = ha+"."+ma+" "+getString(R.string.p_c_h_m)+" ";
            }
        }
        if((phours+pmagazines+pbrochures+pbooks+ptracts+preturnvisits+pbiblestudies+p_pioncred).equals(empty)){
            pmonthmethodstring = getString(R.string.no_email);
        }else {
            pmonthmethodstring = phours + pmagazines + pbrochures + pbooks + ptracts + preturnvisits + pbiblestudies + p_pioncred;
        }
        return pmonthmethodstring;
    }
    public String curmonthmethod(String monthyear){
        String cmonthmethodstring = "";
        ch = report.queryTotalmHours(monthyear);
        cm = report.queryTotalmMags(monthyear);
        cbr = report.queryTotalmBroch(monthyear);
        cbo = report.queryTotalmBook(monthyear);
        ct = report.queryTotalmTract(monthyear);
        crv = report.queryTotalmRV(monthyear);
        cs = report.queryTotalmStudies(monthyear);
        c_pc = report.queryTotalmCredits(monthyear);
        if(ch == 0){chours = empty;}else{
            Integer ha = ch/3600000;
            Integer ma = (ch/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){chours = empty;
            }else if(ha.equals(0)){
                if(cm == 0 && cbr == 0 && cbo == 0 && ct == 0 && crv == 0 && cs == 0 && c_pc == 0){
                    chours = "0."+ma+" "+getString(R.string.h)+" ";
                }else {chours = "0." + ma + " " + getString(R.string.h) + ", ";}
            }else if(ma.equals(0)){
                if(cm == 0 && cbr == 0 && cbo == 0 && ct == 0 && crv == 0 && cs == 0 && c_pc == 0){
                    chours = ha+" "+getString(R.string.h)+" ";
                }else{chours = ha+" "+getString(R.string.h)+", ";}
            }else{
                if(cm == 0 && cbr == 0 && cbo == 0 && ct == 0 && crv == 0 && cs == 0 && c_pc == 0){
                    chours = ha+"."+ma+" "+getString(R.string.h)+" ";
                }else {chours = ha + "." + ma + " " + getString(R.string.h) + ", ";}
            }
        }
        if(cm == 0){cmagazines = empty;}else{
            if(cbr == 0 && cbo == 0 && ct == 0 && crv == 0 && cs == 0 && c_pc == 0){cmagazines = cm + " " + getString(R.string.m) + " ";
            }else {cmagazines = cm + " " + getString(R.string.m) + ", ";}}
        if(cbr == 0){cbrochures = empty;}else{
            if(cbo == 0 && ct == 0 && crv == 0 && cs == 0 && c_pc == 0){cbrochures = cbr + " " + getString(R.string.br) + " ";
            }else {cbrochures = cbr + " " + getString(R.string.br) + ", ";}}
        if(cbo == 0){cbooks = empty;}else{
            if(ct == 0 && crv == 0 && cs == 0 && c_pc == 0){cbooks = cbo + " " + getString(R.string.bo) + " ";
            }else {cbooks = cbo + " " + getString(R.string.bo) + ", ";}}
        if(ct == 0){ctracts = empty;}else{
            if(crv == 0 && cs == 0 && c_pc == 0){ctracts = ct + " " + getString(R.string.t) + " ";
            }else {ctracts = ct + " " + getString(R.string.t) + ", ";}}
        if(crv == 0){creturnvisits = empty;}else{
            if(cs == 0 && c_pc == 0){creturnvisits = crv + " " + getString(R.string.r_v) + " ";
            }else {creturnvisits = crv + " " + getString(R.string.r_v) + ", ";}}
        if(cs == 0){cbiblestudies = empty;}else{
            if(c_pc == 0){cbiblestudies = cs+" "+getString(R.string.b_s)+" ";
            }else {cbiblestudies = cs+" "+getString(R.string.b_s)+", ";}}
        if(c_pc == 0){c_pioncred = empty;}else{
            Integer ha = c_pc/3600000;
            Integer ma = (c_pc/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){c_pioncred = empty;
            }else if(ha.equals(0)){
                c_pioncred = "0."+ma+" "+getString(R.string.p_c_h_m)+" ";
            }else if(ma.equals(0)){
                c_pioncred = ha+" "+getString(R.string.p_c)+" ";
            }else{
                c_pioncred = ha+"."+ma+" "+getString(R.string.p_c_h_m)+" ";
            }
        }
        if((chours+cmagazines+cbrochures+cbooks+ctracts+creturnvisits+cbiblestudies+c_pioncred).equals(empty)){
            cmonthmethodstring = getString(R.string.no_email);
        }else{
            cmonthmethodstring = chours+cmagazines+cbrochures+cbooks+ctracts+creturnvisits+cbiblestudies+c_pioncred;
        }
        return cmonthmethodstring;
    }
    public Integer pmymethod(){
        Integer pmonthyear = 0;
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        if(localCalendar.get(Calendar.MONTH)+1 == 1)      {pmonth = 12;}
        else if(localCalendar.get(Calendar.MONTH)+1 == 2) {pmonth = 1; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 3) {pmonth = 2; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 4) {pmonth = 3; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 5) {pmonth = 4; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 6) {pmonth = 5; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 7) {pmonth = 6; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 8) {pmonth = 7; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 9) {pmonth = 8; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 10){pmonth = 9; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 11){pmonth = 10;}
        else if(localCalendar.get(Calendar.MONTH)+1 == 12){pmonth = 11;}
        if(localCalendar.get(Calendar.MONTH)+1 == 1){pyear = localCalendar.get(Calendar.YEAR)-1;}else{pyear = localCalendar.get(Calendar.YEAR);}
        String s = pmonth + "" + pyear;
        pmonthyear = Integer.parseInt(s);
        return pmonthyear;
    }
    public Integer cmymethod(){
        Integer cmonthyear = 0;
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        if(localCalendar.get(Calendar.MONTH)+1 == 1)      {cmonth = 1; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 2) {cmonth = 2; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 3) {cmonth = 3; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 4) {cmonth = 4; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 5) {cmonth = 5; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 6) {cmonth = 6; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 7) {cmonth = 7; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 8) {cmonth = 8; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 9) {cmonth = 9; }
        else if(localCalendar.get(Calendar.MONTH)+1 == 10){cmonth = 10;}
        else if(localCalendar.get(Calendar.MONTH)+1 == 11){cmonth = 11;}
        else if(localCalendar.get(Calendar.MONTH)+1 == 12){cmonth = 12;}
        cyear = localCalendar.get(Calendar.YEAR);
        String s = cmonth + "" + cyear;
        cmonthyear = Integer.parseInt(s);
        return cmonthyear;
    }
    public String pmonthyearmethod(){
        String monthyear = "";
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        if(localCalendar.get(Calendar.MONTH)+1 == 1)      {prevmonth = getString(R.string.dec);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 2) {prevmonth = getString(R.string.jan);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 3) {prevmonth = getString(R.string.feb);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 4) {prevmonth = getString(R.string.march);}
        else if(localCalendar.get(Calendar.MONTH)+1 == 5) {prevmonth = getString(R.string.april);}
        else if(localCalendar.get(Calendar.MONTH)+1 == 6) {prevmonth = getString(R.string.may);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 7) {prevmonth = getString(R.string.june); }
        else if(localCalendar.get(Calendar.MONTH)+1 == 8) {prevmonth = getString(R.string.july); }
        else if(localCalendar.get(Calendar.MONTH)+1 == 9) {prevmonth = getString(R.string.aug);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 10){prevmonth = getString(R.string.sep);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 11){prevmonth = getString(R.string.oct);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 12){prevmonth = getString(R.string.nov);  }
        if(localCalendar.get(Calendar.MONTH)+1 == 1){pyear = localCalendar.get(Calendar.YEAR)-1;}else{pyear = localCalendar.get(Calendar.YEAR);}
        monthyear = prevmonth + " " + pyear;
        return monthyear;
    }
    public String cmonthyearmethod(){
        String monthyear = "";
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        if(localCalendar.get(Calendar.MONTH)+1 == 1)      {currentmonth = getString(R.string.jan);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 2) {currentmonth = getString(R.string.feb);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 3) {currentmonth = getString(R.string.march);}
        else if(localCalendar.get(Calendar.MONTH)+1 == 4) {currentmonth = getString(R.string.april);}
        else if(localCalendar.get(Calendar.MONTH)+1 == 5) {currentmonth = getString(R.string.may);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 6) {currentmonth = getString(R.string.june); }
        else if(localCalendar.get(Calendar.MONTH)+1 == 7) {currentmonth = getString(R.string.july); }
        else if(localCalendar.get(Calendar.MONTH)+1 == 8) {currentmonth = getString(R.string.aug);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 9) {currentmonth = getString(R.string.sep);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 10){currentmonth = getString(R.string.oct);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 11){currentmonth = getString(R.string.nov);  }
        else if(localCalendar.get(Calendar.MONTH)+1 == 12){currentmonth = getString(R.string.dec);  }
        cyear = localCalendar.get(Calendar.YEAR);
        monthyear = currentmonth + " " + cyear;
        return monthyear;
    }
}