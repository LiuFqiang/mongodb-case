package com.pigliu.mongodb.controller;

import cn.hutool.core.lang.hash.Hash;
import cn.hutool.json.JSONUtil;
import com.pigliu.mongodb.dto.mongo.QuickPage;
import com.pigliu.mongodb.service.PageService;
import com.pigliu.mongodb.service.QuickH5Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/h5")
@RequiredArgsConstructor
public class H5Controller {

    private final PageService pageService;

    @GetMapping("/h5View")
    public ModelAndView h5View() {
        ModelAndView view = new ModelAndView("index1");
        Optional<QuickPage> byId = pageService.findById("641a7be1fa6ddb7c90b7f235");
        view.addObject("pageData", JSONUtil.toJsonStr(byId.get()));
        return view;
    }

    @GetMapping("/test")
    public Object test() {
        return "1";
    }
}
