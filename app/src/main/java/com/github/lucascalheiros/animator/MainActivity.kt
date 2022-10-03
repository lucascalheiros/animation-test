package com.github.lucascalheiros.animator

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.github.lucascalheiros.animator.databinding.ActivityMainBinding
import com.github.lucascalheiros.animator.databinding.ImageItemBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val images = mutableListOf(
            R.drawable.name1,
            R.drawable.name2,
            R.drawable.name3,
            R.drawable.name4
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        val adapter = ViewFlipperAdapter(
            images
        )
        binding.viewFlipper.adapter = adapter
        setContentView(binding.root)


        binding.btn1.setOnClickListener {
            images.removeAt(1)
            adapter.images = images

            binding.viewFlipper.adapter = adapter
            binding.viewFlipper.animation = loadAnimation(this, android.R.anim.fade_in)
            binding.viewFlipper.displayedChild = 0

        }
        binding.btn2.setOnClickListener {
            adapter.notifyDataSetChanged()
            binding.viewFlipper.animation = loadAnimation(this, android.R.anim.slide_in_left)

            binding.viewFlipper.displayedChild = 1
        }
        binding.btn3.setOnClickListener {
            binding.viewFlipper.animation = loadAnimation(this, R.anim.rotate_out)

            binding.viewFlipper.displayedChild = 2
        }
        binding.btn4.setOnClickListener {
            binding.viewFlipper.animation = loadAnimation(this, R.anim.wipe_in)

            binding.viewFlipper.displayedChild = 3

        }
    }

}

internal class ViewFlipperAdapter(
    var images: List<Int>
) : BaseAdapter() {


    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Int {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return images[position].toLong()
    }


    override fun getView(position: Int, view: View?, parent: ViewGroup?): View? {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent?.context), null, false)

        binding.image.setImageResource(getItem(position))
        return binding.root
    }

}
