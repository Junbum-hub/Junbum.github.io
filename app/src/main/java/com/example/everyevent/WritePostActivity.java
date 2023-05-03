package com.example.everyevent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.everyevent.BasicActivity;
import com.example.everyevent.PostInfo;
import com.example.everyevent.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class WritePostActivity extends BasicActivity {
    private static final String TAG = "WritePostActivity";
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        findViewById(R.id.check).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.check:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        final String title = ((EditText)findViewById(R.id.titleEditText)).getText().toString();
        final String contents = ((EditText)findViewById(R.id.contentsEditText)).getText().toString();
        final String address = ((EditText)findViewById(R.id.addressEditText)).getText().toString();
        final String startDate = ((EditText)findViewById(R.id.startDateEditText)).getText().toString();
        final String numberOfPeopleCanApply = ((EditText)findViewById(R.id.numberOfPeopleCanApplyEditText)).getText().toString();
        if(title.length() > 0 && contents.length() > 0 && address.length() >0 && startDate.length() > 0 && numberOfPeopleCanApply.length() >0 ) {

            PostInfo postInfo = new PostInfo(userId,title, contents,address,startDate,numberOfPeopleCanApply);
            uploader(postInfo);

        }else{
            startToast("이벤트를 작성해 주세요");

        }

    }

    private void uploader(PostInfo postInfo) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(postInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG,"DocumentSnapshot written with ID:"+documentReference.getId());
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG,"Error adding document",e);
                    }
                });


    }
    private void startToast(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();
    }

}
