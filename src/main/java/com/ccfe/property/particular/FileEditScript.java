package com.ccfe.property.particular;

import com.ccfe.property.common.utils.FileUtil;

public class FileEditScript{

    public void rewrite(){
        String path = System.getProperty("user.dir")+"/src/main/resources/properties_format.json";
        String words = FileUtil.readFile(path);
        String a = words.replaceAll("\"value\": \"(?! FALSE|TRUE)[^\"]*\"","\"value\": \"\"");
        System.out.println(a);
    }
}
