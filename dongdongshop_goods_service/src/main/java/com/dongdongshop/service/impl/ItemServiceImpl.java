package com.dongdongshop.service.impl;

import com.dongdongshop.mapper.TbItemCatMapper;
import com.dongdongshop.mapper.TbTypeTemplateMapper;
import com.dongdongshop.model.TbItemCat;
import com.dongdongshop.model.TbItemCatExample;
import com.dongdongshop.service.ItemService;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.vo.TbItemCatVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private TbItemCatMapper itemCatMapper;

    @Resource
    private TbTypeTemplateMapper typeMapper;

    @Override
    public PageUtil<TbItemCatVO> getItemList(PageParms<TbItemCatVO> parms) {
        TbItemCatVO tbItemCatVO = parms.getParms();
        com.github.pagehelper.Page<Object> os = PageHelper.startPage(parms.getPageNum(), parms.getPageSize());
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(tbItemCatVO.getParentId());
        List<TbItemCat> catList = itemCatMapper.selectByExample(example);
        List<TbItemCatVO> collect = catList.stream().map(c -> {
            TbItemCatVO vo = new TbItemCatVO();
            vo.setParentId(c.getParentId());
            vo.setId(c.getId());
            vo.setName(c.getName());
            vo.setTypeName(typeMapper.selectByPrimaryKey(c.getTypeId()).getName());
            return vo;
        }).collect(Collectors.toList());
        PageInfo<TbItemCatVO> info = new PageInfo<TbItemCatVO>(collect);
        info.setTotal(os.getTotal());
        info.setPageNum(os.getPageNum());
        info.setPageSize(os.getPageSize());
        info.setPages(os.getPages());
        return new PageUtil<TbItemCatVO>(info.getPageNum(), info.getPageSize(), info.getTotal(), info.getList(), info.getPages());
    }

    @Override
    public Set<TbItemCatVO> getItemNameById(Long id) {
        Set<TbItemCatVO> set = new LinkedHashSet<>();
        TbItemCat tbItemCat = itemCatMapper.selectByPrimaryKey(id);
        if(tbItemCat == null){
            return null;
        }
        TbItemCatVO vo =new TbItemCatVO();
        BeanUtils.copyProperties(tbItemCat,vo);
        set.add(vo);
        TbItemCat tb = itemCatMapper.selectByPrimaryKey(vo.getParentId());
        if(tb == null){
            return set;
        }
        TbItemCatVO v =new TbItemCatVO();
        BeanUtils.copyProperties(tb,v);
        set.add(v);
        return set;
    }

    @Override
    public void addItem(TbItemCatVO itemCatVO) {
        TbItemCat item = new TbItemCat();
        BeanUtils.copyProperties(itemCatVO,item);
        itemCatMapper.insertSelective(item);
    }

    @Override
    public TbItemCatVO getItemById(Long id) {
        TbItemCat tbItemCat = itemCatMapper.selectByPrimaryKey(id);
        TbItemCatVO vo = new TbItemCatVO();
        BeanUtils.copyProperties(tbItemCat,vo);
        return vo;
    }

    @Override
    public void updateItem(TbItemCatVO itemCatVO) {
        TbItemCat item = new TbItemCat();
        BeanUtils.copyProperties(itemCatVO,item);
        itemCatMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public void deleteAll(Long[] ids) {
       TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        itemCatMapper.deleteByExample(example);
    }

    @Override
    public List<TbItemCatVO> getItem(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> catList = itemCatMapper.selectByExample(example);
        List<TbItemCatVO> collect = catList.stream().map(c -> {
            TbItemCatVO vo = new TbItemCatVO();
            vo.setParentId(c.getParentId());
            vo.setId(c.getId());
            vo.setName(c.getName());
            vo.setTypeId(c.getTypeId());
//            vo.setTypeName(typeMapper.selectByPrimaryKey(c.getTypeId()).getName());
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }
}
