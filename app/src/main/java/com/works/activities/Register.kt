package com.works.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.works.databinding.ActivityRegisterBinding
import com.works.models.UserRegister
import com.works.service.Service
import com.works.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {
    private lateinit var bind :ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        val service= ApiClient.getClient().create(Service::class.java)

        bind.registerButton.setOnClickListener {
            val name = bind.txtName.text.toString().trim()
            val surname = bind.txtSurname.text.toString().trim()
            val phone = bind.txtNumber.text.toString().trim()
            val email = bind.txtEmail.text.toString().trim()
            val password=bind.txtPassword.text.toString().trim()

            if(email.isEmpty()){
                bind.txtEmail.error = "Email Girişi Yapmadınız!"
                bind.txtEmail.requestFocus()
                return@setOnClickListener
            }
            if(surname.isEmpty()){
                bind.txtSurname.error = "Soyadı Girişi Yapmadınız!"
                bind.txtSurname.requestFocus()
                return@setOnClickListener
            }
            if(phone.isEmpty()){
                bind.txtNumber.error = "Telefon Numarası Girişi Yapmadınız!"
                bind.txtNumber.requestFocus()
                return@setOnClickListener
            }
            if(name.isEmpty()){
                bind.txtName.error = "Email Girişi Yapmadınız!"
                bind.txtName.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                bind.txtPassword.error = "Şifre Girişi Yapmadınız!"
                bind.txtPassword.requestFocus()
                return@setOnClickListener
            }
            val dataService = service.userRegister(name,surname,phone,email,password)
            dataService.enqueue(object : Callback<UserRegister>{
                override fun onResponse( call: Call<UserRegister>,response: Response<UserRegister>) {
                    if (response.isSuccessful){
                        val reg=response.body()
                        val durum = reg?.user?.get(0)?.durum
                        val mesaj = reg?.user?.get(0)?.mesaj
                        if (durum == true){
                            Toast.makeText(this@Register, "Bilgi : " + mesaj, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Register, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this@Register, "Bilgi : "+mesaj, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<UserRegister>, t: Throwable) {
                    Log.d("onFailure Register", "onFailure: $t")
                }

            })
        }
        bind.txtGetLogin.setOnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }
    }

}