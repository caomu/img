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
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class EXIFReader {

	public static void main(String args[]) throws IOException {
		String path = "D:\\2011-10-08";
		String filename = "DSC01436.JPG";
		File jpegFile = new File(path + File.separator + filename);
		Metadata metadata;
		try {
			metadata = ImageMetadataReader.readMetadata(jpegFile);
			for (Directory directory : metadata.getDirectories()) {
				System.out.println("~~~~~~~~~~~~~~~"
						+ directory.getClass().getName());
				for (Tag tag : directory.getTags()) {
					System.out.println(tag.getClass().getName() + "||||||"
							+ tag);
				}
			}
			// obtain the Exif directory
			Directory directory = metadata
					.getDirectory(ExifSubIFDDirectory.class);
			// query the tag's value
			Date date = directory
					.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			System.out.println(sdf.format(date));
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		}

	}
}
