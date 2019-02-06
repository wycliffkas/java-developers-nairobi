package com.example.javadevelopersnairobi.javadevelopersnairobi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.javadevelopersnairobi.javadevelopersnairobi.R;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;
import com.example.javadevelopersnairobi.javadevelopersnairobi.view.DetailActivity;

import java.util.List;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.GithubUsersHolder> {
	public static final String EXTRA_NAME = "username";
	public static final String EXTRA_LINK = "githubLink";
	public static final String EXTRA_IMAGE = "imageUrl";


	private List<GithubUsers> githubUsers;
	private Context context;

	public GithubUsersAdapter(List<GithubUsers> githubUsers, Context context) {
		this.githubUsers = githubUsers;
		this.context = context;
	}

	@Override
	public GithubUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_developers, parent, false);
		return new GithubUsersHolder(view);
	}

	@Override
	public void onBindViewHolder(GithubUsersHolder holder, final int position) {


		GithubUsers githubUser = githubUsers.get(position);
		holder.username.setText(githubUsers.get(position).getUsername());
		Glide.with(context)
				.load(githubUser.getAvatarUrl())
				.into(holder.profile_image);
	}

	@Override
	public int getItemCount() {
		return githubUsers.size();
	}


	public class GithubUsersHolder extends RecyclerView.ViewHolder{

		CardView users_layout;
		ImageView profile_image;
		TextView username;

		public GithubUsersHolder(View v) {
			super(v);
			users_layout = v.findViewById(R.id.users_layout);
			profile_image =  v.findViewById(R.id.profile_image);
			username = v.findViewById(R.id.username);

			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = getAdapterPosition();
					Intent detailIntent = new Intent(v.getContext(), DetailActivity.class);
					GithubUsers clickedDeveloper =  githubUsers.get(position);

					detailIntent.putExtra(EXTRA_NAME,clickedDeveloper.getUsername());
					detailIntent.putExtra(EXTRA_LINK, clickedDeveloper.getHtmlUrl());
					detailIntent.putExtra(EXTRA_IMAGE, clickedDeveloper.getAvatarUrl());
					context.startActivity(detailIntent);
				}
			});
		}
	}
}
