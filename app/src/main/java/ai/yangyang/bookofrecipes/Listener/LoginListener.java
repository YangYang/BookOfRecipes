package ai.yangyang.bookofrecipes.Listener;

import ai.yangyang.bookofrecipes.Bean.LoginResBean;

public interface LoginListener {
    /**
     * @param user 登录者的对象
     * @exception Exception e 网络错误 登录失败消息
     * @return 没有返回值
     * */
    public void done(LoginResBean user, Exception e);
}
