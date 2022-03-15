package net.ddns.iiiedug02.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.repository.ArticalRepository;


@Service
public class ArticalService {

	@Autowired
	private ArticalRepository articalRepository;
	
	@Transactional            //對應bean檔名
	public ArticleBean insert(ArticleBean a123) {
		//System.out.println("create");
	    return articalRepository.save(a123);//新增bean物件資料
	}
	
	@Transactional
	public void delete(int id) {
		//System.out.println("delete");
	    articalRepository.deleteById(id);//以id(流水號數字)識別去做刪除
	}
	
	@Transactional           
	public ArticleBean update(ArticleBean a123) {
		//System.out.println("update");
		return articalRepository.save(a123); //存取bean物件資料
	}
	
	@Transactional
	public ArticleBean getById(int id) {
		//System.out.println("read");
		return articalRepository.getById(id);
	}
}
