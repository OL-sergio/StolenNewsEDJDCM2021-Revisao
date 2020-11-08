package com.example.stolennewsedjdcm2021_reviso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var articles     :   MutableList<Article>    =   ArrayList()

    var articleAdapter  =   ArticlesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listVieArticle = findViewById<ListView>(R.id.listViewArticles)
        listVieArticle.adapter = articleAdapter

        Backend.getLatestNews() { result ->

            if (result == "Sem internet") {
                Toast.makeText(this@MainActivity, "", Toast.LENGTH_LONG).show()

            } else {
                val jsonObject = JSONObject(result)

                if (jsonObject.get("status").equals("ok")) {

                    var jsonArray: JSONArray = jsonObject.getJSONArray("articles")
                    for (index in 0 until jsonArray.length()) {
                        val jsonArticle = jsonArray.getJSONObject(index)
                        val article = Article.fromJson(jsonArticle)
                        articles.add(article)
                    }
                    articleAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    inner class ArticlesAdapter :   BaseAdapter ()  {

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rowView   = layoutInflater.inflate(R.layout.row_article, viewGroup,   false)

            val textViewTitle   =   rowView.findViewById<TextView>(R.id.textViewTitle)
            val textViewDescription =   rowView.findViewById<TextView>(R.id.textViewDescription)
            val imageViewPhoto  =   rowView.findViewById<TextView>(R.id.imageViewPhoto)

            textViewTitle.text          =   articles[position].title
            textViewDescription.text    =   articles[position].description


            if ((articles[position].urlToImage?:"").contains("http")){
                Backend.getBitmapFromUrl(articles[position].urlToImage!!){
                    //imageViewPhoto.setImageBitmap(it)
                }

            }

            rowView.isClickable =   true
            rowView.setOnClickListener{
                val intent  =   Intent  (this@MainActivity, ArticlesDetailActivity::class.java)
                    intent.putExtra(ArticlesDetailActivity.ARTICLE_URL  ,   articles[position].url   )
                    intent.putExtra(ArticlesDetailActivity.ARTICLE_URL  ,   articles[position].url   )
                    startActivity(intent)

            }

            return rowView
        }

        override fun getCount(): Int {
            return articles.size
        }

        override fun getItem(position: Int): Any {
            return articles[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }
    }
}

