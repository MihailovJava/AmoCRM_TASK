package com.dbulgakov.amocrmlogin.other.accmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AmoCRMAuthenticatorService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return new AmoCRMAuthenticator(this).getIBinder();
    }
}