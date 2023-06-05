package com.example.dundermifflinemployee

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val name: String,
    val role: String,
    val image: Int,
    val description: String
): Parcelable
