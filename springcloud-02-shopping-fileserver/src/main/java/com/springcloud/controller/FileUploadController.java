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
	
	//从application.properties文件中获得指定键的值，并赋值给相应的成员变量
	@Value("${web.user-path}")
	private String userPath;
	
	@Value("${web.goods-path}")
	private String goodsPath;
	
	//上传用户图片
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
		rv.setMessage("用户头像上传失败！！！");
		return rv;
	}
	
	
	//上传商品图片
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
		rv.setMessage("商品图片上传失败！！！");
		return rv;
	}
	
	/**
	 * 删除商品图片
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/deleteGoodsImg")
	public ResultValue deletGoodsImg(@RequestParam("goodsImg") String goodsImg) {
		ResultValue rv = new ResultValue();
		
		try {
			//从url中获得商品图片的名字(找到url的最后一个/来获取图片名字)
			int indexOf = goodsImg.lastIndexOf("/");
			if(indexOf != -1) {
				//从url的下一个字符开始截取
				String fileName = goodsImg.substring(indexOf +1);
				
				File file = new File(this.goodsPath + fileName);
				//判断文件或目录是否存在
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
	 * 删除用户图片
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/deleteUserPhoto")
	public ResultValue deletUserImg(@RequestParam("userImage") String userImage) {
		ResultValue rv = new ResultValue();
		
		try {
			//从url中获得用户头像的名字(找到url的最后一个/来获取图片名字)
			int indexOf = userImage.lastIndexOf("/");
			if(indexOf != -1) {
				//从url的下一个字符开始截取
				String fileName = userImage.substring(indexOf +1);
				
				File file = new File(this.userPath + fileName.trim());
				//判断文件或目录是否存在
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
	 * 上传文件
	 * @param path    上传文件的路径
	 * @param file    上传的文件
	 * @return         成功返回java.util.Map类型的实例，否则返回null
	 * @throws IOException 
	 */
	private Map<String,Object> uploadFile(String path,MultipartFile file) throws IOException{
		//获得新的文件名
		String fileName = UploadUtils.getFileName();
		
		//根据上传的文件名获得文件的扩展名
		String extendName = UploadUtils.getExendedName(file.getOriginalFilename());
		
		//上传文件
		//1,将文件转换为字节类型的数组
		byte[] bytes = file.getBytes();
		//2,创建file类的对象，并设置文件名,上传路径及文件名扩展名
		File saveFile = new File(path + fileName + extendName);
		//3，上传文件
		FileCopyUtils.copy(bytes, saveFile);
		
		Map<String,Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("extendName", extendName);
		return map;
	}

}
