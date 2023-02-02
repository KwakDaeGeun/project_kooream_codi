package com.kooream.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kooream.domain.AttachFileVO;
import com.kooream.domain.SizeVO;
import com.kooream.mapper.BrandProductUploadMapper;
import com.kooream.mapper.SizeMapper;

import lombok.Setter;


@Service
  public class SizeServiceImpl implements SizeService{
	
	@Setter(onMethod_ = @Autowired )
	private SizeMapper mapper;


	@Override
	public int addSize(SizeVO vo) {
		return mapper.addSize(vo);

	}



  
  
  }
 
