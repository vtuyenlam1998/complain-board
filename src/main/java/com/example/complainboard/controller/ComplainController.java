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
//This annotation specifies that the controller will handle requests with the base path "/complain." It means that all the methods in this controller will handle URLs starting with "/complain."
@RequestMapping("/complain")
//This annotation is used to generate a constructor for the class that automatically injects the dependencies required for the controller. In this case, it injects ComplainService and UserService.
@RequiredArgsConstructor
//This annotation allows Cross-Origin Resource Sharing (CORS) from any origin. It permits web browsers to make requests to this controller from any domain.
@CrossOrigin("*")
public class ComplainController {
    private final ComplainService complainService;
    private final UserService userService;


    @GetMapping
    //@RequestParam annotations are used to capture query parameters from the request, specifically "page" and "size," with default values of 0 and 5, respectively.
    public ModelAndView showAllComplains(@RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
                                     @RequestParam(name = "size",required = false,defaultValue = "5") Integer size
                                     ) throws IllegalAccessException {

        // This line creates a MyBatisPageable object named pageable. It is likely a custom class or an extension of MyBatis to handle pagination.
        //It takes two parameters: page and size, which are the current page number and the number of items to display per page, respectively. These values are typically passed as query parameters in the HTTP request.
        MyBatisPageable pageable = new MyBatisPageable(page, size);
//        This line initializes a ModelAndView object named modelAndView.
//        It sets the view name to "complain/homepage." This suggests that the controller intends to render the "homepage" view for displaying complaints.
        ModelAndView modelAndView = new ModelAndView("complain/homepage");
//        This line invokes a method called findByPage on the complainService. It passes the pageable object as a parameter to retrieve a paginated list of complaints.
//                The result is stored in a PageResponseDTO object named complains. This object likely contains information about the paginated data, such as the list of complaints for the current page.
        PageResponseDTO complains = complainService.findByPage(pageable);
//        This line retrieves information about the currently logged-in user by invoking the getCurrentUser method on the userService. The result is stored in a CurrentUserResponseDTO object named currentUser.
        CurrentUserResponseDTO currentUser = userService.getCurrentUser();
//        This line adds the complains object (which contains the paginated list of complaints) to the modelAndView. This makes the data available to the view for rendering.
        modelAndView.addObject("complains", complains);
//        This line adds the currentUser object (which contains information about the logged-in user) to the modelAndView. This data is also made available to the view.
        modelAndView.addObject("user", currentUser);
//        Finally, this line returns the modelAndView, which will be processed by Spring MVC to render the "complain/homepage" view. The view can access the "complains" and "user" objects to display the paginated complaints and user information.
        return modelAndView;
    }
//    This annotation maps an HTTP GET request to a URL with a path variable id. It means that this method will handle requests like "/complain/{id}" where {id} is a placeholder for the complaint's unique identifier.
    @GetMapping("/{id}")
//    This is the method signature. It accepts the id path variable, which represents the unique identifier of the complaint.
    public ModelAndView showComplainDetail(@PathVariable Long id) throws IllegalAccessException {

//        This line initializes a ModelAndView object with the view name "complain/detail." It specifies that the controller intends to render the "detail" view for displaying complaint details.
        ModelAndView modelAndView = new ModelAndView("complain/detail");

//        This line retrieves information about the currently logged-in user by calling the getCurrentUser method from the userService. The result is stored in a CurrentUserResponseDTO object named currentUser.
        CurrentUserResponseDTO currentUser = userService.getCurrentUser();

//        This line fetches information about the user who created the complaint with the specified id. It uses the findByComplainId method from the userService. The result is stored in a User object named user.
        User user = userService.findByComplainId(id);

//        !Objects.equals(user.getUsername(), currentUser.getUsername()): It checks if the username of the user who created the complaint (user.getUsername()) is not equal to the username of the currently logged-in user (currentUser.getUsername()). If this condition is met, it means the logged-in user is not the owner of the complaint.
//        currentUser.getRole().equals("ADMIN"): It checks if the role of the currently logged-in user is "ADMIN." If this condition is met, it means the logged-in user has administrative privileges.
//        If both conditions are true (i.e., the logged-in user is not the owner of the complaint and has an "ADMIN" role), the method throws a NotFoundException with the message "Complain not found." This is typically used to prevent unauthorized access to complaint details.
        if (!Objects.equals(user.getUsername(), currentUser.getUsername()) && currentUser.getRole().equals("ADMIN")) {
            throw new NotFoundException("Complain not found");
        }
//        This line fetches the details of the complaint with the specified id using the findById method from the complainService. The result is stored in a ComplainResponseDTO object named complain.
        ComplainResponseDTO complain = complainService.findById(id);
//        This line adds the complain object (which contains the details of the complaint) to the modelAndView. This makes the complaint data available to the "complain/detail" view for rendering.
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

//    This annotation maps an HTTP GET request to the "/create" endpoint. It means that this method will handle requests like "/complain/create" when users want to access the complaint creation form.
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("complain/create");
//        This line creates a new instance of the Complain class. This object will be used to bind form input fields on the "create" view when a user submits the form.
        Complain complain = new Complain();
        modelAndView.addObject("complain",complain);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComplain(@PathVariable Long id,RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        Complain complain = complainService.delete(id);

//        Set the view to redirect to "/complain," indicating that the user should be redirected to the "/complain" page after the deletion.
        modelAndView.setViewName("redirect:/complain");

//        Add a flash attribute named "message" with the value "Delete complain successfully" to provide a success message that can be displayed on the redirected page (e.g., to confirm that the complaint was deleted successfully). Flash attributes are suitable for displaying messages after a redirect because they are available for a short duration and automatically cleared after being used once.
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
