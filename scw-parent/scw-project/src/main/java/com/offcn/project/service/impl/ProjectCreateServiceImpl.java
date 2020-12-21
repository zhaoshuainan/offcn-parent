package com.offcn.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.offcn.project.contants.ProjectConstant;
import com.offcn.project.service.ProjectCreateService;
import com.offcn.project.vo.req.ProjectRedisStorageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.UUID;

@Service
public class ProjectCreateServiceImpl implements ProjectCreateService {

    /*初始化项目*/
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String initCreateProject(Integer memeberId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        //项目的临时对象
        ProjectRedisStorageVo initVo = new ProjectRedisStorageVo();
        initVo.setMemberid(memeberId);

        //将initVo转为json字符串

        String jsonString = JSON.toJSONString(initVo);

        stringRedisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX + token, jsonString);

        return token;
    }
}
