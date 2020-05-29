package com.aaa.util;


import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 树状菜单工具类
 * 
 * @author teacherChen
 */
public class TreeUtils
{
    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<LayUiTree> getChildPerms(List<Menu> list, int parentId)
    {
        List<LayUiTree> returnList = new ArrayList<LayUiTree>();
        for (Menu menu : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if(menu.getParentId()==parentId){
                LayUiTree tree = fromMenuToTree(menu);
                //开始递归，把所有菜单和当前菜单放入
                recursionFn(list, tree);
                returnList.add(tree);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param t
     */
    private static void recursionFn(List<Menu> list, LayUiTree t)
    {
        // 得到t的子节点列表
        List<LayUiTree> childList = getChildList(list, t);
        t.setChildren(childList);
        for (LayUiTree tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<LayUiTree> it = childList.iterator();
                while (it.hasNext())
                {
                    LayUiTree n =  it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<LayUiTree> getChildList(List<Menu> list, LayUiTree t)
    {

        List<LayUiTree> tlist = new ArrayList<LayUiTree>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext())
        {
            Menu menu = (Menu) it.next();
            if (menu.getParentId() == t.getId())
            {
                LayUiTree tree = fromMenuToTree(menu);
                tlist.add(tree);
            }
        }
        return tlist;
    }


/**
     * 判断是否有子节点
     */

    private static boolean hasChild(List<Menu> list, LayUiTree t)
    {
        int size = getChildList(list, t).size();
        if(size>0){
            return true;
        }else
        {
            return false;
        }
    }

    /**
     * 将menu对象转换成tree对象
     * @param menu
     * @return
     */
    private static LayUiTree fromMenuToTree(Menu menu){
        //构造tree对象
        LayUiTree tree= new LayUiTree();
        tree.setId(menu.getMenuId());
        tree.setTitle(menu.getMenuName());
        tree.setChecked(false);
        tree.setUrl(menu.getUrl());
        return tree;
    }

}
