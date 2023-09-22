package com.example.complainboard.controller;

import com.example.complainboard.exception.NotFoundException;
import com.example.complainboard.model.Complain;
import com.example.complainboard.model.User;
import com.example.complainboard.pageable.MyBatisPageable;
import com.example.complainboard.payload.request.EditComplainRequestDTO;
import com.example.complainboard.payload.response.ComplainResponseDTO;
import com.example.complainboard.payload.response.CurrentUserResponseDTO;
import com.example.complainboard.payload.response.PageResponseDTO;
import com.example.complainboard.service.ComplainService;
import com.example.complainboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/complain")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ComplainController {
    private final ComplainService complainService;
    private final UserService userService;


    @GetMapping
    public ModelAndView showAllComplains(@RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
                                     @RequestParam(name = "size",required = false,defaultValue = "5") Integer size
                                     ) throws IllegalAccessException {
        MyBatisPageable pageable = new MyBatisPageable(page,size);
        ModelAndView modelAndView = new ModelAndView("complain/homepage");
        PageResponseDTO complains = complainService.findByPage(pageable);
        CurrentUserResponseDTO currentUser = userService.getCurrentUser();
        modelAndView.addObject("complains",complains);
        modelAndView.addObject("user",currentUser);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView showComplainDetail(@PathVariable Long id) throws IllegalAccessException {
        ModelAndView modelAndView = new ModelAndView("complain/detail");
        CurrentUserResponseDTO currentUser = userService.getCurrentUser();
        User user = userService.findByComplainId(id);
        if (!Objects.equals(user.getUsername(), currentUser.getUsername()) && currentUser.getRole().equals("ADMIN")) {
            throw new NotFoundException("Complain not found");
        }
        ComplainResponseDTO complain = complainService.findById(id);
        modelAndView.addObject("complain",complain);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("complain/edit");
        ComplainResponseDTO complain = complainService.findById(id);
        modelAndView.addObject("complain",complain);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("complain/create");
        Complain complain = new Complain();
        modelAndView.addObject("complain",complain);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComplain(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        Complain complain = complainService.delete(id);
        modelAndView.setViewName("redirect:/complain");
        redirectAttributes.addFlashAttribute("message","Delete complain successfully");
        return modelAndView;
    }



    @PostMapping("/edit")
    public ModelAndView updateComplain(@ModelAttribute EditComplainRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/complain");
        redirectAttributes.addFlashAttribute("message","Complain has been edited successfully!");
        complainService.update(requestDTO);
        return modelAndView;
    }
}
