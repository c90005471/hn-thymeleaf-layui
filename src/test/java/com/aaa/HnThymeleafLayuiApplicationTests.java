package com.aaa;

import com.aaa.biz.impl.MenuBizImpl;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
class HnThymeleafLayuiApplicationTests {
    @Autowired
    private MenuBizImpl menuBiz;

    @Test
    void contextLoads() {
        List<LayUiTree> menuList = menuBiz.selectAllMenu();
        for (LayUiTree menu : menuList) {
            System.out.println(menu.toString());
        }
    }

}
