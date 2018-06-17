package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.imooc.o2o.dto.ImageHolder;
public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	/**
	 * 将CommonsMultipartFile转换为File类
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile){
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	/**
	 * 处理缩略图，返回新生成图片的相对值路径
	 * @param thumbnail
	 * @param tragetAddr
	 * @return
	 */
	public static String generateThumbnail(ImageHolder thumbnail,String tragetAddr){
		String realFileName = getRandomFileName();
		//扩展名
		String extension = getFileExtension(thumbnail.getImageName());
		makeDirPath(tragetAddr);
		String relativeAddr = tragetAddr + realFileName +extension;
		logger.debug("current relative address is : "+relativeAddr);
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		logger.debug("current complete address is :  "+PathUtil.getImgBasePath()+relativeAddr);
		logger.debug("base path is :"+ basePath);
		try{
			Thumbnails.of(thumbnail.getImage()).size(200,200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f)
			.outputQuality(0.8f).toFile(dest);
		}catch(IOException e){
			logger.error(e.toString());
			e.printStackTrace();
		}
		return relativeAddr;
		
	}
	/**
	 * 创建目标路径所涉及的目录，即/home..../xxx.jpg
	 * 文件夹自动创建
	 * @param tragetAddr
	 */
	private static void makeDirPath(String tragetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+tragetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
	}
	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String  fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * 生成随机文件名，当前时间 年月日小时分钟秒钟=五位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr+rannum;
	}

	public static void main(String[] args) throws IOException {
 		Thumbnails.of(new File("C:\\Users\\guowe\\Desktop\\47588106.jpg"))
		.size(200, 200).watermark(Positions.BOTTOM_RIGHT, 
				ImageIO.read(new File(basePath+"/watermark.jpg")),0.25f).outputQuality(0.8f)
		.toFile("C:\\Users\\guowe\\Desktop\\47588106-new.jpg");
		
	}
	/**
	 * storePath是文件的路径还是目录的路径，
	 * 如果是文件的路径，则删除该文件，
	 * 如果是目录路径，则删除该目录下所有的文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i=0; i<files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
	/**
	 * 处理详情图，并返回新生成图片的相对值路径
	 * 
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		// 获取不重复的随机名
		String realFileName = getRandomFileName();
		// 获取文件的扩展名如png,jpg等
		String extension = getFileExtension(thumbnail.getImageName());
		// 如果目标路径不存在，则自动创建
		makeDirPath(targetAddr);
		// 获取文件存储的相对路径(带文件名)
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is :" + relativeAddr);
		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is :" + PathUtil.getImgBasePath() + relativeAddr);
		// 调用Thumbnails生成带有水印的图片
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.9f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			throw new RuntimeException("创建缩图片失败：" + e.toString());
		}
		// 返回图片相对路径地址
		return relativeAddr;
	}

}
