package com.dip.mymcimessages.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.dip.mymcimessages.R
import com.dip.mymcimessages.utils.Utils

class AnimationHandler {


    fun animateView(id: Int, root: ConstraintLayout, animationView: View) {

        val constraintSet = ConstraintSet()
        constraintSet.clone(root)
        constraintSet.clear(animationView.id, ConstraintSet.RIGHT)
        constraintSet.connect(
            animationView.id,
            ConstraintSet.RIGHT,
            id,
            ConstraintSet.RIGHT
        )
        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = Utils.AnimationDurationLong

        TransitionManager.beginDelayedTransition(root, transition)
        constraintSet.applyTo(root)

    }

    fun changeTextColor(textView: AppCompatTextView, colorFrom: Int, colorTo: Int) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.addUpdateListener { animator ->
            textView.setTextColor(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    fun showDeleteDialog(root: ConstraintLayout, animationView: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(root)
        constraintSet.connect(
            animationView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP
        )
        constraintSet.connect(
            animationView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )
        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = Utils.AnimationDurationLong

        TransitionManager.beginDelayedTransition(root, transition)
        constraintSet.applyTo(root)
    }

    fun hideDeleteDialog(root: ConstraintLayout, animationView: View) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(root)
        constraintSet.clear(animationView.id, ConstraintSet.BOTTOM)
        constraintSet.connect(
            animationView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM
        )

        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = Utils.AnimationDurationLong

        TransitionManager.beginDelayedTransition(root, transition)
        constraintSet.applyTo(root)
    }

}