package Ui.Item;

import com.jasperhale.myprivacy.R;

import Repository.Model.AppInfo;

public class LoadItem extends AppItem{
    @Override
    public int getViewType() {
        return R.layout.item_loaddata;
    }
    public LoadItem(){
        super(null);
    }
}
