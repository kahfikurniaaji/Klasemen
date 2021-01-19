package com.kahfi.klasemen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    val ClubList: ArrayList<ClubModel> = ArrayList()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    ////
    private var requestQueue: RequestQueue? = null
    ////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Klasemen Liga Inggris"
        ////
        requestQueue = Volley.newRequestQueue(this)
        ////
        jsonParse()
    }

    private fun jsonParse() {
        val url = "https://api-football.azharimm.tk/leagues/eng.1/standings?season=2020&sort=asc"
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            try {
                val jsonArray = response.getJSONObject("data").getJSONArray("standings")
                for (i in 0 until jsonArray.length()){
                    val results = jsonArray.getJSONObject(i)
                    val rank = results.getJSONArray("stats").getJSONObject(8).getString("value")
                    val urlLogo = results.getJSONObject("team").getJSONArray("logos").getJSONObject(0).getString("href")
                    val nama = results.getJSONObject("team").getString("shortDisplayName")
                    val bermain = results.getJSONArray("stats").getJSONObject(3).getString("value")
                    val menang = results.getJSONArray("stats").getJSONObject(0).getString("value")
                    val seri = results.getJSONArray("stats").getJSONObject(1).getString("value")
                    val kalah = results.getJSONArray("stats").getJSONObject(2).getString("value")
                    val gm = results.getJSONArray("stats").getJSONObject(4).getString("value")
                    val ga = results.getJSONArray("stats").getJSONObject(5).getString("value")
                    val sg = results.getJSONArray("stats").getJSONObject(9).getString("value")
                    val poin = results.getJSONArray("stats").getJSONObject(6).getString("value")
                    ClubList.add(ClubModel(rank, urlLogo, nama, bermain, menang, seri, kalah, gm, ga, sg, poin))
//                    ClubList.add(ClubModel("name", BitmapFactory.decodeResource(resources, R.drawable.icon3)))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            ////
            layoutManager = LinearLayoutManager(this)
            rvClubList.layoutManager = layoutManager
            rvClubList.adapter = RecyclerViewAdapter(this, ClubList)
            ////
        }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}