package org.campusconnect.campusconnect.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.model.Post;

public class PostRecyclerAdapter {
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();
    private Query query = mDatabaseRef.child("Posts");
    private FirebaseRecyclerOptions<Post> options =
            new FirebaseRecyclerOptions.Builder<Post>()
                    .setQuery(query, Post.class)
                    .build();

    FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {
        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // inflating recycler item view
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.post_recycler, parent, false);
            PostViewHolder holder = new PostViewHolder(itemView);
            return holder;
        }

        @Override
        protected void onBindViewHolder(PostViewHolder holder, int position, Post model) {
            holder.postImage.setText(model.getImageUrl());
            holder.postTitle.setText(model.getTitle());
            holder.postDesc.setText(model.getDesc());
            holder.postUser.setText(model.getUsername());
        }
    };

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView postImage;
        private AppCompatTextView postTitle;
        private AppCompatTextView postDesc;
        private AppCompatTextView postUser;

        private PostViewHolder(View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.post_image);
            postTitle = itemView.findViewById(R.id.post_title_txtview);
            postDesc = itemView.findViewById(R.id.post_desc_txtview);
            postUser = itemView.findViewById(R.id.post_user);
        }
    }

    /*
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }
    }
    */
}
