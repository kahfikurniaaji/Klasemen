package com.kahfi.klasemen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_club_view.view.*

class RecyclerViewAdapter(private val context: MainActivity, private val clubList:
ArrayList<ClubModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_club_view, parent, false))
    }

    override fun getItemCount(): Int {
        return clubList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(clubList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clubPeringkat = view.txtClubPeringkat
        val clubLogo = view.imgClubLogo
        val clubName = view.txtClubNama
        val clubMain = view.txtClubMain
        val clubMenang = view.txtClubMenang
        val clubSeri = view.txtClubSeri
        val clubKalah = view.txtClubKalah
        val clubGM = view.txtClubGM
        val clubGA = view.txtClubGA
        val clubSG = view.txtClubSG
        val clubPoint = view.txtClubPoin

        fun bindItems(data : ClubModel){
            clubPeringkat?.text = data.peringkat
//            clubLogo?.setImageURI(data.logo)
            Picasso.get().load(data.logo).into(clubLogo)
            clubName?.text = data.nama
            clubMain?.text = data.bermain
            clubMenang?.text = data.menang
            clubSeri?.text = data.seri
            clubKalah?.text = data.kalah
            clubGM?.text = data.gm
            clubGA?.text = data.ga
            clubSG?.text = data.sg
            clubPoint?.text = data.poin

            itemView.setOnClickListener {
                val text = data.nama
                Toast.makeText(itemView.context, text, Toast.LENGTH_SHORT).show()
            }
        }
    }
}