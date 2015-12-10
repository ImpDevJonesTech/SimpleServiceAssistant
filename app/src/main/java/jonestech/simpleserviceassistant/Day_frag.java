package jonestech.simpleserviceassistant;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by Josiah on 12/4/2014.
 */
public class Day_frag extends Fragment {
    private static final int URL_LOADER = 0;
    Report report;
    Cursor mCursor;
    DatabaseHandler dh;
    ListView listView;
    MCursorAdapter adapter;
    String query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return	inflater.inflate(R.layout.day_table, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        dh = new DatabaseHandler(getActivity(), dh.DATABASE_NAME, null, dh.DATABASE_VERSION);
        SQLiteDatabase db = dh.getWritableDatabase();
        listView = (ListView)getActivity().findViewById(R.id.list_View);
        query = "Select * From "+dh.TABLE_REPORT;
        mCursor = db.rawQuery(query, null);
        adapter = new MCursorAdapter(getActivity(), mCursor);
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete_report_dialog);
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //dialog.dismiss();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                report = new Report(getActivity());
                Integer iid = (int) (id);
                Intent intent = new Intent(getActivity(), Edit_Report_Class.class);
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                intent.putExtra("id", iid);
                intent.putExtra("date", report.queryDate(iid));
                intent.putExtra("hour", report.queryHours(iid));
                intent.putExtra("mag", report.queryMagazines(iid));
                intent.putExtra("br", report.queryBrochures(iid));
                intent.putExtra("bo", report.queryBooks(iid));
                intent.putExtra("t", report.queryTracts(iid));
                intent.putExtra("rv", report.queryRVs(iid));
                intent.putExtra("s", report.queryStudies(iid));
                intent.putExtra("c", report.queryDetails(iid));
                intent.putExtra("pc", report.queryCredits(iid));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                final int num = (int)(id);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        report = new Report(getActivity());
                        report.deleteOneReport(num);
                        mCursor.requery();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), R.string.report_delete, Toast.LENGTH_LONG).show();
                        //dialog.dismiss();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                    }
                });
                builder.show();
                return true;
            }
        });
    }
}
