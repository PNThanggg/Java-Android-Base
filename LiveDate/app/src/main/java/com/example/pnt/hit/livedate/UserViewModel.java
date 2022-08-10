package com.example.pnt.hit.livedate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<User>> mutableLiveData;
    private List<User> listUser;

    public UserViewModel() {
        mutableLiveData = new MutableLiveData<>();
        initData();
    }

    private void initData() {
        listUser = new ArrayList<>();

        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));
        listUser.add(new User(R.mipmap.ic_launcher, "abc", "abc"));

        mutableLiveData.setValue(listUser);
    }

    public MutableLiveData<List<User>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<List<User>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public void addUser(User user) {
        listUser.add(user);
    }
}
