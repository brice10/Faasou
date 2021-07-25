package com.gteam.faasou.services;

import com.gteam.faasou.models.SearchModel;

import okhttp3.OkHttpClient;

/**
 *
 * @author Arléon Zemtsop
 */
public interface SearchService {
	public String search(SearchModel searchModel);
}
