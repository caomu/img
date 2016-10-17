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
 * Created by caomu on 2016年10月17日 11时31分08秒 星期一.
 */

/**
 * 
 */
package com.caomu.img;

import org.eclipse.e4.xwt.IConstants;
import org.eclipse.e4.xwt.XWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.*;

import java.net.URL;

/**
 * @author gr5154
 * 
 */
public class Main {
	public static Shell shell = null;
	public static String directoryPath = null;

	public static void main(String args[]) throws Exception {
		URL url = Main.class.getResource(Main.class.getSimpleName()
				+ IConstants.XWT_EXTENSION_SUFFIX);
		Control control = XWT.load(url);
		Main.shell = control.getShell();
		Main.shell.layout();
		Main.centerInDisplay(Main.shell);
		// run events loop
		Main.shell.open();
		while (!Main.shell.isDisposed()) {
			if (!Main.shell.getDisplay().readAndDispatch()) {
				Main.shell.getDisplay().sleep();
			}
		}
	}

	private static void centerInDisplay(Shell shell) {
		Rectangle displayArea = shell.getDisplay().getClientArea();
		shell.setBounds(displayArea.width / 4, 0, displayArea.width / 2,
				displayArea.height);
	}

	public void onOpenFolderButtonMouseDown(Event event) {
		final DirectoryDialog directoryDialog = new DirectoryDialog(Main.shell,
				SWT.OPEN);
		Main.directoryPath = directoryDialog.open();
		// System.out.println(directoryPath);
		Text path = (Text) Main.shell.getChildren()[3];
		path.setText(Main.directoryPath);
		Button start = (Button) Main.shell.getChildren()[5];
		start.setEnabled(true);
	}

	public static StringBuffer log = null;

	public void onStartButtonMouseDown(Event event) {
		Text path = (Text) Main.shell.getChildren()[3];
		Main.directoryPath = path.getText();
		Main.log = new StringBuffer();
		Distribute distribute = new Distribute();
		distribute.setLog(Main.log);
		distribute.distribute(Main.directoryPath, Main.directoryPath, true,
				true, true);
		distribute.getLog().append(
				"~~~~~~~~~~~~~~~~~~~finish!!!~~~~~~~~~~~~~~~~~\n");
		Text log = (Text) Main.shell.getChildren()[7];
		log.setText("");
		log.setText(distribute.getLog().toString());
	}
}
