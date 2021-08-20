package com.spring.mvc.service.border;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.dao.BorderDAO;
import com.spring.mvc.dao.MyBatisBorderDao;
import com.spring.mvc.model.BorderDtlModel;
import com.spring.mvc.model.beans.BorderBean;

@Service
public class BorderDtlService {
	
	@Autowired
	private MyBatisBorderDao myBatisBorderDao;
	
	public BorderDtlModel getBorderDtlModel(String border_code) {
		int i_border_code = Integer.parseInt(border_code);
		
		BorderDtlModel model = new BorderDtlModel();
		
		//BorderBean borderBean = borderDAO.getBorderDtl(i_border_code);
		BorderBean borderBean = myBatisBorderDao.getBorderDtl(i_border_code);
		BorderBean borderBean_pre = myBatisBorderDao.getPreBorderCode(i_border_code);
		BorderBean borderBean_Next = myBatisBorderDao.getNextBorderCode(i_border_code);
		
		model.setBorder_title(borderBean.getBorder_title());
		model.setBorder_date(borderBean.getBorder_date());
		model.setWriter_name(borderBean.getWriter_name());
		model.setBorder_count(borderBean.getBorder_count());
		model.setBorder_file(borderBean.getBorder_file());
		model.setBorder_tempfile(borderBean.getBorder_tempfile());
		
		StringBuilder fileUri = new StringBuilder();
		StringTokenizer oName = new StringTokenizer(borderBean.getBorder_file(), ",");
		StringTokenizer tName = new StringTokenizer(borderBean.getBorder_tempfile(), ",");
		
		for(int i = 0; oName.hasMoreTokens(); i++) {
			tName.hasMoreTokens();
			fileUri.append("originName=");
			fileUri.append(oName.nextToken());
			fileUri.append("&tempName=");
			fileUri.append(tName.nextToken());
			fileUri.append(",");
		}
		fileUri.delete(fileUri.length()-1, fileUri.length());
		System.out.println(fileUri.toString());
		model.setBorder_content(borderBean.getBorder_content());
		if(borderBean_pre != null) {
			model.setBorder_code_pre(borderBean_pre.getBorder_code());
			model.setBorder_title_pre(borderBean_pre.getBorder_title());
		}
		if(borderBean_Next != null) {
			model.setBorder_code_next(borderBean_Next.getBorder_code());
			model.setBorder_title_next(borderBean_Next.getBorder_title());
		}
		return model;
	}
	
	
	
}
