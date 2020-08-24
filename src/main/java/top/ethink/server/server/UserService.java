package top.ethink.server.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ethink.server.entity.User;

/**
 * Created by chendong on 2019/9/3.
 */
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int addUser(User userinfo){
        logger.debug("create user success, uid=" + userinfo.getUid());
        return 0;
    }
}
