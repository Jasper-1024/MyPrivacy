package Repository.Room.Base;

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.CheckBox;

import java.lang.reflect.Field;

import Base.LogUtil;
import Repository.Room.ItemHook;
import Repository.Room.hookgetList;
import Ui.Databind.BindingAdapterItem;
import Ui.Item.BaseItem;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

import static android.support.constraint.Constraints.TAG;

@SuppressWarnings("ALL")
public abstract class BaseHookGroup extends BaseItem implements hookgetList {

    public String group_Name = "default";
    public boolean value = false;
    public boolean load = false;
    public ObservableArrayList<BindingAdapterItem> items;

    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public ObservableArrayList<BindingAdapterItem> getitems() {
        if (this.items == null) {
            this.items = new ObservableArrayList<>();
        }
        items.clear();
        addItems(this);
        return this.items;
    }

    @Override
    public int getViewType() {
        return R.layout.item_group;
    }

    public BaseHookGroup() {
        //initItems(this);
        setOnClickListener(view -> {
            LogUtil.d("HookGroup", group_Name);
            switch (view.getId()) {
                case R.id.ivExpander:
                    if (getValue()) {
                        setLoad(!load);
                    } else {
                        setLoad(false);
                    }
                    break;
                case R.id.tvLabel:
                    if (getValue()) {
                        setLoad(!load);
                    } else {
                        setLoad(false);
                    }
                    break;
            }
        });
    }

    @Bindable
    public String getGroup_Name() {
        LogUtil.d("BaseHookGroup", "getGroup_Name");
        return group_Name;
    }

    public void setGroup_Name(String group_Name) {
        this.group_Name = group_Name;
        notifyPropertyChanged(BR.group_Name);
    }

    @Bindable
    public boolean getLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
        notifyPropertyChanged(BR.load);
    }

    @Bindable
    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }

    public void valuechange(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        if (!checked) {
            setLoad(false);
        }
        setItems(this, checked);
    }


    public void itemschange(View view) {
        setValue(getValue(this));
    }

    private <T> void setItems(T t, boolean value) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook.setValue(value);
                        LogUtil.d(group_Name, field.getType().toString());
                        break;
                    }
                    default:
                        LogUtil.d(group_Name, "nomatch" + field.getType().toString());

                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(group_Name, e.toString());
        }
    }

    private <T> boolean getValue(T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        if (itemHook.value) {
                            return true;
                        }
                        LogUtil.d(TAG, field.getType().toString());
                        break;
                    }
                    default:
                        LogUtil.d(TAG, "nomatch" + field.getType().toString());

                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(TAG, e.toString());
        }
        return false;
    }

    public  <T> void initItems(T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        itemHook = new ItemHook(field.getName());
                        LogUtil.d(this.group_Name, "initItems" + field.getName());
                        break;
                    }
                    default:
                        LogUtil.d(this.group_Name, "nomatch" + field.getType().toString());
                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(group_Name, e.toString());
        }
    }

    private <T> void addItems(T t) {
        Class c = t.getClass();
        Field[] fields = c.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                switch (field.getType().toString()) {
                    case "class Repository.Room.ItemHook": {
                        ItemHook itemHook = (ItemHook) field.get(t);
                        this.items.add(itemHook);
                        LogUtil.d(this.group_Name, field.getType().toString());
                        break;
                    }
                    default:
                        LogUtil.d(this.group_Name, "nomatch" + field.getType().toString());
                }
            }
        } catch (IllegalAccessException e) {
            LogUtil.d(group_Name, e.toString());
        }
    }


}