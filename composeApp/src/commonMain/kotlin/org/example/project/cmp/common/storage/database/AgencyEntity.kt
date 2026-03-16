package org.example.project.cmp.common.storage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "agency"
)
data class AgencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="abbrev")
    val abbrev: String,
    @ColumnInfo(name="ceo")
    val ceo: String,
    @ColumnInfo(name="featured")
    val featured: Boolean,
    @ColumnInfo(name="country")
    val countryName: List<String>,
    @ColumnInfo(name="image_name")
    val imageName: String?,
    @ColumnInfo(name="image_URL")
    val imageURL: String?,
    @ColumnInfo("description")
    val description: String?,
    @ColumnInfo("founding_year")
    val foundingYear: String?,
    @ColumnInfo("social_logo_url")
    val logo: String?
)