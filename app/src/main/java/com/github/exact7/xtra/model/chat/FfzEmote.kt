package com.github.exact7.xtra.model.chat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FfzEmote(
        override val name: String,
        val url: String,
        val width: Float?) : Emote, Parcelable