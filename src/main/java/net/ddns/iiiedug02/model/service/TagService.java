package net.ddns.iiiedug02.model.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.TagBean;
import net.ddns.iiiedug02.model.repository.TagRepository;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<TagBean> findAll() {
        return tagRepository.findAll();
    }

    public List<TagBean> findByCategory(String category) {
        return tagRepository.findByCategory(category);
    }

    public List<TagBean> findByIdIn(Collection<Integer> ids) {
        return tagRepository.findByIdIn(ids);
    }
}
