package com.zdl.community;

import com.zdl.community.dao.DiscussPostMapper;
import com.zdl.community.dao.LoginTicketMapper;
import com.zdl.community.entity.DiscussPost;
import com.zdl.community.entity.LoginTicket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0,0,10, 0);
        for(DiscussPost post : discussPosts){
            System.out.println(post);
        }
    }
    @Test
    public void testselectDiscussPostRows(){
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }
    @Test
    public void testaddDiscussPost(){
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(103);
        discussPost.setTitle("11112222333");
        discussPost.setContent("测试testaddDiscussPost");
        discussPost.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(discussPost);
    }
}
