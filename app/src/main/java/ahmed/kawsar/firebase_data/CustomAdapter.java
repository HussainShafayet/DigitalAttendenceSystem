package ahmed.kawsar.firebase_data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
       private Activity context;
       private List<Student> studentList;

    public CustomAdapter(Activity context, List<Student> studentList) {
        super(context, R.layout.sample_layout,studentList);
        this.context=context;
        this.studentList=studentList;

    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);
        Student student  = studentList.get(position);
        TextView t1 = view.findViewById(R.id.nametextviewid);
        TextView t2 = view.findViewById(R.id.subjecttextviewid);
        TextView t3 = view.findViewById(R.id.agetextviewid);
        TextView t4 = view.findViewById(R.id.referencetextviewid);

        t1.setText("Date: "+student.getDate());

        t2.setText("Subject: "+student.getSubject());

        t3.setText("Dept-Session: "+student.getDeptsession());

        t4.setText("Roll :                 Present "+student.getAttendance());
        return view;
    }
}
