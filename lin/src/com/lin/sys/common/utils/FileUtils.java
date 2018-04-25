package com.lin.sys.common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * �ļ�����������
 * ʵ���ļ��Ĵ�����ɾ�������ơ�ѹ������ѹ�Լ�Ŀ¼�Ĵ�����ɾ�������ơ�ѹ����ѹ�ȹ���
 * @author ThinkGem
 * @version 2015-3-16
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	
	//private static //logger //logger = //loggerFactory.get//logger(FileUtils.class);
     
	/**
	 * ���Ƶ����ļ������Ŀ���ļ����ڣ��򲻸���
	 * @param srcFileName �����Ƶ��ļ���
	 * @param descFileName Ŀ���ļ���
	 * @return ������Ƴɹ����򷵻�true�����򷵻�false
	 */
	public static boolean copyFile(String srcFileName, String descFileName) {
		return FileUtils.copyFileCover(srcFileName, descFileName, false);
	}

	/**
	 * ���Ƶ����ļ�
	 * @param srcFileName �����Ƶ��ļ���
	 * @param descFileName Ŀ���ļ���
	 * @param coverlay ���Ŀ���ļ��Ѵ��ڣ��Ƿ񸲸�
	 * @return ������Ƴɹ����򷵻�true�����򷵻�false
	 */
	public static boolean copyFileCover(String srcFileName,
			String descFileName, boolean coverlay) {
		File srcFile = new File(srcFileName);
		// �ж�Դ�ļ��Ƿ����
		if (!srcFile.exists()) {
			//logger.debug("�����ļ�ʧ�ܣ�Դ�ļ� " + srcFileName + " ������!");
			return false;
		}
		// �ж�Դ�ļ��Ƿ��ǺϷ����ļ�
		else if (!srcFile.isFile()) {
			//logger.debug("�����ļ�ʧ�ܣ�" + srcFileName + " ����һ���ļ�!");
			return false;
		}
		File descFile = new File(descFileName);
		// �ж�Ŀ���ļ��Ƿ����
		if (descFile.exists()) {
			// ���Ŀ���ļ����ڣ�����������
			if (coverlay) {
				//logger.debug("Ŀ���ļ��Ѵ��ڣ�׼��ɾ��!");
				if (!FileUtils.delFile(descFileName)) {
					//logger.debug("ɾ��Ŀ���ļ� " + descFileName + " ʧ��!");
					return false;
				}
			} else {
				//logger.debug("�����ļ�ʧ�ܣ�Ŀ���ļ� " + descFileName + " �Ѵ���!");
				return false;
			}
		} else {
			if (!descFile.getParentFile().exists()) {
				// ���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽�Ŀ¼
				//logger.debug("Ŀ���ļ����ڵ�Ŀ¼�����ڣ�����Ŀ¼!");
				// ����Ŀ���ļ����ڵ�Ŀ¼
				if (!descFile.getParentFile().mkdirs()) {
					//logger.debug("����Ŀ���ļ����ڵ�Ŀ¼ʧ��!");
					return false;
				}
			}
		}

		// ׼�������ļ�
		// ��ȡ��λ��
		int readByte = 0;
		InputStream ins = null;
		OutputStream outs = null;
		try {
			// ��Դ�ļ�
			ins = new FileInputStream(srcFile);
			// ��Ŀ���ļ��������
			outs = new FileOutputStream(descFile);
			byte[] buf = new byte[1024];
			// һ�ζ�ȡ1024���ֽڣ���readByteΪ-1ʱ��ʾ�ļ��Ѿ���ȡ���
			while ((readByte = ins.read(buf)) != -1) {
				// ����ȡ���ֽ���д�뵽�����
				outs.write(buf, 0, readByte);
			}
			//logger.debug("���Ƶ����ļ� " + srcFileName + " ��" + descFileName+ "�ɹ�!");
			return true;
		} catch (Exception e) {
			//logger.debug("�����ļ�ʧ�ܣ�" + e.getMessage());
			return false;
		} finally {
			// �ر���������������ȹر��������Ȼ���ٹر�������
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException oute) {
					oute.printStackTrace();
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException ine) {
					ine.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��������Ŀ¼�����ݣ����Ŀ��Ŀ¼���ڣ��򲻸���
	 * @param srcDirName ԴĿ¼��
	 * @param descDirName Ŀ��Ŀ¼��
	 * @return ������Ƴɹ�����true�����򷵻�false
	 */
	public static boolean copyDirectory(String srcDirName, String descDirName) {
		return FileUtils.copyDirectoryCover(srcDirName, descDirName,
				false);
	}

	/**
	 * ��������Ŀ¼������ 
	 * @param srcDirName ԴĿ¼��
	 * @param descDirName Ŀ��Ŀ¼��
	 * @param coverlay ���Ŀ��Ŀ¼���ڣ��Ƿ񸲸�
	 * @return ������Ƴɹ�����true�����򷵻�false
	 */
	public static boolean copyDirectoryCover(String srcDirName,
			String descDirName, boolean coverlay) {
		File srcDir = new File(srcDirName);
		// �ж�ԴĿ¼�Ƿ����
		if (!srcDir.exists()) {
			//logger.debug("����Ŀ¼ʧ�ܣ�ԴĿ¼ " + srcDirName + " ������!");
			return false;
		}
		// �ж�ԴĿ¼�Ƿ���Ŀ¼
		else if (!srcDir.isDirectory()) {
			//logger.debug("����Ŀ¼ʧ�ܣ�" + srcDirName + " ����һ��Ŀ¼!");
			return false;
		}
		// ���Ŀ���ļ����������ļ��ָ�����β���Զ�����ļ��ָ���
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		// ���Ŀ���ļ��д���
		if (descDir.exists()) {
			if (coverlay) {
				// ������Ŀ��Ŀ¼
				//logger.debug("Ŀ��Ŀ¼�Ѵ��ڣ�׼��ɾ��!");
				if (!FileUtils.delFile(descDirNames)) {
					//logger.debug("ɾ��Ŀ¼ " + descDirNames + " ʧ��!");
					return false;
				}
			} else {
				//logger.debug("Ŀ��Ŀ¼����ʧ�ܣ�Ŀ��Ŀ¼ " + descDirNames + " �Ѵ���!");
				return false;
			}
		} else {
			// ����Ŀ��Ŀ¼
			//logger.debug("Ŀ��Ŀ¼�����ڣ�׼������!");
			if (!descDir.mkdirs()) {
				//logger.debug("����Ŀ��Ŀ¼ʧ��!");
				return false;
			}

		}

		boolean flag = true;
		// �г�ԴĿ¼�µ������ļ�������Ŀ¼��
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// �����һ�������ļ�����ֱ�Ӹ���
			if (files[i].isFile()) {
				flag = FileUtils.copyFile(files[i].getAbsolutePath(),
						descDirName + files[i].getName());
				// ��������ļ�ʧ�ܣ����˳�ѭ��
				if (!flag) {
					break;
				}
			}
			// �������Ŀ¼�����������Ŀ¼
			if (files[i].isDirectory()) {
				flag = FileUtils.copyDirectory(files[i]
						.getAbsolutePath(), descDirName + files[i].getName());
				// �������Ŀ¼ʧ�ܣ����˳�ѭ��
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			//logger.debug("����Ŀ¼ " + srcDirName + " �� " + descDirName + " ʧ��!");
			return false;
		}
		//logger.debug("����Ŀ¼ " + srcDirName + " �� " + descDirName + " �ɹ�!");
		return true;

	}

	/**
	 * 
	 * ɾ���ļ�������ɾ�������ļ����ļ���
	 * 
	 * @param fileName ��ɾ�����ļ���
	 * @return ���ɾ���ɹ����򷵻�true�����Ƿ���false
	 */
	public static boolean delFile(String fileName) {
 		File file = new File(fileName);
		if (!file.exists()) {
			//logger.debug(fileName + " �ļ�������!");
			return true;
		} else {
			if (file.isFile()) {
				return FileUtils.deleteFile(fileName);
			} else {
				return FileUtils.deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 
	 * ɾ�������ļ�
	 * 
	 * @param fileName ��ɾ�����ļ���
	 * @return ���ɾ���ɹ����򷵻�true�����򷵻�false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				//logger.debug("ɾ���ļ� " + fileName + " �ɹ�!");
				return true;
			} else {
				//logger.debug("ɾ���ļ� " + fileName + " ʧ��!");
				return false;
			}
		} else {
			//logger.debug(fileName + " �ļ�������!");
			return true;
		}
	}

	/**
	 * 
	 * ɾ��Ŀ¼��Ŀ¼�µ��ļ�
	 * 
	 * @param dirName ��ɾ����Ŀ¼���ڵ��ļ�·��
	 * @return ���Ŀ¼ɾ���ɹ����򷵻�true�����򷵻�false
	 */
	public static boolean deleteDirectory(String dirName) {
		String dirNames = dirName;
		if (!dirNames.endsWith(File.separator)) {
			dirNames = dirNames + File.separator;
		}
		File dirFile = new File(dirNames);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			//logger.debug(dirNames + " Ŀ¼������!");
			return true;
		}
		boolean flag = true;
		// �г�ȫ���ļ�����Ŀ¼
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// ɾ�����ļ�
			if (files[i].isFile()) {
				flag = FileUtils.deleteFile(files[i].getAbsolutePath());
				// ���ɾ���ļ�ʧ�ܣ����˳�ѭ��
				if (!flag) {
					break;
				}
			}
			// ɾ����Ŀ¼
			else if (files[i].isDirectory()) {
				flag = FileUtils.deleteDirectory(files[i]
						.getAbsolutePath());
				// ���ɾ����Ŀ¼ʧ�ܣ����˳�ѭ��
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			//logger.debug("ɾ��Ŀ¼ʧ��!");
			return false;
		}
		// ɾ����ǰĿ¼
		if (dirFile.delete()) {
			//logger.debug("ɾ��Ŀ¼ " + dirName + " �ɹ�!");
			return true;
		} else {
			//logger.debug("ɾ��Ŀ¼ " + dirName + " ʧ��!");
			return false;
		}

	}

	/**
	 * ���������ļ�
	 * @param descFileName �ļ���������·��
	 * @return ��������ɹ����򷵻�true�����򷵻�false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			//logger.debug("�ļ� " + descFileName + " �Ѵ���!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			//logger.debug(descFileName + " ΪĿ¼�����ܴ���Ŀ¼!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// ����ļ����ڵ�Ŀ¼�����ڣ��򴴽�Ŀ¼
			if (!file.getParentFile().mkdirs()) {
				//logger.debug("�����ļ����ڵ�Ŀ¼ʧ��!");
				return false;
			}
		}

		// �����ļ�
		try {
			if (file.createNewFile()) {
				//logger.debug(descFileName + " �ļ������ɹ�!");
				return true;
			} else {
				//logger.debug(descFileName + " �ļ�����ʧ��!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.debug(descFileName + " �ļ�����ʧ��!");
			return false;
		}

	}

	/**
	 * ����Ŀ¼
	 * @param descDirName Ŀ¼��,����·��
	 * @return ��������ɹ����򷵻�true�����򷵻�false
	 */
	public static boolean createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			//logger.debug("Ŀ¼ " + descDirNames + " �Ѵ���!");
			return false;
		}
		// ����Ŀ¼
		if (descDir.mkdirs()) {
			//logger.debug("Ŀ¼ " + descDirNames + " �����ɹ�!");
			return true;
		} else {
			//logger.debug("Ŀ¼ " + descDirNames + " ����ʧ��!");
			return false;
		}

	}

	/**
	 * д���ļ�
	 * @param file Ҫд����ļ�
	 */
	public static void writeToFile(String fileName, String content, boolean append) {
		try {
			//FileUtils.write(new File(fileName), content, "utf-8", append);
			FileWriter writer = new FileWriter(new File(fileName));
			writer.write(content);
			//logger.debug("�ļ� " + fileName + " д��ɹ�!");
		} catch (IOException e) {
			//logger.debug("�ļ� " + fileName + " д��ʧ��! " + e.getMessage());
		}
	}

	/**
	 * д���ļ�
	 * @param file Ҫд����ļ�
	 */
	public static void writeToFile(String fileName, String content, String encoding, boolean append) {
		try {
			//FileUtils.write(new File(fileName), content, encoding, append);
			FileWriter writer = new FileWriter(new File(fileName));
			writer.write(content);
			//logger.debug("�ļ� " + fileName + " д��ɹ�!");
		} catch (IOException e) {
			//logger.debug("�ļ� " + fileName + " д��ʧ��! " + e.getMessage());
		}
	}
	
	/**
	 * ѹ���ļ���Ŀ¼
	 * @param srcDirName ѹ���ĸ�Ŀ¼
	 * @param fileName ��Ŀ¼�µĴ�ѹ�����ļ������ļ�����������*��""��ʾ��Ŀ¼�µ�ȫ���ļ�
	 * @param descFileName Ŀ��zip�ļ�
	 */
	public static void zipFiles(String srcDirName, String fileName,
			String descFileName) {
		// �ж�Ŀ¼�Ƿ����
		if (srcDirName == null) {
			//logger.debug("�ļ�ѹ��ʧ�ܣ�Ŀ¼ " + srcDirName + " ������!");
			return;
		}
		File fileDir = new File(srcDirName);
		if (!fileDir.exists() || !fileDir.isDirectory()) {
			//logger.debug("�ļ�ѹ��ʧ�ܣ�Ŀ¼ " + srcDirName + " ������!");
			return;
		}
		String dirPath = fileDir.getAbsolutePath();
		File descFile = new File(descFileName);
		try {
			ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
					descFile));
			if ("*".equals(fileName) || "".equals(fileName)) {
				FileUtils.zipDirectoryToZipFile(dirPath, fileDir, zouts);
			} else {
				File file = new File(fileDir, fileName);
				if (file.isFile()) {
					FileUtils.zipFilesToZipFile(dirPath, file, zouts);
				} else {
					FileUtils
							.zipDirectoryToZipFile(dirPath, file, zouts);
				}
			}
			zouts.close();
			//logger.debug(descFileName + " �ļ�ѹ���ɹ�!");
		} catch (Exception e) {
			//logger.debug("�ļ�ѹ��ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * ��ѹ��ZIP�ļ�����ZIP�ļ�������ݽ�ѹ��descFileNameĿ¼��
	 * @param zipFileName ��Ҫ��ѹ��ZIP�ļ�
	 * @param descFileName Ŀ���ļ�
	 */
	public static boolean unZipFiles(String zipFileName, String descFileName) {
		String descFileNames = descFileName;
		if (!descFileNames.endsWith(File.separator)) {
			descFileNames = descFileNames + File.separator;
		}		
        try {
			// ����ZIP�ļ�����ZipFile����
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String descFileDir = null;
			byte[] buf = new byte[4096];
			int readByte = 0;
			// ��ȡZIP�ļ������е�entry
			@SuppressWarnings("rawtypes")
			Enumeration enums = zipFile.getEntries();
		
			while (enums.hasMoreElements()) {
				entry = (ZipEntry) enums.nextElement();
				
				entryName = entry.getName();
				descFileDir = descFileNames + entryName;
				if (entry.isDirectory()) {
					
					new File(descFileDir).mkdirs();
					continue;
				} else {
		
					new File(descFileDir).getParentFile().mkdirs();
				}
				File file = new File(descFileDir);

				OutputStream os = new FileOutputStream(file);
	
		        InputStream is = zipFile.getInputStream(entry);
				while ((readByte = is.read(buf)) != -1) {
					os.write(buf, 0, readByte);
				}
				os.close();
				is.close();
			}
			zipFile.close();
	
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	/**
	 * ��Ŀ¼ѹ����ZIP�����
	 * @param dirPath Ŀ¼·��
	 * @param fileDir �ļ���Ϣ
	 * @param zouts �����
	 */
	public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts) {
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();
			// �յ��ļ���
			if (files.length == 0) {
				// Ŀ¼��Ϣ
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try {
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// ������ļ���������ļ�ѹ������
					FileUtils
							.zipFilesToZipFile(dirPath, files[i], zouts);
				} else {
					// �����Ŀ¼����ݹ����
					FileUtils.zipDirectoryToZipFile(dirPath, files[i],
							zouts);
				}
			}
		}
	}

	/**
	 * ���ļ�ѹ����ZIP�����
	 * @param dirPath Ŀ¼·��
	 * @param file �ļ�
	 * @param zouts �����
	 */
	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
		FileInputStream fin = null;
		ZipEntry entry = null;
		// �������ƻ�����
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile()) {
			try {
				// ����һ���ļ�������
				fin = new FileInputStream(file);
				// ����һ��ZipEntry
				entry = new ZipEntry(getEntryName(dirPath, file));
				// �洢��Ϣ��ѹ���ļ�
				zouts.putNextEntry(entry);
				// �����ֽڵ�ѹ���ļ�
				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
				System.out
						.println("����ļ� " + file.getAbsolutePath() + " ��zip�ļ���!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ��ȡ��ѹ���ļ���ZIP�ļ���entry�����֣�������ڸ�Ŀ¼�����·����
	 * @param dirPat Ŀ¼��
	 * @param file entry�ļ���
	 * @return
	 */
	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator)) {
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// ����Ŀ¼��������entry���ֺ������"/"����ʾ������Ŀ¼��洢
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}

	/**
	 * ���ݡ��ļ����ĺ�׺����ȡ�ļ��������ͣ����Ǹ���File.getContentType()��ȡ���ļ����ͣ�
	 * @param returnFileName ����֤���ļ���
	 * @return �����ļ�����
	 */
	public static String getContentType(String returnFileName) {
		String contentType = "application/octet-stream";
		if (returnFileName.lastIndexOf(".") < 0)
			return contentType;
		returnFileName = returnFileName.toLowerCase();
		returnFileName = returnFileName.substring(returnFileName.lastIndexOf(".") + 1);
		if (returnFileName.equals("html") || returnFileName.equals("htm") || returnFileName.equals("shtml")) {
			contentType = "text/html";
		} else if (returnFileName.equals("apk")) {
			contentType = "application/vnd.android.package-archive";
		} else if (returnFileName.equals("sis")) {
			contentType = "application/vnd.symbian.install";
		} else if (returnFileName.equals("sisx")) {
			contentType = "application/vnd.symbian.install";
		} else if (returnFileName.equals("exe")) {
			contentType = "application/x-msdownload";
		} else if (returnFileName.equals("msi")) {
			contentType = "application/x-msdownload";
		} else if (returnFileName.equals("css")) {
			contentType = "text/css";
		} else if (returnFileName.equals("xml")) {
			contentType = "text/xml";
		} else if (returnFileName.equals("gif")) {
			contentType = "image/gif";
		} else if (returnFileName.equals("jpeg") || returnFileName.equals("jpg")) {
			contentType = "image/jpeg";
		} else if (returnFileName.equals("js")) {
			contentType = "application/x-javascript";
		} else if (returnFileName.equals("atom")) {
			contentType = "application/atom+xml";
		} else if (returnFileName.equals("rss")) {
			contentType = "application/rss+xml";
		} else if (returnFileName.equals("mml")) {
			contentType = "text/mathml";
		} else if (returnFileName.equals("txt")) {
			contentType = "text/plain";
		} else if (returnFileName.equals("jad")) {
			contentType = "text/vnd.sun.j2me.app-descriptor";
		} else if (returnFileName.equals("wml")) {
			contentType = "text/vnd.wap.wml";
		} else if (returnFileName.equals("htc")) {
			contentType = "text/x-component";
		} else if (returnFileName.equals("png")) {
			contentType = "image/png";
		} else if (returnFileName.equals("tif") || returnFileName.equals("tiff")) {
			contentType = "image/tiff";
		} else if (returnFileName.equals("wbmp")) {
			contentType = "image/vnd.wap.wbmp";
		} else if (returnFileName.equals("ico")) {
			contentType = "image/x-icon";
		} else if (returnFileName.equals("jng")) {
			contentType = "image/x-jng";
		} else if (returnFileName.equals("bmp")) {
			contentType = "image/x-ms-bmp";
		} else if (returnFileName.equals("svg")) {
			contentType = "image/svg+xml";
		} else if (returnFileName.equals("jar") || returnFileName.equals("var") 
				|| returnFileName.equals("ear")) {
			contentType = "application/java-archive";
		} else if (returnFileName.equals("doc")) {
			contentType = "application/msword";
		} else if (returnFileName.equals("pdf")) {
			contentType = "application/pdf";
		} else if (returnFileName.equals("rtf")) {
			contentType = "application/rtf";
		} else if (returnFileName.equals("xls")) {
			contentType = "application/vnd.ms-excel";
		} else if (returnFileName.equals("ppt")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (returnFileName.equals("7z")) {
			contentType = "application/x-7z-compressed";
		} else if (returnFileName.equals("rar")) {
			contentType = "application/x-rar-compressed";
		} else if (returnFileName.equals("swf")) {
			contentType = "application/x-shockwave-flash";
		} else if (returnFileName.equals("rpm")) {
			contentType = "application/x-redhat-package-manager";
		} else if (returnFileName.equals("der") || returnFileName.equals("pem")
				|| returnFileName.equals("crt")) {
			contentType = "application/x-x509-ca-cert";
		} else if (returnFileName.equals("xhtml")) {
			contentType = "application/xhtml+xml";
		} else if (returnFileName.equals("zip")) {
			contentType = "application/zip";
		} else if (returnFileName.equals("mid") || returnFileName.equals("midi") 
				|| returnFileName.equals("kar")) {
			contentType = "audio/midi";
		} else if (returnFileName.equals("mp3")) {
			contentType = "audio/mpeg";
		} else if (returnFileName.equals("ogg")) {
			contentType = "audio/ogg";
		} else if (returnFileName.equals("m4a")) {
			contentType = "audio/x-m4a";
		} else if (returnFileName.equals("ra")) {
			contentType = "audio/x-realaudio";
		} else if (returnFileName.equals("3gpp")
				|| returnFileName.equals("3gp")) {
			contentType = "video/3gpp";
		} else if (returnFileName.equals("mp4")) {
			contentType = "video/mp4";
		} else if (returnFileName.equals("mpeg")
				|| returnFileName.equals("mpg")) {
			contentType = "video/mpeg";
		} else if (returnFileName.equals("mov")) {
			contentType = "video/quicktime";
		} else if (returnFileName.equals("flv")) {
			contentType = "video/x-flv";
		} else if (returnFileName.equals("m4v")) {
			contentType = "video/x-m4v";
		} else if (returnFileName.equals("mng")) {
			contentType = "video/x-mng";
		} else if (returnFileName.equals("asx") || returnFileName.equals("asf")) {
			contentType = "video/x-ms-asf";
		} else if (returnFileName.equals("wmv")) {
			contentType = "video/x-ms-wmv";
		} else if (returnFileName.equals("avi")) {
			contentType = "video/x-msvideo";
		}
		return contentType;
	}
	
	/**
	 * ������������ļ����أ�֧�ֶϵ�����
	 * @param file Ҫ���ص��ļ�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @return ���ش�����Ϣ���޴�����Ϣ����null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response){
		 return downFile(file, request, response, null);
	}
	
	/**
	 * ������������ļ����أ�֧�ֶϵ�����
	 * @param file Ҫ���ص��ļ�
	 * @param request �������
	 * @param response ��Ӧ����
	 * @param fileName ָ�����ص��ļ���
	 * @return ���ش�����Ϣ���޴�����Ϣ����null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response, String fileName){
		String error  = null;
		if (file != null && file.exists()) {
			if (file.isFile()) {
				if (file.length() <= 0) {
					error = "���ļ���һ�����ļ���";
				}
				if (!file.canRead()) {
					error = "���ļ�û�ж�ȡȨ�ޡ�";
				}
			} else {
				error = "���ļ���һ���ļ��С�";
			}
		} else {
			error = "�ļ��Ѷ�ʧ�򲻴��ڣ�";
		}
		if (error != null){
			//logger.debug("---------------" + file + " " + error);
			return error;
		}

		long fileLength = file.length(); // ��¼�ļ���С
		long pastLength = 0; 	// ��¼�������ļ���С
		int rangeSwitch = 0; 	// 0����ͷ��ʼ��ȫ�����أ�1����ĳ�ֽڿ�ʼ�����أ�bytes=27000-����2����ĳ�ֽڿ�ʼ��ĳ�ֽڽ��������أ�bytes=27000-39000��
		long toLength = 0; 		// ��¼�ͻ�����Ҫ���ص��ֽڶε����һ���ֽ�ƫ����������bytes=27000-39000�������ֵ��Ϊ39000��
		long contentLength = 0; // �ͻ���������ֽ�����
		String rangeBytes = ""; // ��¼�ͻ��˴��������硰bytes=27000-�����ߡ�bytes=27000-39000��������
		RandomAccessFile raf = null; // �����ȡ����
		OutputStream os = null; 	// д������
		OutputStream out = null; 	// ����
		byte b[] = new byte[1024]; 	// �ݴ�����

		if (request.getHeader("Range") != null) { // �ͻ�����������ص��ļ���Ŀ�ʼ�ֽ�
			response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
			//logger.debug("request.getHeader(\"Range\") = " + request.getHeader("Range"));
			rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
			if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {// bytes=969998336-
				rangeSwitch = 1;
				rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				pastLength = Long.parseLong(rangeBytes.trim());
				contentLength = fileLength - pastLength; // �ͻ���������� 969998336  ֮����ֽ�
			} else { // bytes=1275856879-1275877358
				rangeSwitch = 2;
				String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
				pastLength = Long.parseLong(temp0.trim()); // bytes=1275856879-1275877358���ӵ� 1275856879 ���ֽڿ�ʼ����
				toLength = Long.parseLong(temp2); // bytes=1275856879-1275877358������ 1275877358 ���ֽڽ���
				contentLength = toLength - pastLength; // �ͻ���������� 1275856879-1275877358 ֮����ֽ�
			}
		} else { // �ӿ�ʼ��������
			contentLength = fileLength; // �ͻ���Ҫ��ȫ������
		}

		// �����������Content-Length����ͻ��˻��Զ����ж��߳����ء������ϣ��֧�ֶ��̣߳���Ҫ������������� ��Ӧ�ĸ�ʽ��:
		// Content-Length: [�ļ����ܴ�С] - [�ͻ�����������ص��ļ���Ŀ�ʼ�ֽ�]
		// ServletActionContext.getResponse().setHeader("Content- Length", new Long(file.length() - p).toString());
		response.reset(); 
		if (pastLength != 0) {
			response.setHeader("Accept-Ranges", "bytes");

			switch (rangeSwitch) {
				case 1: { // ��� bytes=27000- ������
					String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-")
							.append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString()).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				case 2: { // ��� bytes=27000-39000 ������
					String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				default: {
					break;
				}
			}
		} else {
			// �Ǵӿ�ʼ����
			//logger.debug("---------------�Ǵӿ�ʼ�������أ�");
		}

		try {
			//response.addHeader("Content-Disposition", "attachment; filename=\"" +  Encodes.urlEncode(StringUtils.isBlank(fileName) ? file.getName() : fileName) + "\"");
			response.addHeader("Content-Disposition", "attachment; filename=\"" +  fileName == null ? file.getName() : fileName + "\"");
			response.setContentType(getContentType(file.getName())); // set the MIME type.
			response.addHeader("Content-Length", String.valueOf(contentLength));
			os = response.getOutputStream();
			out = new BufferedOutputStream(os);
			raf = new RandomAccessFile(file, "r");
			try {
				switch (rangeSwitch) {
					case 0: { // ��ͨ���أ����ߴ�ͷ��ʼ������ ͬ1
					}
					case 1: { // ��� bytes=27000- ������
						raf.seek(pastLength); // ���� bytes=969998336- �Ŀͻ����������� 969998336 ���ֽ�
						int n = 0;
						while ((n = raf.read(b, 0, 1024)) != -1) {
							out.write(b, 0, n);
						}
						break;
					}
					case 2: { // ��� bytes=27000-39000 ������
						raf.seek(pastLength); // ���� bytes=1275856879-1275877358 �Ŀͻ��������ҵ��� 1275856879 ���ֽ�
						int n = 0;
						long readLength = 0; // ��¼�Ѷ��ֽ���
						while (readLength <= contentLength - 1024) {// �󲿷��ֽ��������ȡ
							n = raf.read(b, 0, 1024);
							readLength += 1024;
							out.write(b, 0, n);
						}
						if (readLength <= contentLength) { // ���µĲ��� 1024 ���ֽ��������ȡ
							n = raf.read(b, 0, (int) (contentLength - readLength));
							out.write(b, 0, n);
						}
						break;
					}
					default: {
						break;
					}
				}
				out.flush();
				//logger.debug("---------------������ɣ�");
			} catch (IOException ie) {
				/**
				 * ��д���ݵ�ʱ�� ���� ClientAbortException ֮����쳣��
				 * ����Ϊ�ͻ���ȡ�������أ����������˼����������д������ʱ�� �׳�����쳣������������ġ�
				 * �����Ƕ���Ѹ��������Ѫ�Ŀͻ�������� �����Ѿ���һ���߳��ڶ�ȡ bytes=1275856879-1275877358��
				 * �����ʱ����û�ж�ȡ��ϣ�Ѹ�׻������ڶ������������������߳�����ȡ��ͬ���ֽڶΣ� ֱ����һ���̶߳�ȡ��ϣ�Ѹ�׻� KILL
				 * ��������������ͬһ�ֽڶε��̣߳� ǿ����ֹ�ֽڶ�������ɷ������� ClientAbortException��
				 * ���ԣ����Ǻ��������쳣
				 */
				//logger.debug("���ѣ���ͻ��˴���ʱ����IO�쳣�������쳣������ģ��п��ܿͻ���ȡ�������أ����´��쳣�����ù��ģ�");
			}
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					//logger.error(e.getMessage(), e);
				}
			}
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					//logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}


	public static String path(String path){
		String p = StringUtils.replace(path, "\\", "/");
		p = StringUtils.join(StringUtils.split(p, "/"), "/");
		if (!StringUtils.startsWithAny(p, "/") && StringUtils.startsWithAny(path, "\\", "/")){
			p += "/";
		}
//		if (!StringUtils.endsWithAny(p, "/") && StringUtils.endsWithAny(path, "\\", "/")){
//			p = p + "/";
//		}
		if (path != null && path.startsWith("/")){
			p = "/" + p; // linux��·��
		}
		return p;
	}
	

	public static List<String> findChildrenList(File dir, boolean searchDirs) {
		List<String> files = new ArrayList();
		for (String subFiles : dir.list()) {
			File file = new File(dir + "/" + subFiles);
			if (((searchDirs) && (file.isDirectory())) || ((!searchDirs) && (!file.isDirectory()))) {
				files.add(file.getName());
			}
		}
		return files;
	}

	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1) || (fileName.lastIndexOf(".") == fileName.length() - 1)) {
			return null;
		}
		return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf(".") + 1));
	}

	public static String getFileNameWithoutExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
}
