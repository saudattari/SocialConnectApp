package com.example.socialconnect.dataModel

data class CommentData(
    val userName: String,
    val commentContent: String,
    val timeAgo: Long = System.currentTimeMillis(),
    val profileImage: Int,
    val userId : String,
    val postId : String
)
