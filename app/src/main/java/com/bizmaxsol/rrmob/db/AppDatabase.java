package com.bizmaxsol.rrmob.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bizmaxsol.rrmob.models.Client;
import com.bizmaxsol.rrmob.models.ResponseUser;

@Database(entities = {Client.class, ResponseUser.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

        public abstract ClientDao clientDao();

        public abstract ResponseUserDao userDao();

}