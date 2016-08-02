package com.atguigu.springdata.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.springdata.entity.Storage;
import com.atguigu.springdata.repository.StorageRespository;
import com.atguigu.springdata.utils.DynamicSpecifications;
import com.atguigu.springdata.utils.SearchFilter;

@Service
@Transactional(readOnly=true)
public class StorageService {
	
	@Autowired
	private StorageRespository storageRespository;
	
	public Page<Storage> getPage(int pageNo, Map<String, Object> reqParams){
		
		PageRequest pageable = new PageRequest(pageNo - 1, 3);//pageNo从0开始计数
		
		Specification<Storage> spec = buildSpecification(reqParams);
		
		return storageRespository.findAll(spec, pageable);
	}
	
	/**
	 * 创建动态查询条件组合
	 */
	protected Specification<Storage> buildSpecification(Map<String, Object> searchParams){
		
		Map<String, SearchFilter> filters = null;

		if(searchParams == null){
			filters = new HashMap<String, SearchFilter>();
		}else{
			filters = SearchFilter.parse(searchParams);
		}
		
		/*Map<String, SearchFilter> map = buildSearchFilters();
		if(map != null){
			filters.putAll(map); 
		}*/
		
		Specification<Storage> specification = null;
		specification =	DynamicSpecifications.bySearchFilter(filters.values());
		return specification;
	}

}
