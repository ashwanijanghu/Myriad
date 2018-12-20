package com.jango.test.myriad.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity
data class UnsplashItem(
    @Expose
    @SerializedName("description")
    var description: String? = null,

    @PrimaryKey
    @NotNull
    @Expose
    @SerializedName("id")
    var id: String,

    @Expose
    @SerializedName("likes")
    var likes: Long? = null,

    @Expose
    @field:Embedded(prefix = "url_")
    @SerializedName("urls")
    var urls: Urls? = null){

    var indexInResponse: Int = -1
}
