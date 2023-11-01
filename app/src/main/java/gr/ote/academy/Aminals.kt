package gr.ote.academy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Dog(
    val name: String,
    val age: Int
) : java.io.Serializable

@Parcelize
data class Cat(
    val name: String,
    val age: Int
) : Parcelable