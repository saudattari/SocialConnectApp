package com.example.socialconnect.dataModel

data class PostData(
    val userId : String,
    val postId : String,
    val timeAgo: Long = System.currentTimeMillis(),
    val postContent: String,
    val postImage: List<String>? = null,
    val likes : Int = 0,
    val commentCount:Int = 0
)
