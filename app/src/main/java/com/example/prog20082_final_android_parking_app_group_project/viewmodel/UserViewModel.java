package com.example.prog20082_final_android_parking_app_group_project.viewmodel;

import android.app.Application;

import com.example.prog20082_final_android_parking_app_group_project.db.UserRepository;
import com.example.prog20082_final_android_parking_app_group_project.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by ammarkhan
 * <p>
 * studentID: 991439943
 * <p>
 * on 2019-11-26
 */
public class UserViewModel extends AndroidViewModel
{
    private LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application)
    {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public void insert (User user)
    {
        userRepository.insert(user);
    }

    public LiveData<List<User>> getAllUsers() {

        return allUsers;
    }

    public void deleteUserByEmail(String email)
    {
        userRepository.deleteUserByEmail(email);
    }

    public void updateUser(User user){ userRepository.updateUser(user);}

}
