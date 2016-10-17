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

/**
 * 
 */
package com.caomu.xwt.test;

import java.net.URL;

import org.eclipse.e4.xwt.IConstants;
import org.eclipse.e4.xwt.forms.XWTForms;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author gr5154
 *
 */
public class XWTFormsApplication {

	public static void main(String args[]) throws Exception {
		URL url = XWTFormsApplication.class
				.getResource(XWTFormsApplication.class.getSimpleName()
						+ IConstants.XWT_EXTENSION_SUFFIX);
		Control control = XWTForms.load(url);
		Shell shell = control.getShell();
		shell.layout();
		centerInDisplay(shell);
		// run events loop
		shell.open();
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}
	}

	private static void centerInDisplay(Shell shell) {
		Rectangle displayArea = shell.getDisplay().getClientArea();
		shell.setBounds(displayArea.width / 4, displayArea.height / 4,
				displayArea.width / 2, displayArea.height / 2);
	}

}
