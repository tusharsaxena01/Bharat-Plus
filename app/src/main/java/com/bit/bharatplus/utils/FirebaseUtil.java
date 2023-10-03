package com.bit.bharatplus.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bit.bharatplus.classes.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUtil {

    public static UserModel getCurrentUserDetails(Context context, String uid){
        final UserModel[] userModel = new UserModel[1];
        FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            userModel[0] = snapshot.getValue(UserModel.class);
                            if(userModel[0] == null){
                                AndroidUtils.showAlertDialog(context, "Error", "Server Not Responding");
                                Log.w("check", "Error fetching current user info");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        AndroidUtils.showAlertDialog(context, "Error", error.getMessage());
                    }
                });
        return userModel[0];
    }

}
