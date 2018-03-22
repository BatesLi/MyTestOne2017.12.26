package com.example.test.base;

import android.app.Application;
import android.content.Context;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

/**
 * 本App的Application
 * Created by liu on 2016/10/12.
 */
public class App extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {

        mContext = getApplicationContext();

        super.onCreate();
        // 配置Realm数据库
        RealmConfiguration configuration = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(6)
                .migration(new RealmMigration()
                {

                    @Override
                    public void migrate(DynamicRealm realm, long oldVersion, long newVersion)
                    {

                    }
                }).build();

        Realm.setDefaultConfiguration(configuration);

    }
    public static Context getContext() {
        return mContext;
    }
}
