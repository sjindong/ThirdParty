import android.database.Cursor;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import auto.DataBeanDao;
import bean.DataBean;

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

    /**
     * 删除数据
     *
     * @param dataBean 数据单元
     */
    public static void del(DataBean dataBean) {
        user().delete(dataBean);

        // 批量删除数据
        //deleteInTx(Iterable < T > entities)
    }

    /**
     * 删除主键
     *
     * @param key 主键
     */
    public static void delKey(Long key) {
        user().deleteByKey(key);

        //批量按数据删除数据
        //deleteByKeyInTx(Iterable < K > keys)
    }

    /**
     * 删除全部
     */
    public static void delAll() {
        //删除所有数据
        user().deleteAll();
    }


    /**
     * 修改数据
     *
     * @param dataBean 数据
     */
    public static void update(DataBean dataBean) {
        user().update(dataBean);

        //批量更新数据
        //updateInTx(Iterable < T > entities)
    }

    /**
     * 查找数据
     *
     * @param key 主键
     */
    public static DataBean load(long key) {
        return user().load(key);

        //根据行号查找数据
        //loadByRowId( long rowId)
        //查找全部数据
        //loadAll();
    }

    /**
     * 删除表
     */
    public static void delTable() {
        Database database = DataManager.getInstance().getNewSession().getDatabase();
        DataBeanDao.dropTable(database, true);
    }

    /**
     * 新建表
     */
    public static void creatTable() {
        Database database = DataManager.getInstance().getNewSession().getDatabase();
        DataBeanDao.createTable(database, true);
    }

    public static void logData(String mothed, DataBean dataBean) {
        if (dataBean == null) {
            Log.e(TAG, mothed + ":  dataBean is null");
        } else {
            dataBean.toString();
            Log.e(TAG, mothed + ":" + dataBean.toString());
        }
    }

    /**
     * 最后的参数
     *
     * @return
     */
    public static DataBean autoGetLast() {
        DataBean data = user().queryBuilder().where(DataBeanDao.Properties.Id.eq(lastKey())).build().unique();
        return data;
    }

    /**
     * 获取最后的 key
     *
     * @return key int
     */
    public static int lastKey() {
        String sql = "select last_insert_rowid() from " + user().getTablename();
        Cursor cursor = DataManager.getInstance().getSession().getDatabase().rawQuery(sql, null);
        int a = -1;
        if (cursor.moveToFirst()) {
            a = cursor.getInt(0);
        }
        cursor.close();
        Log.e(TAG, "lastID: a =" + a);
        return a;
    }
}
