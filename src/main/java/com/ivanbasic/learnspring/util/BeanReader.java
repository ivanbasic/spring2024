package com.ivanbasic.learnspring.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class BeanReader {

    //@Bean
    public CommandLineRunner listOfSpringBootBeans(ApplicationContext ctx) {
        return args -> {

            String[] beanNames = ctx.getBeanDefinitionNames();
            List<String> beanList = Arrays.asList(beanNames);
            beanList.sort(Comparator.naturalOrder());

            printBeans(beanList , ctx);
        };
    }

    private static void printBeans(
            List<String> beanList,
            ApplicationContext ctx) {

        System.out.println("\nLet's inspect the beans:\n");
        beanList.forEach(s -> printBean(s,ctx) );
        System.out.println("\n\n");
    }

    private static void printBean(String beanName, ApplicationContext ctx ) {
        System.out.println(beanName+" "+ctx.getBean(beanName).getClass().getName() );
    }


}
