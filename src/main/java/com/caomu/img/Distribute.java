/*
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                             O\ = /O
 *                         ____/`---'\____
 *                       .   ' \\| |// `.
 *                        / \\||| : |||// \
 *                      / _||||| -:- |||||- \
 *                        | | \\\ - /// | |
 *                      | \_| ''\---/'' | |
 *                       \ .-\__ `-` ___/-. /
 *                    ___`. .' /--.--\ `. . __
 *                 ."" '< `.___\_<|>_/___.' >'"".
 *                | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *                  \ \ `-. \_ __\ /__ _/ .-` / /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *
 *          .............................................
 *                   佛祖保佑             永无BUG
 *           佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 *
 * Created by caomu on 2016年10月17日 11时24分59秒 星期一.
 */
package com.caomu.img;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

/**
 * @author gr5154
 * 
 */
public class Distribute {
	private final String[] extName = new String[] { "mp4", "png", "jpg", "mov",
			"gif", "avi", "mpg", "bmp" };
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @param path
	 * @param isChild
	 * @param isCleanFolder
	 */
	void distribute(final String path, String toPath, final boolean isChild,
			final boolean isChildMoveToPath, final boolean isCleanFolder) {
		final List<File> fileList = new ArrayList<File>();
		final List<File> directoryList = new ArrayList<File>();
		final File filePath = new File(path);
		for (final File f : filePath.listFiles()) {
			if (f.isDirectory()) {
				System.out.println(f.getName() + "\t\t\tis a directory;");
				this.log.append(f.getName() + "\t\t\tis a directory;\n");
				directoryList.add(f);
			} else {
				fileList.add(f);
			}
		}
		for (final File f : fileList) {
			String date = null;
			try {
				date = this.getDateFromImgEXIF(f);
			} catch (final IOException e) {
				System.out.println(f.getPath() + "\t\t\tis not exist");
				this.log.append(f.getPath() + "\t\t\tis not exist\n");
				e.printStackTrace();
			}
			if (null == date) {
				System.out.print(f.getPath() + "\t\t\thas not EXIF\t\t\t");
				this.log.append(f.getPath() + "\t\t\thas not EXIF\t\t\t");
				date = this.getDateFromImgModifyDate(f);
			}
			if (null == date) {
				System.out.println(f.getPath() + "\t\t\tcan not be processed");
				this.log.append(f.getPath() + "\t\t\tcan not be processed\n");
				continue;
			}
			final String newPath = toPath + File.separator + date;
			final File newFilePath = new File(newPath);
			if (!newFilePath.exists()) {
				newFilePath.mkdirs();
			}
			final String newFilename = newPath + File.separator + f.getName();
			// System.out.println(f.getParent() + "\t\t\t" + newPath
			// + File.separator);
			if (!f.getParent().equals(newPath)) {
				f.renameTo(new File(newFilename));
				if (f.exists()) {
					f.renameTo(new File(toPath + File.separator + f.getName()));
				}
				System.out.println(f.getPath() + "\t\t\t===>>\t\t\t"
						+ newFilename);
				this.log.append(f.getPath() + "\t\t\t===>>\t\t\t" + newFilename
						+ "\n");
			} else {
				System.out.println(f.getPath() + "\t\t\tneed not to be moved");
				this.log.append(f.getPath() + "\t\t\tneed not to be moved\n");
			}
		}
		if (isChild) {
			for (final File f : directoryList) {
				if (!isChildMoveToPath) {
					toPath = f.getPath();
				}
				this.distribute(f.getPath(), toPath, isChild,
						isChildMoveToPath, isCleanFolder);
			}
		}
		if (isCleanFolder) {
			for (final File f : directoryList) {
				if (0 == f.listFiles().length) {
					System.out.println(f.getPath() + " have been deleted!");
					this.log.append(f.getPath() + " have been deleted!\n");
					f.deleteOnExit();
				}
			}
		}
	}

	/**
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private String getDateFromImgEXIF(final File file) throws IOException {
		String date = null;
		if (file.isFile()) {
			try {
				final Metadata metadata = ImageMetadataReader
						.readMetadata(file);
				// obtain the Exif directory
				final Directory directory = metadata
						.getDirectory(ExifSubIFDDirectory.class);
				if (null != directory) {
					final Date tagDate = directory
							.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
					if (null != tagDate) {
						date = this.sdf.format(tagDate);
					}
				}
			} catch (final ImageProcessingException e) {
				// e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * @param f
	 * @return
	 */
	private String getDateFromImgModifyDate(final File file) {
		String fileExtName = null, date = null;
		final String name[] = file.getName().split("\\.");
		if (name.length > 1) {
			fileExtName = name[1];
		}
		boolean isImgFile = false;
		for (final String ext : this.extName) {
			if (ext.equalsIgnoreCase(fileExtName)) {
				isImgFile = true;
				break;
			}
		}
		if (isImgFile) {
			date = this.sdf.format(new Date(file.lastModified()));
		}
		return date;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) {
		String path = null;
		if (0 == args.length) {
		} else {
			path = args[0];
		}

		String toPath = path;
		boolean isChildMoveToPath = false;
		if (args.length > 1) {
			toPath = args[1];
			isChildMoveToPath = true;
		}

		boolean isChild = false;
		if (args.length > 2) {
			isChild = new Boolean(args[2]);
		}

		boolean isCleanFolder = false;
		if (args.length > 3) {
			isCleanFolder = new Boolean(args[3]);
		}
		path = "F:\\???\\2007??????????";
		toPath = path;
		isChild = true;
		isChildMoveToPath = true;
		isCleanFolder = true;
		Distribute distribute = new Distribute();
		distribute.distribute(path, toPath, isChild, isChildMoveToPath,
				isCleanFolder);

		System.out.println("~~~~~~~~~~~~~~~~~~~finish!!!~~~~~~~~~~~~~~~~~");
	}

	/**
	 * @return the log
	 */
	public StringBuffer getLog() {
		return this.log;
	}

	private StringBuffer log;

	/**
	 * @param log
	 */
	public void setLog(StringBuffer log) {
		this.log = log;
	}

}
