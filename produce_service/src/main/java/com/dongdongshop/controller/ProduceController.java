package com.dongdongshop.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONObject;
import com.dongdongshop.data.Result;
import com.dongdongshop.data.UserVo;
import com.dongdongshop.em.ResultEnum;
import com.dongdongshop.service.ProduceService;
import com.dongdongshop.vo.ProduceVO;
import com.dongdongshop.vo.ProductVO;
import io.jsonwebtoken.Claims;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("produce")
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    //查询全部
    @PostMapping("getProduceList")
    public Result getProduceList(@RequestBody ProduceVO vo) {
        try {
            List<ProduceVO> produceVOList = produceService.getProduceList(vo);
            return Result.ok().setData(produceVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("addProduce")
    public Result addProduce(@RequestBody ProduceVO vo) {
        try {
            produceService.addProduce(vo);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("getProduceById")
    public Result getProduceById(@RequestParam String pid) {
        try {
            ProduceVO vo = produceService.getProduceById(pid);
            return Result.ok().setData(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("updateProduce")
    public Result updateProduce(@RequestBody ProduceVO vo) {
        try {
            produceService.updateProduce(vo);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @PostMapping("deleteProduce")
    public Result deleteProduce(@RequestParam String pid) {
        try {
            produceService.deleteProduce(pid);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }


    @PostMapping("getProListByPid")
    public Result getProListByPid(@RequestParam Long pid) {
        try {
            List<ProductVO> list = produceService.getProListByPid(pid);
            return Result.ok().setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.result(ResultEnum.ERROR).setData(e.getMessage());
        }
    }

    @GetMapping("excelOut")
    public void excelOut(HttpServletResponse response) throws IOException {
        List<ProduceVO> list = produceService.getList();
        //在内存操作，写到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名(将对应的字段转化成自己需要的名称)
        writer.addHeaderAlias("pid", "工单编码");
        writer.addHeaderAlias("pname", "工单名称");
        writer.addHeaderAlias("psid", "工单来源");
        writer.addHeaderAlias("porder", "订单编号");
        writer.addHeaderAlias("productOrder", "产品编号");
        writer.addHeaderAlias("productName", "产品名称");
        writer.addHeaderAlias("spec", "规格型号");
        writer.addHeaderAlias("unit", "单位");
        writer.addHeaderAlias("orderNum", "工单数量");
        writer.addHeaderAlias("adjustQuantity", "调整数量");
        writer.addHeaderAlias("quantitProduced", "已生产数量");
        writer.addHeaderAlias("batchNum", "批次号");
        writer.addHeaderAlias("customerCode", "客户编码");
        writer.addHeaderAlias("customerName", "客户名称");
        writer.addHeaderAlias("needTime", "需求日期");
        writer.addHeaderAlias("status", "单据状态");
        writer.addHeaderAlias("parentid", "父节点");
        // 默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除之
        writer.setOnlyAlias(true);
        //默认配置
        writer.write(list, true);
        // 设置所有列为自动宽度，不考虑合并单元格
        setSizeColumn(writer.getSheet(), 16);
        //设置content—type
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset:utf-8");
        //设置标题
        long l = System.currentTimeMillis();
        String fileName = URLEncoder.encode("workorder" + l, "UTF-8");
        //Content-disposition是MIME协议的扩展，MIME协议指示MIME用户代理如何显示附加的文件。
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        //将Writer刷新到OutPut
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    public void setSizeColumn(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum <= size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 320);
        }
    }

}
