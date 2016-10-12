package com.longshihan.makeview;

import android.app.Application;

/**
 * @author Administrator
 * @time 2016/10/10 10:26
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class App extends Application {
    private static App sMe;

    public static App getInstance() {
        return sMe;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMe = this;
    }
}
