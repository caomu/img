package com.caomu.xwt.test;

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
 * Created by caomu on 2016年10月17日 11时31分37秒 星期一.
 */
import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.xwt.DefaultLoadingContext;
import org.eclipse.e4.xwt.IConstants;
import org.eclipse.e4.xwt.IXWTLoader;
import org.eclipse.e4.xwt.XWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author gr5154
 *
 */
public class XWTComposite extends Composite {

	public XWTComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		// load XWT
		String name = XWTComposite.class.getSimpleName() + IConstants.XWT_EXTENSION_SUFFIX;
		try {
			URL url = XWTComposite.class.getResource(name);
			Map<String, Object> options = new HashMap<>();
			options.put(IXWTLoader.CLASS_PROPERTY, this);
			options.put(IXWTLoader.CONTAINER_PROPERTY, this);
			XWT.setLoadingContext(new DefaultLoadingContext(this.getClass().getClassLoader()));
			XWT.loadWithOptions(url, options);
		} catch (Throwable e) {
			throw new Error("Unable to load " + name, e);
		}
	}

}
