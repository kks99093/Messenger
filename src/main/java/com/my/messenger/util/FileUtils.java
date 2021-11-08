package com.my.messenger.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.my.messenger.model.RestFile;

public class FileUtils {


	
	public static String createFile(String path, RestFile restFile) {
		String saveFileNm = "";
		MultipartFile mf = restFile.getProfileImg();
		try {
			String originNm = mf.getOriginalFilename();
			if(originNm != null && ! "".equals(originNm)) {
				String ext = originNm.substring(originNm.lastIndexOf("."));
				saveFileNm = UUID.randomUUID() + ext;
				mf.transferTo(new File(path + saveFileNm));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return saveFileNm;
		
	}
	
	public static int deleteFile(String path, String imgName) {
		int result = 0;
		File file = new File(path+imgName);
		if(file.exists()) { //파일이 존재하는지(존재한다면 true)
			if(file.delete()) {//file.delete()도 결과값이 boolean으로 성공여부가 날아옴 그걸 이용해서 기록남기면됨
				System.out.println("이미지 파일 삭제성공 후 이미지 업로드");
				result = 1;
			}else {
				System.out.println("파일 삭제 안됨");
			}
		}
		
		return result;
	}

}
