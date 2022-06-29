package com.dip.mymcimessages.activities

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color.blue
import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.dip.mymcimessages.R
import com.dip.mymcimessages.databinding.ActivityMainBinding
import com.dip.mymcimessages.ui.AnimationHandler
import com.dip.mymcimessages.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvPublicMessages.setOnClickListener {
            val animationHandler = AnimationHandler()
            animationHandler.changeTextColor(
                binding.tvPublicMessages,
                resources.getColor(R.color.unselected_color),
                resources.getColor(R.color.black)
            )
            animationHandler.changeTextColor(
                binding.tvBookmarkMessages,
                resources.getColor(R.color.black),
                resources.getColor(R.color.unselected_color)
            )
            animationHandler.animateView(
                binding.tvPublicMessages.id,
                binding.animationRoot,
                binding.animationView
            )
        }

        binding.tvBookmarkMessages.setOnClickListener {
            val animationHandler = AnimationHandler()
            animationHandler.changeTextColor(
                binding.tvBookmarkMessages,
                resources.getColor(R.color.unselected_color),
                resources.getColor(R.color.black)
            )
            animationHandler.changeTextColor(
                binding.tvPublicMessages,
                resources.getColor(R.color.black),
                resources.getColor(R.color.unselected_color)
            )
            animationHandler.animateView(
                binding.tvBookmarkMessages.id,
                binding.animationRoot,
                binding.animationView
            )
        }

    }
}
