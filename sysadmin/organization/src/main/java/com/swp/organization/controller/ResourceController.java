package com.swp.organization.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swp.ncloud.common.core.entity.vo.Result;
import com.swp.organization.entity.form.ResourceForm;
import com.swp.organization.entity.form.ResourceQueryForm;
import com.swp.organization.entity.form.ResourceUpdateForm;
import com.swp.organization.entity.param.ResourceQueryParam;
import com.swp.organization.entity.po.Resource;
import com.swp.organization.service.ResourceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/resource")
@Api("资源管理")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping
    public Result add(@Valid @RequestBody ResourceForm resourceForm) {
        boolean result = this.resourceService.add(resourceForm.toPo(Resource.class));
        if (result) {
            return Result.success("添加成功");
        } else {
            return Result.fail("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        boolean result = this.resourceService.delete(id);
        if (result) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id, @Valid @RequestBody ResourceUpdateForm resourceUpdateForm) {
        Resource resource = resourceUpdateForm.toPo(Resource.class);
        resource.setId(id);
        boolean result = this.resourceService.update(resource);
        if (result) {
            return Result.success("更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable int id){
        Resource resource = this.resourceService.get(id);
        return Result.success(resource);
    }

    @GetMapping("/all")
    public Result get(){
        List<Resource> all = this.resourceService.getAll();
        return Result.success(all);
    }

    @PostMapping("/search")
    public Result search(@RequestBody ResourceQueryForm resourceQueryForm){
        IPage<Resource> page = this.resourceService.query(resourceQueryForm.getPage(), resourceQueryForm.toParam(ResourceQueryParam.class));
        return Result.success(page.getRecords());
    }

}
