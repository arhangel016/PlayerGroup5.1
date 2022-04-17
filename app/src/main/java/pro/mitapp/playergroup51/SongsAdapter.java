package pro.mitapp.playergroup51;

import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {

    private FragmentActivity activity;
    private List<Uri> songs = new ArrayList<>();

    public SongsAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongsViewHolder(view);
    }

    public void addSong(Uri uri){
        this.songs.add(uri);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(holder.itemView.getContext(), songs.get(position));
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        byte[] image = retriever.getEmbeddedPicture();

        holder.txtTitle.setText(title);
        holder.txtArtist.setText(artist);

        if (image != null){
            holder.album.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        } else {

        }

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtArtist;
        ImageView album;

        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_song_title);
            txtArtist = itemView.findViewById(R.id.txt_song_artist);
            album = itemView.findViewById(R.id.img_poster);
        }
    }
    public interface OnItemClickListener {
        public void onClick(int position, List<Uri> songs);
    }
}
