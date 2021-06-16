package com.ottoboni.corelocalstorage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_user")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)
