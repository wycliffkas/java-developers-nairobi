package com.example.javadevelopersnairobi.javadevelopersnairobi.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubUsers implements Parcelable {

	public static final Parcelable.Creator<GithubUsers> CREATOR = new Creator<GithubUsers>() {


		@SuppressWarnings({
				"unchecked"
		})
		public GithubUsers createFromParcel(Parcel in) {
			return new GithubUsers(in);
		}

		public GithubUsers[] newArray(int size) {
			return (new GithubUsers[size]);
		}

	};
	@SerializedName("login")
	@Expose
	private String username;


	@SerializedName("avatar_url")
	@Expose
	private String avatarUrl;


	@SerializedName("html_url")
	@Expose
	private String htmlUrl;


	public GithubUsers(Parcel in) {
		this.username = ((String) in.readValue((String.class.getClassLoader())));

		this.avatarUrl = ((String) in.readValue((String.class.getClassLoader())));

		this.htmlUrl = ((String) in.readValue((String.class.getClassLoader())));
	}

	public GithubUsers(String userName, String userRepo, String userAvatar) {
		this.username = userName;
		this.htmlUrl = userRepo;
		this.avatarUrl = userAvatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(username);
		dest.writeValue(avatarUrl);
		dest.writeValue(htmlUrl);

	}

	public int describeContents() {
		return 0;
	}

}