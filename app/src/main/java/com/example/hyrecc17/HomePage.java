package com.example.hyrecc17;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    private ImageView profileImage;
    private TextView usernameText;
    private TextView followingText;
    private TextView discussionText;
    private TextView postTitle;
    private TextView postContent;
    private ImageView postImage;
    private TextView seeMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize views
        profileImage = findViewById(R.id.profile_image);
        usernameText = findViewById(R.id.username_text);
        followingText = findViewById(R.id.following_text);
        discussionText = findViewById(R.id.discussion_text);
        postTitle = findViewById(R.id.post_title);
        postContent = findViewById(R.id.post_content);
        postImage = findViewById(R.id.post_image);
        seeMore = findViewById(R.id.see_more);

        // Get post data from intent
        Post post = (Post) getIntent().getSerializableExtra("post");

        // Populate views with post data
        if(post != null) {
            Glide.with(this)
                    .load(post.getUserProfileImage())
                    .circleCrop()
                    .into(profileImage);

            usernameText.setText(post.getUsername());
            followingText.setText(post.getFollowingCount() + " Following");
            discussionText.setText(post.getDiscussionCount() + " Discussions");
            postTitle.setText(post.getTitle());
            postContent.setText(post.getContent());

            if(post.getImageUrl() != null) {
                Glide.with(this)
                        .load(post.getImageUrl())
                        .into(postImage);
                postImage.setVisibility(View.VISIBLE);
            } else {
                postImage.setVisibility(View.GONE);
            }
        }

        seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle see more click
                Intent intent = new Intent(PostDetailActivity.this, FullPostActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
            }
        });
