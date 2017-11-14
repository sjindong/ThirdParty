import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import java.util.List;

import auto.DataBeanDao;
import bean.DataBean;
import cn.digirun.update.data.autocreate.DataBeanDao;
import cn.digirun.update.receiver.MsgReceiver;

/**
 * Created by SJD on 2017/10/19.
 *
 * @author SJD
 */

public class DataUtils {
    public static String TAG = "OTA DataUtils";

    /**
     * 获取对应的表
     *
     * @return
     */
    public static DataBeanDao user() {
        return DataManager.getInstance().getNewSession().getDataBeanDao();
    }


    /**
     * 添加一条数据
     *
     * @param dataBean 数据单元
     */
    public static void add(DataBean dataBean) {
        // 当指定主键在表中存在时会发生异常
        user().insert(dataBean);

        //当指定主键在表中存在时会覆盖数据
        //user().insertOrReplace(dataBean);

        //批量插入数据
        //insertInTx(Iterable < T > entities)
    }

    public static void del(Context context, String msg) {
        Log.e(TAG, "del: msg =" + msg);
        DataBean data = find(msg);
        del(context, data);
    }

    /**
     * 删除
     *
     * @param context context上下文
     * @param user    数据
     */
    public static void del(final Context context, DataBean user) {
//        User user = new User(null, name, age, studentId);
        log("del", user);
        if (user == null) {
            return;
        }
        user().delete(user);
        if (context != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    //请求处理新的Msg
                    intent.setAction(MsgReceiver.HANDLE_NEWMSG);
                    context.sendBroadcast(intent);
                }
            }, 5 * 1000);
        }
//        delete(T entity) 删除数据
//        deleteByKey(K key) 指定主键删除数据
//        deleteInTx(Iterable < T > entities) 批量删除数据
//        deleteByKeyInTx(Iterable < K > keys) 批量按数据删除数据
//        deleteAll() 删除所有数据
    }

    public static void delKey(long key) {
        Log.e(TAG, "delKey:  key = " + key);
        user().deleteByKey(key);
    }

    /**
     * 改：
     *
     * @param user 数据
     */
    public static void update(DataBean user) {
//        User user = new User(id, name, age, studentId);
        log("update", user);
        user().update(user);

//        update(T entity) 修改数据，
//
//        主键需相同
//        updateInTx(Iterable < T > entities) 批量更新数据
    }

    /**
     * 查：
     *
     * @param id id序列号
     */
    public static void find(long id) {
        Log.e(TAG, "find: id = " + id);
        user().load(id);

//        load(K key) 根据id查找数据
//        loadByRowId( long rowId)根据行号查找数据
//        loadAll() 查找全部数据
    }

    public static DataBean find(String msg) {
        Log.e(TAG, "find: msg = " + msg);
        return user().queryBuilder().where(DataBeanDao.Properties.Msg.eq(msg)).build().unique();
    }


    public static List<DataBean> loadAll() {
        List<DataBean> data = user().loadAll();
        for (DataBean d : data) {
            log("loadAll", d);
        }
        return data;
    }

    public static void log(String mothed, DataBean dataBean) {
        if (dataBean == null) {
            Log.e(TAG, mothed + ":  dataBean is null");
        } else {
            Log.e(TAG, mothed + ":  id = " + dataBean.getId() + "  msg = " + dataBean.getMsg() + " state = " + dataBean.getState());
        }

    }

    private static void delAndCreatTable() {
        //  删表和建表
        Database database = DataManager.getInstance().getNewSession().getDatabase();
        DataBeanDao.dropTable(database, true);
        DataBeanDao.createTable(DataManager.getInstance().getNewSession().getDatabase(), true);

    }
}
