package com.charjack.bohe.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class PagerPassageInfo implements Serializable {

    private ArrayList<PassageInfo> items;
    private NextPassageUrlInfo paging;

    public PagerPassageInfo(ArrayList<PassageInfo> items) {
        this.items = items;
    }

    public NextPassageUrlInfo getPaging() {
        return paging;
    }

    public void setPaging(NextPassageUrlInfo paging) {
        this.paging = paging;
    }

    public ArrayList<PassageInfo> getItems() {
        return items;
    }

    public void setItems(ArrayList<PassageInfo> items) {
        this.items = items;
    }


}
