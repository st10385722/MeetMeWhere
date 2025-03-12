package com.example.meetmewherejerry

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDAO {
    @Insert
    //change made here
    fun insertUser(users: Users)
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<Users>>
    @Query("DELETE FROM users where id = :id")
    fun deleteUser(id: Int)
    @Query("SELECT * FROM users where username = :username LIMIT 1")
    fun getSingleUser(username: String) : Users?
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun isValidUser(username: String, password: String) : Users?
}