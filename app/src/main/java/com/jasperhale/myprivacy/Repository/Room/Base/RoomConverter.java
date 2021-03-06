package com.jasperhale.myprivacy.Repository.Room.Base;

import android.arch.persistence.room.TypeConverter;

import com.jasperhale.myprivacy.Repository.Room.ItemHook;

public class RoomConverter {
    @TypeConverter
    public static ItemHook revertDate(boolean value) {
        return new ItemHook(value);
    }

    @TypeConverter
    public static boolean converterDate(ItemHook value) {
        return value.getValue();
    }
}
