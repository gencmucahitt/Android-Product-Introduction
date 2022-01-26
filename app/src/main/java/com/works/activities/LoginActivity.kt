package com.works.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.works.utils.UserLoginObject
import com.works.databinding.ActivityLoginBinding
import com.works.models.UserLogin
import com.works.service.Service
import com.works.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var bind:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()
        val service=ApiClient.getClient().create(Service::class.java)

        bind.loginButton.setOnClickListener{
            val email =bind.txtLoginEmail.text.toString().trim()
            val password =bind.txtLoginPassword.text.toString().trim()
            if(email.isEmpty()){
                bind.txtLoginEmail.error = "Email Girişi Yapmadınız!"
                bind.txtLoginEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                bind.txtLoginPassword.error = "Şifre Girişi Yapmadınız!"
                bind.txtLoginPassword.requestFocus()
                return@setOnClickListener
            }
            val dataService = service.userLogin(email,password)
            dataService.enqueue(object : Callback<UserLogin>{
                override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                    if (response.isSuccessful){
                        val u=response.body()
                        val durum = u?.user?.get(0)?.durum
                        if (durum == true){
                            UserLoginObject.userId = u?.user?.get(0)?.bilgiler?.userId
                            val intent = Intent(this@LoginActivity, Product::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, "E-Posta veya Şifre Hatalı Tekrar Giriş Yapınız", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    Log.d("onFailure Login", "onFailure: $t ")
                }

            })
        }
        bind.textRegisterGecis.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
            finish()
        }
    }
}