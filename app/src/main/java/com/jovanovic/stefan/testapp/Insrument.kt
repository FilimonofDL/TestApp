package com.jovanovic.stefan.testapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_insrument.*
import org.w3c.dom.Comment
//import com.google.firebase.referencecode.database.kotlin.models.Post


class Insrument : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insrument)
        lateinit var mAuth: FirebaseAuth
        val database = FirebaseDatabase.getInstance(getString(R.string.firebaseadress))
        val myRef = database.getReference("message")

        btInstrSave.setOnClickListener {
            System.out.println("Save Instr");
            val type = TypeInstrumObj(etInstrType.text.toString())
            val sostoyanieObj = SostoyanieObj(etInstrSost.text.toString())
            val ukogoObj = UkogoObj(etInstrUkogo.text.toString())

            val insrumObj = InstrumObj(
                etInstrId.text.toString(),
                etInstrName.text.toString(),
                etInstrType.text.toString(),
                etInstrStoim.text.toString(),
                etInstrDate.text.toString(),
                etInstrSost.text.toString(),
                etInstrUkogo.text.toString(),
                etInstrSN.text.toString()
            )

            val fireUser = FirebaseAuth.getInstance().currentUser
            if (fireUser != null) {
                System.out.println(" User " + fireUser.uid)
                // User is signed in
            } else {
                // No user is signed in
            }


            val user = UkogoObj("name")

            val objToBase = InsrumIdObj(insrumObj.id, insrumObj)
            database.reference.child("instrument").setValue(objToBase)



        }

        btInstrMySQL.setOnClickListener {
            System.out.println("Mysql");

        }
//        private fun addMySQL() {
//
//            val url:String = "http://198.128.34.23/main.php?op=dbadd"
//            //      val rq:RequestQueue=Volley.newRequestQueue(this)
//            val stringRequest = object: StringRequest(Request.Method.POST, url,
//                Response.Listener<String> { response ->
//                    // Process the json
//                    try {
//                        val obj = JSONObject(response)
//                        db_display.text = obj.getString("message")
//                    }catch (e:Exception){
//                        db_display.text = "Exception: $e"
//                    }
//
//                }, Response.ErrorListener { error ->
//                    db_display.text = error.message
//                }) {
//
//
//                @Throws(AuthFailureError::class)
//                override fun getParams(): Map<String, String>
//                {
//                    val params = HashMap<String, String>()
//                    params.put("name", "Maria24")
//                    params.put("role", "Parthena243")
//                    return params
//
//                }
//
//            }
//            // Add the volley post request to the request queue
//            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
//
//
//
//        }
        database.reference.child("instrument").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                val value = dataSnapshot.getValue<InsrumIdObj>()
                Log.d(TAG, "Value is: ")
        }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Value is Failed to read value.", error.toException())
            }
        }
        )









    }}