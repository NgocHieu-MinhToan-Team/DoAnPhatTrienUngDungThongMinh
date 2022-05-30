package com.example.pepperluchapplication.Service;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static com.example.pepperluchapplication.Service.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pepperluchapplication.ActivityHomePage;
import com.example.pepperluchapplication.Adapter.RV_OrdersAdapter;
import com.example.pepperluchapplication.DTO.CART;
import com.example.pepperluchapplication.DTO.ORDER;
import com.example.pepperluchapplication.Fragments.fragmentHistory;
import com.example.pepperluchapplication.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class MyService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // nếu dùng bound service thì cần return IBinder
        // dùng unbound thì return null
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e( "Toan says: ","Toan's service onCreate" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ArrayList<ORDER> values = new ArrayList<>();
        ArrayList<String> keys = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://dbpepperlunch-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = database.getReference("Database/Order/"+MyApplication.getCustomer().getID_CUSTOMER());
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ORDER value = snapshot.getValue(ORDER.class);
                String key=snapshot.getKey();
                keys.add(key);
                values.add(keys.indexOf(key),value);
                Bundle bundle = intent.getExtras();
                Log.e( "Toan says: ","Toan's is add");
                if(bundle!=null){
                    ORDER order= (ORDER) bundle.get("YourOrder");
                    sendNotification(order);
                }
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ORDER value = snapshot.getValue(ORDER.class);
                String key=snapshot.getKey();
                values.set(keys.indexOf(key),value);
                Log.e( "Toan says: ","Toan's is changed");
                stopSelf();
                Toast.makeText(MyService.this, "Đơn Hàng Đã Được Xác Nhận !", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        Bundle bundle = intent.getExtras();
//        if(bundle!=null){
//            ORDER order= (ORDER) bundle.get("YourOrder");
//            sendNotification(order);
//            Log.e( "Toan says: ","Toan's is running");
//
//        }
        return START_NOT_STICKY;
    }

    private void sendNotification( ORDER order) {
        //        click vào notifiction sẽ mở activity nào lên
        //Intent intent = new Intent(this, fragmentHistory.class);
        Intent intent = new Intent(this, ActivityHomePage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        // add layout into notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.layout_notification_custom);
        remoteViews.setTextViewText(R.id.tv_notification_title,order.getID_CUSTOMER());
        // get count all product in list cart
        int count=0;
        for(Map.Entry<String, CART> cart : order.getLIST_CART().entrySet()) {
                count+= cart.getValue().getSoluong();
        }
        String content = "Đơn Hàng "+count + "món";
        remoteViews.setTextViewText(R.id.tv_notification_content,content);


        Notification notify = new NotificationCompat.Builder(this,CHANNEL_ID)
               .setSmallIcon(R.drawable.pepperlunch_logo)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews).build();

        startForeground(1,notify);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Toan says: ","Toan's service onDestroy" );

    }
}
