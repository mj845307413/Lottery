package engine;

import net.protocal.Message;
import bean.User;

/**
 * 用户相关的业务操作的接口
 * @author Administrator
 *
 */
public interface UserEngine {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	Message login(User user);
	/**
	 * 获取用户余额
	 * @param user
	 * @return
	 */
	Message getBalance(User user);
	/**
	 * 用户投注
	 * @param user
	 * @return
	 */
	Message bet(User user);
}
