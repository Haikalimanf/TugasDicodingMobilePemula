package com.example.tugasmembuataplikasiandroidpemula

import android.app.Person
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.example.tugasmembuataplikasiandroidpemula.databinding.ActivityFriendBinding
import com.example.tugasmembuataplikasiandroidpemula.databinding.ActivityMainBinding

class FriendActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Friend Details"
            setDisplayHomeAsUpEnabled(true)
        }

        val dataFriend = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Friend>(key_friend, Friend::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Friend>(key_friend)
        }

        val tvDetailName: TextView = binding.tvShortName
        val tvDetailDescription: TextView = binding.tvDetailPerson
        val ivDetailPhoto: ImageView = binding.imageFriend
        val tvDetailPerson: TextView = binding.tvDataPerson
        val tvDivision: TextView = binding.tvDataDivisi
        val tvMotto: TextView = binding.tvDataMotto
        val tvInstagram: TextView = binding.tvDataInstagram
        val tvClasses: TextView = binding.tvDataClasses

        tvDetailName.text = dataFriend?.name
        tvDetailDescription.text = dataFriend?.description
        tvDetailPerson.text = dataFriend?.data
        tvMotto.text = dataFriend?.motto
        tvInstagram.text = dataFriend?.instagram
        tvClasses.text = dataFriend?.classes
        tvDivision.text = dataFriend?.divisi
        dataFriend?.photo?.let { ivDetailPhoto.setImageResource(it) }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_friend_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareFriendDetails()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareFriendDetails() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, """
            Check out my friend:
            Name: ${binding.tvShortName.text}
            Description: ${binding.tvDetailPerson.text}
            Motto: ${binding.tvDataMotto.text}
            Instagram: ${binding.tvDataInstagram.text}
        """.trimIndent())
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    companion object {
        const val key_friend = "key_friend"
    }
}
