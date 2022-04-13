package com.mbh.moviebrowser.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlin.math.roundToInt

fun Float.toRatingFloat(maxValue: Float, requiredMaxValue: Float, stepSize: Float): Float {
    val ratingValue = this / (maxValue / requiredMaxValue)
    return (((ratingValue / stepSize).roundToInt()) * stepSize)
}

fun Fragment.getNavigationResult(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(key)

fun Fragment.setNavigationResult(result: String, key: String = "result") {
    findNavController().currentBackStackEntry?.savedStateHandle?.set(key, result)
}