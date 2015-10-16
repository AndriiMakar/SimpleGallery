package com.makar.andrii.simplegallery;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.PersonViewHolder> {


    public void setOnRecyclerClickListener(OnRecyclerClickListener onRecyclerClickListener) {
        this.onRecyclerClickListener = onRecyclerClickListener;
    }

    private OnRecyclerClickListener onRecyclerClickListener;
    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView personPhoto;

        public PersonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            personPhoto = (ImageView) itemView.findViewById(R.id.myview);
        }


        @Override
        public void onClick(View view) {                                        // when click on image get the position of the image
            if (onRecyclerClickListener != null) {
                onRecyclerClickListener.onClick(getPosition(), view);           // send the position
            }
        }
    }

    ArrayList<ImageClass> imageClasses;                                         // array list of images

    Adapter(ArrayList<ImageClass> imageClasses) {
        this.imageClasses = imageClasses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);      //get the item view from layout
        PersonViewHolder pvh = new PersonViewHolder(v);                                                     // attach the view to adapter
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        String urll = imageClasses.get(i).url;                                  // get the url of each image in array list
        Picasso
                .with(personViewHolder.personPhoto.getContext())
                .load(urll)                                                     // url to load
                .placeholder(R.drawable.loading)                                // show when image is not loaded
                .into(personViewHolder.personPhoto);                            // loading into image view

    }

    @Override
    public int getItemCount() {
        return imageClasses.size();

    }


}


