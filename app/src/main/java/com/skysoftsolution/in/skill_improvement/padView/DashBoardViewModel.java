package com.skysoftsolution.in.skill_improvement.padView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.skysoftsolution.in.paymentintegration.dashboard.entity.ModuleForUse;

import java.util.ArrayList;
import java.util.List;

public class DashBoardViewModel extends ViewModel {
    private MutableLiveData<List<ModuleForUse>> userList;

    public DashBoardViewModel() {
        userList = new MutableLiveData<>();
        userList.setValue(new ArrayList<>());
    }

    public LiveData<List<ModuleForUse>> getUserList() {
        return userList;
    }

    public void addModule(ModuleForUse module) {
        List<ModuleForUse> currentList = userList.getValue();
        if (currentList != null) {
            currentList.add(module);
            userList.setValue(currentList);
        }
    }
}
