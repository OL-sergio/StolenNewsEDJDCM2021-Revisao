package com.example.stolennewsedjdcm2021_reviso

import android.accounts.AuthenticatorDescription
import android.icu.text.CaseMap
import android.media.Image
import org.json.JSONObject

class Article {


    var title       :String? = null
    var description :String? = null
    var url         :String? = null
    var urlToImage  :String? = null

    constructor(title: String?, description: String?, url:String?, urlToImage: String?) {

        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage

    }

    constructor(){

    }

    companion object {

        fun fromJson(jsonObject: JSONObject) : Article{

            var article         =    Article()
            article.title       =   jsonObject.getString("title")
            article.description =   jsonObject.getString("")
            article.url         =   jsonObject.getString("")
            article.urlToImage  =   jsonObject.getString("")
            return article
        }
    }
}