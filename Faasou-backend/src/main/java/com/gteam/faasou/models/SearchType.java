package com.gteam.faasou.models;

public enum SearchType {

    SEARCH_TYPE_UNDEFINED("SEARCH_TYPE_UNDEFINED"), IMAGE("IMAGE");

    private String searchType;

    SearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return this.searchType;
    }

}
