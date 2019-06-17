package com.springcloud.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springcloud.common.UploadUtils;
import com.springcloud.vo.ResultValue;

@RestController
public class FileUploadController {
	
	//��application.properties�ļ��л��ָ������ֵ������ֵ����Ӧ�ĳ�Ա����
	@Value("${web.user-path}")
	private String userPath;
	
	@Value("${web.goods-path}")
	private String goodsPath;
	
	//�ϴ��û�ͼƬ
	@RequestMapping(value = "/userUpload")
	public ResultValue userUpload(@RequestParam("userImage") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		try {
			Map<String, Object> map = this.uploadFile(userPath,file);
			if(map != null && map.size() >0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û�ͷ���ϴ�ʧ�ܣ�����");
		return rv;
	}
	
	
	//�ϴ���ƷͼƬ
	@RequestMapping(value = "/goodsUpload")
	public ResultValue goodsUpload(@RequestParam("goodsImage") MultipartFile file) {
		ResultValue rv = new ResultValue();
		
		try {
			Map<String, Object> map = this.uploadFile(goodsPath,file);
			if(map != null && map.size() >0) {
				rv.setCode(0);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("��ƷͼƬ�ϴ�ʧ�ܣ�����");
		return rv;
	}
	
	/**
	 * ɾ����ƷͼƬ
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/deleteGoodsImg")
	public ResultValue deletGoodsImg(@RequestParam("goodsImg") String goodsImg) {
		ResultValue rv = new ResultValue();
		
		try {
			//��url�л����ƷͼƬ������(�ҵ�url�����һ��/����ȡͼƬ����)
			int indexOf = goodsImg.lastIndexOf("/");
			if(indexOf != -1) {
				//��url����һ���ַ���ʼ��ȡ
				String fileName = goodsImg.substring(indexOf +1);
				
				File file = new File(this.goodsPath + fileName);
				//�ж��ļ���Ŀ¼�Ƿ����
					file.delete();
					rv.setCode(0);
					return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	/**
	 * ɾ���û�ͼƬ
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/deleteUserPhoto")
	public ResultValue deletUserImg(@RequestParam("userImage") String userImage) {
		ResultValue rv = new ResultValue();
		
		try {
			//��url�л���û�ͷ�������(�ҵ�url�����һ��/����ȡͼƬ����)
			int indexOf = userImage.lastIndexOf("/");
			if(indexOf != -1) {
				//��url����һ���ַ���ʼ��ȡ
				String fileName = userImage.substring(indexOf +1);
				
				File file = new File(this.userPath + fileName.trim());
				//�ж��ļ���Ŀ¼�Ƿ����
					file.delete();
					rv.setCode(0);
					return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	
	/**
	 * �ϴ��ļ�
	 * @param path    �ϴ��ļ���·��
	 * @param file    �ϴ����ļ�
	 * @return         �ɹ�����java.util.Map���͵�ʵ�������򷵻�null
	 * @throws IOException 
	 */
	private Map<String,Object> uploadFile(String path,MultipartFile file) throws IOException{
		//����µ��ļ���
		String fileName = UploadUtils.getFileName();
		
		//�����ϴ����ļ�������ļ�����չ��
		String extendName = UploadUtils.getExendedName(file.getOriginalFilename());
		
		//�ϴ��ļ�
		//1,���ļ�ת��Ϊ�ֽ����͵�����
		byte[] bytes = file.getBytes();
		//2,����file��Ķ��󣬲������ļ���,�ϴ�·�����ļ�����չ��
		File saveFile = new File(path + fileName + extendName);
		//3���ϴ��ļ�
		FileCopyUtils.copy(bytes, saveFile);
		
		Map<String,Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendName", extendName);
		return map;
	}

}
