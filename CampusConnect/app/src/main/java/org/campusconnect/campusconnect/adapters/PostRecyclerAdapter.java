/*
package org.campusconnect.campusconnect.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import org.campusconnect.campusconnect.R;
import org.campusconnect.campusconnect.fragments.SocialFeedsFragment;
import org.campusconnect.campusconnect.model.Post;

public class PostRecyclerAdapter extends FirebaseRecyclerAdapter<Post, PostRecyclerAdapter.PostViewHolder> {
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

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Post, SocialFeedsFragment.PostViewHolder>(options) {
            @Override
            public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // inflating recycler item view
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.post_recycler, parent, false);

                return new PostViewHolder(itemView);
            }

            @Override
            protected void onBindViewHolder(SocialFeedsFragment.PostViewHolder holder, int position, Post model) {
                Glide.with(getActivity()).load(model.getImageUrl()).into(holder.postImage);
                holder.postTitle.setText(model.getTitle());
                holder.postDesc.setText(model.getDesc());
                holder.postUser.setText(model.getUsername());
            }
        };
    }
}
*/