package web.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import org.springframework.stereotype.Controller;
import web.model.UserForm;
import web.servise.UserService;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Value("${error.message}")
    private String errorMessage;

    public UserController(UserService userService) {
        this.userService = userService;
        userService.add(new User("Karella", "or_009@mail.ru"));
        userService.add(new User("YuIIOO-", "Jupitr@yandex.ru"));
        userService.add(new User("88IoI88", "portyan@mail.ru"));
        userService.add(new User("Lotry=-", "toolong@gmail.com"));
        userService.add(new User("tea_for_one", "urban@gmail.com"));
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/userList"}, method = RequestMethod.GET)
    public String userList(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "addUser";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String saveUser(Model model, @ModelAttribute("userForm") UserForm userForm) {

        String nickname = userForm.getNickname();
        String email = userForm.getEmail();

        if (nickname != null && nickname.length() > 0
                && email != null && email.length() > 0) {
            userService.add(new User(nickname, email));
            return "redirect:/userList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addUser";
    }

    @RequestMapping(value = {"/editUser"}, method = RequestMethod.GET)
    public String showEditPage(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String editUser(@ModelAttribute("userForm") UserForm editForm, @RequestParam(value = "id") Long id) {
        String newNickname = editForm.getNickname();
        String newEmail = editForm.getEmail();

        if (newNickname != null && newNickname.length() > 0) {
            userService.changeNickname(id, newNickname);
        }
        if (newEmail != null && newEmail.length() > 0) {
            userService.changeEmail(id, newEmail);
        }

        return "redirect:/userList";
    }


    @RequestMapping(value = {"/delete"})
    public String delete(@RequestParam(value = "id") Long id) {
        System.out.println(id);
        userService.deleteUser(id);
        return "redirect:/userList";
    }

}

