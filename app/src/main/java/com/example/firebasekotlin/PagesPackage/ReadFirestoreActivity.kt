package com.example.firebasekotlin.PagesPackage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlin.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_read_firestore.*

class ReadFirestoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_firestore)

        readFirestore()
    }

    private fun readFirestore() {
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    textViewFirestore.append(document.data["fullName"].toString() + "\n")
                    Log.i("check1", document.data["fullName"].toString() + "\n")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("check1", "Error getting documents: ", exception)
            }
    }

}
