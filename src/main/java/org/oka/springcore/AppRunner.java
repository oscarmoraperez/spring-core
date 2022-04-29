package org.oka.springcore;

import org.oka.springcore.facade.BookingFacade;
import org.oka.springcore.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRunner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("configuration.xml");
        BookingFacade bookingFacade = ctx.getBean(BookingFacade.class);
        User userById = bookingFacade.getUserById(1);
        System.out.println(userById);
    }
}
