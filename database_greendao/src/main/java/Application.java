import android.content.Context;

/**
 * Created by SJD on 2017/11/14.
 */

public class Application extends android.app.Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initGreenDaoData();
    }

    /**
     * 初始化数据库
     */
    private void initGreenDaoData() {
        DataManager.getInstance();
    }

}
