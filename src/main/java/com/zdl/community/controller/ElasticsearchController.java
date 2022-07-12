package com.zdl.community.controller;

import com.zdl.community.entity.DiscussPost;
import com.zdl.community.entity.PageHelper;
import com.zdl.community.service.ElasticsearchService;
import com.zdl.community.service.LikeService;
import com.zdl.community.service.UserService;
import com.zdl.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ElasticsearchController implements CommunityConstant {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    // search?keyword=xxx
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(String keyword, PageHelper pageHelper, Model model) {
        // 搜索帖子
        Page<DiscussPost> searchResult =
                elasticsearchService.searchDiscussPost(keyword, pageHelper.getCurrentPage() - 1, pageHelper.getLimit());
        // 聚合数据
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (searchResult != null) {
            for (DiscussPost post : searchResult) {
                Map<String, Object> map = new HashMap<>();
                // 帖子
                map.put("post", post);
                // 作者
                map.put("user", userService.findUserById(post.getUserId()));
                // 点赞数量
                map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("keyword", keyword);

        // 分页信息
        pageHelper.setPath("/search?keyword=" + keyword);
        pageHelper.setRows(searchResult == null ? 0 : (int) searchResult.getTotalElements());

        return "/site/search";
    }
}
