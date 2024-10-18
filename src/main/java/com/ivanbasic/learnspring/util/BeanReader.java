package com.ivanbasic.learnspring.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BeanReader {

    //@Bean
    public CommandLineRunner listOfSpringBootBeans(ApplicationContext ctx) {
        return args -> {

            String[] beanNames = ctx.getBeanDefinitionNames();
            List<String> beanNamesBySpring = new ArrayList<>();
            List<String> beanNamesByIvanBasic = new ArrayList<>();

            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                if ( ctx.getBean(beanName).getClass().getName().startsWith("com.ivanbasic")) {
                    beanNamesByIvanBasic.add(beanName);
                } else {
                    beanNamesBySpring.add(beanName);
                }
            }
            printBeans(beanNamesByIvanBasic, beanNamesBySpring, ctx);
        };
    }
    private static void printBeans(
            List<String> beanNamesByIvanBasic,
            List<String> beanNamesBySpring,
            ApplicationContext ctx) {
        System.out.println("\nLet's inspect the beans provided by Spring Boot:");

        System.out.println("\ncom.ivanbasic beans: ");
        beanNamesByIvanBasic.forEach(s -> printBean(s,ctx) );

        System.out.println("\nspring beans:");
        beanNamesBySpring.forEach(s -> printBean(s,ctx) );
    }
    private static void printBean(String beanName, ApplicationContext ctx ) {
        System.out.println(beanName+" "+ctx.getBean(beanName).getClass().getName() );
    }


}
