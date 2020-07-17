import dao.EmpDao;
import jedis.JedisUtil;
import jedis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.Emp;
import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class BatisTest {
    @Autowired
    private EmpDao empDao;
//    @Autowired
//    private RedisUtil redisUtil;
    @Test
    public void btest(){
        Emp emp = empDao.findUserByuserName("Steven");
        System.out.println(emp.getPassword());
    }

    @Test
    public void redis(){
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("lulala","lalulu");
    }
}
