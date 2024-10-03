package com.example.tugasmembuataplikasiandroidpemula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasmembuataplikasiandroidpemula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Friend>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvHeroes = binding.rvFriend
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListFriends())
        showRecyclerList()

        supportActionBar?.apply {
            title = "Anggota Volunter"
        }

    }

    private fun getListFriends(): ArrayList<Friend> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPerson = resources.getStringArray(R.array.data_person)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDivision = resources.getStringArray(R.array.data_person_divisi)
        val dataClasses = resources.getStringArray(R.array.data_person_class)
        val dataInstagram = resources.getStringArray(R.array.data_person_instagram)
        val dataMotto = resources.getStringArray(R.array.data_person_motto)
        val listFriend = ArrayList<Friend>()
        for (i in dataName.indices) {
            val person = Friend(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1),dataPerson[i],dataClasses[i],dataDivision[i],dataMotto[i],dataInstagram[i])
            listFriend.add(person)
        }
        return listFriend
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListFriendAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListFriendAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Friend) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(person: Friend) {
        Toast.makeText(this, "Kamu memilih " + person.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

