package com.bizmaxsol.rrmob.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.bizmaxsol.rrmob.models.Client;


@Dao
public interface ClientDao {

        @Query("SELECT * FROM client")
        Client getClient();

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertClient(Client client);

        @Delete
        void deleteClient(Client client);

}
