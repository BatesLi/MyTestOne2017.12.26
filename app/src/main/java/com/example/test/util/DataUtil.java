package com.example.test.util;

import com.example.test.bean.GankMeiziInfo;
import com.example.test.bean.ResultsBean;
import io.realm.Realm;
import java.util.List;

/**
 * 数据库工具类
 * Created by liu on 2016/10/19.
 */
public class DataUtil {
    /**
     * 保存gank妹子到数据库中
     *
     * @param gankMeiziInfos
     */

    public static void putGankMeiziCache(List<GankMeiziInfo> gankMeiziInfos)
    {

        ResultsBean meizi;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 0; i < gankMeiziInfos.size(); i++)
        {
            meizi = new ResultsBean();
            String url = gankMeiziInfos.get(i).url;
            String desc = gankMeiziInfos.get(i).desc;
            meizi.setUrl(url);
            meizi.setDesc(desc);
            realm.copyToRealm(meizi);
        }
        realm.commitTransaction();
        realm.close();
    }
}
