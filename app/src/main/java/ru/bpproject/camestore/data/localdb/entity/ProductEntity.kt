package ru.bpproject.camestore.data.localdb.entity

import android.provider.BaseColumns
import androidx.room.*

@Entity(
    tableName = "products"
)
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BaseColumns._ID)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "old_price")
    val oldPrice: Int?,

    @ColumnInfo(name = "category")
    val categoryId: Long,

    @ColumnInfo(name = "functionality")
    val functionality: String
)

data class CategoryWithProducts(
    @Embedded val category : CategoryEntity,
    @Relation(
        parentColumn = BaseColumns._ID,
        entityColumn = "category"
    )
    val products: List<ProductEntity>
)