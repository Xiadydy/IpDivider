package com.gdut.ipdivider.tool;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class StatService {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<Map<String, Object>> object2Map(List<?> source) {
        List<Map<String, Object>> listStat = new ArrayList<Map<String, Object>>();
        if (source != null && !source.isEmpty()) {
            for (Object dto : source) {
                String statStr = JSONObject.toJSONStringWithDateFormat(dto, "yyyy-MM-dd HH:mm:ss");
                Map m = JSONObject.parseObject(statStr, Map.class);
                listStat.add(m);
            }
        }
        return listStat;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<Map<String, Object>> object2Map(List<?> source, String dateFormat) {
        List<Map<String, Object>> listStat = new ArrayList<Map<String, Object>>();
        if (source != null && !source.isEmpty()) {
            for (Object dto : source) {
                String statStr = JSONObject.toJSONStringWithDateFormat(dto, dateFormat);
                Map m = JSONObject.parseObject(statStr, Map.class);
                listStat.add(m);
            }
        }
        return listStat;
    }
}
