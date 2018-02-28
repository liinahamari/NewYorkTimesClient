package com.example.guest.newyorktimesclient.utils;

import com.example.guest.newyorktimesclient.mvp.model.LatestModel.News;
import com.example.guest.newyorktimesclient.mvp.model.LatestModel.Response;
import com.example.guest.newyorktimesclient.mvp.model.Article;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by l1maginaire on 2/28/18.
 */

public class NewsMapper {

    @Inject
    public NewsMapper() {
    }

    public List<Article> mapNews(Response response) {
        List<Article> articles = new ArrayList<>();
        if (response != null) {
            List<News> news = response.getNews();
            if (news != null) {
                for (News single : news) {
                    Article article = new Article();
                    if(single.getThumbnailStandard() == null || single.getThumbnailStandard().isEmpty())
                        continue;
                    article.setPicUrl(single.getThumbnailStandard());
                    if(single.getPublishedDate() == null || single.getPublishedDate().isEmpty())
                        continue;
                    article.setDate(single.getPublishedDate());
                    if(single.getAbstract() == null || single.getAbstract().isEmpty())
                        continue;
                    article.setSummary(single.getAbstract());
                    if(single.getTitle() == null || single.getTitle().isEmpty())
                        continue;
                    article.setTitle(single.getTitle());
                    if(single.getUrl() == null || single.getUrl().isEmpty())
                        continue;
                    article.setWebUrl(single.getUrl());
                    articles.add(article);
                }
            }
        }
        return articles;
    }
}