package controllers;

import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {
  static Form<Task> taskForm = form(Task.class);
	
	
  public static Result index() {
	  return redirect(routes.Application.Tasks());
  }
  
  
  public static Result Tasks(){
	  return ok(views.html.index.render(Task.all(), taskForm));
  }
  
  public static Result newTask(){
	  Form<Task> filledForm = taskForm.bindFromRequest();
	  if (filledForm.hasErrors()){
		  return badRequest(
				  views.html.index.render(Task.all(), taskForm)
				  );
				  
	  } else {
		  Task.create(filledForm.get());
		  return redirect(routes.Application.Tasks());
	  }
	  
  }
  
  public static Result deleteTask(Long id){
	  Task.delete(id);
	  return redirect(routes.Application.Tasks());
  }
}