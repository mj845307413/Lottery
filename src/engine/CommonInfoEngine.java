package engine;

import net.protocal.Message;


/**
 * 公共数据处理
 * @author Administrator
 *
 */
public interface CommonInfoEngine {
	/**
	 * 获取当前�?售期信息
	 * @param integer：彩种的标示
	 * @return
	 */
	Message getCurrentIssueInfo(Integer integer);

}
