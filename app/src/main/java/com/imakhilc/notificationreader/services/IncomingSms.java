package com.imakhilc.notificationreader.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.telephony.SmsMessage;
import android.util.Log;

public class IncomingSms extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    //TODO: call the API here!

                    Intent msgrcv = new Intent("Msg");
                    msgrcv.putExtra("package", "");
                    msgrcv.putExtra("ticker", senderNum);
                    msgrcv.putExtra("title", senderNum);
                    msgrcv.putExtra("text", message);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }
}