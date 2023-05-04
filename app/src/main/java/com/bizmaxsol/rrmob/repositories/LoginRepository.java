package com.bizmaxsol.rrmob.repositories;



import static com.bizmaxsol.rrmob.constants.AppClientConstants.CLIENT_SERVICE;
import static com.bizmaxsol.rrmob.constants.AppConstants.loginService;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;


import com.bizmaxsol.rrmob.db.AppDatabase;
import com.bizmaxsol.rrmob.db.ClientDao;
import com.bizmaxsol.rrmob.db.ResponseUserDao;
import com.bizmaxsol.rrmob.models.Client;
import com.bizmaxsol.rrmob.models.QuestionDefault;
import com.bizmaxsol.rrmob.models.ResponseUser;
import com.bizmaxsol.rrmob.models.Status;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static final String TAG=LoginRepository.class.getSimpleName();
    MutableLiveData<ResponseUser> data=new MutableLiveData<>();
    ClientDao clientDao;
    ResponseUserDao responseUserDao;




    public LoginRepository(Application application){
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "rrmob_database").allowMainThreadQueries().build();
        clientDao=db.clientDao();
        responseUserDao =db.userDao();
    }

    public Client getClientFromDatabase() { return clientDao.getClient(); }
    public void deleteClientFromDatabase(Client client){ clientDao.deleteClient(client); }
    public void insertClientToDatabase(Client client) {
        clientDao.insertClient(client);
    }

    public ResponseUser getUserFromDatabase(){return  responseUserDao.getUser();}
    public void deleteUserFromDatabase(ResponseUser responseUser){
        responseUserDao.deleteUser(responseUser);}
    public void insertUsertoDatabase(ResponseUser responseUser){
        if (getUserFromDatabase()==null){
            responseUserDao.insertUser(responseUser);
        }else{
            deleteUserFromDatabase(getUserFromDatabase());
            responseUserDao.insertUser(responseUser);
        }
    }

    public LiveData<List<Client>> getClient(String cName){
        MutableLiveData<List<Client>> q=new MutableLiveData<>();
        Call<List<Client>> call=CLIENT_SERVICE.getClient(cName);
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                q.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
            }
        });
        return q;
    }

    public LiveData<ResponseUser> checkUser(String uId, String uPin, int loginWith){

        Call<ResponseUser> call;
        if(loginWith==1){
            call=loginService.getLoginPass(uId,uPin);
        }else{
            call=loginService.getLoginPin(uId,uPin);
        }
        call.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {

                if(response.isSuccessful()) {
                    data.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
            }
        });
        return data;
    }


    public LiveData<List<QuestionDefault>> requestQuestion(){
        MutableLiveData<List<QuestionDefault>> q=new MutableLiveData<>();
        Call<List<QuestionDefault>> call=loginService.getQuestion();
        call.enqueue(new Callback<List<QuestionDefault>>() {
            @Override
            public void onResponse(Call<List<QuestionDefault>> call, Response<List<QuestionDefault>> response) {
                q.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<QuestionDefault>> call, Throwable t) {

            }
        });
        return q;
    }

    public LiveData<Status> setPin(ResponseUser user){
        MutableLiveData<Status> s=new MutableLiveData<>();
        Call<Status> call=loginService.editUser(user.getUsersapp_nid(),user);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                s.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Status status=new Status();
                status.setStatus(3);
                s.setValue(status);
            }
        });
        return s;
    }
}
