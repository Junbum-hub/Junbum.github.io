package com.example.everyevent;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {
    private final ArrayList<PostInfo> mDataset;
    private final Activity activity;
    public static class PostListViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public PostListViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public PostListAdapter(Activity activity, ArrayList<PostInfo> myDataset){
        mDataset = myDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        final PostListViewHolder postListViewHolder = new PostListViewHolder(cardView);
        cardView.setOnClickListener((v) -> {

        });
        return postListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostListViewHolder holder, int position){
        CardView cardView = holder.cardView;
        long difference = 0;
        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date start = format.parse(mDataset.get(position).getStartDate());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LocalDate now = LocalDate.now();
                Date formNow = format.parse(now.toString());
                long diff = start.getTime() - formNow.getTime();
                TimeUnit time = TimeUnit.DAYS;
                difference = time.convert(diff,TimeUnit.MILLISECONDS);
                if(difference > 0) {
                    mDataset.get(position).setRunning(false);
                }else{
                    mDataset.get(position).setRunning(true);
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        //제목
        TextView titleTextView = cardView.findViewById(R.id.eventTitle);
        titleTextView.setText(mDataset.get(position).getTitle());

        //이벤트 장소
        TextView userName = cardView.findViewById(R.id.eventAddress);
        userName.setText(mDataset.get(position).getAddress());

        TextView people = cardView.findViewById(R.id.numberOfPeople);
        if(mDataset.get(position).getRunning()==true){
            //이벤트 출석 인원
            people.setText(mDataset.get(position).getNumberOfAttendees()+"");
        }else{
            //이벤트 신청 인원
            people.setText(mDataset.get(position).getNumberOfApplicants() + "");
        }


        //이벤트 수용 가능 인원
        TextView numberOfPeopleCanApply = cardView.findViewById(R.id.numberOfPeopleCanApply);
        numberOfPeopleCanApply.setText(mDataset.get(position).getNumberOfPeopleCanApply());

        //이벤트 상태 (실행중/남은 날짜)
        if(mDataset.get(position).getRunning()){
            //이벤트 실행중
            TextView startDate = cardView.findViewById(R.id.eventState);
            startDate.setText("실행중");
        }else{
            //이벤트 남은 날짜
            TextView startDate = cardView.findViewById(R.id.eventState);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            startDate.setText("남은 날짜 : "+difference);


        }


        //컨텐츠
        /*ArrayList<String> contentsList = mDataset.get(position).getMain_content();
        LinearLayout baseLayout = cardView.findViewById(R.id.layout_base);
        LinearLayout contentsLayout = baseLayout.findViewById(R.id.layout_contents);
        TextView contentTextView = contentsLayout.findViewById(R.id.text_content);
        contentTextView.setText(contentsList.get(0));

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int contentsSize = contentsList.size();
        if (contentsSize != 1) {
            String contents = contentsList.get(1);
            if (Patterns.WEB_URL.matcher(contents).matches()) {
                ImageView imageView = new ImageView(activity);
                imageView.setLayoutParams(layoutParams);
                baseLayout.addView(imageView);
                Glide.with(activity).load(contents).override(imageSize).into(imageView);
            }
        }*/
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}
