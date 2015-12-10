package jonestech.simpleserviceassistant;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimeZone;
/**
 * Created by Josiah on 12/4/2014.
 */
public class Month_frag extends Fragment {
    Report report;
    Integer h, m, br, bo, t, rv, s, pc = 0;
    public String month;
    public String monthyear;
    public Integer nmonth;
    public Integer nyear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return	inflater.inflate(R.layout.month_table, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        report = new Report(getActivity());
        final TextView hour = (TextView)getActivity().findViewById(R.id.hour);
        final TextView mag = (TextView)getActivity().findViewById(R.id.mag);
        final TextView broch = (TextView)getActivity().findViewById(R.id.brch);
        final TextView book = (TextView)getActivity().findViewById(R.id.book);
        final TextView tract = (TextView)getActivity().findViewById(R.id.t);
        final TextView rtrnvst = (TextView)getActivity().findViewById(R.id.rv);
        final TextView study = (TextView)getActivity().findViewById(R.id.s);
        final TextView picr = (TextView)getActivity().findViewById(R.id.pc);
        final TextView tV = (TextView)getActivity().findViewById(R.id.month);
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        final int currentYear = localCalendar.get(Calendar.YEAR);
        nyear = currentYear;
        final Integer nummonth = localCalendar.get(Calendar.MONTH)+1;
        nmonth = nummonth;
        Button prevMonthB = (Button)getActivity().findViewById(R.id.prevmonth_button);
        Button laterMonthB = (Button)getActivity().findViewById(R.id.monthafter_button);
        prevMonthB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmonth = nmonth-1;
                if(nmonth == 0){
                    nmonth = 12;
                    nyear = nyear-1;
                }
                if(nmonth == 0){month = getString(R.string.dec);}
                else if(nmonth == 1){month = getString(R.string.jan);}
                else if(nmonth == 2){month = getString(R.string.feb);}
                else if(nmonth == 3){month = getString(R.string.march);}
                else if(nmonth == 4){month = getString(R.string.april);}
                else if(nmonth == 5){month = getString(R.string.may);}
                else if(nmonth == 6){month = getString(R.string.june);}
                else if(nmonth == 7){month = getString(R.string.july);}
                else if(nmonth == 8){month = getString(R.string.aug);}
                else if(nmonth == 9){month = getString(R.string.sep);}
                else if(nmonth == 10){month = getString(R.string.oct);}
                else if(nmonth == 11){month = getString(R.string.nov);}
                else if(nmonth == 12){month = getString(R.string.dec);}
                else{month = ""+nmonth;}
                monthyear = nmonth+""+nyear;
                //add values to the month chart
                h = report.queryTotalmHours(monthyear);
                m = report.queryTotalmMags(monthyear);
                br = report.queryTotalmBroch(monthyear);
                bo = report.queryTotalmBook(monthyear);
                t = report.queryTotalmTract(monthyear);
                rv = report.queryTotalmRV(monthyear);
                s = report.queryTotalmStudies(monthyear);
                pc = report.queryTotalmCredits(monthyear);
                //insert values to the textviews
                tV.setText(month+" "+nyear);
                if(h == 0){hour.setText("0");}else{
                    Integer ha = (h/3600000);
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
                mag.setText(""+m);
                broch.setText(""+br);
                book.setText(""+bo);
                tract.setText(""+t);
                rtrnvst.setText(""+rv);
                study.setText(""+s);
                if(pc == 0){picr.setText("0");}else{
                    Integer pch = (pc/3600000);
                    Integer pcm = (pc/60000)-(pch*60);
                    if(pch.equals(0)&&pcm.equals(0)){
                        picr.setText("");
                    }else if(pch.equals(0)){
                        picr.setText("0."+pcm);
                    }else if(pcm.equals(0)){
                        picr.setText(""+pch);
                    }else{
                        picr.setText(pch+"."+pcm);
                    }
                }
                tV.setText(month+" "+nyear);
            }
        });
        laterMonthB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmonth = nmonth+1;
                if(nmonth == 13){
                    nmonth = 1;
                    nyear = nyear+1;
                }
                if(nmonth == 0){month = getString(R.string.dec);}
                else if(nmonth == 1){month = getString(R.string.jan);}
                else if(nmonth == 2){month = getString(R.string.feb);}
                else if(nmonth == 3){month = getString(R.string.march);}
                else if(nmonth == 4){month = getString(R.string.april);}
                else if(nmonth == 5){month = getString(R.string.may);}
                else if(nmonth == 6){month = getString(R.string.june);}
                else if(nmonth == 7){month = getString(R.string.july);}
                else if(nmonth == 8){month = getString(R.string.aug);}
                else if(nmonth == 9){month = getString(R.string.sep);}
                else if(nmonth == 10){month = getString(R.string.oct);}
                else if(nmonth == 11){month = getString(R.string.nov);}
                else if(nmonth == 12){month = getString(R.string.dec);}
                else{month = ""+nmonth;}
                monthyear = nmonth+""+nyear;
                //add values to the month chart
                h = report.queryTotalmHours(monthyear);
                m = report.queryTotalmMags(monthyear);
                br = report.queryTotalmBroch(monthyear);
                bo = report.queryTotalmBook(monthyear);
                t = report.queryTotalmTract(monthyear);
                rv = report.queryTotalmRV(monthyear);
                s = report.queryTotalmStudies(monthyear);
                pc = report.queryTotalmCredits(monthyear);
                //insert values to the textviews
                tV.setText(month+" "+nyear);
                if(h == 0){hour.setText("0");}else{
                    Integer ha = (h/3600000);
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
                mag.setText(""+m);
                broch.setText(""+br);
                book.setText(""+bo);
                tract.setText(""+t);
                rtrnvst.setText(""+rv);
                study.setText(""+s);
                if(pc == 0){picr.setText("0");}else{
                    Integer pch = (pc/3600000);
                    Integer pcm = (pc/60000)-(pch*60);
                    if(pch.equals(0)&&pcm.equals(0)){
                        picr.setText("");
                    }else if(pch.equals(0)){
                        picr.setText("0."+pcm);
                    }else if(pcm.equals(0)){
                        picr.setText(""+pch);
                    }else{
                        picr.setText(pch+"."+pcm);
                    }
                }
                tV.setText(month+" "+nyear);
            }
        });
        if(nmonth == 0){month = getString(R.string.dec);}
        else if(nmonth == 1){month = getString(R.string.jan);}
        else if(nmonth == 2){month = getString(R.string.feb);}
        else if(nmonth == 3){month = getString(R.string.march);}
        else if(nmonth == 4){month = getString(R.string.april);}
        else if(nmonth == 5){month = getString(R.string.may);}
        else if(nmonth == 6){month = getString(R.string.june);}
        else if(nmonth == 7){month = getString(R.string.july);}
        else if(nmonth == 8){month = getString(R.string.aug);}
        else if(nmonth == 9){month = getString(R.string.sep);}
        else if(nmonth == 10){month = getString(R.string.oct);}
        else if(nmonth == 11){month = getString(R.string.nov);}
        else if(nmonth == 12){month = getString(R.string.dec);}
        else{month = ""+nmonth;}
        monthyear = nmonth+""+nyear;
        //add values to the month chart
        h = report.queryTotalmHours(monthyear);
        m = report.queryTotalmMags(monthyear);
        br = report.queryTotalmBroch(monthyear);
        bo = report.queryTotalmBook(monthyear);
        t = report.queryTotalmTract(monthyear);
        rv = report.queryTotalmRV(monthyear);
        s = report.queryTotalmStudies(monthyear);
        pc = report.queryTotalmCredits(monthyear);
        //insert values to the textviews
        tV.setText(month+" "+nyear);
        if(h == 0){hour.setText("0");}else{
            Integer ha = (h/3600000);
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
        mag.setText(""+m);
        broch.setText(""+br);
        book.setText(""+bo);
        tract.setText(""+t);
        rtrnvst.setText(""+rv);
        study.setText(""+s);
        if(pc == 0){picr.setText("0");}else{
            Integer pch = (pc/3600000);
            Integer pcm = (pc/60000)-(pch*60);
            if(pch.equals(0)&&pcm.equals(0)){
                picr.setText("");
            }else if(pch.equals(0)){
                picr.setText("0."+pcm);
            }else if(pcm.equals(0)){
                picr.setText(""+pch);
            }else{
                picr.setText(pch+"."+pcm);
            }
        }
    }
}