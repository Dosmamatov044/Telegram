package com.example.telegram.models

data class CommonModel(
        val id: String = "",
        var videoUrl:String="",
        var username: String = "",
        var bio: String = "",
        var fullname: String = "",
        var state: String = "",
        var phone: String = "",
        var photoUrl: String = "empty",

        var text: String = "",
        var type: String = "",
        var from: String = "",
        var timeStamp: Any = 0,
        var fileUrl: String = "empty",


        var lastMessage:String = "",
        var choice:Boolean = false,

        var name_history :String="",
        var time_history :Any="",
        var photo_history:String="",
        var video_history:String="",

      //  var title:String="",


) {
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }


}