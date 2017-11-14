import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import bean.DataBean;


/**
 * Created by SJD on 2017/10/20.
 *
 * @author SJD
 */

public class TestDataAty extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_test);
    }

    int i = 0;

    public void add(View view) {
        DataUtils.add(new DataBean("msg " + (i++)));
    }

    public void autoGetOld(View view) {
        DataUtils.autoGetOld();
    }

    public void queryAll(View view) {
        DataUtils.queryAll();
    }

    public void autoGetLast(View view) {
        DataUtils.autoGetLast();
    }

    public void loadAll(View view) {
        DataUtils.loadAll();
    }

    public void del(View view) {
//        DataUtils.del();
    }

    public void delKey(View view) {
    }



}
