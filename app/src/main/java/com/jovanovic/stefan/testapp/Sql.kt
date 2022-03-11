package com.jovanovic.stefan.testapp

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_sql.*
import org.json.JSONException
import org.json.JSONObject
import java.sql.DriverManager

class Sql : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql)
        System.out.println("Respons   "+getString(R.string.showcars_php))


        btSql1.setOnClickListener {
//            System.out.println("SQL butt");
            loadUrlData(0)
        }
    }
    private fun loadUrlData(indexStart: Int) {
        System.out.println("Respons   "+getString(R.string.php_server)+getString(R.string.showcars_php))
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            getString(R.string.php_server)+getString(R.string.showcars_php) ,
            Response.Listener { response ->
                try {
                    System.out.println("Respons  TRY "+response)
//                    progressBar.setVisibility(View.GONE)
                    val jsonObject = JSONObject(response)
                    val array = jsonObject.getJSONArray("tovar")
                    for (i in 0 until array.length()) {
                        val jo = array.getJSONObject(i)


                        var id = 0
                        if (jo.getString("id") != "null") {
                            id = jo.getInt("id")
                            println("RESULT ==>   " +id)
                        }
//                        xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.korzinaCount1 =
//                            xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.korzinaCount1 + kolihestvo
//                        val tovars = TovariObjPrilavok(
//                            jo.getString("id"),
//                            jo.getString("naimenovanie"),
//                            jo.getInt("skidka"),
//                            jo.getString("foto"),
//                            jo.getDouble("cena"),
//                            cenaSkidka,
//                            kolihestvo,
//                            selected
//                        )
//                        tovariLists.add(tovars)
//                        tovariAdapterPrilavok.setItems()
                    }
//                    tovariAdapterPrilavok.notifyDataSetChanged()
//                    tovariAdapterPrilavok.setLoaded()
//                    if (xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.korzinaCount1 > 0) {
//                        xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.tvKorzinaCount.setVisibility(
//                            View.VISIBLE
//                        )
//                        xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.tvKorzinaCount.setText(
//                            Integer.toString(xyz.lavaliva.market.TovarList.SpisokTovarovPrilavok.korzinaCount1)
//                        )
//                    }
                } catch (e: JSONException) {
                    println("\n ERR$response")
                    e.printStackTrace()
                    System.out.println("Respons  ERR "+response)
                }
            }, Response.ErrorListener {


                error ->
                    System.out.println("Respons  ERR 2==> "+error.toString())


            }) {
            @Throws(AuthFailureError::class)

            override fun getParams(): Map<String, String>? {


                val parameters: MutableMap<String, String> = HashMap()
                parameters["vetka"] = "2"
                parameters["indexstart"] = Integer.toString(indexStart)
                parameters["count"] =
                    Integer.toString(10)
                parameters["pokupatel"] = "10"
                println("Otpravka na server iz tovar id ==>  "+parameters)
                return parameters
            }

//                Response.ErrorListener { error -> System.out.println("Respons  ERR 2==> "+error.toString())
        }
        var requestQueue = Volley.newRequestQueue(
            applicationContext
        )

        requestQueue.add<String>(stringRequest)
//        progressBar.setVisibility(View.VISIBLE)
    }


}