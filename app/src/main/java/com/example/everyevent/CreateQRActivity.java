package com.example.everyevent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import java.lang.Exception;

public class CreateQRActivity extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.qr_code_create,container,false);

        try {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap;
            Log.e("성공",user.getUid());
            bitmap = barcodeEncoder.encodeBitmap(user.getUid(), BarcodeFormat.QR_CODE, 400, 400);
            ImageView ImageViewQRCode = (ImageView) rootView.findViewById(R.id.imageViewQrCode);
            ImageViewQRCode.setImageBitmap(bitmap);

        } catch (Exception e) {


        }
            return rootView;
        }


}