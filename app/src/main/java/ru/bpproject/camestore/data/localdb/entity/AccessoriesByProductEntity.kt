package ru.bpproject.camestore.data.localdb.entity

import android.provider.BaseColumns
import androidx.room.*

@Entity(
    tableName = "accessories_by_product",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = [BaseColumns._ID],
            childColumns = ["product"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = [BaseColumns._ID],
            childColumns = ["accessory"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices =[Index("product")]
)
data class AccessoriesByProductEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Long = 0,

    @ColumnInfo(name = "product")
    val product: Long = 0,

    @ColumnInfo(name = "accessory")
    val accessory: Long = 0
)
