package org.campusconnect.campusconnect.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.activities.PostActivity;
import org.campusconnect.campusconnect.model.Post;

public class SocialFeedsFragment extends Fragment implements View.OnClickListener {
    private AppCompatButton imagePostBtn;
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private Query query = mDatabaseRef.child("Posts");
    private FirebaseRecyclerOptions<Post> options =
            new FirebaseRecyclerOptions.Builder<Post>()
                    .setQuery(query, Post.class)
                    .build();

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private ImageView postImage;
        private TextView postTitle;
        private TextView postDesc;
        private TextView postUser;

        private PostViewHolder(View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.post_title_txtview);
            postDesc = itemView.findViewById(R.id.post_desc_txtview);
            postUser = itemView.findViewById(R.id.post_user);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_socialfeed, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.postrecyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        imagePostBtn = rootView.findViewById(R.id.imagePostBtn);
        imagePostBtn.setOnClickListener(this);
        return rootView;
    }

    FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {
        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // inflating recycler item view
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_recycler, parent, false);

            return new PostViewHolder(itemView);
        }

        @Override
        protected void onBindViewHolder(PostViewHolder holder, int position, Post model) {
            Glide.with(getActivity()).load(model.getImageUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.postImage);
            holder.postTitle.setText(model.getTitle());
            holder.postDesc.setText(model.getDesc());
            holder.postUser.setText(model.getUsername());
        }
    };

    @Override
    public void onClick(View rootView) {
        int i = rootView.getId();
        if (i == R.id.imagePostBtn) {
            Intent intentPost = new Intent(getActivity(), PostActivity.class);
            startActivity(intentPost);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter != null) {
            adapter.stopListening();
        }
    }
}
