package jonestech.simpleserviceassistant;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimeZone;
/**
 * Created by Josiah on 12/4/2014.
 */
public class Year_frag extends Fragment {
    Report report;
    Integer h, m, br, bo, t, rv, s, pc = 0;
    int placements = 0;
    public int pyear, cyear;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return	inflater.inflate(R.layout.year_table, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        report = new Report(getActivity());
        RelativeLayout p_c_div = (RelativeLayout)getActivity().findViewById(R.id.pcy_row);
        p_c_div.setVisibility(View.GONE);
        SharedPreferences preferences = getActivity().getSharedPreferences("Pioneer_toggle", Context.MODE_PRIVATE);
        boolean bool = preferences.getBoolean("pioneer_credits", false);
        if(bool == true){
            p_c_div.setVisibility(View.VISIBLE);
        }else if(bool == false){
            p_c_div.setVisibility(View.GONE);
        }
        final TextView place = (TextView)getActivity().findViewById(R.id.placy);
        final TextView hour = (TextView)getActivity().findViewById(R.id.houry);
        final TextView mag = (TextView)getActivity().findViewById(R.id.magy);
        final TextView broch = (TextView)getActivity().findViewById(R.id.brchy);
        final TextView book = (TextView)getActivity().findViewById(R.id.booky);
        final TextView tract = (TextView)getActivity().findViewById(R.id.ty);
        final TextView rtrnvst = (TextView)getActivity().findViewById(R.id.rvy);
        final TextView study = (TextView)getActivity().findViewById(R.id.sy);
        final TextView picr = (TextView)getActivity().findViewById(R.id.pcy);
        final TextView tV = (TextView)getActivity().findViewById(R.id.year);
        final int currentYear, prevyear;
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        if(localCalendar.get(Calendar.MONTH) > 7){
            currentYear = localCalendar.get(Calendar.YEAR)+1;
            prevyear = localCalendar.get(Calendar.YEAR);
        }else{
            currentYear = localCalendar.get(Calendar.YEAR);
            prevyear = localCalendar.get(Calendar.YEAR)-1;
        }
        pyear = prevyear; cyear = currentYear;
        Button prevYearB = (Button)getActivity().findViewById(R.id.prevyear_button);
        Button nextYearB = (Button)getActivity().findViewById(R.id.yearafter_button);
        prevYearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear = cyear-1; pyear = pyear-1;
                //add values to the month chart
                h = report.queryTotalyHours(cyear+"");
                m = report.queryTotalyMags(cyear+"");
                br = report.queryTotalyBroch(cyear+"");
                bo = report.queryTotalyBook(cyear+"");
                t = report.queryTotalyTract(cyear+"");
                rv = report.queryTotalyRV(cyear+"");
                s = report.queryTotalyStudies(cyear+"");
                pc = report.queryTotalyCredits(cyear+"");
                placements = m + br + bo + t;
                //insert values to the textviews
                tV.setText(getString(R.string.sep)+", "+pyear+"\n"+" - "+getString(R.string.aug)+", "+cyear);
                if(h == 0){hour.setText("0");}else{
                    Integer ha = (h/3600000);
                    Integer ma = (h/60000)-(ha*60);
                    if(ha.equals(0)&&ma.equals(0)){
                        hour.setText("0");
                    }else if(ha.equals(0)){
                        hour.setText("0."+ma);
                    }else if(ma.equals(0)){
                        hour.setText(""+ha);
                    }else{
                        hour.setText(ha+"."+ma);
                    }
                }
                place.setText(""+placements);
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
                        picr.setText("0");
                    }else if(pch.equals(0)){
                        picr.setText("0."+pcm);
                    }else if(pcm.equals(0)){
                        picr.setText(""+pch);
                    }else{
                        picr.setText(pch+"."+pcm);
                    }
                }
            }
        });
        nextYearB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cyear = cyear+1; pyear = pyear+1;
                //add values to the month chart
                h = report.queryTotalyHours(cyear+"");
                m = report.queryTotalyMags(cyear+"");
                br = report.queryTotalyBroch(cyear+"");
                bo = report.queryTotalyBook(cyear+"");
                t = report.queryTotalyTract(cyear+"");
                rv = report.queryTotalyRV(cyear+"");
                s = report.queryTotalyStudies(cyear+"");
                pc = report.queryTotalyCredits(cyear+"");
                placements = m + br + bo + t;
                //insert values to the textviews
                tV.setText(getString(R.string.sep)+", "+pyear+"\n"+" - "+getString(R.string.aug)+", "+cyear);
                if(h == 0){hour.setText("0");}else{
                    Integer ha = (h/3600000);
                    Integer ma = (h/60000)-(ha*60);
                    if(ha.equals(0)&&ma.equals(0)){
                        hour.setText("0");
                    }else if(ha.equals(0)){
                        hour.setText("0."+ma);
                    }else if(ma.equals(0)){
                        hour.setText(""+ha);
                    }else{
                        hour.setText(ha+"."+ma);
                    }
                }
                place.setText(""+placements);
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
                        picr.setText("0");
                    }else if(pch.equals(0)){
                        picr.setText("0."+pcm);
                    }else if(pcm.equals(0)){
                        picr.setText(""+pch);
                    }else{
                        picr.setText(pch+"."+pcm);
                    }
                }
            }
        });
        //add values to the month chart
        h = report.queryTotalyHours(cyear+"");
        m = report.queryTotalyMags(cyear + "");
        br = report.queryTotalyBroch(cyear + "");
        bo = report.queryTotalyBook(cyear + "");
        t = report.queryTotalyTract(cyear + "");
        rv = report.queryTotalyRV(cyear + "");
        s = report.queryTotalyStudies(cyear + "");
        pc = report.queryTotalyCredits(cyear + "");
        placements = m + br + bo + t;
        //insert values to the textviews
        tV.setText(getString(R.string.sep)+", "+pyear+"\n"+" - "+getString(R.string.aug)+", "+cyear);
        if(h == 0){hour.setText("0");}else{
            Integer ha = (h/3600000);
            Integer ma = (h/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){
                hour.setText("0");
            }else if(ha.equals(0)){
                hour.setText("0."+ma);
            }else if(ma.equals(0)){
                hour.setText(""+ha);
            }else{
                hour.setText(ha+"."+ma);
            }
        }
        place.setText(""+placements);
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
                picr.setText("0");
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