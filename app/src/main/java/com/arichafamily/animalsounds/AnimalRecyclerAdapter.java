package com.arichafamily.animalsounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

public class AnimalRecyclerAdapter extends RecyclerView.Adapter<AnimalRecyclerAdapter.AnimalViewHolder> {

    List<Animal> animals;
    private LayoutInflater inflater;
    private Context context;

    public AnimalRecyclerAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        animals = AnimalDataSource.getAnimals();
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.animal_item, parent, false);
        AnimalViewHolder vh = new AnimalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.ivAnimal.setImageResource(animal.getImageResID());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAnimal;
        public AnimalViewHolder(View v) {
            super(v);
            ivAnimal = (ImageView) v.findViewById(R.id.ivAnimal);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            int position = getAdapterPosition();
            Animal a = animals.get(position);

            MediaPlayer mediaPlayer = MediaPlayer.create(context, a.getSoundReID());
            mediaPlayer.start();

            v.animate().rotation(360).withEndAction(new Runnable() {
                @Override
                public void run() {
                    v.animate().rotation(0);
                }
            });
        }
    }
}
