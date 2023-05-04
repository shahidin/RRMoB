package com.bizmaxsol.rrmob.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bizmaxsol.rrmob.models.ResponseUser;

@Dao
public interface ResponseUserDao {

        @Query("SELECT * FROM responseuser")
        ResponseUser getUser();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertUser(ResponseUser responseUser);

        @Delete
        void deleteUser(ResponseUser responseUser);

}
