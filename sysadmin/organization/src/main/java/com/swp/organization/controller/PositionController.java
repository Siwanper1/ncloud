package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.PositionForm;
import com.swp.organization.entity.form.PositionQueryForm;
import com.swp.organization.entity.form.PositionUpdateForm;
import com.swp.organization.entity.param.PositionQueryParam;
import com.swp.organization.entity.po.Position;
import com.swp.organization.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/position")
@Api("职位管理")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation("添加职位")
    @ApiImplicitParam(name = "positionForm", value = "添加职位表单", required = true, dataType = "PositionForm")
    @PostMapping
    public Result add(@Valid @RequestBody PositionForm positionForm) {
        Position position = positionForm.toPo(Position.class);
        boolean result = this.positionService.add(position);
        if (result) {
            return Result.success(position);
        } else {
            return Result.fail("添加失败，请重试");
        }
    }

    @ApiOperation("删除职位")
    @ApiImplicitParam(name = "id", value = "删除职位ID", required = true, paramType = "Path", dataType = "Long")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable long id){
        boolean result = this.positionService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("添加失败，请重试");
        }
    }

    @ApiOperation("修改职位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "职位ID", required = true, paramType = "Path", dataType = "Long"),
            @ApiImplicitParam(name = "positionUpdateForm", value = "更新职位表单", required = true, dataType = "PositionUpdateForm")
    })
    @PutMapping("/{id}")
    public Result update(@PathVariable long id, @RequestBody PositionUpdateForm positionUpdateForm){
        boolean result = this.positionService.update(positionUpdateForm.toPo(id, Position.class));
        if (result) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败，请重试");
        }
    }

    @ApiOperation("根据ID查找职位")
    @ApiImplicitParam(name = "id", value = "删除职位ID", required = true, paramType = "Path", dataType = "Long")
    @GetMapping("/{id}")
    public Result get(@PathVariable long id){
        return Result.success(this.positionService.get(id));
    }

    @ApiOperation("条件查询职位")
    @ApiImplicitParam(name = "positionQueryForm", value = "条件查询职位表单", required = true, dataType = "PositionQueryForm")
    @PostMapping("/search")
    public Result query(@RequestBody PositionQueryForm positionQueryForm) {
        PositionQueryParam positionQueryParam = positionQueryForm.toParam(PositionQueryParam.class);
        IPage<Position> positions = this.positionService.query(positionQueryForm.getPage(), positionQueryParam);
        return Result.success(positions.getRecords());
    }

}
