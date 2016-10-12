package com.longshihan.makeview.view;

import java.util.TimerTask;

/**
 * @author Administrator
 * @time 2016/10/10 13:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class TimeTaskTest extends TimerTask {
    private CircleViewList mList;
    int i = 0;

    public TimeTaskTest(CircleViewList list) {
        mList = list;
    }

    @Override
    public void run() {
        if (i>4) {
            i = 0;
        } else {
            i++;
        }
        mList.setProgress(204 - 8 * (i-5) * (i-5));

    }
}
