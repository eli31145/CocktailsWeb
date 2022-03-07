package Project.CocktailWeb;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import Project.CocktailWeb.services.EmailSenderService;

@SpringBootApplication
public class CocktailWebApplication {

	@Autowired
	private EmailSenderService emailSenderSvc;

	public static void main(String[] args) {
		SpringApplication.run(CocktailWebApplication.class, args);
	}

	/* @EventListener(ApplicationReadyEvent.class)
	public void triggerMail(){
		emailSenderSvc.sendSimpleEmail("chengen.ho@gmail.com", 
		"Greetings! My name is Elias, and I am the creater of this website which you are now using. I will like to warmly welcome you to our family of cocktail lovers and here's a toast to this wonderful world of alcoholism.", 
		"Welcome to the CocktailWeb Family!");
	} */

	/* @EventListener(ApplicationReadyEvent.class)
	public void triggerAttachmentMail() throws MessagingException{
		emailSenderSvc.sendEmailWithAttachment("chengen.ho@gmail.com", 
		"Greetings! I will like to warmly welcome you to our cocktail family. Attached is a picture showing my favourite drinks. Enjoy!", 
		"Welcome to the CocktailWeb Family!",
		"C:\\Users\\cheng\\Pictures\\Cocktail_Recipes.jpg");
	} */

}
