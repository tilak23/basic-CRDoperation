package crdoperation;

import crdoperation.Json;

class Pair {
    private String key;
    private Json value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Json value) {
        this.value = value;
    }
    public long size(){
        return this.toString().getBytes().length;
    }
    public Json getValue(){
        return  this.value;
    }
    public  boolean containsKey(String key){
        return this.key.equals(key);
    }
    @Override
    public String toString() {
        return this.key+"="+this.value.toString();
    }
}
