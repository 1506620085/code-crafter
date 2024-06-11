package com.crafter.maker;


import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.crafter.meta.Meta;

import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta meta = JSONUtil.toBean(metaJson, Meta.class);
    }
}
