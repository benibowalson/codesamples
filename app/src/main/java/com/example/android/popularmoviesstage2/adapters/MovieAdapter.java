package com.example.android.popularmoviesstage2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.popularmoviesstage2.R;

import java.util.ArrayList;

/**
 * Created by 48101040 on 4/7/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardForMovie> {

    private ArrayList<Movie> mMovies;
    private Context mContext;
    private IListenToClicks mIClickHandler;

    public MovieAdapter(Context mContext, ArrayList<Movie> mMovies, IListenToClicks mIClickHandler) {
        this.mMovies = mMovies;
        this.mContext = mContext;
        this.mIClickHandler = mIClickHandler;
    }

    @Override
    public CardForMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View aCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cardview, parent, false);
        return new CardForMovie(aCard);
    }

    @Override
    public void onBindViewHolder(CardForMovie holder, int position) {
        Movie aMovie = mMovies.get(position);
        Glide.with(mContext)
                .load(aMovie.poster_path)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                //.placeholder()
                //.error()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface IListenToClicks {
        void onMovieThumbnailClick(int clickedPos);
    }

    public class CardForMovie extends RecyclerView.ViewHolder implements OnClickListener {

        public ImageView mImageView;
        public TextView mTextView;

        public CardForMovie(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mIClickHandler.onMovieThumbnailClick(clickedPosition);
        }
    }
}
