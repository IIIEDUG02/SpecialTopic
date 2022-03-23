package net.ddns.iiiedug02.model.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.repository.ArticalRepository;

@Service
public class ArticalService {

  @Autowired
  private ArticalRepository articalRepository;

  @Transactional // 對應bean檔名
  public ArticleBean insert(ArticleBean article) {
    // System.out.println("create");
	 
    return articalRepository.save(article);// 新增bean物件資料
  }

  @Transactional
  public void delete(int id) {
    // System.out.println("delete");
    articalRepository.deleteById(id);// 以id(流水號數字)識別去做刪除
  }

  @Transactional
  public ArticleBean update(ArticleBean a123) {
    // System.out.println("update");
    return articalRepository.save(a123); // 存取bean物件資料
  }

  @Transactional
  public ArticleBean getById(int id) {
    return articalRepository.getById(id);
  }
  
  public List<ArticleBean> findAll() {
	  // 取得所有文章，並且會以 ID 做排序(最新發佈的文章會顯示在頁面最前面)
	  return articalRepository.findAll(Sort.by(Direction.DESC, "id"));
  }
  
  @Transactional
	public List<ArticleBean> getAll() {
	// TODO Auto-generated method stub
	return articalRepository.findAll();
  }
}
