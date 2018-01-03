package com.example.root.retrofit;

import android.app.Service;
import android.os.Build;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by root on 1/3/18.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService
{
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(final String refreshedToken)
    {
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(refreshedToken);
        String phone = Build.MODEL;
        ApiRetrofit api = Retrofitserver.getclient().create(ApiRetrofit.class);
        Call<ResponModel> call = api.tokennya(refreshedToken,phone);
        call.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
//                Log.d("hai", "onResponse: "+refreshedToken);
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Log.d("gagal", "onFailure: ");
            }
        });
    }
}
