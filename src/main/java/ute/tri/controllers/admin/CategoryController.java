package ute.tri.controllers.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import ute.tri.entity.CategoryEntity;
import ute.tri.models.CategoryModel;
import ute.tri.service.ICategoryService;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("")
    public String all(Model model) {
        List<CategoryEntity> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        CategoryModel category = new CategoryModel();
        category.setIsEdit(false);
        model.addAttribute("category", category);
        return "admin/categories/add";
    }

    @PostMapping("/save")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryModel cateModel,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/add");
        }
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(cateModel, entity);
        categoryService.save(entity);

        String message = cateModel.getIsEdit() ? "Category is Edited!" : "Category is saved!";
        model.addAttribute("message", message);
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Long categoryId) {
        Optional<CategoryEntity> optCategory = categoryService.findById(categoryId);
        if (optCategory.isPresent()) {
            CategoryModel cateModel = new CategoryModel();
            CategoryEntity entity = optCategory.get();
            BeanUtils.copyProperties(entity, cateModel);
            cateModel.setIsEdit(true);
            model.addAttribute("category", cateModel);
            return new ModelAndView("admin/categories/add", model);
        }
        model.addAttribute("message", "Category does not exist!");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(ModelMap model, @PathVariable("id") Long categoryId) {
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "Category is deleted!");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("/search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<CategoryEntity> list;
        if (name != null && !name.isEmpty()) {
            list = (List<CategoryEntity>) categoryService.findByNameContaining(name);
        } else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);
        return "admin/categories/search";
    }

    @RequestMapping("/searchpaginated")
    public String searchPaginated(ModelMap model,
                                  @RequestParam(name = "name", required = false) String name,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<CategoryEntity> resultPage;

        if (name != null && !name.isEmpty()) {
            resultPage = categoryService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        } else {
            resultPage = categoryService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) start = end - 4;
                else if (start == 1) end = start + 4;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("categoryPage", resultPage);
        return "admin/categories/searchpaginated";
    }
}
