package com.example.javadevelopersnairobi.javadevelopersnairobi.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.javadevelopersnairobi.javadevelopersnairobi.R;

import static com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter.EXTRA_IMAGE;
import static com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter.EXTRA_LINK;
import static com.example.javadevelopersnairobi.javadevelopersnairobi.adapter.GithubUsersAdapter.EXTRA_NAME;

public class DetailActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.developer_details);

		final Intent intent = getIntent();
		final String username = intent.getStringExtra(EXTRA_NAME);
		final String githubLink = intent.getStringExtra(EXTRA_LINK);
		String profileImage = intent.getStringExtra(EXTRA_IMAGE);

		ImageView image = findViewById(R.id.profilePicture);
		TextView name = findViewById(R.id.username);
		final TextView link = findViewById(R.id.githubLink);
		FloatingActionButton fab = findViewById(R.id.shareButton);

		Glide
			.with(this)
			.load(profileImage)
			.apply(new RequestOptions()
			.fitCenter())
			.into(image);
		name.setText(username);
		link.setText(githubLink);


		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(intent.EXTRA_TEXT, "Check out this awesome developer @" + username + " , " + githubLink + ".");
				startActivity(Intent.createChooser(shareIntent, "Share Profile using"));
			}
		});

		link.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW,
						Uri.parse(githubLink.toString()));
				startActivity(browserIntent);

			}
		});
	}
}
