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
                //开始递归，把当前菜单和所有菜单放入递归，给当前的tree找孩子
                LayUiTree layUiTree = recursionFn(tree, list);
                returnList.add(layUiTree);
            }
        }
        return returnList;
    }

    /**
     * 递归调用，给每一个tree配上自己的孩子
     * @param layUiTree
     * @param list
     * @return
     */
    private static LayUiTree recursionFn(LayUiTree layUiTree,List<Menu> list)
    {
        List<LayUiTree> childNodes = new ArrayList<>();
        for (Menu node : list) {
            if (node.getParentId().equals(layUiTree.getId())) {
                LayUiTree tree = fromMenuToTree(node);
                childNodes.add(recursionFn(tree, list));
            }
        }
        layUiTree.setChildren(childNodes);
        return layUiTree;
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
