package crdoperation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Json {
    private Map jsonObj;

    public Json() {
        jsonObj = new LinkedHashMap<String,Object>();
    }

    public  Map getJsonObj() {
        return jsonObj;
    }

    public  void setJsonObj(Map jsonObj) {
        this.jsonObj = jsonObj;
    }
    public int size(){
        return  this.toString().getBytes().length;
    }
    @Override
    public String toString() {
        String str="{";
        Set set=jsonObj.entrySet();
        Iterator itr=set.iterator();
        while(itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            str += entry.getKey() + ":" + entry.getValue();
            if(itr.hasNext())
                str+=",";
        }
        str+="}";
        return str;
    }
}
