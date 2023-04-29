package kz.bitlab.Lesson4.controllers;

import kz.bitlab.Lesson4.entity.Operator;
import kz.bitlab.Lesson4.entity.Request;
import kz.bitlab.Lesson4.services.CourseService;
import kz.bitlab.Lesson4.services.OperatorService;
import kz.bitlab.Lesson4.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CRMControllers {
    @Autowired
    private CourseService courseService;
    @Autowired
    private OperatorService operatorService;
    @Autowired
    private RequestService requestService;

    @GetMapping(value = "")
    public String openAllReq(Model model) {
        List<Request> requests = requestService.findAll();
        model.addAttribute("requests", requests);

        return "all-req";
    }

    @GetMapping(value = "new-req")
    public String openNewReq(Model model) {
        List<Request> requests = requestService.findAllByHandled(false);
        model.addAttribute("requests", requests);

        return "new-req";
    }

    @GetMapping(value = "processed-req")
    public String openProcessedReq(Model model) {
        List<Request> requests = requestService.findAllByHandled(true);
        model.addAttribute("requests", requests);

        return "processed-req";
    }

    @GetMapping(value = "add-req")
    public String openAddReq(Model model) {
        model.addAttribute("courses", courseService.findAll());

        return "add-req";
    }

    @GetMapping(value = "details-req/{id}")
    public String openDetailsReq(Model model, @PathVariable(name = "id") Long id) {
        Request request = requestService.findById(id);
        List<Operator> operatorsReq = request.getOperators();

        model.addAttribute("request", request);
        model.addAttribute("operators", operatorService.findAll());
        model.addAttribute("operatorsReq", operatorsReq);
        model.addAttribute("courses", courseService.findAll());

        return "details-req";
    }

    @PostMapping(value = "add-req")
    public String addReq(@RequestParam(name = "name") String name,
                         @RequestParam(name = "course_id") Long courseId,
                         @RequestParam(name = "phone") String phone,
                         @RequestParam(name = "commentary") String commentary) {
        String redirect = "/new-req?error";

        Request request = new Request();
        request.setUserName(name);
        request.setCourse(courseService.findById(courseId));
        request.setPhone(phone);
        request.setCommentary(commentary);
        request.setHandled(false);

        if (requestService.add(request) != null) redirect = "/new-req?success";

        return "redirect:" + redirect;
    }

    @PostMapping(value = "save-req/{id}")
    public String saveReq(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "course") Long courseId,
            @RequestParam(name = "phone", required = false, defaultValue = "") String phone,
            @RequestParam(name = "commentary", required = false, defaultValue = "") String commentary,
            @RequestParam(name = "operator_ids", required = false) List<String> operatorIds
            ) {
        String redirect = "/processed-req?error";

        Request request = new Request();
        request.setId(id);
        request.setUserName(name);
        request.setCourse(courseService.findById(courseId));
        request.setPhone(phone);
        request.setCommentary(commentary);
        request.setHandled(true);


        if (requestService.process(request, operatorIds) != null) redirect = "/processed-req?success";

        return "redirect:" + redirect;
    }

    @PostMapping(value = "delete-req/{id}")
    public String deleteReq(@PathVariable(name = "id") Long id) {
        requestService.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "delete-operator")
    public String deleteOperator(@RequestParam(name = "operator_id") Long operatorId,
                                 @RequestParam(name = "req_id") Long reqId) {
        Request request = requestService.findById(reqId);
        Operator operator = operatorService.findById(operatorId);

        System.out.println(request.getId());
        System.out.println(operator.getId());

        request.getOperators().remove(operator);
        requestService.update(request);

        return "redirect:/details-req/" + reqId;
    }
}