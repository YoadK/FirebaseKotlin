package com.example.firebasekotlin.PagesPackage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlin.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initActivity()
        buttonsClicks()
    }

    private fun initActivity() {
        mAuth = FirebaseAuth.getInstance()

        mAuthListener = AuthStateListener { firebaseAuth: FirebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@LoginActivity, ReadFirestoreActivity::class.java))
                finish()
            }
        }
    }

    private fun buttonsClicks() {
        btnLogin.setOnClickListener {
            loginMethod()
        }
    }

    private fun loginMethod() {
        mAuth?.signInWithEmailAndPassword(emailET.text.toString(), passwordET.text.toString())
            ?.addOnCompleteListener(this@LoginActivity)
            { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    Log.d("check1", "signInWithEmail:success")
                    val intent = Intent(this@LoginActivity, ReadFirestoreActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("check1", "singInWithEmail:Fail")
                    Toast.makeText(this@LoginActivity, "The login failed", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

}
