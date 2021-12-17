package com.nguyenvanhoa.book_app_reading.User.Home.Interface;


import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemBookGroup;
import com.nguyenvanhoa.book_app_reading.User.Home.Model.ItemGroup;

import java.util.List;

public interface IFirebaseLoadListener {
    void onFirebaseLoadsuccess (List<ItemGroup> itemGroupList);
    void onFirebaseLoadsuccess1(List<ItemBookGroup> itemGroupList);
    void onFirebaseLoadFailed (String messenger);
}
