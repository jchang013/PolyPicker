package com.example.tccl9.polypicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private Context mCtx;
    private List<Course> courseList;

    public CourseAdapter(Context mCtx, List<Course>courseList){
        this.mCtx=mCtx;
        this.courseList=courseList;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.coursecardview, null);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position){
        Course course = courseList.get(position);

        holder.textViewCName.setText(course.getcName());
        holder.textViewSName.setText(course.getsName());
        holder.textViewPName.setText(course.getpName());
        holder.textViewAggCOPoint.setText(Integer.toString(course.getaggCOPoint()));
        holder.textViewCourseID.setText(course.getCourseID());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCName, textViewSName, textViewPName, textViewAggCOPoint, textViewCourseID;

        public CourseViewHolder(View courseView){
            super(courseView);

            textViewCName=courseView.findViewById(R.id.courseName);
            textViewCourseID=courseView.findViewById(R.id.courseID);
            textViewSName=courseView.findViewById(R.id.schoolName);
            textViewPName=courseView.findViewById(R.id.polyName);
            textViewAggCOPoint=courseView.findViewById(R.id.aggCOPoint);
        }
    }
}
