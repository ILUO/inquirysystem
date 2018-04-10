package com.gluxen.controller;

import com.alibaba.fastjson.JSONObject;
import com.gluxen.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yang Xing Luo on 2018/1/18.
 */
@Api(value = "药方")
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    /**
     * 新增病人主方
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "添加主方")
    @PostMapping("/addMainRecipe")
    public JSONObject addMainRecipe(@RequestBody JSONObject jsonObject){
        return recipeService.addMainRecipe(jsonObject);
    }

    /**
     * 新增/更新二级药方
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "新增二级药方")
    @PostMapping("/addRecipe")
    public JSONObject addRecipe(@RequestBody JSONObject jsonObject){
        return recipeService.addRecipe(jsonObject);
    }

    /**
     * 新增药方细节
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "新增/更新药方细节")
    @PostMapping("/addRecipeDetail")
    public JSONObject addRecipeDetail(@RequestBody JSONObject jsonObject){
        return recipeService.addRecipeDetail(jsonObject);

    }

    /**
     * 获取药方
     * @param inquiryId
     * @return
     */
    @ApiOperation(value = "获取某次问诊药方")
    @GetMapping(value = "/getRecipe")
    public JSONObject getRecipe(
            @ApiParam(value = "问诊Id",required = true)   @RequestParam int inquiryId
    ){
        return recipeService.getRecipe(inquiryId);
    }

    /**
     * 获取药方
     * @param patientId
     * @return
     */
    @ApiOperation(value = "获取最近药方")
    @GetMapping(value = "/getLatestRecipe")
    public JSONObject getLatestRecipe(
            @ApiParam(value = "病人Id",required = true)   @RequestParam Long patientId
    ){
        return recipeService.getLatestRecipe(patientId);
    }

    /**
     * 删除主方
     * @param mainRecipeId
     * @return
     */
    @ApiOperation(value = "删除主方")
    @DeleteMapping(value = "/deleteMainRecipe")
    public JSONObject deleteMainRecipe(
            @ApiParam(value = "主方Id",required = true)   @RequestParam int mainRecipeId
    ){
        return recipeService.deleteMainRecipe(mainRecipeId);
    }

    /**
     * 删除小药方
     * @param recipeId
     * @return
     */
    @ApiOperation(value = "删除小药方")
    @DeleteMapping(value = "/deleteRecipe")
    public JSONObject deleteRecipe(
            @ApiParam(value = "小药方Id",required = true)   @RequestParam int recipeId
    ){
        return recipeService.deleteRecipe(recipeId);
    }

    /**
     * 删除药方细节
     * @param recipeDetailId
     * @return
     */
    @ApiOperation(value = "删除药方细节")
    @DeleteMapping(value = "/deleteRecipeDetail")
    public JSONObject deleteRecipeDetail(
            @ApiParam(value = "药方细节Id",required = true)   @RequestParam int recipeDetailId
    ){
        return recipeService.deleteRecipeDetail(recipeDetailId);
    }


    @ApiOperation(value = "获取药物使用量")
    @GetMapping(value = "/countRecipe")
    public JSONObject countRecipe(
            @ApiParam(value = "第几页，从第一页开始", required = true) @RequestParam("pageNum") int pageNum,
            @ApiParam(value = "每页的大小", required = true) @RequestParam("pageRow") int pageRow,
            @ApiParam(value = "开始日期") @RequestParam(value="startDate",required=false) String startDate,
            @ApiParam(value = "开始日期") @RequestParam(value="endDate",required=false) String endDate,
            @ApiParam(value = "药物名称") @RequestParam(value = "medicine",required = false) String medicine
    ) {
        JSONObject request = new JSONObject();
        request.put("pageRow", pageRow);
        request.put("pageNum", pageNum);
        request.put("startDate", startDate);
        request.put("endDate",endDate);
        request.put("medicine",medicine);
        return recipeService.countRecipe(request);
    }

    @PutMapping(value = "/updateRecipe")
    public JSONObject updateRecipe(
        @RequestBody JSONObject jsonObject
    ){
        return recipeService.updateRecipe(jsonObject);
    }
}
