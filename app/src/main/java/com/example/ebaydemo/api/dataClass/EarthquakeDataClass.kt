package com.example.ebaydemo.api.dataClass

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class EarthquakeDataClass(
    @field:JsonProperty("earthquakes")
    val earthquakes: List<Earthquake?>? = null
) : Parcelable

@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Earthquake(
    @field:JsonProperty("datetime")
    val datetime: String? = null,

    @field:JsonProperty("depth")
    val depth: Double? = null,

    @field:JsonProperty("lng")
    val lng: Double? = null,

    @field:JsonProperty("src")
    val src: String? = null,

    @field:JsonProperty("eqid")
    val eqid: String? = null,

    @field:JsonProperty("magnitude")
    val magnitude: Double? = null,

    @field:JsonProperty("lat")
    val lat: Double? = null
) : Parcelable

