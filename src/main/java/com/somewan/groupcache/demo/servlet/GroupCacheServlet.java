package com.somewan.groupcache.demo.servlet;

import com.somewan.groupcache.demo.service.GroupCacheService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wan on 2017/2/3.
 */
public class GroupCacheServlet extends HttpServlet {
    GroupCacheService service = GroupCacheService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String retStr;
        String url = req.getRequestURI();
        if(StringUtils.isBlank(url)) {
            retStr = service.badRequest();
            output(resp, retStr);
            return;
        }
        // 正确格式：/cachename/groupname/key
        String[] path = url.split("/");
        if(path.length != 4 || !service.checkCacheName(path[1])) {
            retStr = service.badRequest();
            output(resp, retStr);
            return;
        }
        retStr = service.get(path[2], path[3]);
        output(resp, retStr);
    }

    private void output(HttpServletResponse resp, String content) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(content);
    }
}
