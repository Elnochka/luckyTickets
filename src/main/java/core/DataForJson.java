package core;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class DataForJson {

    private SortedMap<String,Integer> dataJson = new TreeMap<>();
    private SortedMap<String, List<String>> listLucky = new TreeMap<>();

    public DataForJson() {
    }

    public SortedMap<String, Integer> getDataJson() {
        return dataJson;
    }

    public SortedMap<String, List<String>> getListLucky() {
        return listLucky;
    }

    @Override
    public String toString() {
        return "DataForJson{" +
                "dataJson=" + dataJson +
                ", listLucky=" + listLucky +
                '}';
    }
}
