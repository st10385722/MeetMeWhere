package com.example.meetmewherejerry

import androidx.lifecycle.LiveData

class UsersViewModel {
    val userDao = MainApplication.appDatabase.getUserDao()
    val usersList: LiveData<List<Users>> = userDao.getAllUsers()

    fun insertUser(username: String, password: String){
        userDao.insertUser(Users(
            username = username,
            password = password
        ))
    }
    fun deleteUser(id: Int){
        userDao.deleteUser(id)
    }
    fun getSingleUser(username: String) : Users?{
        return userDao.getSingleUser(username)
    }
    fun isValidUser(username: String, password: String) : Users?{
        return userDao.isValidUser(username, password)
    }
    fun getAllUsers(){
        usersList
    }
}