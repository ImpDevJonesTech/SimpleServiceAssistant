package jonestech.ministry_report;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
/**
 * Created by Josiah on 12/22/2014.
 */
public class MCursorAdapter extends CursorAdapter {
    private DatabaseHandler dh;
    String empty = "";
    String hours, magazines, brochures, books, tracts, videos, returnvisits, biblestudies, pioncred = "";
    public MCursorAdapter(Context ct, Cursor cs){super(ct, cs);}
    @Override
    public View newView(Context ct, Cursor cs, ViewGroup parent){
        return LayoutInflater.from(ct).inflate(R.layout.list_row, parent, false);
    }
    @Override
    public void bindView(View v, Context ct, Cursor cs){
        TextView date = (TextView)v.findViewById(R.id.date);
        TextView list = (TextView)v.findViewById(R.id.horizontal_div);
        TextView comm = (TextView)v.findViewById(R.id.row_description);
        String d = cs.getString(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_DATE));
        Integer h =  cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_H));
        Integer m =  cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_M));
        Integer br = cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_BRCH));
        Integer bo = cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_BOOK));
        Integer t =  cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_T));
        Integer vs = cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_VS));
        Integer rv = cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_RV));
        Integer s =  cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_S));
        String c = cs.getString(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_D));
        Integer pc = cs.getInt(cs.getColumnIndexOrThrow(DatabaseHandler.KEY_PC));
        date.setText(d);
        if(h == 0){hours = empty;}else{
            Integer ha = h/3600000;
            Integer ma = (h/60000)-(ha*60);
            if(ha.equals(0)&&ma.equals(0)){
                hours = empty;
            }else if(ha.equals(0)){
                hours = "0."+ma+" "+ct.getString(R.string.h)+" ";
            }else if(ma.equals(0)){
                hours = ha+" "+ct.getString(R.string.h)+" ";
            }else{
                hours = ha+"."+ma+" "+ct.getString(R.string.h)+" ";
            }
        }
        if(m == 0){magazines = empty;}else{magazines = m+" "+ct.getString(R.string.m)+" ";}
        if(br == 0){brochures = empty;}else{brochures = br+" "+ct.getString(R.string.br)+" ";}
        if(bo == 0){books = empty;}else{books = bo+" "+ct.getString(R.string.bo)+" ";}
        if(t == 0){tracts = empty;}else{tracts = t+" "+ct.getString(R.string.t)+" ";}
        if(vs == 0){videos = empty;}else{videos = vs+" "+ct.getString(R.string.video)+" ";}
        if(rv == 0){returnvisits = empty;}else{returnvisits = rv+" "+ct.getString(R.string.r_v)+" ";}
        if(s == 0){biblestudies = empty;}else{biblestudies = s+" "+ct.getString(R.string.b_s)+" ";}
        if(pc == 0){pioncred = empty;}else{
            Integer pch = pc/3600000;
            Integer pcm = (pc/60000)-(pch*60);
            if(pch.equals(0)&&pcm.equals(0)){
                pioncred = empty;
            }else if(pch.equals(0)){
                pioncred = "0."+pcm+" "+ct.getString(R.string.p_c)+" ";
            }else if(pcm.equals(0)){
                pioncred = pch+" "+ct.getString(R.string.p_c)+" ";
            }else{
                pioncred = pch+"."+pcm+" "+ct.getString(R.string.p_c)+" ";
            }
        }
        list.setText(hours+magazines+brochures+books+tracts+videos+returnvisits+biblestudies+pioncred);
        comm.setText(c);
    }
}