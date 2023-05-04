package com.bizmaxsol.rrmob.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.bizmaxsol.rrmob.models.Client;
import com.bizmaxsol.rrmob.models.QuestionDefault;
import com.bizmaxsol.rrmob.models.ResponseUser;
import com.bizmaxsol.rrmob.models.Status;
import com.bizmaxsol.rrmob.repositories.LoginRepository;

import java.util.List;


public class LoginViewModel extends AndroidViewModel {

    //Data
    private String uLogin;
    private String uPass;
    private int ipinpass=0;

    private String spLogin;
    private String spPassword;
    private String spPin;
    private String spQuestion;
    private String spAnswer;
    private LoginRepository loginRepository;
    private Client client;




    String spID;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository=new LoginRepository(application);
    }

    //Getter
    public String getuLogin() { return uLogin; }
    public int getIpinpass() { return ipinpass; }



    //Setter
    public void setuLogin(String uLogin) { this.uLogin = uLogin; }
    public void setuPass(String uPass) { this.uPass = uPass; }
    public void setIpinpass(int ipinpass) { this.ipinpass = ipinpass; }
    public void setSpID(String spID) { this.spID = spID; }
    public void setSpLogin(String spLogin) { this.spLogin = spLogin; }
    public void setSpPassword(String spPassword) { this.spPassword = spPassword; }
    public void setSpPin(String spPin) { this.spPin = spPin; }
    public void setSpQuestion(String spQuestion) { this.spQuestion = spQuestion; }
    public void setSpAnswer(String spAnswer) { this.spAnswer = spAnswer; }



    //Repositories call

    public LiveData<List<Client>> getClientList(String cName){ return loginRepository.getClient(cName); }

    public LiveData<ResponseUser> checkLoginFromNetwork(){
        return loginRepository.checkUser(uLogin, uPass, ipinpass);
    }
    public LiveData<Status> setpinLiveData(){
        ResponseUser user=new ResponseUser(spID,spPassword,spQuestion,spAnswer,spPin,spLogin);
        return loginRepository.setPin(user);
    }
    public LiveData<List<QuestionDefault>> getQuestion(){ return loginRepository.requestQuestion(); }




    //from database
    public Client getClient(){
        client=loginRepository.getClientFromDatabase();
        return client;
    }
    public void insertClient(Client c){
        loginRepository.insertClientToDatabase(c);
    }
    public void deleteClient(){
        loginRepository.deleteClientFromDatabase(client);
    }

    public void insertUser(ResponseUser responseUser){
        loginRepository.insertUsertoDatabase(responseUser);
    }
}
