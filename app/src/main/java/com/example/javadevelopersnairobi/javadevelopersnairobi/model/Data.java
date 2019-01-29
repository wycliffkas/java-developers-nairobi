package com.example.javadevelopersnairobi.javadevelopersnairobi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private Boolean incompleteResults;

	@SerializedName("items")
	private List<GithubUsers> results;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Boolean getIncompleteResults() {
		return incompleteResults;
	}

	public void setIncompleteResults(Boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
	}

	public List<GithubUsers> getResults() {
		return results;
	}

	public void setResults(List<GithubUsers> results) {
		this.results = results;
	}
}
