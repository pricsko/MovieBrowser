package com.mbh.moviebrowser.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.Transition
import kotlin.math.roundToInt

fun Float.toRatingFloat(maxValue: Float, requiredMaxValue: Float, stepSize: Float): Float {
    val ratingValue = this / (maxValue / requiredMaxValue)
    return ((ratingValue / stepSize).roundToInt()) * stepSize
}

fun Fragment.getNavigationResult(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(key)

fun Fragment.setNavigationResult(result: String, key: String = "result") {
    findNavController().currentBackStackEntry?.savedStateHandle?.set(key, result)
}

fun Transition.addListeners(
    onStart: ((Transition) -> Unit)? = null,
    onEnd: ((Transition) -> Unit)? = null,
    onCancel: ((Transition) -> Unit)? = null,
    onPause: ((Transition) -> Unit)? = null,
    onResume: ((Transition) -> Unit)? = null
) {
    addListener(object : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) {
            onStart?.invoke(transition)
        }

        override fun onTransitionEnd(transition: Transition) {
            onEnd?.invoke(transition)
        }

        override fun onTransitionCancel(transition: Transition) {
            onCancel?.invoke(transition)
        }

        override fun onTransitionPause(transition: Transition) {
            onPause?.invoke(transition)
        }

        override fun onTransitionResume(transition: Transition) {
            onResume?.invoke(transition)
        }

    })
}