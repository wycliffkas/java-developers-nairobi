package com.example.javadevelopersnairobi.javadevelopersnairobi.model;

import com.google.gson.annotations.SerializedName;

public class GithubUsers{

    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;

    public GithubUsers(String username, String avatarUrl, String htmlUrl){
    	this.avatarUrl = avatarUrl;
    	this.username = username;
    	this.htmlUrl = htmlUrl;
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

}