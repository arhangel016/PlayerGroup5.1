package pro.mitapp.playergroup51;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import pro.mitapp.playergroup51.databinding.FragmentMainBinding;
import pro.mitapp.playergroup51.databinding.FragmentPlayerBinding;


public class PlayerFragment extends Fragment {

    private FragmentPlayerBinding binding;
    private Uri currentSong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlayerBinding.inflate(inflater);
        getData();
        return binding.getRoot();
    }

    private void getData() {
        if (getArguments() != null){
            if (requireArguments().getString("song") != null){
                currentSong = Uri.parse(requireArguments().getString("song"));
                setData();
            }
        }
    }

    private void setData() {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(getContext(), currentSong);
        String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        byte[] image = retriever.getEmbeddedPicture();

        binding.txtSongTitle.setText(title);
        binding.txtSongArtist.setText(artist);

        if (image != null) binding.imgSongImage.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));

    }

}