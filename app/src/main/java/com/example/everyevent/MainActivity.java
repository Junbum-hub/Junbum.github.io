package com.example.everyevent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

public class MainActivity extends BasicActivity {

    CreateQRActivity createQRActivity;
    PostListActivity postListActivity;

    private static final String TAG = "MainActivity";
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            myStartActivity(SignUpActivity.class);
        }else{
            //myStartActivity(MemberInitActivity.class);
            //myStartActivity(CameraActivity.class);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document!=null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                myStartActivity(MemberInitActivity.class);
                                Log.d(TAG, "No such document");
                            }

                        }

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
            //회원가입 or 로그인

        }
        createQRActivity = new CreateQRActivity();
        postListActivity = new PostListActivity();
        container = findViewById(R.id.layout_fragment);
        findViewById(R.id.QRButton).setOnClickListener(onClickListener);
        findViewById(R.id.eventMakingButton).setOnClickListener(onClickListener);
        findViewById(R.id.recentEventButton).setOnClickListener(onClickListener);
        findViewById(R.id.HomeButton).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.QRButton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment,createQRActivity).commit();
                    break;
                case R.id.eventMakingButton:
                    myStartActivity(WritePostActivity.class);
                    break;
                case R.id.recentEventButton:

                    break;
                case R.id.announcementButton:

                    break;
                case R.id.HomeButton:
                    getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment,postListActivity).commit();
                    break;
            }



        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.person_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curld = item.getItemId();
        switch(curld) {
            case R.id.userInfo:

                break;
            case R.id.eventInterested:

                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                myStartActivity(SignUpActivity.class);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }



}