package com.example.tccl9.polypicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.CheckBox;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    private Context mCtx;
    private List<Course> courseList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onBookmarkClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CourseAdapter(Context mCtx, List<Course>courseList){
        this.mCtx=mCtx;
        this.courseList=courseList;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.coursecardview, null);

        return new CourseViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position){
        Course course = courseList.get(position);
        boolean bookmarked = false;
        if (course.getBookmark() == 1) {
            bookmarked = true;
        }
        holder.textViewCName.setText(course.getName());
        holder.textViewSName.setText(course.getSchool());
        holder.textViewPName.setText(course.getPolytechnic());
        holder.textViewAggCOPoint.setText(Integer.toString(course.getCutoff()));
        holder.textViewCourseID.setText(course.getCode());
        holder.bookmarkView.setChecked(bookmarked);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder{
        TextView textViewCName, textViewSName, textViewPName, textViewAggCOPoint, textViewCourseID;
        CheckBox bookmarkView;

        public CourseViewHolder(View courseView, final OnItemClickListener listener){
            super(courseView);

            textViewCName=courseView.findViewById(R.id.courseName);
            textViewCourseID=courseView.findViewById(R.id.courseID);
            textViewSName=courseView.findViewById(R.id.schoolName);
            textViewPName=courseView.findViewById(R.id.polyName);
            textViewAggCOPoint=courseView.findViewById(R.id.aggCOPoint);
            bookmarkView=courseView.findViewById(R.id.bookmark);

            courseView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            bookmarkView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onBookmarkClick(position);
                        }
                    }
                }
            });
        }
    }
}
