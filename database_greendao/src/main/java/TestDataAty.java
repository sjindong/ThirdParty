import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.database_greendao.R;

import auto.DataBeanDao;
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
        DataUtils.add(new DataBean((long) i, "msg " + (i++)));
    }

    public void del(View view) {
        DataUtils.del(DataUtils.autoGetLast());
    }

    public void autoGetOld(View view) {
        DataUtils.autoGetLast();
    }

    public void delAll(View view) {
        DataUtils.delAll();
    }

    public void delKey(View view) {
        DataUtils.delKey((long) DataUtils.lastKey());
    }

    public void update(View view) {
        DataUtils.update(new DataBean((long) i, "msg " + i));
    }

    public void load(View view) {
        for (int j = 0; j < i; j++) {
            DataUtils.load(j);
        }
    }

    public void creatTable(View view) {
        DataUtils.creatTable();
    }

    public void delTable(View view) {
        DataUtils.delTable();
    }
}
