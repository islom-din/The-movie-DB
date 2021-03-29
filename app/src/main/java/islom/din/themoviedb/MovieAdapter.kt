package islom.din.themoviedb

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listOfMoves: List<Result>? = null

    fun setListOfMovies(list: List<Result>) {
        this.listOfMoves = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )


    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(listOfMoves != null) {
            holder.movieName.text = listOfMoves!![position].name
            holder.movieYear.text = listOfMoves!![position].first_air_date
            Glide.with(holder.image)
                .load("https://image.tmdb.org/t/p/w500/${listOfMoves!![position].poster_path}")
                .placeholder(R.drawable.downloading)
                .centerCrop()
                .into(holder.image)
        }
    }

    override fun getItemCount(): Int =
        if (listOfMoves != null)
            listOfMoves!!.size
        else 0

    inner class MovieViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image: ImageView = itemView.image
        val movieName: TextView = itemView.name
        val movieYear: TextView = itemView.year

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClickListener.onItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}