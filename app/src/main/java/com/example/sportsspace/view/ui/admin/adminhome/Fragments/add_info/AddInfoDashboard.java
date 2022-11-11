package com.example.sportsspace.view.ui.admin.adminhome.Fragments.add_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.sportsspace.R;
import com.example.sportsspace.databinding.AddInfoBinding;
import com.example.sportsspace.model.dashboardmodel.DashboardMethods;
import com.example.sportsspace.viewmodels.AddInfoDashVM;


import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class AddInfoDashboard extends Fragment {

    @Inject
    DashboardMethods dashboardModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddInfoBinding addInfoBinding = DataBindingUtil.inflate(
                inflater , R.layout.add_info ,container , false
        );

        addInfoBinding.setAddDashboardVM(new AddInfoDashVM(dashboardModel));
        addInfoBinding.executePendingBindings();
        View view = addInfoBinding.getRoot();

        return view;
    }

}
