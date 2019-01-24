package com.example.javadevelopersnairobi.javadevelopersnairobi.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.javadevelopersnairobi.javadevelopersnairobi.R;
import com.example.javadevelopersnairobi.javadevelopersnairobi.model.GithubUsers;

import java.util.List;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.GithubUsersHolder> {

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
		String url = githubUser.getAvatarUrl();
		holder.username.setText(githubUsers.get(position).getUsername());
		RequestOptions myOptions = new RequestOptions()
				.fitCenter()
				.override(100, 100)
				.transforms(new CenterCrop(), new RoundedCorners(20));
		Glide.with(context)
				.load(githubUser.getAvatarUrl())
				.apply(myOptions)
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
			users_layout = (CardView) v.findViewById(R.id.users_layout);
			profile_image = (ImageView) v.findViewById(R.id.profile_image);
			username = (TextView) v.findViewById(R.id.username);
		}
	}
}
