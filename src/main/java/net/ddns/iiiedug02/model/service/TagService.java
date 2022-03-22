package net.ddns.iiiedug02.model.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.repository.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	// findByCategory 就是要透過 category 這個欄位來得到相關的文章
	public Set<ArticleBean> findByCategory(String category) {
		// tagRepository.findByCategory(category) 回傳的是一個含有 TagBean 物件的集合
		// 因為回傳的是一個集合(Set)，但集合裡面只會有一個物件，所以 .get(0) 是取出第一個 TagBean 物件
		
		// 原始寫法
		// TagBean tag = tagRepository.findByCategory(category).get(0);
		// Set<ArticleBean> articles = tag.getArticles();
		
		// 快速寫法
		Set<ArticleBean> articles = tagRepository.findByCategory(category).get(0).getArticles();
		
		return articles;
	}
}
