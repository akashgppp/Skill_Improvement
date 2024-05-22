package com.skysoftsolution.`in`.skill_improvement.attendance.userRegistration.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.skysoftsolution.`in`.skill_improvement.attendance.userRegistration.entity.RegisterEntity


@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM Register_users_table WHERE Person_ID LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

}