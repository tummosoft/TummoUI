package com.tummosoft.Utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Xml;
import anywheresoftware.b4a.BA;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneHelper {
     private static Context baContext;

    public void initialize(final BA ba) {
        baContext = ba.context;         
    }
    
    public static boolean isPhone() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }
       
    @SuppressLint("HardwareIds")
    public static String getIMEI() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getDeviceId() : null;
    }

    
    @SuppressLint("HardwareIds")
    public static String getIMSI() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getSubscriberId() : null;
    }
       
    public static int getPhoneType() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getPhoneType() : -1;
    }
    
    public static boolean isSimCardReady() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }
        
    public static String getSimOperatorName() {
        TelephonyManager tm = (TelephonyManager) baContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null ? tm.getSimOperatorName() : null;
    }
       
    public static void dial(String phoneNumber) {
        baContext.startActivity(IntentHelper.getDialIntent(phoneNumber));
    }

   
    public static void call(String phoneNumber) {
        baContext.startActivity(IntentHelper.getCallIntent(phoneNumber));
    }
    
    public static void sendSms(String phoneNumber, String content) {
        baContext.startActivity(IntentHelper.getSendSmsIntent(phoneNumber, content));
    }

   
    public static void sendSmsSilent(String phoneNumber, String content) {
        
        PendingIntent sentIntent = PendingIntent.getBroadcast(baContext, 0, new Intent(), 0);
        SmsManager smsManager = SmsManager.getDefault();
        if (content.length() >= 70) {
            List<String> ms = smsManager.divideMessage(content);
            for (String str : ms) {
                smsManager.sendTextMessage(phoneNumber, null, str, sentIntent, null);
            }
        } else {
            smsManager.sendTextMessage(phoneNumber, null, content, sentIntent, null);
        }
    }

    public static List<HashMap<String, String>> getAllContactInfo() {
        SystemClock.sleep(3000);
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();        
        ContentResolver resolver = baContext.getContentResolver();
        Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri date_uri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"}, null, null, null);
        try {            
            if (cursor != null) {
                while (cursor.moveToNext()) {                   
                    String contact_id = cursor.getString(0); 
                        Cursor c = resolver.query(date_uri, new String[]{"data1",
                                        "mimetype"}, "raw_contact_id=?",
                                new String[]{contact_id}, null);
                        HashMap<String, String> map = new HashMap<String, String>();                      
                        if (c != null) {
                            while (c.moveToNext()) {                                
                                String data1 = c.getString(0);
                                String mimetype = c.getString(1);                                
                                if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {                                    
                                    map.put("phone", data1);
                                } else if (mimetype.equals("vnd.android.cursor.item/name")) {                                    
                                    map.put("name", data1);
                                }
                            }
                        }                        
                        list.add(map);                        
                        if (c != null) {
                            c.close();
                        }
                    
                }
            }
        } finally {            
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
       
    public static void getAllSMS() {
        
        ContentResolver resolver = baContext.getContentResolver();       
        Uri uri = Uri.parse("content://sms");        
        Cursor cursor = resolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, null);        
        int count = cursor.getCount();        
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {            
            xmlSerializer.setOutput(new FileOutputStream(new File("/mnt/sdcard/backupsms.xml")), "utf-8");            
            xmlSerializer.startDocument("utf-8", true);            
            xmlSerializer.startTag(null, "smss");            
            while (cursor.moveToNext()) {
                SystemClock.sleep(1000);                
                xmlSerializer.startTag(null, "sms");                
                xmlSerializer.startTag(null, "address");
                String address = cursor.getString(0);                
                xmlSerializer.text(address);
                xmlSerializer.endTag(null, "address");
                xmlSerializer.startTag(null, "date");
                String date = cursor.getString(1);
                xmlSerializer.text(date);
                xmlSerializer.endTag(null, "date");
                xmlSerializer.startTag(null, "type");
                String type = cursor.getString(2);
                xmlSerializer.text(type);
                xmlSerializer.endTag(null, "type");
                xmlSerializer.startTag(null, "body");
                String body = cursor.getString(3);
                xmlSerializer.text(body);
                xmlSerializer.endTag(null, "body");
                xmlSerializer.endTag(null, "sms");
                System.out.println("address:" + address + "   date:" + date + "  type:" + type + "  body:" + body);
            }
            xmlSerializer.endTag(null, "smss");
            xmlSerializer.endDocument();            
            xmlSerializer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
